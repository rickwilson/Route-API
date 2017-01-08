package core.controllers;

import core.entities.*;
import core.services.CurrencyService;
import core.util.CheckData;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping(value="/widget/v2/")
public class WidgetController {

    private final OpenApiQuoteDAO openApiQuoteDAO;
    private final ApiKeyDAO apiKeyDAO;
    private final OpenApiOrderDAO openApiOrderDAO;
    private final CurrencyService currencyService;

    public WidgetController(OpenApiQuoteDAO openApiQuoteDAO,
                            ApiKeyDAO apiKeyDAO,
                            OpenApiOrderDAO openApiOrderDAO,
                            CurrencyService currencyService) {
        Assert.notNull(openApiQuoteDAO, "OpenApiQuoteDAO must not be null!");
        Assert.notNull(apiKeyDAO, "ApiKeyDAO must not be null!");
        Assert.notNull(openApiOrderDAO, "OpenApiOrderDAO must not be null!");
        Assert.notNull(currencyService, "CurrencyService must not be null!");
        this.openApiQuoteDAO = openApiQuoteDAO;
        this.apiKeyDAO = apiKeyDAO;
        this.openApiOrderDAO = openApiOrderDAO;
        this.currencyService = currencyService;
    }

    @RequestMapping(value = "/{widget_key}", produces = "text/javascript")
    @ResponseBody
    public String widgetIndex(@PathVariable("widget_key") String token, @RequestParam(defaultValue = "") String quote_id, @RequestParam(defaultValue = "") String override_display_price) {
        ArrayList<String> errors = new ArrayList<>();
        String insurancePrice = "$TEST";
        if(token != null && token.trim().length() > 30 && (token.startsWith("LIVE_wk_") || token.startsWith("test_wk_"))) {
            ApiKey apiKeyObject = apiKeyDAO.findByWidgetKeyOrTestWidgetKey(token,token);
            if(apiKeyObject == null || apiKeyObject.getAccountId() < 1) {
                errors.add("Route Widget must include a valid widget_key.");
            } else {
                if(token.startsWith("LIVE_wk_") && apiKeyObject.getBillingId() < 1) {
                    errors.add("No billing setup for these keys. Live API requests require valid billing. If this is a test, use the test widget key.");
                }
            }
        } else {
            errors.add("Route Widget must include a valid widget_key.");
        }
        if(errors.isEmpty() && quote_id != null && quote_id.equalsIgnoreCase("test")) {
            if(errors.isEmpty() && override_display_price !=null && override_display_price.trim().length() > 0) {
                insurancePrice = override_display_price+" - TEST";
            }
        }
        if(quote_id == null || quote_id.trim().length() < 1 || !CheckData.isNumbersOnly(quote_id)) {
            errors.add("Route Widget must include a valid quote_id.");
        }
        if(errors.isEmpty()) {
            OpenApiQuote openApiQuote = openApiQuoteDAO.findByIdAndWidgetKey(Long.parseLong(quote_id),token);
            if(openApiQuote == null || openApiQuote.getAccountId() < 1) {
                errors.add("No Route records exist for the provided quote_id and widget_key.");
            } else {
                openApiQuote.setAttemptedWidgetDisplayTries(openApiQuote.getAttemptedWidgetDisplayTries()+1);
                insurancePrice = currencyService.formatCurrencyForCurrencyCode(openApiQuote.getSuggestedInsurancePrice(),openApiQuote.getSuggestedInsuranceCurrency());
                LocalDateTime thirtySixHoursAgo = LocalDateTime.now();
                thirtySixHoursAgo = thirtySixHoursAgo.minusHours(36);
                if(openApiQuote.getCreatedByDateTime().isAfter(thirtySixHoursAgo)) {
                    OpenApiOrder openApiOrder = openApiOrderDAO.findByOpenApiQuoteIdAndApiKey(openApiQuote.getId(), openApiQuote.getApiKey());
                    if(openApiOrder != null && openApiOrder.getAccountId() > 1) {
                        errors.add("The provided quote_id ("+quote_id+") has already been used. Quotes can only be applied to a single order.");
                    }
                    if(errors.isEmpty() && override_display_price !=null && override_display_price.trim().length() > 0) {
                        openApiQuote.setOverridePrice(override_display_price);
                        insurancePrice = override_display_price;
                    }
                } else {
                    errors.add("The provided quote_id ("+quote_id+") has expired. Quotes must be used within 24 hours.");
                }
                openApiQuoteDAO.save(openApiQuote);
            }
        }
        if(errors.isEmpty()) {
            return "routeWidgetJSON({\"html\":\"<span style=\\\"font-size:11px;position:relative;color:#000;\\\"><img src=\\\"https://d1pv9ulu41r3n5.cloudfront.net/infobox.png\\\" alt=\\\"route\\\" id=\\\"routePopup\\\" style=\\\"width:382px;position:absolute;bottom:27px;z-index:100;border:1px solid #3a3a3a ; display:none;\\\"><img src=\\\"https://d1pv9ulu41r3n5.cloudfront.net/logoIcon.gif\\\" alt=\\\"route\\\" style=\\\"width:64px;height:16px;vertical-align:text-bottom; margin-right:4px;\\\" onmouseover=\\\"document.getElementById('routePopup').style.display='block';\\\"  onMouseOut=\\\"document.getElementById('routePopup').style.display='none';\\\">(Accept <input type=\\\"checkbox\\\" id=\\\"routeInsureOrder\\\" name=\\\"routeInsureOrder\\\" value=\\\"on\\\" checked style=\\\"position: relative;top:1px;\\\"> ) Shipping Protection "+insurancePrice+"</span>\"})";
        }
        String printedError = "";
        for(String longError: errors) {
            printedError = printedError+"<br>"+longError;
        }
        return "routeWidgetJSON({\"html\":\"<span style=\\\"font-size:11px;position:relative;color:#000;\\\">"+printedError+"</span>\"})";
    }

    @RequestMapping(value = "konnektive/{widget_key}", produces = "text/javascript")
    @ResponseBody
    public String widgetKonnektive(@PathVariable("widget_key") String token, @RequestParam(defaultValue = "") String quote_id, @RequestParam(defaultValue = "") String override_display_price) {
        return "routeWidgetJSON({\"html\":\"<span style=\\\"font-size:11px;position:relative;color:#000;\\\"><img src=\\\"https://d1pv9ulu41r3n5.cloudfront.net/infobox.png\\\" alt=\\\"route\\\" id=\\\"routePopup\\\" style=\\\"width:382px;position:absolute;bottom:27px;z-index:100;border:1px solid #3a3a3a ; display:none;\\\"><img src=\\\"https://d1pv9ulu41r3n5.cloudfront.net/logoIcon.gif\\\" alt=\\\"route\\\" style=\\\"width:64px;height:16px;vertical-align:text-bottom; margin-right:4px;\\\" onmouseover=\\\"document.getElementById('routePopup').style.display='block';\\\"  onMouseOut=\\\"document.getElementById('routePopup').style.display='none';\\\">(Accept <input type=\\\"checkbox\\\" id=\\\"routeDecline\\\" name=\\\"routeDecline\\\" value=\\\"on\\\" checked onclick=\\\"document.getElementById('insureShipment').value = !this.checked ? '' : 'YES'\\\" style=\\\"position: relative;top:3px;\\\"> ) Shipping Protection $3.97<span style=\\\"position:relative;font-size:9px; display:block; margin-top: 2px;\\\">*billed separately</span></span><input type=\\\"hidden\\\" name=\\\"insureShipment\\\" id=\\\"insureShipment\\\" value=\\\"YES\\\">\"})";
    }
}
