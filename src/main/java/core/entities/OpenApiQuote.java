package core.entities;

import core.entities.enums.OrderType;
import core.entities.openApi.Quote;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class OpenApiQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    @NotNull
    @Column(nullable = false)
    private String apiKey;

    @NotNull
    @Column(nullable = false)
    private String widgetKey;

    private long openApiOrderId;

    private long openApiTrackingId;

    private long omniTransactionId;

    @NotNull
    @Column(nullable = false)
    private BigDecimal orderBaseValue = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private BigDecimal orderBaseTotal = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private String orderCurrencyCode;

    @NotNull
    @Column(nullable = false)
    private OrderType orderType;

    @NotNull
    @Column(nullable = false)
    private BigDecimal insuranceCostUsd = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private BigDecimal suggestedInsurancePrice = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private String suggestedInsuranceCurrency;

    @NotNull
    @Column(nullable = false)
    private BigDecimal exchangeRate = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private int totalItems;

    private String customA1;
    private String customA2;
    private String customA3;
    private String customA4;
    private String overridePrice;
    private int attemptedWidgetDisplayTries;

    private String createdByIpAddress;

    private LocalDateTime createdByDateTime;

    public OpenApiQuote() {
    }

    public OpenApiQuote(Quote quote,String apiKey, String widgetKey, long accountId) {
        this.apiKey = apiKey;
        this.widgetKey = widgetKey;
        this.accountId = accountId;
        this.orderBaseValue = quote.getOrder_base_value();
        this.orderBaseTotal = quote.getOrder_base_total();
        this.orderCurrencyCode = quote.getOrder_currency_code();
        this.orderType = OrderType.UNKNOWN;
        try {
            this.orderType = OrderType.valueOf(quote.getOrder_type());
        } catch (Exception e) {

        }
        this.insuranceCostUsd = quote.getInsurance_cost_usd();
        this.suggestedInsurancePrice = quote.getSuggested_insurance_price();
        this.suggestedInsuranceCurrency = quote.getSuggested_insurance_currency();
        this.exchangeRate = quote.getExchange_rate();
        this.totalItems = quote.getTotal_items();
        this.customA1 = quote.getCustom_a1();
        this.customA2 = quote.getCustom_a2();
        this.customA3 = quote.getCustom_a3();
        this.customA4 = quote.getCustom_a4();
        this.createdByIpAddress = quote.getIp_address();
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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getWidgetKey() {
        return widgetKey;
    }

    public void setWidgetKey(String widgetKey) {
        this.widgetKey = widgetKey;
    }

    public long getOpenApiOrderId() {
        return openApiOrderId;
    }

    public void setOpenApiOrderId(long openApiOrderId) {
        this.openApiOrderId = openApiOrderId;
    }

    public long getOpenApiTrackingId() {
        return openApiTrackingId;
    }

    public void setOpenApiTrackingId(long openApiTrackingId) {
        this.openApiTrackingId = openApiTrackingId;
    }

    public long getOmniTransactionId() {
        return omniTransactionId;
    }

    public void setOmniTransactionId(long omniTransactionId) {
        this.omniTransactionId = omniTransactionId;
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

    public String getOrderCurrencyCode() {
        return orderCurrencyCode;
    }

    public void setOrderCurrencyCode(String orderCurrencyCode) {
        this.orderCurrencyCode = orderCurrencyCode;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getInsuranceCostUsd() {
        return insuranceCostUsd;
    }

    public void setInsuranceCostUsd(BigDecimal insuranceCostUsd) {
        this.insuranceCostUsd = insuranceCostUsd;
    }

    public BigDecimal getSuggestedInsurancePrice() {
        return suggestedInsurancePrice;
    }

    public void setSuggestedInsurancePrice(BigDecimal suggestedInsurancePrice) {
        this.suggestedInsurancePrice = suggestedInsurancePrice;
    }

    public String getSuggestedInsuranceCurrency() {
        return suggestedInsuranceCurrency;
    }

    public void setSuggestedInsuranceCurrency(String suggestedInsuranceCurrency) {
        this.suggestedInsuranceCurrency = suggestedInsuranceCurrency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String getCustomA1() {
        return customA1;
    }

    public void setCustomA1(String customA1) {
        this.customA1 = customA1;
    }

    public String getCustomA2() {
        return customA2;
    }

    public void setCustomA2(String customA2) {
        this.customA2 = customA2;
    }

    public String getCustomA3() {
        return customA3;
    }

    public void setCustomA3(String customA3) {
        this.customA3 = customA3;
    }

    public String getCustomA4() {
        return customA4;
    }

    public void setCustomA4(String customA4) {
        this.customA4 = customA4;
    }

    public String getOverridePrice() {
        return overridePrice;
    }

    public void setOverridePrice(String overridePrice) {
        this.overridePrice = overridePrice;
    }

    public int getAttemptedWidgetDisplayTries() {
        return attemptedWidgetDisplayTries;
    }

    public void setAttemptedWidgetDisplayTries(int attemptedWidgetDisplayTries) {
        this.attemptedWidgetDisplayTries = attemptedWidgetDisplayTries;
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
