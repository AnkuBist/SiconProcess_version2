package com.hgil.siconprocess.retrofit.loginResponse.dbModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 07-02-2017.
 */

public class CustomerItemPriceModel {

    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("price")
    @Expose
    private Double price;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}