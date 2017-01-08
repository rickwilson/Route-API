package core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/docs/")
public class DocsController {

    @RequestMapping(value = "/")
    public String docsIndex() {
        return "docs/getting-started";
    }

    @RequestMapping(value = {"api","/api/overview"})
    public String apiIndex() {
        return "docs/api/overview";
    }

    @RequestMapping(value = "api/request/quote")
    public String apiRequestQuote() {
        return "docs/api/requestQuote";
    }

    @RequestMapping(value = "api/insure/order")
    public String apiInsureOrder() {
        return "docs/api/insureOrder";
    }

    @RequestMapping(value = "api/update/tracking")
    public String apiUpdateTracking() {
        return "docs/api/updateTracking";
    }

    @RequestMapping(value = "api/widget")
    public String apiCheckoutWidget() {
        return "docs/api/widget";
    }

    @RequestMapping(value = "api/get/codes/currency")
    public String apiGetCurrencyCodes() {
        return "docs/api/currencyCodes";
    }

    @RequestMapping(value = "api/get/codes/shipping/carriers")
    public String apiGetShippingCarrierCodes() {
        return "docs/api/shippingCarrierCodes";
    }

    @RequestMapping(value = "api/get/types/order")
    public String apiGetOrderTypes() {
        return "docs/api/orderTypes";
    }

    @RequestMapping(value = "api/get/types/payment")
    public String apiGetPaymentTypes() {
        return "docs/api/paymentTypes";
    }

    @RequestMapping(value = "api/konnektive/add/account")
    public String apiKonnektiveAddAccount() {
        return "docs/api/konnektiveNewAccountApi";
    }

    @RequestMapping(value = "integration/konnektive")
    public String integrationKonnektive() {
        return "docs/integration/konnektive";
    }

    @RequestMapping(value = "integration/limelight")
    public String integrationlimelight() {
        return "docs/integration/limelight";
    }
}
