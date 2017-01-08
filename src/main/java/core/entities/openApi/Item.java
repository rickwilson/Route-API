package core.entities.openApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import core.entities.OpenApiItem;
import core.entities.TestOpenApiItem;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private String item_sku;
    private String item_id;                                     // REQUIRED
    private int item_qty;                                       // REQUIRED
    private String item_name;                                   // REQUIRED
    private String item_desc;
    private BigDecimal item_cost_per_unit = BigDecimal.ZERO;    // REQUIRED
    private BigDecimal item_value_per_unit = BigDecimal.ZERO;   // REQUIRED

    public Item() {
    }

    public Item(OpenApiItem oai) {
        this.item_sku = oai.getItemSku();
        this.item_id = oai.getItemId();
        this.item_qty = oai.getItemQty();
        this.item_name = oai.getItemName();
        this.item_desc = oai.getItemDesc();
        this.item_cost_per_unit = oai.getItemCostPerUnit();
        this.item_value_per_unit = oai.getItemValuePerUnit();
    }

    public Item(TestOpenApiItem toai) {
        this.item_sku = toai.getItemSku();
        this.item_id = toai.getItemId();
        this.item_qty = toai.getItemQty();
        this.item_name = toai.getItemName();
        this.item_desc = toai.getItemDesc();
        this.item_cost_per_unit = toai.getItemCostPerUnit();
        this.item_value_per_unit = toai.getItemValuePerUnit();
    }

    public String getItem_sku() {
        return item_sku;
    }

    public void setItem_sku(String item_sku) {
        this.item_sku = item_sku;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public int getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(int item_qty) {
        this.item_qty = item_qty;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public BigDecimal getItem_cost_per_unit() {
        return item_cost_per_unit;
    }

    public void setItem_cost_per_unit(BigDecimal item_cost_per_unit) {
        this.item_cost_per_unit = item_cost_per_unit;
    }

    public BigDecimal getItem_value_per_unit() {
        return item_value_per_unit;
    }

    public void setItem_value_per_unit(BigDecimal item_value_per_unit) {
        this.item_value_per_unit = item_value_per_unit;
    }
}
