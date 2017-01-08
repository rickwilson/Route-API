package core.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class KonnektiveOrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private String crmPushApiKey;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    @NotNull
    @Column(nullable = false)
    private String apiKey;

    @NotNull
    @Column(nullable = false)
    private String insPrice;

    @NotNull
    @Column(nullable = false)
    private String trackingNumber;

    @NotNull
    @Column(nullable = false)
    private String passedOrderType;

    private String orderId;    // C2C4AB06FE
    private String sourceId;    // 3
    private String sourceValue1;    // 10976
    private String sourceValue2;    // fcd5c80b869441ca1df63970f47a6757
    private String dateCreated;    // 2016-09-16 23:14:42
    private String orderType;    // NEW_SALE
    private String orderStatus;    // COMPLETE
    private String totalAmount;    // 4.97
    private String campaignName;    // US Hair 01
    private String orderValue;    // 0.00
    private String customerId;    // 16796
    private String name;    // Elaina Yeager
    private String emailAddress;    // Elainay29@gmail.com
    private String phoneNumber;    // 2672053515
    private String firstName;    // Elaina
    private String lastName;    // Yeager
    private String address1;    // 2780 Willits rd apt a
    private String shipmentInsured;    // 1
    private String shipmentInsurancePrice;    // 3.97
    private String insuranceCharged;    // 0
    private String city;    // Philadelphia
    private String state;    // PA
    private String country;    // US
    private String postalCode;    // 19136
    private String shipFirstName;    // Elaina
    private String shipLastName;    // Yeager
    private String shipAddress1;    // 2780 Willits rd apt a
    private String shipCity;    // Philadelphia
    private String shipState;    // PA
    private String shipCountry;    // US
    private String shipPostalCode;    // 19136
    private String paySource;    // CREDITCARD
    private String cardType;    // VISA
    private String cardLast4;    // 8271
    private String cardExpiryDate;    // 07/20
    private String agentUserId;    // 15142
    private String basePrice;    // 0.00
    private String baseShipping;    // 4.97
    private String discountPrice;    // 0.00
    private String salesTax;    // 0.00
    private String currencySymbol;    // $
    private String campaignId;    // 1
    private String originalCycleNumber;    // 1
    private String subTotal;    // 0.00
    private String shipTotal;    // 4.97
    private String taxTotal;    // 0.00
    private String totalDiscount;    // 0.00
    private String amountPaid;    //

    public KonnektiveOrderDetails() {
    }

    public KonnektiveOrderDetails(String crmPushApiKey, long accountId, String apiKey, String insPrice, String trackingNumber, String passedOrderType, String orderId, String sourceId, String sourceValue1, String sourceValue2, String dateCreated, String orderType, String orderStatus, String totalAmount, String campaignName, String orderValue, String customerId, String name, String emailAddress, String phoneNumber, String firstName, String lastName, String address1, String shipmentInsured, String shipmentInsurancePrice, String insuranceCharged, String city, String state, String country, String postalCode, String shipFirstName, String shipLastName, String shipAddress1, String shipCity, String shipState, String shipCountry, String shipPostalCode, String paySource, String cardType, String cardLast4, String cardExpiryDate, String agentUserId, String basePrice, String baseShipping, String discountPrice, String salesTax, String currencySymbol, String campaignId, String originalCycleNumber, String subTotal, String shipTotal, String taxTotal, String totalDiscount, String amountPaid) {
        this.crmPushApiKey = crmPushApiKey;
        this.accountId = accountId;
        this.apiKey = apiKey;
        this.insPrice = insPrice;
        this.trackingNumber = trackingNumber;
        this.passedOrderType = passedOrderType;
        this.orderId = orderId;
        this.sourceId = sourceId;
        this.sourceValue1 = sourceValue1;
        this.sourceValue2 = sourceValue2;
        this.dateCreated = dateCreated;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.campaignName = campaignName;
        this.orderValue = orderValue;
        this.customerId = customerId;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.shipmentInsured = shipmentInsured;
        this.shipmentInsurancePrice = shipmentInsurancePrice;
        this.insuranceCharged = insuranceCharged;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.shipFirstName = shipFirstName;
        this.shipLastName = shipLastName;
        this.shipAddress1 = shipAddress1;
        this.shipCity = shipCity;
        this.shipState = shipState;
        this.shipCountry = shipCountry;
        this.shipPostalCode = shipPostalCode;
        this.paySource = paySource;
        this.cardType = cardType;
        this.cardLast4 = cardLast4;
        this.cardExpiryDate = cardExpiryDate;
        this.agentUserId = agentUserId;
        this.basePrice = basePrice;
        this.baseShipping = baseShipping;
        this.discountPrice = discountPrice;
        this.salesTax = salesTax;
        this.currencySymbol = currencySymbol;
        this.campaignId = campaignId;
        this.originalCycleNumber = originalCycleNumber;
        this.subTotal = subTotal;
        this.shipTotal = shipTotal;
        this.taxTotal = taxTotal;
        this.totalDiscount = totalDiscount;
        this.amountPaid = amountPaid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCrmPushApiKey() {
        return crmPushApiKey;
    }

    public void setCrmPushApiKey(String crmPushApiKey) {
        this.crmPushApiKey = crmPushApiKey;
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

    public String getInsPrice() {
        return insPrice;
    }

    public void setInsPrice(String insPrice) {
        this.insPrice = insPrice;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getPassedOrderType() {
        return passedOrderType;
    }

    public void setPassedOrderType(String passedOrderType) {
        this.passedOrderType = passedOrderType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceValue1() {
        return sourceValue1;
    }

    public void setSourceValue1(String sourceValue1) {
        this.sourceValue1 = sourceValue1;
    }

    public String getSourceValue2() {
        return sourceValue2;
    }

    public void setSourceValue2(String sourceValue2) {
        this.sourceValue2 = sourceValue2;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getShipmentInsured() {
        return shipmentInsured;
    }

    public void setShipmentInsured(String shipmentInsured) {
        this.shipmentInsured = shipmentInsured;
    }

    public String getShipmentInsurancePrice() {
        return shipmentInsurancePrice;
    }

    public void setShipmentInsurancePrice(String shipmentInsurancePrice) {
        this.shipmentInsurancePrice = shipmentInsurancePrice;
    }

    public String getInsuranceCharged() {
        return insuranceCharged;
    }

    public void setInsuranceCharged(String insuranceCharged) {
        this.insuranceCharged = insuranceCharged;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getShipFirstName() {
        return shipFirstName;
    }

    public void setShipFirstName(String shipFirstName) {
        this.shipFirstName = shipFirstName;
    }

    public String getShipLastName() {
        return shipLastName;
    }

    public void setShipLastName(String shipLastName) {
        this.shipLastName = shipLastName;
    }

    public String getShipAddress1() {
        return shipAddress1;
    }

    public void setShipAddress1(String shipAddress1) {
        this.shipAddress1 = shipAddress1;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipState() {
        return shipState;
    }

    public void setShipState(String shipState) {
        this.shipState = shipState;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getPaySource() {
        return paySource;
    }

    public void setPaySource(String paySource) {
        this.paySource = paySource;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardLast4() {
        return cardLast4;
    }

    public void setCardLast4(String cardLast4) {
        this.cardLast4 = cardLast4;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getAgentUserId() {
        return agentUserId;
    }

    public void setAgentUserId(String agentUserId) {
        this.agentUserId = agentUserId;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getBaseShipping() {
        return baseShipping;
    }

    public void setBaseShipping(String baseShipping) {
        this.baseShipping = baseShipping;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(String salesTax) {
        this.salesTax = salesTax;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getOriginalCycleNumber() {
        return originalCycleNumber;
    }

    public void setOriginalCycleNumber(String originalCycleNumber) {
        this.originalCycleNumber = originalCycleNumber;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getShipTotal() {
        return shipTotal;
    }

    public void setShipTotal(String shipTotal) {
        this.shipTotal = shipTotal;
    }

    public String getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(String taxTotal) {
        this.taxTotal = taxTotal;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }
}
