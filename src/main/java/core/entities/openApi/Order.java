package core.entities.openApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import core.entities.enums.OrderType;
import core.entities.enums.PaymentType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private long route_order_id;
    private long route_quote_id;                        // REQUIRED
    private String order_id;                            // REQUIRED
    private String order_date;                          // REQUIRED
    private OrderType order_type;
    private boolean order_insured;                      // REQUIRED
    private BigDecimal order_base_value = BigDecimal.ZERO;                // REQUIRED
    private BigDecimal order_base_total = BigDecimal.ZERO;                // REQUIRED
    private BigDecimal shipping_total = BigDecimal.ZERO;                  // REQUIRED
    private BigDecimal handling_total = BigDecimal.ZERO;                  // REQUIRED
    private BigDecimal sales_tax_total = BigDecimal.ZERO;                 // REQUIRED
    private String currency_code;                       // REQUIRED
    private BigDecimal exchange_rate = BigDecimal.ZERO;
    private BigDecimal insurance_cost_usd = BigDecimal.ZERO;
    private BigDecimal customer_paid_insurance_price = BigDecimal.ZERO;   // REQUIRED
    private String payment_type;                        // REQUIRED     // TODO: ENUM
    private String payment_type_other;
    private String card_last_four;                      // MAYBE
    private String card_expire_date;                    // MAYBE
    private String first_name;                          // REQUIRED
    private String last_name;                           // REQUIRED
    private String email;                               // REQUIRED
    private String phone;                               // REQUIRED
    private String billing_address_1;                   // MAYBE
    private String billing_address_2;                   // MAYBE
    private String billing_city;                        // MAYBE
    private String billing_province;                    // MAYBE
    private String billing_postal_code;                 // MAYBE
    private String billing_country;                     // MAYBE
    private boolean billing_same_as_shipping;           // REQUIRED
    private String shipping_address_1;                  // REQUIRED
    private String shipping_address_2;                  // REQUIRED
    private String shipping_city;                       // REQUIRED
    private String shipping_province;                   // REQUIRED
    private String shipping_postal_code;                // REQUIRED
    private String shipping_country;                    // REQUIRED
    private String shipping_carrier_code;
    private String shipping_method;
    private String shipped_date;
    private String tracking_number;
    private ArrayList<Item> items;

    private String custom_b1;
    private String custom_b2;
    private String custom_b3;
    private String custom_b4;
    private String ip_address;
    private Timestamp created;

    public Order() {
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

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public OrderType getOrder_type() {
        return order_type;
    }

    public void setOrder_type(OrderType order_type) {
        this.order_type = order_type;
    }

    public boolean isOrder_insured() {
        return order_insured;
    }

    public void setOrder_insured(boolean order_insured) {
        this.order_insured = order_insured;
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

    public BigDecimal getSales_tax_total() {
        return sales_tax_total;
    }

    public void setSales_tax_total(BigDecimal sales_tax_total) {
        this.sales_tax_total = sales_tax_total;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public BigDecimal getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(BigDecimal exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public BigDecimal getInsurance_cost_usd() {
        return insurance_cost_usd;
    }

    public void setInsurance_cost_usd(BigDecimal insurance_cost_usd) {
        this.insurance_cost_usd = insurance_cost_usd;
    }

    public BigDecimal getCustomer_paid_insurance_price() {
        return customer_paid_insurance_price;
    }

    public void setCustomer_paid_insurance_price(BigDecimal customer_paid_insurance_price) {
        this.customer_paid_insurance_price = customer_paid_insurance_price;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPayment_type_other() {
        return payment_type_other;
    }

    public void setPayment_type_other(String payment_type_other) {
        this.payment_type_other = payment_type_other;
    }

    public String getCard_last_four() {
        return card_last_four;
    }

    public void setCard_last_four(String card_last_four) {
        this.card_last_four = card_last_four;
    }

    public String getCard_expire_date() {
        return card_expire_date;
    }

    public void setCard_expire_date(String card_expire_date) {
        this.card_expire_date = card_expire_date;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getBilling_address_1() {
        return billing_address_1;
    }

    public void setBilling_address_1(String billing_address_1) {
        this.billing_address_1 = billing_address_1;
    }

    public String getBilling_address_2() {
        return billing_address_2;
    }

    public void setBilling_address_2(String billing_address_2) {
        this.billing_address_2 = billing_address_2;
    }

    public String getBilling_city() {
        return billing_city;
    }

    public void setBilling_city(String billing_city) {
        this.billing_city = billing_city;
    }

    public String getBilling_province() {
        return billing_province;
    }

    public void setBilling_province(String billing_province) {
        this.billing_province = billing_province;
    }

    public String getBilling_postal_code() {
        return billing_postal_code;
    }

    public void setBilling_postal_code(String billing_postal_code) {
        this.billing_postal_code = billing_postal_code;
    }

    public String getBilling_country() {
        return billing_country;
    }

    public void setBilling_country(String billing_country) {
        this.billing_country = billing_country;
    }

    public boolean isBilling_same_as_shipping() {
        return billing_same_as_shipping;
    }

    public void setBilling_same_as_shipping(boolean billing_same_as_shipping) {
        this.billing_same_as_shipping = billing_same_as_shipping;
    }

    public String getShipping_address_1() {
        return shipping_address_1;
    }

    public void setShipping_address_1(String shipping_address_1) {
        this.shipping_address_1 = shipping_address_1;
    }

    public String getShipping_address_2() {
        return shipping_address_2;
    }

    public void setShipping_address_2(String shipping_address_2) {
        this.shipping_address_2 = shipping_address_2;
    }

    public String getShipping_city() {
        return shipping_city;
    }

    public void setShipping_city(String shipping_city) {
        this.shipping_city = shipping_city;
    }

    public String getShipping_province() {
        return shipping_province;
    }

    public void setShipping_province(String shipping_province) {
        this.shipping_province = shipping_province;
    }

    public String getShipping_postal_code() {
        return shipping_postal_code;
    }

    public void setShipping_postal_code(String shipping_postal_code) {
        this.shipping_postal_code = shipping_postal_code;
    }

    public String getShipping_country() {
        return shipping_country;
    }

    public void setShipping_country(String shipping_country) {
        this.shipping_country = shipping_country;
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
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
        return "Order{" +
                "route_order_id=" + route_order_id +
                ", route_quote_id=" + route_quote_id +
                ", order_id='" + order_id + '\'' +
                ", order_date='" + order_date + '\'' +
                ", order_type=" + order_type +
                ", order_insured=" + order_insured +
                ", order_base_value=" + order_base_value +
                ", order_base_total=" + order_base_total +
                ", shipping_total=" + shipping_total +
                ", handling_total=" + handling_total +
                ", sales_tax_total=" + sales_tax_total +
                ", currency_code='" + currency_code + '\'' +
                ", exchange_rate=" + exchange_rate +
                ", insurance_cost_usd=" + insurance_cost_usd +
                ", customer_paid_insurance_price=" + customer_paid_insurance_price +
                ", payment_type='" + payment_type + '\'' +
                ", payment_type_other='" + payment_type_other + '\'' +
                ", card_last_four='" + card_last_four + '\'' +
                ", card_expire_date='" + card_expire_date + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", billing_address_1='" + billing_address_1 + '\'' +
                ", billing_address_2='" + billing_address_2 + '\'' +
                ", billing_city='" + billing_city + '\'' +
                ", billing_province='" + billing_province + '\'' +
                ", billing_postal_code='" + billing_postal_code + '\'' +
                ", billing_country='" + billing_country + '\'' +
                ", billing_same_as_shipping=" + billing_same_as_shipping +
                ", shipping_address_1='" + shipping_address_1 + '\'' +
                ", shipping_address_2='" + shipping_address_2 + '\'' +
                ", shipping_city='" + shipping_city + '\'' +
                ", shipping_province='" + shipping_province + '\'' +
                ", shipping_postal_code='" + shipping_postal_code + '\'' +
                ", shipping_country='" + shipping_country + '\'' +
                ", shipping_carrier_code='" + shipping_carrier_code + '\'' +
                ", shipping_method='" + shipping_method + '\'' +
                ", shipped_date='" + shipped_date + '\'' +
                ", tracking_number='" + tracking_number + '\'' +
                ", items=" + items +
                ", custom_b1='" + custom_b1 + '\'' +
                ", custom_b2='" + custom_b2 + '\'' +
                ", custom_b3='" + custom_b3 + '\'' +
                ", custom_b4='" + custom_b4 + '\'' +
                ", ip_address='" + ip_address + '\'' +
                ", created=" + created +
                '}';
    }
}
