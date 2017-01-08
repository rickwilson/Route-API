package core.controllers;

import core.email.ApiNewAccountEmailTemplate;
import core.email.NewAccountEnrolledTemplate;
import core.entities.*;
import core.entities.konnektive.Account;
import core.entities.konnektive.KonnektiveRegisterResponse;
import core.entities.openApi.OpenApiStringResponse;
import core.outbound.MailedIt;
import core.security.GenerateAPIKey;
import core.security.IpAddress;
import core.security.PrintHttpReport;
import core.services.*;
import core.util.CheckData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(value="/konnektive/v2/")
public class KonnektiveController {

    private final UserService userService;
    private final AccountService accountService;
    private final ApiKeyService apiKeyService;
    private final ApiKeyDAO apiKeyDAO;
    private final OmniTransactionService omniTransactionService;
    private final KonnektiveOrderDetailsDAO konnektiveOrderDetailsDAO;
    private final KonnektiveOrderDetailsItemDAO konnektiveOrderDetailsItemDAO;
    private final ErrorAttributes errorAttributes;
    private final PrintHttpReport printHttpReport;
    private final UserRoleService userRoleService;
    private final RegisterFormDAO registerFormDAO;
    private final EnvVariablesService envVariablesService;
    private final MailedIt mailedIt;

    @Autowired
    public KonnektiveController(UserService userService,
                                AccountService accountService,
                                ApiKeyService apiKeyService,
                                ApiKeyDAO apiKeyDAO,
                                OmniTransactionService omniTransactionService,
                                KonnektiveOrderDetailsDAO konnektiveOrderDetailsDAO,
                                KonnektiveOrderDetailsItemDAO konnektiveOrderDetailsItemDAO,
                                ErrorAttributes errorAttributes,
                                PrintHttpReport printHttpReport,
                                UserRoleService userRoleService,
                                RegisterFormDAO registerFormDAO,
                                EnvVariablesService envVariablesService,
                                MailedIt mailedIt) {
        Assert.notNull(userService, "UserService must not be null!");
        Assert.notNull(accountService, "AccountService must not be null!");
        Assert.notNull(apiKeyService, "ApiKeyService must not be null!");
        Assert.notNull(apiKeyDAO, "ApiKeyDAO must not be null!");
        Assert.notNull(omniTransactionService, "OmniTransactionService must not be null!");
        Assert.notNull(konnektiveOrderDetailsDAO, "KonnektiveOrderDetailsDAO must not be null!");
        Assert.notNull(konnektiveOrderDetailsItemDAO, "KonnektiveOrderDetailsItemDAO must not be null!");
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null!");
        Assert.notNull(printHttpReport, "PrintHttpReport must not be null!");
        Assert.notNull(userRoleService, "UserRoleService must not be null!");
        Assert.notNull(registerFormDAO, "RegisterFormDAO must not be null!");
        Assert.notNull(envVariablesService, "EnvVariablesService must not be null!");
        Assert.notNull(mailedIt, "MailedIt must not be null!");
        this.userService = userService;
        this.accountService = accountService;
        this.apiKeyService = apiKeyService;
        this.apiKeyDAO = apiKeyDAO;
        this.omniTransactionService = omniTransactionService;
        this.konnektiveOrderDetailsDAO = konnektiveOrderDetailsDAO;
        this.konnektiveOrderDetailsItemDAO = konnektiveOrderDetailsItemDAO;
        this.errorAttributes = errorAttributes;
        this.printHttpReport = printHttpReport;
        this.userRoleService = userRoleService;
        this.registerFormDAO = registerFormDAO;
        this.envVariablesService = envVariablesService;
        this.mailedIt = mailedIt;
    }

    @RequestMapping(value = "add/account/json", method = RequestMethod.GET)
    public OpenApiStringResponse noGetMethod() {
        ArrayList<String> errors = new ArrayList<>();
        errors.add("API calls to /konnektive/v2/add/account require request method POST.");
        return new OpenApiStringResponse(errors);
    }

