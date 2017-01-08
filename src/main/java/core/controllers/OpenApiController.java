package core.controllers;

import core.entities.*;
import core.entities.enums.OrderType;
import core.entities.enums.PaymentType;
import core.entities.enums.ShippingNotificationType;
import core.entities.openApi.*;
import core.security.PrintHttpReport;
import core.services.*;
import core.util.CheckData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping(value={"/open_api/v1/"})
public class OpenApiController {
    private final OpenApiOrderDAO openApiOrderDAO;
    private final OpenApiQuoteDAO openApiQuoteDAO;
    private final ApiKeyDAO apiKeyDAO;
    private final CurrencyService currencyService;
    private final CourierNameService courierNameService;
    private final QuoteService quoteService;
    private final OpenApiItemDAO openApiItemDAO;
    private final OpenApiTrackingDAO openApiTrackingDAO;
    private final PrintHttpReport printHttpReport;
    private final TestOpenApiQuoteDAO testOpenApiQuoteDAO;
    private final TestOpenApiItemDAO testOpenApiItemDAO;
    private final TestOpenApiOrderDAO testOpenApiOrderDAO;
    private final TestOpenApiTrackingDAO testOpenApiTrackingDAO;
    private final OmniTransactionService omniTransactionService;
    private final TestOmniTransactionService testOmniTransactionService;

    @Autowired
    public OpenApiController(OpenApiOrderDAO openApiOrderDAO, ApiKeyDAO apiKeyDAO,
                             CurrencyService currencyService,
                             CourierNameService courierNameService,
                             QuoteService quoteService,
                             OpenApiQuoteDAO openApiQuoteDAO,
                             OpenApiItemDAO openApiItemDAO,
                             OpenApiTrackingDAO openApiTrackingDAO,
                             PrintHttpReport printHttpReport,
                             TestOpenApiQuoteDAO testOpenApiQuoteDAO,
                             TestOpenApiItemDAO testOpenApiItemDAO,
                             TestOpenApiOrderDAO testOpenApiOrderDAO,
                             TestOpenApiTrackingDAO testOpenApiTrackingDAO,
                             OmniTransactionService omniTransactionService,
                             TestOmniTransactionService testOmniTransactionService) {
        Assert.notNull(openApiOrderDAO, "OpenApiOrderDAO must not be null!");
        Assert.notNull(apiKeyDAO, "ApiKeyDAO must not be null!");
        Assert.notNull(currencyService, "CurrencyService must not be null!");
        Assert.notNull(courierNameService, "CourierNameService must not be null!");
        Assert.notNull(quoteService, "QuoteService must not be null!");
        Assert.notNull(openApiQuoteDAO, "OpenApiQuoteDAO must not be null!");
        Assert.notNull(openApiItemDAO, "OpenApiItemDAO must not be null!");
        Assert.notNull(openApiTrackingDAO, "OpenApiTrackingDAO must not be null!");
        Assert.notNull(printHttpReport, "PrintHttpReport must not be null!");
        Assert.notNull(testOpenApiQuoteDAO, "TestOpenApiQuoteDAO must not be null!");
        Assert.notNull(testOpenApiItemDAO, "TestOpenApiItemDAO must not be null!");
        Assert.notNull(testOpenApiOrderDAO, "TestOpenApiOrderDAO must not be null!");
        Assert.notNull(testOpenApiTrackingDAO, "TestOpenApiTrackingDAO must not be null!");
        Assert.notNull(omniTransactionService, "OmniTransactionService must not be null!");
        Assert.notNull(testOmniTransactionService, "TestOmniTransactionService must not be null!");
        this.openApiOrderDAO = openApiOrderDAO;
        this.apiKeyDAO = apiKeyDAO;
        this.currencyService = currencyService;
        this.courierNameService = courierNameService;
        this.quoteService = quoteService;
        this.openApiQuoteDAO = openApiQuoteDAO;
        this.openApiItemDAO = openApiItemDAO;
        this.openApiTrackingDAO = openApiTrackingDAO;
        this.printHttpReport = printHttpReport;
//        this.transactionService = transactionService;
//        this.shippingTrackingService = shippingTrackingService;
//        this.bugsnag = bugsnag;
        this.testOpenApiQuoteDAO = testOpenApiQuoteDAO;
        this.testOpenApiItemDAO = testOpenApiItemDAO;
        this.testOpenApiOrderDAO = testOpenApiOrderDAO;
        this.testOpenApiTrackingDAO = testOpenApiTrackingDAO;
        this.omniTransactionService = omniTransactionService;
        this.testOmniTransactionService = testOmniTransactionService;
    }

    @RequestMapping(value = {"get/json/currency_codes", "get/json/order_types", "get/json/payment_types", "get/json/shipping_carrier_codes"},
            method = {RequestMethod.POST, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.TRACE, RequestMethod.OPTIONS})
    public OpenApiStringResponse noPostMethod() {
        ArrayList<String> errors = new ArrayList<>();
        errors.add("This API requires request method GET. Detailed API documentation can be found at https://api.routeit.cloud");
        return new OpenApiStringResponse(errors);
    }

    @RequestMapping(value = "get/json/currency_codes", method = RequestMethod.GET)
    public OpenApiHashMapResponse getCurrencyCodes(@RequestHeader(defaultValue = "") String api_key,
                                                   @RequestHeader(defaultValue = "") String secret) {
        ArrayList<String> errors = new ArrayList<>();
        if(simpleKeyCheck(api_key,secret,errors)) {
            return new OpenApiHashMapResponse(currencyService.getCurrencyCodeCurrencyNameMap());
        }
        return new OpenApiHashMapResponse(errors);
    }

