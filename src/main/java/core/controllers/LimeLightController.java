package core.controllers;

import core.util.CheckData;
import core.entities.*;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@RestController
@RequestMapping(value="/limelight/v2/")
public class LimeLightController {

    private final LimeLightInitializerDAO limeLightInitializerDAO;
    private final ApiKeyDAO apiKeyDAO;

    public LimeLightController(LimeLightInitializerDAO limeLightInitializerDAO,
                               ApiKeyDAO apiKeyDAO) {
        Assert.notNull(limeLightInitializerDAO, "LimeLightInitializerDAO must not be null!");
        Assert.notNull(apiKeyDAO, "ApiKeyDAO must not be null!");
        this.limeLightInitializerDAO = limeLightInitializerDAO;
        this.apiKeyDAO = apiKeyDAO;
    }

    @RequestMapping(value = "add/insurance")
    public APIResponse addInsurance(String apiKey, String orderId, boolean insuranceSelected, String cvv, HttpServletRequest request, HttpServletResponse response) {

        APIResponse apiResponse = new APIResponse();
        apiResponse.setResult(APIResponse.ResultType.ERROR);
        String resultMessage;

        if(cvv == null || !CheckData.isNumbersOnly(cvv) || Integer.parseInt(cvv) < 1) {
            resultMessage = "Invalid CVV";
        } else {
            if(orderId == null || !CheckData.isNumbersOnly(orderId) || Long.parseLong(orderId)  < 1){
                resultMessage = "Invalid Order ID";
            } else {
                if(apiKey == null || apiKey.trim().length()<30){
                    resultMessage = "Invalid API Key.";
                } else {
                    // Check API Key
                    ApiKey apiKeyObject = apiKeyDAO.findByApiKeyAndActive(apiKey,true);
                    if(apiKeyObject == null || apiKeyObject.getAccountId() < 1) {
                        resultMessage = "Invalid API Key.";
                    } else {
                        if(isDuplicateLimeLightInitializer(apiKeyObject.getAccountId(),orderId)) {
                            resultMessage = "Duplicate Order ID";
                        } else {
                            String ipAddress = request.getHeader("X-FORWARDED-FOR");
                            if (ipAddress == null) {
                                ipAddress = request.getRemoteAddr();
                            }
                            // save Order to db mysql
                            LimeLightInitializer limeLightInitializer = new LimeLightInitializer();
                            limeLightInitializer.setAccountId(apiKeyObject.getAccountId());
                            limeLightInitializer.setApiKey(apiKey);
                            limeLightInitializer.setParentOrderId(orderId);
                            limeLightInitializer.setInsuranceSelected(insuranceSelected);
                            limeLightInitializer.setCvv(cvv);
                            limeLightInitializer.setActive(insuranceSelected); // If insurance is NOT selected, we don't need to process
                            limeLightInitializer.setCreated(new Timestamp(System.currentTimeMillis()));
                            if(!insuranceSelected){
                                limeLightInitializer.setProcessed(new Timestamp(System.currentTimeMillis()));
                                limeLightInitializer.setProcessState(LimeLightInitializer.ProcessState.PROCESSED);
                            } else {
                                limeLightInitializer.setProcessState(LimeLightInitializer.ProcessState.NEW);
                            }
                            limeLightInitializer.setSenderIp(ipAddress);
                            limeLightInitializerDAO.save(limeLightInitializer);
                                resultMessage = "Insurance product is staged to process when Lime Light Order ID "+orderId+" changes shipping status to Shipped.";
                            apiResponse.setResult(APIResponse.ResultType.SUCCESS);
                        }
                    }
                }
            }
        }
        apiResponse.setMessage(resultMessage);
        return apiResponse;
    }

    @RequestMapping(value = "subscription")
    public APIResponse addInsuranceToReship(String apiKey, String orderId, String parentOrder, String isShippable) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setResult(APIResponse.ResultType.ERROR);
        String resultMessage;

        if(orderId == null || !CheckData.isNumbersOnly(orderId) || Long.parseLong(orderId)  < 1){
            resultMessage = "Invalid Order ID";
        } else {
            if(apiKey == null || apiKey.trim().length()<30){
                resultMessage = "API Key NULL or too short.";
            } else {
                // Check API Key
                ApiKey apiKeyObject = apiKeyDAO.findByApiKeyAndActive(apiKey,true);
                if(apiKeyObject == null || apiKeyObject.getAccountId() < 1) {
                    resultMessage = "Invalid API Key.";
                } else {
                    if(isDuplicateLimeLightInitializer(apiKeyObject.getAccountId(),orderId)) {
                        resultMessage = "Duplicate Order ID";
                    } else {
                        // check if parent initializer Order exists
                        LimeLightInitializer parentLimeLightInitializer = limeLightInitializerDAO.findByAccountIdAndParentOrderId(apiKeyObject.getAccountId(),parentOrder);
                        if(parentLimeLightInitializer == null || parentLimeLightInitializer.getId() < 1) {
                            // parentLimeLightInitializer does not exist
                            resultMessage = "Parent Order ID Does Not Exist In Our Database.";
                        } else {
                            LimeLightInitializer limeLightInitializer = new LimeLightInitializer();
                            boolean insuranceSelected = parentLimeLightInitializer.isInsuranceSelected();
                            limeLightInitializer.setAccountId(apiKeyObject.getAccountId());
                            limeLightInitializer.setApiKey(apiKey);
                            limeLightInitializer.setParentOrderId(orderId);
                            limeLightInitializer.setGrandParentOrderId(parentOrder);
                            limeLightInitializer.setInsuranceSelected(insuranceSelected);
                            limeLightInitializer.setCvv(parentLimeLightInitializer.getCvv());
                            limeLightInitializer.setActive(insuranceSelected); // If insurance is NOT selected, we don't need to process
                            limeLightInitializer.setCreated(new Timestamp(System.currentTimeMillis()));
                            if(!insuranceSelected){
                                limeLightInitializer.setProcessed(new Timestamp(System.currentTimeMillis()));
                                limeLightInitializer.setProcessState(LimeLightInitializer.ProcessState.PROCESSED);
                            } else if(isShippable != null && isShippable.equals("0")) {
                                limeLightInitializer.setProcessed(new Timestamp(System.currentTimeMillis()));
                                limeLightInitializer.setProcessState(LimeLightInitializer.ProcessState.PROCESSED_UNSHIPPABLE);
                            } else {
                                limeLightInitializer.setProcessState(LimeLightInitializer.ProcessState.NEW);
                            }
                            limeLightInitializerDAO.save(limeLightInitializer);
                            resultMessage = "Insurance product is staged to process when Lime Light Order ID " + orderId + " changes shipping status to Shipped.";
                            apiResponse.setResult(APIResponse.ResultType.SUCCESS);
                        }
                    }
                }
            }
        }
        apiResponse.setMessage(resultMessage);
        return apiResponse;
    }

    private boolean isDuplicateLimeLightInitializer(Long accountId, String orderId) {
        // The assumption is that LimeLight does not reuse Order ids for the same Account
        LimeLightInitializer limeLightInitializer = limeLightInitializerDAO.findByAccountIdAndParentOrderId(accountId,orderId);
        if(limeLightInitializer == null || limeLightInitializer.getId() < 1) {
            return false;
        }
        return true;
    }
}
