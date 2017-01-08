package core.entities;

import core.entities.enums.PaymentType;
import core.entities.openApi.Order;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class TestOpenApiOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    @NotNull
    @Column(nullable = false)
    private String testApiKey;

    private long testOpenApiQuoteId;

    private long testOpenApiTrackingId;

    private long testOmniTransactionId;

    @NotNull
    @Column(nullable = false)
    private String orderId;

    @NotNull
    @Column(nullable = false)
    private String orderDate;

    @NotNull
    @Column(nullable = false)
    private boolean insured;

    @NotNull
    @Column(nullable = false)
    private BigDecimal orderBaseValue = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private BigDecimal orderBaseTotal = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private BigDecimal shippingTotal = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private BigDecimal handlingTotal = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private BigDecimal salesTaxTotal = BigDecimal.ZERO;

    private String currencyCode;

    private BigDecimal exchangeRate = BigDecimal.ZERO;

    private BigDecimal insuranceCostUsd = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private BigDecimal customerPaidInsurancePrice = BigDecimal.ZERO;

    private PaymentType paymentType;

    private String paymentTypeOther;

    private String cardLastFour;

    private String cardExpireDate;

    @NotNull
    @Column(nullable = false)
    private String first;

    @NotNull
    @Column(nullable = false)
    private String last;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String phone;

    private String billingAddressOne;

    private String billingAddressTwo;

    private String billingCity;

    private String billingProvince;

    private String billingPostalCode;

    private String billingCountry;

    private boolean billingSameAsShipping;

    @NotNull
    @Column(nullable = false)
    private String shippingAddressOne;

    private String shippingAddressTwo;

    @NotNull
    @Column(nullable = false)
    private String shippingCity;

    @NotNull
    @Column(nullable = false)
    private String shippingProvince;

    @NotNull
    @Column(nullable = false)
    private String shippingPostalCode;

    @NotNull
    @Column(nullable = false)
    private String shippingCountry;

    private String shippingCarrierCode;

    private String shippingMethod;

    private String shippedDate;

    private String trackingNumber;

    private String custom_b1;
    private String custom_b2;
    private String custom_b3;
    private String custom_b4;

    private String createdByIpAddress;

    private LocalDateTime createdByDateTime;

    public TestOpenApiOrder() {
    }

    public TestOpenApiOrder(Order order, String testApiKey, long accountId) {
        this.accountId = accountId;
        this.testApiKey = testApiKey;
        this.testOpenApiQuoteId = order.getRoute_quote_id();
        this.orderId = order.getOrder_id();
        this.orderDate = order.getOrder_date();
        this.insured = order.isOrder_insured();
        this.orderBaseValue = order.getOrder_base_value();
        this.orderBaseTotal = order.getOrder_base_total();
        this.shippingTotal = order.getShipping_total();
        this.handlingTotal = order.getHandling_total();
        this.salesTaxTotal = order.getSales_tax_total();
        this.currencyCode = order.getCurrency_code();
        this.exchangeRate = order.getExchange_rate();
        this.insuranceCostUsd = order.getInsurance_cost_usd();
        this.customerPaidInsurancePrice = order.getCustomer_paid_insurance_price();
        this.paymentType = PaymentType.valueOf(order.getPayment_type());
        this.paymentTypeOther = order.getPayment_type_other();
        this.cardLastFour = order.getCard_last_four();
        this.cardExpireDate = order.getCard_expire_date();
        this.first = order.getFirst_name();
        this.last = order.getLast_name();
        this.email = order.getEmail();
        this.phone = order.getPhone();
        this.shippingAddressOne = order.getShipping_address_1();
        this.shippingAddressTwo = order.getShipping_address_2();
        this.shippingCity = order.getShipping_city();
        this.shippingProvince = order.getShipping_province();
        this.shippingPostalCode = order.getShipping_postal_code();
        this.shippingCountry = order.getShipping_country();
        this.shippingCarrierCode = order.getShipping_carrier_code();
        this.shippingMethod = order.getShipping_method();
        this.shippedDate = order.getShipped_date();
        this.trackingNumber = order.getTracking_number();
        this.custom_b1 = order.getCustom_b1();
        this.custom_b2 = order.getCustom_b2();
        this.custom_b3 = order.getCustom_b3();
        this.custom_b4 = order.getCustom_b4();
        this.createdByIpAddress = order.getIp_address();

        this.billingSameAsShipping = order.isBilling_same_as_shipping();
        if(order.isBilling_same_as_shipping()) {
            this.billingAddressOne = order.getShipping_address_1();
            this.billingAddressTwo = order.getShipping_address_2();
            this.billingCity = order.getShipping_city();
            this.billingProvince = order.getShipping_province();
            this.billingPostalCode = order.getShipping_postal_code();
            this.billingCountry = order.getShipping_country();
        } else {
            this.billingAddressOne = order.getBilling_address_1();
            this.billingAddressTwo = order.getBilling_address_2();
            this.billingCity = order.getBilling_city();
            this.billingProvince = order.getBilling_province();
            this.billingPostalCode = order.getBilling_postal_code();
            this.billingCountry = order.getBilling_country();
        }
        this.createdByDateTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getTestApiKey() {
        return testApiKey;
    }

    public void setTestApiKey(String testApiKey) {
        this.testApiKey = testApiKey;
    }

    public long getTestOpenApiQuoteId() {
        return testOpenApiQuoteId;
    }

    public void setTestOpenApiQuoteId(long testOpenApiQuoteId) {
        this.testOpenApiQuoteId = testOpenApiQuoteId;
    }

    public long getTestOpenApiTrackingId() {
        return testOpenApiTrackingId;
    }

    public void setTestOpenApiTrackingId(long testOpenApiTrackingId) {
        this.testOpenApiTrackingId = testOpenApiTrackingId;
    }

    public long getTestOmniTransactionId() {
        return testOmniTransactionId;
    }

    public void setTestOmniTransactionId(long testOmniTransactionId) {
        this.testOmniTransactionId = testOmniTransactionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isInsured() {
        return insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    public BigDecimal getOrderBaseValue() {
        return orderBaseValue;
    }

    public void setOrderBaseValue(BigDecimal orderBaseValue) {
        this.orderBaseValue = orderBaseValue;
    }

    public BigDecimal getOrderBaseTotal() {
        return orderBaseTotal;
    }

    public void setOrderBaseTotal(BigDecimal orderBaseTotal) {
        this.orderBaseTotal = orderBaseTotal;
    }

    public BigDecimal getShippingTotal() {
        return shippingTotal;
    }

    public void setShippingTotal(BigDecimal shippingTotal) {
        this.shippingTotal = shippingTotal;
    }

    public BigDecimal getHandlingTotal() {
        return handlingTotal;
    }

    public void setHandlingTotal(BigDecimal handlingTotal) {
        this.handlingTotal = handlingTotal;
    }

    public BigDecimal getSalesTaxTotal() {
        return salesTaxTotal;
    }

    public void setSalesTaxTotal(BigDecimal salesTaxTotal) {
        this.salesTaxTotal = salesTaxTotal;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getInsuranceCostUsd() {
        return insuranceCostUsd;
    }

    public void setInsuranceCostUsd(BigDecimal insuranceCostUsd) {
        this.insuranceCostUsd = insuranceCostUsd;
    }

    public BigDecimal getCustomerPaidInsurancePrice() {
        return customerPaidInsurancePrice;
    }

    public void setCustomerPaidInsurancePrice(BigDecimal customerPaidInsurancePrice) {
        this.customerPaidInsurancePrice = customerPaidInsurancePrice;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeOther() {
        return paymentTypeOther;
    }

    public void setPaymentTypeOther(String paymentTypeOther) {
        this.paymentTypeOther = paymentTypeOther;
    }

    public String getCardLastFour() {
        return cardLastFour;
    }

    public void setCardLastFour(String cardLastFour) {
        this.cardLastFour = cardLastFour;
    }

    public String getCardExpireDate() {
        return cardExpireDate;
    }

    public void setCardExpireDate(String cardExpireDate) {
        this.cardExpireDate = cardExpireDate;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBillingAddressOne() {
        return billingAddressOne;
    }

    public void setBillingAddressOne(String billingAddressOne) {
        this.billingAddressOne = billingAddressOne;
    }

    public String getBillingAddressTwo() {
        return billingAddressTwo;
    }

    public void setBillingAddressTwo(String billingAddressTwo) {
        this.billingAddressTwo = billingAddressTwo;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingProvince() {
        return billingProvince;
    }

    public void setBillingProvince(String billingProvince) {
        this.billingProvince = billingProvince;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public boolean isBillingSameAsShipping() {
        return billingSameAsShipping;
    }

    public void setBillingSameAsShipping(boolean billingSameAsShipping) {
        this.billingSameAsShipping = billingSameAsShipping;
    }

    public String getShippingAddressOne() {
        return shippingAddressOne;
    }

    public void setShippingAddressOne(String shippingAddressOne) {
        this.shippingAddressOne = shippingAddressOne;
    }

    public String getShippingAddressTwo() {
        return shippingAddressTwo;
    }

    public void setShippingAddressTwo(String shippingAddressTwo) {
        this.shippingAddressTwo = shippingAddressTwo;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingProvince() {
        return shippingProvince;
    }

    public void setShippingProvince(String shippingProvince) {
        this.shippingProvince = shippingProvince;
    }

    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingCarrierCode() {
        return shippingCarrierCode;
    }

    public void setShippingCarrierCode(String shippingCarrierCode) {
        this.shippingCarrierCode = shippingCarrierCode;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getCustom_b1() {
        return custom_b1;
    }

    public void setCustom_b1(String custom_b1) {
        this.custom_b1 = custom_b1;
    }

    public String getCustom_b2() {
        return custom_b2;
    }

    public void setCustom_b2(String custom_b2) {
        this.custom_b2 = custom_b2;
    }

    public String getCustom_b3() {
        return custom_b3;
    }

    public void setCustom_b3(String custom_b3) {
        this.custom_b3 = custom_b3;
    }

    public String getCustom_b4() {
        return custom_b4;
    }

    public void setCustom_b4(String custom_b4) {
        this.custom_b4 = custom_b4;
    }

    public String getCreatedByIpAddress() {
        return createdByIpAddress;
    }

    public void setCreatedByIpAddress(String createdByIpAddress) {
        this.createdByIpAddress = createdByIpAddress;
    }

    public LocalDateTime getCreatedByDateTime() {
        return createdByDateTime;
    }

    public void setCreatedByDateTime(LocalDateTime createdByDateTime) {
        this.createdByDateTime = createdByDateTime;
    }
}
