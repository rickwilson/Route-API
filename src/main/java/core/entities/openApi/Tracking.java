package core.entities.openApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracking {
    private long route_order_id;                            // MAYBE
    private long route_quote_id;                            // MAYBE
    private BigDecimal shipping_total = BigDecimal.ZERO;    // REQUIRED
    private BigDecimal handling_total = BigDecimal.ZERO;    // REQUIRED
    private String shipping_carrier_code;
    private String shipping_method;
    private String shipped_date;                            // REQUIRED
    private String tracking_number;                         // REQUIRED
    private String custom_c1;
    private String custom_c2;
    private String custom_c3;
    private String custom_c4;
    private String ip_address;
    private Timestamp created;

    public Tracking() {
    }

    public long getRoute_order_id() {
        return route_order_id;
    }

    public void setRoute_order_id(long route_order_id) {
        this.route_order_id = route_order_id;
    }

    public long getRoute_quote_id() {
        return route_quote_id;
    }

    public void setRoute_quote_id(long route_quote_id) {
        this.route_quote_id = route_quote_id;
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

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "route_order_id=" + route_order_id +
                ", route_quote_id=" + route_quote_id +
                ", shipping_total=" + shipping_total +
                ", handling_total=" + handling_total +
                ", shipping_carrier_code='" + shipping_carrier_code + '\'' +
                ", shipping_method='" + shipping_method + '\'' +
                ", shipped_date='" + shipped_date + '\'' +
                ", tracking_number='" + tracking_number + '\'' +
                ", custom_c1='" + custom_c1 + '\'' +
                ", custom_c2='" + custom_c2 + '\'' +
                ", custom_c3='" + custom_c3 + '\'' +
                ", custom_c4='" + custom_c4 + '\'' +
                ", ip_address='" + ip_address + '\'' +
                ", created=" + created +
                '}';
    }
}
