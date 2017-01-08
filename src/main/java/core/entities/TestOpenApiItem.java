package core.entities;

import core.entities.openApi.Item;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class TestOpenApiItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    @NotNull
    @Column(nullable = false)
    private String testApiKey;

    @NotNull
    @Column(nullable = false)
    private long testOpenApiQuoteId;
    private long testOpenApiOrderId;
    private long testOpenApiTrackingId;
    private long testOmniTransactionId;

    private String itemSku;

    @NotNull
    @Column(nullable = false)
    private String itemId;                                      // REQUIRED

    @NotNull
    @Column(nullable = false)
    private int itemQty;                                        // REQUIRED

    @NotNull
    @Column(nullable = false)
    private String itemName;                                    // REQUIRED

    private String itemDesc;

    @NotNull
    @Column(nullable = false)
    private BigDecimal itemCostPerUnit = BigDecimal.ZERO;       // REQUIRED

    @NotNull
    @Column(nullable = false)
    private BigDecimal itemValuePerUnit = BigDecimal.ZERO;      // REQUIRED

    private String createdByIpAddress;

    private LocalDateTime createdByDateTime;

    public TestOpenApiItem() {
    }

    public TestOpenApiItem(Item item, long testOpenApiQuoteId, String testApiKey, long accountId, String ipAddress) {
        this.accountId = accountId;
        this.testApiKey = testApiKey;
        this.testOpenApiQuoteId = testOpenApiQuoteId;
        this.itemSku = item.getItem_sku();
        this.itemId = item.getItem_id();
        this.itemQty = item.getItem_qty();
        this.itemName = item.getItem_name();
        this.itemDesc = item.getItem_desc();
        this.itemCostPerUnit = item.getItem_cost_per_unit();
        this.itemValuePerUnit = item.getItem_value_per_unit();
        this.createdByIpAddress = ipAddress;
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

    public long getTestOpenApiOrderId() {
        return testOpenApiOrderId;
    }

    public void setTestOpenApiOrderId(long testOpenApiOrderId) {
        this.testOpenApiOrderId = testOpenApiOrderId;
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

    public String getItemSku() {
        return itemSku;
    }

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public BigDecimal getItemCostPerUnit() {
        return itemCostPerUnit;
    }

    public void setItemCostPerUnit(BigDecimal itemCostPerUnit) {
        this.itemCostPerUnit = itemCostPerUnit;
    }

    public BigDecimal getItemValuePerUnit() {
        return itemValuePerUnit;
    }

    public void setItemValuePerUnit(BigDecimal itemValuePerUnit) {
        this.itemValuePerUnit = itemValuePerUnit;
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
