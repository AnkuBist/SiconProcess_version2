package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 20-04-2017.
 */

public class ItemDetailModel {

    @SerializedName("item_code")
    @Expose
    private String itemCode;
    @SerializedName("item_name")
    @Expose
    private String itemName;
    @SerializedName("item_desc")
    @Expose
    private String itemDesc;
    @SerializedName("sequence")
    @Expose
    private Integer sequence;
    @SerializedName("item_price")
    @Expose
    private Double itemPrice;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

}