    @RequestMapping(value = "add/account/json", method = RequestMethod.POST)
    public KonnektiveRegisterResponse account(@RequestHeader(defaultValue = "") String partner_key,
                                         @RequestHeader(defaultValue = "") String secret,
                                         @RequestBody(required = false) Account account,
                                              HttpServletRequest request) {
        ArrayList<String> errors = new ArrayList<>();
        String companyName = "";
        if(account != null) {
            if(account.getCompany_name() != null && account.getCompany_name().trim().length() > 2) {
                boolean nameExists = true;
                boolean firstTry = true;
                int num = 0;
                while (nameExists) {
                    companyName = account.getCompany_name();
                    if(!firstTry) {
                        companyName += "-"+num;
                    }
                    nameExists = accountService.companyNameExists(companyName);
                    firstTry = false;
                    num++;
                }
            } else {
                errors.add("Missing required parameter company_name. Company name must be three or more characters.");
            }
            if(account.getFirst() != null && account.getFirst().trim().length() > 2) {

            } else {
                errors.add("Missing required parameter first. First name must be three or more characters.");
            }
            if(account.getLast() != null && account.getLast().trim().length() > 2) {

            } else {
                errors.add("Missing required parameter last. Last name must be three or more characters.");
            }
            if(account.getEmail() != null) {
                if(!CheckData.isValidEmailAddress(account.getEmail())) {
                    errors.add("All new accounts must include a valid and unique email address.");
                } else {
                    if(userService.emailAddressExists(account.getEmail())) {
                        errors.add("This email address is associated with an account already. All new accounts must include a valid and unique email address.");
                    }
                }

            } else {
                errors.add("Missing required parameter email.");
            }
            if(account.getPhone() != null && account.getPhone().trim().length() > 6) {

            } else {
                errors.add("Missing required parameter phone.");
            }
        } else {
            errors.add("This API call requires the following fields, company_name, first, last, email, and phone.");
        }

        String checkKeysError = checkKeys(partner_key,secret);
        if(checkKeysError != null) {
            errors.add(checkKeysError);
        }
        if(errors.isEmpty()) {
            long accountId = accountService.createAccount(companyName, core.entities.Account.Type.CLIENT);
            String password = GenerateAPIKey.generateKey(companyName,"password");
            long userId = userService.createUser(accountId,account.getEmail(),password,account.getFirst(),account.getLast());
            ApiKey apiKeyObject = apiKeyService.createInitialApiKey(accountId,companyName,userId,account.getFirst()+" "+account.getLast(),false);
            account.setApi_key(apiKeyObject.getApiKey());
            account.setSecret(apiKeyObject.getSecret());
            long ghostId = userService.createGhost(accountId);
            userRoleService.createRole(ghostId,"ACCOUNT_ADMIN");
            userRoleService.createRole(ghostId,"SECRET_VIEWER");
            // save new registration
            RegisterForm registerForm = new RegisterForm();
            registerForm.setCompanyName(companyName);
            registerForm.setEmail(account.getEmail());
            registerForm.setFirst(account.getFirst());
            registerForm.setLast(account.getLast());
            registerForm.setPassword("AUTO_GENERATED-"+password);
            registerForm.setPasswordConfirm("AUTO_GENERATED-"+password);
            registerForm.setPhone(account.getPhone());
            registerForm.setTerms(false);
            registerForm.setTransactions("0");
            registerForm.setUrl("UNKNOWN - KONNEKTIVE AUTO REGISTRATION");
            registerForm.setCreated(new Timestamp(System.currentTimeMillis()));
            registerForm.setIpAddress(IpAddress.getIpAddress(request));
            registerForm.setAccountId(accountId);
            registerForm.setUserId(userId);
            registerForm.setApiKey(apiKeyObject.getApiKey());
            registerForm.setCrm("Konnektive");
            registerFormDAO.save(registerForm);

            // send confirmation email
            mailedIt.generateAndSendEmail(registerForm.getEmail(),"Email Verification", ApiNewAccountEmailTemplate.createBody(account.getFirst()+" "+account.getLast(),userId,account.getEmail(),password,apiKeyObject.getApiKey()),true, "New Registration, Verify Email Address");

            return new KonnektiveRegisterResponse(account);
        }
        return new KonnektiveRegisterResponse(errors);
    }

