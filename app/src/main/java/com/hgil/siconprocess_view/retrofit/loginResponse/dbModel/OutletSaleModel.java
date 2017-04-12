package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 12-04-2017.
 */

public class OutletSaleModel {

    @SerializedName("CashierId")
    @Expose
    private String cashierId;
    @SerializedName("CashierName")
    @Expose
    private String cashierName;
    @SerializedName("beat_code")
    @Expose
    private String beatCode;
    @SerializedName("Inv_date")
    @Expose
    private String invDate;
    @SerializedName("Inv_Datetime")
    @Expose
    private String invDatetime;
    @SerializedName("inv_id")
    @Expose
    private String invId;
    @SerializedName("item_count")
    @Expose
    private Integer itemCount;
    @SerializedName("Reason")
    @Expose
    private String reason;
    @SerializedName("outlet_code")
    @Expose
    private String outletCode;

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getBeatCode() {
        return beatCode;
    }

    public void setBeatCode(String beatCode) {
        this.beatCode = beatCode;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public String getInvDatetime() {
        return invDatetime;
    }

    public void setInvDatetime(String invDatetime) {
        this.invDatetime = invDatetime;
    }

    public String getInvId() {
        return invId;
    }

    public void setInvId(String invId) {
        this.invId = invId;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOutletCode() {
        return outletCode;
    }

    public void setOutletCode(String outletCode) {
        this.outletCode = outletCode;
    }

}