    @RequestMapping(value = "get/json/order_types", method = RequestMethod.GET)
    public OpenApiHashMapResponse getOrderTypes(@RequestHeader(defaultValue = "") String api_key,
                                               @RequestHeader(defaultValue = "") String secret) {
        ArrayList<String> errors = new ArrayList<>();
        if(simpleKeyCheck(api_key,secret,errors)) {
            HashMap<String,String> orderTypes = new HashMap<>();
            for(OrderType orderType : OrderType.values()) {
                orderTypes.put(orderType.toString(),orderType.getName());
            }
            return new OpenApiHashMapResponse(orderTypes);
        }
        return new OpenApiHashMapResponse(errors);
    }

    @RequestMapping(value = "get/json/payment_types", method = RequestMethod.GET)
    public OpenApiHashMapResponse getPaymentTypes(@RequestHeader(defaultValue = "") String api_key,
                                                 @RequestHeader(defaultValue = "") String secret) {
        ArrayList<String> errors = new ArrayList<>();
        if(simpleKeyCheck(api_key,secret,errors)) {
            HashMap<String,String> paymentTypes = new HashMap<>();
            for(PaymentType paymentType : PaymentType.values()) {
                paymentTypes.put(paymentType.toString(),paymentType.getName());
            }
            return new OpenApiHashMapResponse(paymentTypes);
        }
        return new OpenApiHashMapResponse(errors);
    }

    @RequestMapping(value = "get/json/shipping_carrier_codes", method = RequestMethod.GET)
    public OpenApiHashMapResponse getShippingCarrierCodes(@RequestHeader(defaultValue = "") String api_key,
                                                   @RequestHeader(defaultValue = "") String secret) {
        ArrayList<String> errors = new ArrayList<>();
        if(simpleKeyCheck(api_key,secret,errors)) {
            return new OpenApiHashMapResponse(courierNameService.getShippingCarrierCodeShippingCarrierNameMap());
        }
        return new OpenApiHashMapResponse(errors);
    }

    private boolean simpleKeyCheck(String apiKey, String secret, ArrayList<String> errors) {
        if(checkLiveKeys(apiKey,secret,errors)) {
            if(errors.isEmpty()) {
                return true;
            }
        } else if(checkTestKeys(apiKey,secret,errors)) {
            return true;
        }
        errors.add("All API calls must include a valid api_key and secret in the header to authenticate.");
        errors.add("Detailed API documentation can be found at https://api.routeit.cloud");
        return false;
    }

