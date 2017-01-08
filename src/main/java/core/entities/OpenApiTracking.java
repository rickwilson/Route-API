package core.entities;

import core.entities.openApi.Tracking;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class OpenApiTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    @NotNull
    @Column(nullable = false)
    private String apiKey;

    private long openApiQuoteId;

    private long openApiOrderId;

    private long omniTransactionId;

    private BigDecimal shipping_total = BigDecimal.ZERO;
    private BigDecimal handling_total = BigDecimal.ZERO;
    private String shipping_carrier_code;
    private String shipping_method;

    @NotNull
    @Column(nullable = false)
    private String shipped_date;                            // REQUIRED

    @NotNull
    @Column(nullable = false)
    private String tracking_number;                         // REQUIRED
    private String custom_c1;
    private String custom_c2;
    private String custom_c3;
    private String custom_c4;

    private String createdByIpAddress;

    private LocalDateTime createdByDateTime;

    public OpenApiTracking() {
    }

    public OpenApiTracking(Tracking tracking, String apiKey, long accountId, long omniTransactionId) {
        this.accountId = accountId;
        this.apiKey = apiKey;
        this.omniTransactionId = omniTransactionId;
        this.openApiQuoteId = tracking.getRoute_quote_id();
        this.openApiOrderId = tracking.getRoute_order_id();
        this.shipping_total = tracking.getShipping_total();
        this.handling_total = tracking.getHandling_total();
        this.shipping_carrier_code = tracking.getShipping_carrier_code();
        this.shipping_method = tracking.getShipping_method();
        this.shipped_date = tracking.getShipped_date();
        this.tracking_number = tracking.getTracking_number();
        this.custom_c1 = tracking.getCustom_c1();
        this.custom_c2 = tracking.getCustom_c2();
        this.custom_c3 = tracking.getCustom_c3();
        this.custom_c4 = tracking.getCustom_c4();
        this.createdByIpAddress = tracking.getIp_address();
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

    public long getOpenApiQuoteId() {
        return openApiQuoteId;
    }

    public void setOpenApiQuoteId(long openApiQuoteId) {
        this.openApiQuoteId = openApiQuoteId;
    }

    public long getOpenApiOrderId() {
        return openApiOrderId;
    }

    public void setOpenApiOrderId(long openApiOrderId) {
        this.openApiOrderId = openApiOrderId;
    }

    public long getOmniTransactionId() {
        return omniTransactionId;
    }

    public void setOmniTransactionId(long omniTransactionId) {
        this.omniTransactionId = omniTransactionId;
    }

    public BigDecimal getShipping_total() {
        return shipping_total;
    }

    public void setShipping_total(BigDecimal shipping_total) {
        this.shipping_total = shipping_total;
    }

    public BigDecimal getHandling_total() {
        return handling_total;
    }

    public void setHandling_total(BigDecimal handling_total) {
        this.handling_total = handling_total;
    }

    public String getShipping_carrier_code() {
        return shipping_carrier_code;
    }

    public void setShipping_carrier_code(String shipping_carrier_code) {
        this.shipping_carrier_code = shipping_carrier_code;
    }

    public String getShipping_method() {
        return shipping_method;
    }

    public void setShipping_method(String shipping_method) {
        this.shipping_method = shipping_method;
    }

    public String getShipped_date() {
        return shipped_date;
    }

    public void setShipped_date(String shipped_date) {
        this.shipped_date = shipped_date;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public String getCustom_c1() {
        return custom_c1;
    }

    public void setCustom_c1(String custom_c1) {
        this.custom_c1 = custom_c1;
    }

    public String getCustom_c2() {
        return custom_c2;
    }

    public void setCustom_c2(String custom_c2) {
        this.custom_c2 = custom_c2;
    }

    public String getCustom_c3() {
        return custom_c3;
    }

    public void setCustom_c3(String custom_c3) {
        this.custom_c3 = custom_c3;
    }

    public String getCustom_c4() {
        return custom_c4;
    }

    public void setCustom_c4(String custom_c4) {
        this.custom_c4 = custom_c4;
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
