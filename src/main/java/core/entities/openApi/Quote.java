package core.entities.openApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    private long route_quote_id;
    private BigDecimal order_base_value = BigDecimal.ZERO;                  // REQUIRED
    private BigDecimal order_base_total = BigDecimal.ZERO;                  // REQUIRED
    private String order_currency_code;                                     // REQUIRED
    private String order_type;                                              // REQUIRED
    private BigDecimal insurance_cost_usd = BigDecimal.ZERO;
    private BigDecimal suggested_insurance_price = BigDecimal.ZERO;
    private String suggested_insurance_currency;
    private BigDecimal exchange_rate = BigDecimal.ZERO;
    private int total_items;                                                // REQUIRED
    private ArrayList<Item> items;                                          // REQUIRED
    private String custom_a1;
    private String custom_a2;
    private String custom_a3;
    private String custom_a4;
    private String ip_address;
    private Timestamp created;

    public Quote() {
    }

    public long getRoute_quote_id() {
        return route_quote_id;
    }

    public void setRoute_quote_id(long route_quote_id) {
        this.route_quote_id = route_quote_id;
    }

    public BigDecimal getOrder_base_value() {
        return order_base_value;
    }

    public void setOrder_base_value(BigDecimal order_base_value) {
        this.order_base_value = order_base_value;
    }

    public BigDecimal getOrder_base_total() {
        return order_base_total;
    }

    public void setOrder_base_total(BigDecimal order_base_total) {
        this.order_base_total = order_base_total;
    }

    public String getOrder_currency_code() {
        return order_currency_code;
    }

    public void setOrder_currency_code(String order_currency_code) {
        this.order_currency_code = order_currency_code;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public BigDecimal getInsurance_cost_usd() {
        return insurance_cost_usd;
    }

    public void setInsurance_cost_usd(BigDecimal insurance_cost_usd) {
        this.insurance_cost_usd = insurance_cost_usd;
    }

    public BigDecimal getSuggested_insurance_price() {
        return suggested_insurance_price;
    }

    public void setSuggested_insurance_price(BigDecimal suggested_insurance_price) {
        this.suggested_insurance_price = suggested_insurance_price;
    }

    public String getSuggested_insurance_currency() {
        return suggested_insurance_currency;
    }

    public void setSuggested_insurance_currency(String suggested_insurance_currency) {
        this.suggested_insurance_currency = suggested_insurance_currency;
    }

    public BigDecimal getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(BigDecimal exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public int getTotal_items() {
        return total_items;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getCustom_a1() {
        return custom_a1;
    }

    public void setCustom_a1(String custom_a1) {
        this.custom_a1 = custom_a1;
    }

    public String getCustom_a2() {
        return custom_a2;
    }

    public void setCustom_a2(String custom_a2) {
        this.custom_a2 = custom_a2;
    }

    public String getCustom_a3() {
        return custom_a3;
    }

    public void setCustom_a3(String custom_a3) {
        this.custom_a3 = custom_a3;
    }

    public String getCustom_a4() {
        return custom_a4;
    }

    public void setCustom_a4(String custom_a4) {
        this.custom_a4 = custom_a4;
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
        return "Quote{" +
                "route_quote_id=" + route_quote_id +
                ", order_base_value=" + order_base_value +
                ", order_base_total=" + order_base_total +
                ", order_currency_code='" + order_currency_code + '\'' +
                ", order_type='" + order_type + '\'' +
                ", insurance_cost_usd=" + insurance_cost_usd +
                ", suggested_insurance_price=" + suggested_insurance_price +
                ", suggested_insurance_currency='" + suggested_insurance_currency + '\'' +
                ", exchange_rate=" + exchange_rate +
                ", total_items=" + total_items +
                ", items=" + items +
                ", custom_a1='" + custom_a1 + '\'' +
                ", custom_a2='" + custom_a2 + '\'' +
                ", custom_a3='" + custom_a3 + '\'' +
                ", custom_a4='" + custom_a4 + '\'' +
                ", ip_address='" + ip_address + '\'' +
                ", created=" + created +
                '}';
    }
}