    @RequestMapping(value = {"post/json/request_quote", "post/json/insure_order", "post/json/update_tracking"},
            method = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.TRACE, RequestMethod.OPTIONS})
    public OpenApiStringResponse noGetMethod() {
        ArrayList<String> errors = new ArrayList<>();
        errors.add("This API requires request method POST. Detailed API documentation can be found at https://api.routeit.cloud");
        return new OpenApiStringResponse(errors);
    }

    private boolean checkLiveKeys(String apiKey, String secret, ArrayList<String> errors) {
        if(apiKey.trim().length() > 30 && apiKey.startsWith("LIVE_ak_")
                && secret.trim().length() > 30 && secret.startsWith("LIVE_s_")) {
            ApiKey apiKeyObject = apiKeyDAO.findByApiKeyAndSecretAndActive(apiKey,secret,true);
            if(apiKeyObject == null || apiKeyObject.getAccountId() < 1) {
                errors.add("Invalid api_key or secret.");
            } else if(apiKeyObject.getBillingId() < 1) {
                errors.add("No billing setup for these keys. Live API requests require valid billing. If this is a test, use the Test API keys.");
            }
            return true;
        }
        return false;
    }

    private boolean checkTestKeys(String apiKey, String secret, ArrayList<String> errors) {
        if(apiKey.trim().length() > 30 && apiKey.startsWith("test_ak_")
                && secret.trim().length() > 30 && secret.startsWith("test_s_")) {
            ApiKey apiKeyObject = apiKeyDAO.findByTestApiKeyAndTestSecretAndActive(apiKey,secret,true);
            if(apiKeyObject == null || apiKeyObject.getAccountId() < 1) {
                errors.add("Invalid api_key or secret.");
            }
            return true;
        }
        return false;
    }

    @Cacheable("isOrderType")
    public boolean isOrderType(String testOrderType) {
        for(OrderType orderType : OrderType.values()) {
            if(OrderType.valueOf(testOrderType).equals(orderType)) {
                return true;
            }
        }
        return false;
    }

    @Cacheable("isPaymentType")
    public boolean isPaymentType(String testPaymentType) {
        for(PaymentType paymentType : PaymentType.values()) {
            if(PaymentType.valueOf(testPaymentType).equals(paymentType)) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "post/json/request_quote", method = RequestMethod.POST)
    public OpenApiQuoteResponse getQuote(@RequestHeader(defaultValue = "") String api_key,
                                         @RequestHeader(defaultValue = "") String secret,
                                         @RequestBody(required = false) Quote quote,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();

        boolean isLive = false;
        boolean isTest = false;
        if(checkLiveKeys(api_key,secret,errors)) {
            isLive = true;
        } else if(checkTestKeys(api_key,secret,errors)) {
            isTest = true;
        } else {
            errors.add("All API calls must include a valid api_key and secret in the header to authenticate.");
        }
        if(quote != null) {
            if(quote.getOrder_base_value() == null || quote.getOrder_base_value().compareTo(BigDecimal.ZERO) <= 0) {
                errors.add("Parameter order_base_value must be greater than zero.");
            } else if(quote.getOrder_base_value().compareTo(quote.getOrder_base_total()) < 0) {
                errors.add("Parameter order_base_value must be the same as order_base_total or greater than order_base_total.");
            }
            if(quote.getOrder_base_total() == null || quote.getOrder_base_total().compareTo(BigDecimal.ZERO) < 0) {
                errors.add("Parameter order_base_value must be zero or greater.");
            }
            if(quote.getOrder_currency_code() == null || quote.getOrder_currency_code().trim().length() != 3 || !currencyService.isCurrencyCode(quote.getOrder_currency_code())) {
                errors.add("Missing required parameter order_currency_code. Find a list of Currency Codes at https://api.routeit.cloud/open_api/v1/get/json/currency_codes");
            }
            if(quote.getOrder_type() == null || quote.getOrder_type().isEmpty() || !isOrderType(quote.getOrder_type())) {
                errors.add("Missing required parameter order_type. Find a list of Order Types at https://api.routeit.cloud/open_api/v1/get/json/order_types");
            }
            if(quote.getTotal_items() < 1) {
                errors.add("Missing required parameter total_items.");
            }
            if(quote.getItems() == null || quote.getItems().isEmpty()) {
                errors.add("Missing items. Items are required.");
            } else {
                ArrayList<String> itemErrors = checkItems(quote.getItems(),quote.getTotal_items(),quote.getOrder_base_value(),quote.getOrder_base_total(),quote.getOrder_currency_code());
                if(!itemErrors.isEmpty()){
                    errors.addAll(itemErrors);
                }
            }
        } else {
            errors.add("Missing ALL required parameters.");
        }
        if(errors.isEmpty()) {
            ApiKey apiKeyObject = null;
            if(isLive) {
                apiKeyObject = apiKeyDAO.findByApiKeyAndSecretAndActive(api_key,secret,true);
            } else {
                apiKeyObject = apiKeyDAO.findByTestApiKeyAndTestSecretAndActive(api_key,secret,true);
            }
            String widgetKey = apiKeyObject.getTestWidgetKey();
            BigDecimal insuranceCostUSD = quoteService.getInsuranceCostUSD(quote.getOrder_base_value(),quote.getOrder_currency_code(), apiKeyObject);
            quote.setInsurance_cost_usd(insuranceCostUSD);
            BigDecimal suggestedInsurancePrice = quoteService.getSuggestedInsurancePrice(insuranceCostUSD,quote.getOrder_currency_code(),apiKeyObject);
            quote.setSuggested_insurance_price(suggestedInsurancePrice);
            quote.setExchange_rate(new BigDecimal(currencyService.getExchangeRate(quote.getOrder_currency_code())));
            quote.setSuggested_insurance_currency(quote.getOrder_currency_code());
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            quote.setIp_address(ipAddress);
            quote.setCreated(new Timestamp(System.currentTimeMillis()));
            if(isLive) {
                OpenApiQuote openApiQuote = new OpenApiQuote(quote,api_key,widgetKey,apiKeyObject.getAccountId());
                openApiQuoteDAO.save(openApiQuote);
                quote.setRoute_quote_id(openApiQuote.getId());
                ArrayList<OpenApiItem> itemsToSave = new ArrayList<>();
                for(Item item : quote.getItems()) {
                    itemsToSave.add(new OpenApiItem(item,openApiQuote.getId(),api_key,apiKeyObject.getAccountId(),ipAddress));
                }
                openApiItemDAO.save(itemsToSave);
                printHttpReport.emailReport(PrintHttpReport.ReportType.INFO,response.getStatus(),ipAddress,null,request.getParameterMap(),quote.toString(),null,"Request sent to Open API request_quote");
            } else if(isTest) {
                TestOpenApiQuote testOpenApiQuote = new TestOpenApiQuote(quote,api_key,widgetKey,apiKeyObject.getAccountId());
                testOpenApiQuoteDAO.save(testOpenApiQuote);
                quote.setRoute_quote_id(testOpenApiQuote.getId());
                ArrayList<TestOpenApiItem> itemsToSave = new ArrayList<>();
                for(Item item : quote.getItems()) {
                    itemsToSave.add(new TestOpenApiItem(item,testOpenApiQuote.getId(),api_key,apiKeyObject.getAccountId(),ipAddress));
                }
                testOpenApiItemDAO.save(itemsToSave);
                printHttpReport.emailReport(PrintHttpReport.ReportType.INFO,response.getStatus(),ipAddress,null,request.getParameterMap(),quote.toString(),null,"TEST request sent to Open API request_quote");
            }
            return new OpenApiQuoteResponse(quote);
        }
        errors.add("Detailed API documentation can be found at https://api.routeit.cloud");
        return new OpenApiQuoteResponse(errors);
    }

    private ArrayList<String> checkItems(ArrayList<Item> items, int totalItems, BigDecimal orderBaseValue, BigDecimal orderBaseTotal, String currencyCode) {
        int itemCount = 0;
        BigDecimal itemTotalSum = BigDecimal.ZERO;
        BigDecimal itemValueSum = BigDecimal.ZERO;
        ArrayList<String> errors = new ArrayList<>();

        for(Item item : items) {
            if(item.getItem_id() == null || item.getItem_id().isEmpty()) {
                errors.add("One or more items missing required parameter item_id.");
            }
            if(item.getItem_qty() < 1) {
                errors.add("Required parameter item_id must be one or greater.");
                itemCount++;
            } else {
                itemCount += item.getItem_qty();
            }
            if(item.getItem_name() == null || item.getItem_name().isEmpty()) {
                errors.add("One or more items missing required parameter item_name.");
            }
            if(item.getItem_value_per_unit().compareTo(item.getItem_cost_per_unit()) < 0) {
                errors.add("Parameter item_value_per_unit must be the same as item_cost_per_unit or greater than item_cost_per_unit.");
            }
            if(item.getItem_cost_per_unit() == null || item.getItem_cost_per_unit().compareTo(BigDecimal.ZERO) < 0) {
                errors.add("Required parameter item_cost_per_unit must be zero or greater.");
            } else {
                if(item.getItem_qty() == 1) {
                    itemTotalSum = itemTotalSum.add(item.getItem_cost_per_unit());
                } else if(item.getItem_qty() > 1) {
                    int count = 0;
                    while(count < item.getItem_qty()) {
                        itemTotalSum = itemTotalSum.add(item.getItem_cost_per_unit());
                        count++;
                    }
                }
            }
            if(item.getItem_value_per_unit() == null || item.getItem_value_per_unit().compareTo(BigDecimal.ZERO) < 0) {
                errors.add("Required parameter item_value_per_unit must be zero or greater.");
            } else {
                if(item.getItem_qty() == 1) {
                    itemValueSum = itemValueSum.add(item.getItem_value_per_unit());
                } else if(item.getItem_qty() > 1) {
                    int count = 0;
                    while(count < item.getItem_qty()) {
                        itemValueSum = itemValueSum.add(item.getItem_value_per_unit());
                        count++;
                    }
                }
            }
        }
        // Check the order total against the summed item total
        if(orderBaseTotal.compareTo(itemTotalSum) != 0) {
            errors.add("The sum of item_cost_per_unit ("+orderBaseTotal.toString()+") does not equal order_base_total ("+itemTotalSum.toString()+").");
        }
        // Check the order value against the summed item value
        if(orderBaseValue.compareTo(itemValueSum) != 0) {
            errors.add("The sum of item_value_per_unit ("+orderBaseValue.toString()+") does not equal order_base_value ("+itemValueSum.toString()+").");
        }
        // Check the order total count against the item summed count
        if(itemCount != totalItems) {
            errors.add("The sum of item_qty ("+itemCount+") does not equal total_items ("+totalItems+").");
        }
        return errors;
    }

    private Order copyBillingFromShipping(Order order) {
        if(order.isBilling_same_as_shipping()) {
            order.setBilling_address_1(order.getShipping_address_1());
            order.setBilling_address_2(order.getShipping_address_2());
            order.setBilling_city(order.getShipping_city());
            order.setBilling_province(order.getShipping_province());
            order.setBilling_country(order.getShipping_country());
            order.setBilling_postal_code(order.getShipping_postal_code());
        }
        return order;
    }

    @RequestMapping(value = "post/json/insure_order", method = RequestMethod.POST)
    public OpenApiOrderResponse insureOrder(@RequestHeader(defaultValue = "") String api_key,
                                             @RequestHeader(defaultValue = "") String secret,
                                             @RequestBody(required = false) Order order,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();

        boolean isLive = false;
        boolean isTest = false;
        if(checkTestKeys(api_key,secret,errors)) {
            System.out.println("");
            isTest = true;
        } else if(checkLiveKeys(api_key,secret,errors)) {
            isLive = true;
        } else {
            errors.add("All API calls must include a valid api_key and secret in the header to authenticate.");
        }
        if(order != null) {
            LocalDateTime thirtySixHoursAgo = LocalDateTime.now();
            thirtySixHoursAgo = thirtySixHoursAgo.minusHours(36);
            if(isLive) {
                OpenApiQuote openApiQuote = openApiQuoteDAO.findByIdAndApiKey(order.getRoute_quote_id(),api_key);
                if(openApiQuote != null && openApiQuote.getId() > 0) {
                    if(openApiQuote.getCreatedByDateTime().isAfter(thirtySixHoursAgo)) {
                        if(openApiQuote.getOpenApiOrderId() < 1) {
                            ArrayList<String> orderErrors = checkOrder(order,openApiQuote.getOrderBaseValue(), openApiQuote.getOrderBaseTotal());
                            if(!orderErrors.isEmpty()){
                                errors.addAll(orderErrors);
                            } else {
                                order = copyBillingFromShipping(order);
                            }
                        } else {
                            errors.add("Quote for route_quote_id ("+order.getRoute_quote_id()+") has already been used. Quotes can only be applied to a single order.");
                        }
                    } else {
                        errors.add("Quote for route_quote_id ("+order.getRoute_quote_id()+") has expired. Quotes must be used within 24 hours.");
                    }
                } else {
                    errors.add("No quote exists for given api_key ("+api_key+") and route_quote_id ("+order.getRoute_quote_id()+").");
                }
            } else {
                TestOpenApiQuote testOpenApiQuote = testOpenApiQuoteDAO.findByIdAndTestApiKey(order.getRoute_quote_id(),api_key);
                if(testOpenApiQuote != null && testOpenApiQuote.getId() > 0) {
                    if(testOpenApiQuote.getCreatedByDateTime().isAfter(thirtySixHoursAgo)) {
                        if(testOpenApiQuote.getTestOpenApiOrderId() < 1) {
                            ArrayList<String> orderErrors = checkOrder(order,testOpenApiQuote.getOrderBaseValue(), testOpenApiQuote.getOrderBaseTotal());
                            if(!orderErrors.isEmpty()){
                                errors.addAll(orderErrors);
                            } else {
                                order = copyBillingFromShipping(order);
                            }
                        } else {
                            errors.add("Quote for test_route_quote_id ("+order.getRoute_quote_id()+") has already been used. Quotes can only be applied to a single order.");
                        }
                    } else {
                        errors.add("Quote for test_route_quote_id ("+order.getRoute_quote_id()+") has expired. Quotes must be used within 24 hours.");
                    }
                } else {
                    errors.add("No quote exists for given test_api_key ("+api_key+") and test_route_quote_id ("+order.getRoute_quote_id()+").");
                }
            }
        } else {
            errors.add("Missing ALL required parameters.");
        }
        if(errors.isEmpty()) {
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            order.setIp_address(ipAddress);
            order.setCreated(new Timestamp(System.currentTimeMillis()));
            if(isLive) {
                OpenApiQuote openApiQuote = openApiQuoteDAO.findByIdAndApiKey(order.getRoute_quote_id(),api_key);
                order.setOrder_type(openApiQuote.getOrderType());
                order.setCurrency_code(openApiQuote.getOrderCurrencyCode());
                order.setExchange_rate(openApiQuote.getExchangeRate());
                order.setInsurance_cost_usd(openApiQuote.getInsuranceCostUsd());

                ApiKey apiKeyObject = apiKeyDAO.findByApiKeyAndSecretAndActive(api_key,secret,true);

                OpenApiOrder openApiOrder = new OpenApiOrder(order,api_key,apiKeyObject.getAccountId());
                openApiOrderDAO.save(openApiOrder);
                OmniTransaction omniTransaction = omniTransactionService.openApiOrderToOmniTransaction(openApiOrder, openApiQuote, apiKeyObject, ShippingNotificationType.EMAIL);
                order.setRoute_order_id(openApiOrder.getId());
                ArrayList<OpenApiItem> openApiItemsToUpdate = new ArrayList<>();
                ArrayList<Item> allItemsForOrder = new ArrayList<>();
                for(OpenApiItem openApiItem : openApiItemDAO.findByOpenIdQuoteIdAndApiKey(order.getRoute_quote_id(),api_key)) {
                    openApiItem.setOpenApiOrderId(openApiOrder.getId());
                    openApiItem.setOmniTransactionId(omniTransaction.getId());
                    omniTransactionService.openApiItemToOmniTransactionItems(openApiItem);
                    allItemsForOrder.add(new Item(openApiItem));
                    openApiItemsToUpdate.add(openApiItem);
                }
                openApiItemDAO.save(openApiItemsToUpdate);
                order.setItems(allItemsForOrder);
                printHttpReport.emailReport(PrintHttpReport.ReportType.INFO,response.getStatus(),ipAddress,null,request.getParameterMap(),order.toString(),null,"Request sent to Open API insure_order -- OmniTransaction.id: "+omniTransaction.getId());
            } else if(isTest) {
                TestOpenApiQuote testOpenApiQuote = testOpenApiQuoteDAO.findByIdAndTestApiKey(order.getRoute_quote_id(),api_key);
                order.setOrder_type(testOpenApiQuote.getOrderType());
                order.setCurrency_code(testOpenApiQuote.getOrderCurrencyCode());
                order.setExchange_rate(testOpenApiQuote.getExchangeRate());
                order.setInsurance_cost_usd(testOpenApiQuote.getInsuranceCostUsd());

                ApiKey apiKeyObject = apiKeyDAO.findByTestApiKeyAndTestSecretAndActive(api_key,secret,true);

                TestOpenApiOrder testOpenApiOrder = new TestOpenApiOrder(order,api_key,apiKeyObject.getAccountId());
                testOpenApiOrderDAO.save(testOpenApiOrder);
                TestOmniTransaction testOmniTransaction = testOmniTransactionService.testOpenApiOrderToTestOmniTransaction(testOpenApiOrder, testOpenApiQuote, apiKeyObject, ShippingNotificationType.EMAIL);
                order.setRoute_order_id(testOpenApiOrder.getId());
                ArrayList<TestOpenApiItem> openApiItemsToUpdate = new ArrayList<>();
                ArrayList<Item> allItemsForOrder = new ArrayList<>();
                for(TestOpenApiItem testOpenApiItem : testOpenApiItemDAO.findByTestOpenApiQuoteIdAndTestApiKey(order.getRoute_quote_id(),api_key)) {
                    testOpenApiItem.setTestOpenApiOrderId(testOpenApiOrder.getId());
                    testOpenApiItem.setTestOmniTransactionId(testOmniTransaction.getId());
                    testOmniTransactionService.testOpenApiOrderToTestOmniTransactionItems(testOpenApiItem);
                    allItemsForOrder.add(new Item(testOpenApiItem));
                    openApiItemsToUpdate.add(testOpenApiItem);
                }
                testOpenApiItemDAO.save(openApiItemsToUpdate);
                order.setItems(allItemsForOrder);
                printHttpReport.emailReport(PrintHttpReport.ReportType.INFO,response.getStatus(),ipAddress,null,request.getParameterMap(),order.toString(),null,"TEST request sent to Open API insure_order -- TestOmniTransaction: "+testOmniTransaction.getId());
            }
            return new OpenApiOrderResponse(order);
        }
        errors.add("Detailed API documentation can be found at https://api.routeit.cloud");
        return new OpenApiOrderResponse(errors);
    }

    private ArrayList<String> checkOrder(Order order, BigDecimal quote_order_base_value, BigDecimal quote_order_base_total) {
        ArrayList<String> errors = new ArrayList<>();

        if(order.getOrder_id() == null || order.getOrder_id().isEmpty()) {
            errors.add("Missing required parameter order_id.");
        }
        if(order.getOrder_date() == null || order.getOrder_date().isEmpty()) {
            errors.add("Missing required parameter order_date.");
        }
        if(order.getOrder_base_value() == null || order.getOrder_base_value().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("Parameter order_base_value must be greater than zero.");
        } else if(order.getOrder_base_value().compareTo(quote_order_base_value) != 0) {
            errors.add("Parameter order_base_value is required to match order_base_value used to generate quote_id "+order.getRoute_quote_id()+". order.order_base_value: "+order.getOrder_base_value()+" != quote.order_base_value: "+quote_order_base_value);
        }
        if(order.getOrder_base_total() == null || order.getOrder_base_total().compareTo(BigDecimal.ZERO) < 0) {
            errors.add("Parameter order_base_total must be zero or greater.");
        } else if(order.getOrder_base_total().compareTo(quote_order_base_total) != 0) {
            errors.add("Parameter order_base_total is required to match order_base_total used to generate quote_id "+order.getRoute_quote_id()+". order.order_base_total: "+order.getOrder_base_total()+" != quote.order_base_total: "+quote_order_base_total);
        }
        if(order.getShipping_total() == null || order.getShipping_total().compareTo(BigDecimal.ZERO) < 0) {
            errors.add("Parameter shipping_total must be zero or greater.");
        }
        if(order.getHandling_total() == null || order.getHandling_total().compareTo(BigDecimal.ZERO) < 0) {
            errors.add("Parameter handling_total must be zero or greater.");
        }
        if(order.getSales_tax_total() == null || order.getSales_tax_total().compareTo(BigDecimal.ZERO) < 0) {
            errors.add("Parameter sales_tax_total must be zero or greater.");
        }
        if(order.getCustomer_paid_insurance_price() == null || order.getCustomer_paid_insurance_price().compareTo(BigDecimal.ZERO) < 0) {
            errors.add("Parameter customer_paid_insurance_price must be zero or greater.");
        }
        if(order.getPayment_type() == null || order.getPayment_type().isEmpty() || !isPaymentType(order.getPayment_type())) {
            errors.add("Missing required parameter payment_type. Find a list of Payment Types at https://api.routeit.cloud/open_api/v1/get/json/payment_types");
        } else if(order.getPayment_type().equals(PaymentType.CREDIT_CARD)) {
            if(order.getCard_last_four() == null || order.getCard_last_four().isEmpty() || !CheckData.isNumbersOnly(order.getCard_last_four())) {
                errors.add("Parameter card_last_four is required when payment_type is CREDIT_CARD.");
            }
            if(order.getCard_expire_date() == null || order.getCard_last_four().isEmpty()) {
                errors.add("Parameter card_expire_date is required when payment_type is CREDIT_CARD.");
            }
        } else if(order.getPayment_type().equals(PaymentType.OTHER)) {
            if(order.getPayment_type_other() == null || order.getPayment_type_other().isEmpty()) {
                errors.add("Parameter payment_type_other is required when payment_type is OTHER.");
            }
        }
        if(order.getFirst_name() == null || order.getFirst_name().isEmpty()) {
            errors.add("Missing required parameter first_name.");
        }
        if(order.getLast_name() == null || order.getLast_name().isEmpty()) {
            errors.add("Missing required parameter last_name.");
        }
        if(order.getEmail() == null || order.getEmail().isEmpty()) {
            errors.add("Missing required parameter email.");
        }
        if(order.getPhone() == null || order.getPayment_type().isEmpty()) {
            errors.add("Missing required parameter phone.");
        }
        if(order.getShipping_address_1() == null || order.getShipping_address_1().isEmpty()) {
            errors.add("Missing required parameter shipping_address_1.");
        }
        if(order.getShipping_city() == null || order.getShipping_city().isEmpty()) {
            errors.add("Missing required parameter shipping_city.");
        }
        if(order.getShipping_province() == null || order.getShipping_province().isEmpty()) {
            errors.add("Missing required parameter shipping_province.");
        }
        if(order.getShipping_postal_code() == null || order.getShipping_postal_code().isEmpty()) {
            errors.add("Missing required parameter shipping_postal_code.");
        }
        if(order.getShipping_country() == null || order.getShipping_country().isEmpty()) {
            errors.add("Missing required parameter shipping_country.");
        }
        if(order.getShipping_carrier_code() != null && !order.getShipping_carrier_code().isEmpty()) {
            if(!courierNameService.isSlug(order.getShipping_carrier_code())) {
                String slugFromName = courierNameService.getCourierSlug(order.getShipping_carrier_code());
                if(slugFromName == null) {
                    errors.add("Value for shipping_carrier_code "+order.getShipping_carrier_code()+" is invalid. Find a list of Shipping Carrier Codes at https://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes");
                } else {
                    order.setShipping_carrier_code(slugFromName);
                }
            }
        }
        if(!order.isBilling_same_as_shipping()) {
            if(order.getBilling_address_1() == null || order.getBilling_address_1().isEmpty()) {
                errors.add("Missing required parameter billing_address_1.");
            }
            if(order.getBilling_city() == null || order.getBilling_city().isEmpty()) {
                errors.add("Missing required parameter billing_city.");
            }
            if(order.getBilling_province() == null || order.getBilling_province().isEmpty()) {
                errors.add("Missing required parameter billing_province.");
            }
            if(order.getBilling_postal_code() == null || order.getBilling_postal_code().isEmpty()) {
                errors.add("Missing required parameter billing_postal_code.");
            }
            if(order.getBilling_country() == null || order.getBilling_country().isEmpty()) {
                errors.add("Missing required parameter shipping_country.");
            }
        }
        return errors;
    }

    @RequestMapping(value = "post/json/update_tracking", method = RequestMethod.POST)
    public OpenApiTrackingResponse addTracking(@RequestHeader(defaultValue = "") String api_key,
                              @RequestHeader(defaultValue = "") String secret,
                              @RequestBody(required = false) Tracking tracking,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        boolean isLive = false;
        boolean isTest = false;
        long routeQuoteId = -1;
        long routeOrderId = -1;
        if(checkTestKeys(api_key,secret,errors)) {
            isTest = true;
        } else if(checkLiveKeys(api_key,secret,errors)) {
            isLive = true;
            System.out.println("");
        } else {
            errors.add("All API calls must include a valid api_key and secret in the header to authenticate.");
        }
        if(tracking != null) {
            if(tracking.getRoute_quote_id() < 1 && tracking.getRoute_order_id() < 1) {
                errors.add("Request must include a valid route_order_id or route_quote_id to update tracking.");
            } else {
                if(isLive) {
                    boolean foundOrder = false;
                    if(tracking.getRoute_order_id() > 0) {
                        OpenApiOrder openApiOrder = openApiOrderDAO.findByIdAndApiKey(tracking.getRoute_order_id(),api_key);
                        if(openApiOrder != null && openApiOrder.getAccountId() > 0) {
                            foundOrder = true;
                            routeOrderId = openApiOrder.getId();
                            routeQuoteId = openApiOrder.getOpenApiQuoteId();
                        }
                    }
                    if(!foundOrder && tracking.getRoute_quote_id() > 0) {
                        OpenApiOrder openApiOrder = openApiOrderDAO.findByOpenApiQuoteIdAndApiKey(tracking.getRoute_quote_id(),api_key);
                        if(openApiOrder != null && openApiOrder.getAccountId() > 0) {
                            foundOrder = true;
                            routeOrderId = openApiOrder.getId();
                            routeQuoteId = openApiOrder.getOpenApiQuoteId();
                        }
                    }
                    if(!foundOrder) {
                        errors.add("Unable to find route_order_id "+tracking.getRoute_order_id()+" or route_quote_id "+tracking.getRoute_quote_id()+" associated to api_key: "+api_key+".");
                    }
                    ArrayList<String> trackingErrors = checkTracking(tracking);
                    if(!trackingErrors.isEmpty()){
                        errors.addAll(trackingErrors);
                    }
                } else if(isTest) {
                    boolean foundTestOrder = false;
                    if(tracking.getRoute_order_id() > 0) {
                        TestOpenApiOrder testOpenApiOrder = testOpenApiOrderDAO.findByIdAndTestApiKey(tracking.getRoute_order_id(),api_key);
                        if(testOpenApiOrder != null && testOpenApiOrder.getAccountId() > 0) {
                            foundTestOrder = true;
                            routeOrderId = testOpenApiOrder.getId();
                            routeQuoteId = testOpenApiOrder.getTestOpenApiQuoteId();
                        }
                    }
                    if(!foundTestOrder && tracking.getRoute_quote_id() > 0) {
                        TestOpenApiOrder testOpenApiOrder = testOpenApiOrderDAO.findByTestOpenApiQuoteIdAndTestApiKey(tracking.getRoute_quote_id(),api_key);
                        if(testOpenApiOrder != null && testOpenApiOrder.getAccountId() > 0) {
                            foundTestOrder = true;
                            routeOrderId = testOpenApiOrder.getId();
                            routeQuoteId = testOpenApiOrder.getTestOpenApiQuoteId();
                        }
                    }
                    if(!foundTestOrder) {
                        errors.add("Unable to find test route_order_id "+tracking.getRoute_order_id()+" or test route_quote_id "+tracking.getRoute_quote_id()+" associated to test api_key: "+api_key+".");
                    }
                    ArrayList<String> trackingErrors = checkTracking(tracking);
                    if(!trackingErrors.isEmpty()){
                        errors.addAll(trackingErrors);
                    }
                }
            }
        }
        if(errors.isEmpty()) {
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            tracking.setIp_address(ipAddress);
            tracking.setCreated(new Timestamp(System.currentTimeMillis()));
            tracking.setRoute_order_id(routeOrderId);
            tracking.setRoute_quote_id(routeQuoteId);
            if(isLive) {
                OpenApiOrder openApiOrder = openApiOrderDAO.findByIdAndApiKey(routeOrderId,api_key);
                if(tracking.getShipping_total().compareTo(BigDecimal.ZERO) < 0) {
                    tracking.setShipping_total(BigDecimal.ZERO);
                }
                if(tracking.getShipping_total().compareTo(openApiOrder.getShippingTotal()) < 0) {
                    tracking.setShipping_total(openApiOrder.getShippingTotal());
                } else if (tracking.getShipping_total().compareTo(openApiOrder.getShippingTotal()) > 0) {
                    openApiOrder.setShippingTotal(tracking.getShipping_total());
                }
                if(tracking.getHandling_total().compareTo(BigDecimal.ZERO) < 0) {
                    tracking.setHandling_total(BigDecimal.ZERO);
                }
                if(tracking.getHandling_total().compareTo(openApiOrder.getHandlingTotal()) < 0) {
                    tracking.setHandling_total(openApiOrder.getHandlingTotal());
                } else if (tracking.getHandling_total().compareTo(openApiOrder.getHandlingTotal()) > 0) {
                    openApiOrder.setHandlingTotal(tracking.getHandling_total());
                }
                openApiOrder.setTrackingNumber(tracking.getTracking_number());
                openApiOrder.setShippedDate(tracking.getShipped_date());
                if(tracking.getShipping_carrier_code() != null && courierNameService.isSlug(tracking.getShipping_carrier_code())) {
                    openApiOrder.setShippingCarrierCode(tracking.getShipping_carrier_code());
                }
                if(tracking.getShipping_method() != null && tracking.getShipping_method().isEmpty()) {
                    openApiOrder.setShippingMethod(tracking.getShipping_method());
                }

                ApiKey apiKeyObject = apiKeyDAO.findByApiKeyAndSecretAndActive(api_key,secret,true);

                OpenApiTracking openApiTracking = new OpenApiTracking(tracking,api_key,apiKeyObject.getAccountId(),openApiOrder.getOmniTransactionId());
                openApiTrackingDAO.save(openApiTracking);

                openApiOrder.setOpenApiTrackingId(openApiTracking.getId());
                openApiOrderDAO.save(openApiOrder);

                OpenApiQuote openApiQuote = openApiQuoteDAO.findOne(routeQuoteId);
                if(openApiQuote != null && openApiQuote.getAccountId() > 0) {
                    openApiQuote.setOpenApiTrackingId(openApiTracking.getId());
                    openApiQuoteDAO.save(openApiQuote);
                }
                OmniTransaction omniTransaction = omniTransactionService.openApiTrackingToOmniTransaction(openApiOrder,openApiTracking);
                if(omniTransaction == null) {
                    printHttpReport.emailReport(PrintHttpReport.ReportType.ERROR,response.getStatus(),ipAddress,null,request.getParameterMap(),tracking.toString(),null,"TestOmniTransactionService.testOpenApiTrackingToTestOmniTransaction: NO TestOmniTransaction exists for OpenApiOrder.id: "+openApiOrder.getId());
                } else {
                    printHttpReport.emailReport(PrintHttpReport.ReportType.INFO,response.getStatus(),ipAddress,null,request.getParameterMap(),tracking.toString(),null,"Request sent to Open API update_tracking -- OmniTransaction.id: "+omniTransaction.getId());
                }
            } else if(isTest) {
                TestOpenApiOrder testOpenApiOrder = testOpenApiOrderDAO.findByIdAndTestApiKey(routeOrderId,api_key);
                if(tracking.getShipping_total().compareTo(BigDecimal.ZERO) < 0) {
                    tracking.setShipping_total(BigDecimal.ZERO);
                }
                if(tracking.getShipping_total().compareTo(testOpenApiOrder.getShippingTotal()) < 0) {
                    tracking.setShipping_total(testOpenApiOrder.getShippingTotal());
                } else if (tracking.getShipping_total().compareTo(testOpenApiOrder.getShippingTotal()) > 0) {
                    testOpenApiOrder.setShippingTotal(tracking.getShipping_total());
                }
                if(tracking.getHandling_total().compareTo(BigDecimal.ZERO) < 0) {
                    tracking.setHandling_total(BigDecimal.ZERO);
                }
                if(tracking.getHandling_total().compareTo(testOpenApiOrder.getHandlingTotal()) < 0) {
                    tracking.setHandling_total(testOpenApiOrder.getHandlingTotal());
                } else if (tracking.getHandling_total().compareTo(testOpenApiOrder.getHandlingTotal()) > 0) {
                    testOpenApiOrder.setHandlingTotal(tracking.getHandling_total());
                }
                testOpenApiOrder.setTrackingNumber(tracking.getTracking_number());
                testOpenApiOrder.setShippedDate(tracking.getShipped_date());
                if(tracking.getShipping_carrier_code() != null && courierNameService.isSlug(tracking.getShipping_carrier_code())) {
                    testOpenApiOrder.setShippingCarrierCode(tracking.getShipping_carrier_code());
                }
                if(tracking.getShipping_method() != null && tracking.getShipping_method().isEmpty()) {
                    testOpenApiOrder.setShippingMethod(tracking.getShipping_method());
                }

                ApiKey apiKeyObject = apiKeyDAO.findByTestApiKeyAndTestSecretAndActive(api_key,secret,true);

                TestOpenApiTracking testOpenApiTracking = new TestOpenApiTracking(tracking,api_key,apiKeyObject.getAccountId(),testOpenApiOrder.getTestOmniTransactionId());
                testOpenApiTrackingDAO.save(testOpenApiTracking);

                testOpenApiOrder.setTestOpenApiTrackingId(testOpenApiTracking.getId());
                testOpenApiOrderDAO.save(testOpenApiOrder);

                TestOpenApiQuote testOpenApiQuote = testOpenApiQuoteDAO.findOne(routeQuoteId);
                if(testOpenApiQuote != null && testOpenApiQuote.getAccountId() > 0) {
                    testOpenApiQuote.setTestOpenApiTrackingId(testOpenApiTracking.getId());
                    testOpenApiQuoteDAO.save(testOpenApiQuote);
                }
                TestOmniTransaction testOmniTransaction = testOmniTransactionService.testOpenApiTrackingToTestOmniTransaction(testOpenApiOrder,testOpenApiTracking);
                if(testOmniTransaction == null) {
                    printHttpReport.emailReport(PrintHttpReport.ReportType.ERROR,response.getStatus(),ipAddress,null,request.getParameterMap(),tracking.toString(),null,"TestOmniTransactionService.testOpenApiTrackingToTestOmniTransaction: NO TestOmniTransaction exists for TestOpenApiOrder.id: "+testOpenApiOrder.getId());
                } else {
                    printHttpReport.emailReport(PrintHttpReport.ReportType.INFO,response.getStatus(),ipAddress,null,request.getParameterMap(),tracking.toString(),null,"TEST request sent to Open API update_tracking -- TestOmniTransaction: "+testOmniTransaction.getId());
                }
            }
            return new OpenApiTrackingResponse(tracking);
        }


        errors.add("Detailed API documentation can be found at https://api.routeit.cloud");
        return new OpenApiTrackingResponse(errors);
    }

    private ArrayList<String> checkTracking(Tracking tracking) {
        ArrayList<String> errors = new ArrayList<>();
        if(tracking.getTracking_number() == null || tracking.getTracking_number().isEmpty()) {
            errors.add("Missing required parameter tracking_number.");
        }
        if(tracking.getShipped_date() == null || tracking.getShipped_date().isEmpty()) {
            errors.add("Missing required parameter shipped_date.");
        }
        if(tracking.getShipping_total() == null || tracking.getShipping_total().compareTo(BigDecimal.ZERO) < 0) {
            errors.add("Parameter shipping_total must be zero or greater.");
        }
        if(tracking.getHandling_total() == null || tracking.getHandling_total().compareTo(BigDecimal.ZERO) < 0) {
            errors.add("Parameter handling_total must be zero or greater.");
        }
        if(tracking.getShipping_carrier_code() != null && !tracking.getShipping_carrier_code().isEmpty()) {
            if(!courierNameService.isSlug(tracking.getShipping_carrier_code())) {
                String slugFromName = courierNameService.getCourierSlug(tracking.getShipping_carrier_code());
                if(slugFromName == null) {
                    errors.add("Invalid value for shipping_carrier_code "+tracking.getShipping_carrier_code()+". Find a list of Shipping Carrier Codes at https://api.routeit.cloud/open_api/v1/get/json/shipping_carrier_codes");
                } else {
                    tracking.setShipping_carrier_code(slugFromName);
                }
            }
        }
        return errors;
    }
}