    private String checkKeys(String apiKey, String secret) {
        if(apiKey == null || !apiKey.equals(envVariablesService.getKonnektiveKey())) {
            return "This API call must include a valid partner_key in the header to authenticate.";
        }
        return null; // no errors, so return null for error message
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

    @RequestMapping(value = "add/order")
    public APIResponse KonneKtiveBot(@RequestParam(value = "api_token", required = false) String konnectiveKey,
                                     @RequestParam(value = "client_id", required = false) String apiKey,
                                     @RequestParam(value = "eco_price", required = false) String insPrice,
                                     @RequestParam(value = "order_type", required = false) String orderType,
                                     @RequestParam(value = "tracking_number", required = false) String trackingNumber,
                                     @RequestParam(value = "order_details[orderId]", required = false) String orderId,
                                     @RequestParam(value = "order_details[sourceId]", required = false) String sourceId,
                                     @RequestParam(value = "order_details[sourceValue1]", required = false) String sourceValue1,
                                     @RequestParam(value = "order_details[sourceValue2]", required = false) String sourceValue2,
                                     @RequestParam(value = "order_details[dateCreated]", required = false) String dateCreated,
                                     @RequestParam(value = "order_details[orderType]", required = false) String detailOrderType,
                                     @RequestParam(value = "order_details[orderStatus]", required = false) String orderStatus,
                                     @RequestParam(value = "order_details[totalAmount]", required = false) String totalAmount,
                                     @RequestParam(value = "order_details[campaignName]", required = false) String campaignName,
                                     @RequestParam(value = "order_details[orderValue]", required = false) String orderValue,
                                     @RequestParam(value = "order_details[customerId]", required = false) String customerId,
                                     @RequestParam(value = "order_details[name]", required = false) String name,
                                     @RequestParam(value = "order_details[emailAddress]", required = false) String emailAddress,
                                     @RequestParam(value = "order_details[phoneNumber]", required = false) String phoneNumber,
                                     @RequestParam(value = "order_details[firstName]", required = false) String firstName,
                                     @RequestParam(value = "order_details[lastName]", required = false) String lastName,
                                     @RequestParam(value = "order_details[address1]", required = false) String address1,
                                     @RequestParam(value = "order_details[shipmentInsured]", required = false) String shipmentInsured,
                                     @RequestParam(value = "order_details[shipmentInsurancePrice]", required = false) String shipmentInsurancePrice,
                                     @RequestParam(value = "order_details[insuranceCharged]", required = false) String insuranceCharged,
                                     @RequestParam(value = "order_details[city]", required = false) String city,
                                     @RequestParam(value = "order_details[state]", required = false) String state,
                                     @RequestParam(value = "order_details[country]", required = false) String country,
                                     @RequestParam(value = "order_details[postalCode]", required = false) String postalCode,
                                     @RequestParam(value = "order_details[shipFirstName]", required = false) String shipFirstName,
                                     @RequestParam(value = "order_details[shipLastName]", required = false) String shipLastName,
                                     @RequestParam(value = "order_details[shipAddress1]", required = false) String shipAddress1,
                                     @RequestParam(value = "order_details[shipCity]", required = false) String shipCity,
                                     @RequestParam(value = "order_details[shipState]", required = false) String shipState,
                                     @RequestParam(value = "order_details[shipCountry]", required = false) String shipCountry,
                                     @RequestParam(value = "order_details[shipPostalCode]", required = false) String shipPostalCode,
                                     @RequestParam(value = "order_details[paySource]", required = false) String paySource,
                                     @RequestParam(value = "order_details[cardType]", required = false) String cardType,
                                     @RequestParam(value = "order_details[cardLast4]", required = false) String cardLast4,
                                     @RequestParam(value = "order_details[cardExpiryDate]", required = false) String cardExpiryDate,
                                     @RequestParam(value = "order_details[agentUserId]", required = false) String agentUserId,
                                     @RequestParam(value = "order_details[basePrice]", required = false) String basePrice,
                                     @RequestParam(value = "order_details[baseShipping]", required = false) String baseShipping,
                                     @RequestParam(value = "order_details[discountPrice]", required = false) String discountPrice,
                                     @RequestParam(value = "order_details[salesTax]", required = false) String salesTax,
                                     @RequestParam(value = "order_details[currencySymbol]", required = false) String currencySymbol,
                                     @RequestParam(value = "order_details[campaignId]", required = false) String campaignId,
                                     @RequestParam(value = "order_details[originalCycleNumber]", required = false) String originalCycleNumber,
                                     @RequestParam(value = "order_details[subTotal]", required = false) String subTotal,
                                     @RequestParam(value = "order_details[shipTotal]", required = false) String shipTotal,
                                     @RequestParam(value = "order_details[taxTotal]", required = false) String taxTotal,
                                     @RequestParam(value = "order_details[totalDiscount]", required = false) String totalDiscount,
                                     @RequestParam(value = "order_details[amountPaid]", required = false) String amountPaid,
                                     HttpServletRequest request, HttpServletResponse response) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setResult(APIResponse.ResultType.ERROR);
        String message = "";
        if (konnectiveKey == null || !konnectiveKey.equals(envVariablesService.getKonnektiveKey())) {
            message = "API Token is Required";
        } else {
            if (apiKey == null || apiKey.trim().length() < 1) {
                message = "Client ID is Required";
            } else {
                if (insPrice == null || insPrice.trim().length() < 1) {
                    message = "Eco Price is Required";
                } else {
                    if (orderType == null || orderType.trim().length() < 1) {
                        message = "Order Type is Required";
                    } else {
                        if (trackingNumber == null || trackingNumber.trim().length() < 1) {
                            message = "Tracking Number is Required";
                        } else {

                            ApiKey apiKeyEntity = apiKeyDAO.findByApiKeyAndActive(apiKey, true);
                            if (apiKeyEntity == null || apiKeyEntity.getId() < 1) {
                                message = "Client ID is Required";
                            } else {

                                KonnektiveOrderDetails konnektiveOrderDetails = new KonnektiveOrderDetails(
                                        konnectiveKey,
                                        apiKeyEntity.getAccountId(),
                                        apiKey,
                                        insPrice,
                                        trackingNumber,
                                        orderType,
                                        orderId,
                                        sourceId,
                                        sourceValue1,
                                        sourceValue2,
                                        dateCreated,
                                        detailOrderType,
                                        orderStatus,
                                        totalAmount,
                                        campaignName,
                                        orderValue,
                                        customerId,
                                        name,
                                        emailAddress,
                                        phoneNumber,
                                        firstName,
                                        lastName,
                                        address1,
                                        shipmentInsured,
                                        shipmentInsurancePrice,
                                        insuranceCharged,
                                        city,
                                        state,
                                        country,
                                        postalCode,
                                        shipFirstName,
                                        shipLastName,
                                        shipAddress1,
                                        shipCity,
                                        shipState,
                                        shipCountry,
                                        shipPostalCode,
                                        paySource,
                                        cardType,
                                        cardLast4,
                                        cardExpiryDate,
                                        agentUserId,
                                        basePrice,
                                        baseShipping,
                                        discountPrice,
                                        salesTax,
                                        currencySymbol,
                                        campaignId,
                                        originalCycleNumber,
                                        subTotal,
                                        shipTotal,
                                        taxTotal,
                                        totalDiscount,
                                        amountPaid);
                                konnektiveOrderDetailsDAO.save(konnektiveOrderDetails);
                                String ipAddress = IpAddress.getIpAddress(request);
                                OmniTransaction omniTransaction =  omniTransactionService.konnektiveOrderToOmniTransaction(konnektiveOrderDetails,apiKeyEntity,ipAddress);

                                boolean hasMoreProducts = true;
                                int productItemNum = 0;

                                while (hasMoreProducts) {
                                    String productItemSeed = "order_details[items][" + productItemNum + "]";
                                    if (request.getParameter(productItemSeed + "[productId]") == null || request.getParameter(productItemSeed + "[productId]").trim().length() < 1) {
                                        hasMoreProducts = false;
                                    } else {
                                        KonnektiveOrderDetailsItem konnektiveOrderDetailsItem = new KonnektiveOrderDetailsItem();
                                        konnektiveOrderDetailsItem.setCrmPushApiKey(konnectiveKey);
                                        konnektiveOrderDetailsItem.setAccountId(apiKeyEntity.getAccountId());
                                        konnektiveOrderDetailsItem.setApiKey(apiKey);
                                        konnektiveOrderDetailsItem.setKonnektiveOrderDetailsId(konnektiveOrderDetails.getId());
                                        konnektiveOrderDetailsItem.setOrderId(orderId);
                                        konnektiveOrderDetailsItem.setProductId(request.getParameter(productItemSeed + "[productId]"));

                                        konnektiveOrderDetailsItem.setName(request.getParameter(productItemSeed + "[name]"));    // LaFolie Hair Growth Serum
                                        konnektiveOrderDetailsItem.setQty(request.getParameter(productItemSeed + "[qty]"));    // 1
                                        konnektiveOrderDetailsItem.setShipping(request.getParameter(productItemSeed + "[shipping]"));    // 4.97
                                        konnektiveOrderDetailsItem.setPrice(request.getParameter(productItemSeed + "[price]"));    // 0.00
                                        konnektiveOrderDetailsItem.setInitialSalesTax(request.getParameter(productItemSeed + "[initialSalesTax]"));    // 0.00
                                        konnektiveOrderDetailsItem.setCustomSalesTax(request.getParameter(productItemSeed + "[customSalesTax]"));    // 0.00
                                        konnektiveOrderDetailsItem.setRefundRemaining(request.getParameter(productItemSeed + "[refundRemaining]"));    // 4.97
                                        konnektiveOrderDetailsItem.setPurchaseStatus(request.getParameter(productItemSeed + "[purchaseStatus]"));    // TRIAL
                                        konnektiveOrderDetailsItem.setBillingCycleType(request.getParameter(productItemSeed + "[billingCycleType]"));    // RECURRING
                                        konnektiveOrderDetailsItem.setFinalBillingCycle(request.getParameter(productItemSeed + "[finalBillingCycle]"));    //
                                        konnektiveOrderDetailsItem.setIsPreauthVoid(request.getParameter(productItemSeed + "[isPreauthVoid]"));    // 0
                                        konnektiveOrderDetailsItem.setNextBillDate(request.getParameter(productItemSeed + "[nextBillDate]"));    // 2016-10-04
                                        konnektiveOrderDetailsItem.setTrialEnabled(request.getParameter(productItemSeed + "[trialEnabled]"));    // 1
                                        konnektiveOrderDetailsItem.setTrialType(request.getParameter(productItemSeed + "[trialType]"));    // STANDARD
                                        konnektiveOrderDetailsItem.setRegularPrice(request.getParameter(productItemSeed + "[regularPrice]"));    // 89.97
                                        konnektiveOrderDetailsItem.setProductQty(request.getParameter(productItemSeed + "[productQty]"));    // 1
                                        konnektiveOrderDetailsItem.setCycle1_billDelay(request.getParameter(productItemSeed + "[cycle1_billDelay]"));    // 18
                                        konnektiveOrderDetailsItem.setCycle2_price(request.getParameter(productItemSeed + "[cycle2_price]"));    // 89.97
                                        konnektiveOrderDetailsItem.setCycle2_shipPrice(request.getParameter(productItemSeed + "[cycle2_shipPrice]"));    // 0.00
                                        konnektiveOrderDetailsItem.setCycle2_isShippable(request.getParameter(productItemSeed + "[cycle2_isShippable]"));    // 0
                                        konnektiveOrderDetailsItem.setCycle2_billDelay(request.getParameter(productItemSeed + "[cycle2_billDelay]"));    // 30
                                        konnektiveOrderDetailsItem.setCycle3_price(request.getParameter(productItemSeed + "[cycle3_price]"));    // 89.97
                                        konnektiveOrderDetailsItem.setCycle3_shipPrice(request.getParameter(productItemSeed + "[cycle3_shipPrice]"));    // 7.97
                                        konnektiveOrderDetailsItem.setCycle3_isShippable(request.getParameter(productItemSeed + "[cycle3_isShippable]"));    // 1
                                        konnektiveOrderDetailsItem.setCycle3_billDelay(request.getParameter(productItemSeed + "[cycle3_billDelay]"));    // 30
                                        konnektiveOrderDetailsItem.setLastCustomCycle(request.getParameter(productItemSeed + "[lastCustomCycle]"));    // 3
                                        konnektiveOrderDetailsItem.setTaxRate(request.getParameter(productItemSeed + "[taxRate]"));    //
                                        konnektiveOrderDetailsItem.setPurchaseCycle(request.getParameter(productItemSeed + "[purchaseCycle]"));    // 1
                                        konnektiveOrderDetailsItem.setTxnType(request.getParameter(productItemSeed + "[txnType]"));    // SALE
                                        konnektiveOrderDetailsItem.setCancellationScheduled(request.getParameter(productItemSeed + "[cancellationScheduled]"));    // 0
                                        konnektiveOrderDetailsItem.setCancelAfterDate(request.getParameter(productItemSeed + "[cancelAfterDate]"));    //
                                        konnektiveOrderDetailsItem.setBillingCycleNumber(request.getParameter(productItemSeed + "[billingCycleNumber]"));    // 1
                                        konnektiveOrderDetailsItem.setStaggerIntervalCycles(request.getParameter(productItemSeed + "[staggerIntervalCycles]"));    // 2
                                        konnektiveOrderDetailsItem.setStaggerFulfillments(request.getParameter(productItemSeed + "[staggerFulfillments]"));    // 1
                                        konnektiveOrderDetailsItemDAO.save(konnektiveOrderDetailsItem);
                                        omniTransactionService.konnektiveItemToOmniTransactionItems(konnektiveOrderDetailsItem,omniTransaction.getId(),ipAddress);
                                    }
                                    productItemNum++;
                                }
                                omniTransactionService.updateItemTotal(productItemNum,omniTransaction);
                                apiResponse.setResult(APIResponse.ResultType.SUCCESS);
                                message = "Order " + orderId + " saved successfully";
                            }
                        }
                    }
                }
            }
        }
        Map<String, Object> errorAttributes = getErrorAttributes(request, true);
        printHttpReport.emailReport(PrintHttpReport.ReportType.INFO, response.getStatus(), PrintHttpReport.getIp(request), errorAttributes, request.getParameterMap(), PrintHttpReport.getReqBody(request), message, "NEW-API Konnektive Push");
        apiResponse.setMessage(message);
        return apiResponse;
    }
}
