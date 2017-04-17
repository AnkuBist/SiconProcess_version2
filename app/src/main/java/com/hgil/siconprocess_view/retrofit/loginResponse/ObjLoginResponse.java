package com.hgil.siconprocess_view.retrofit.loginResponse;

/**
 * Created by mohan.giri on 24-01-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.DemandTargetModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.ItemModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletSaleModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.PaymentModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.SaleHistoryModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.VanStockModel;

import java.io.Serializable;
import java.util.List;

public class ObjLoginResponse implements Serializable {
    @SerializedName("arrRoutes")
    @Expose
    private List<RouteModel> arrRoutes;
/*    @SerializedName("arrItems")
    @Expose
    private List<ItemModel> arrItems;*/
    @SerializedName("arrOutlets")
    @Expose
    private List<OutletModel> arrOutlets;
    @SerializedName("arrDemandTarget")
    @Expose
    private List<DemandTargetModel> arrDemandTarget;
    @SerializedName("arrVanStock")
    @Expose
    private List<VanStockModel> arrVanStock;
    @SerializedName("arrOutletSale")
    @Expose
    private List<OutletSaleModel> arrOutletSale;
    @SerializedName("arrPayment")
    @Expose
    private List<PaymentModel> arrPayment;
    @SerializedName("arrSaleHistory")
    @Expose
    private List<SaleHistoryModel> arrSaleHistory;

    public List<RouteModel> getArrRoutes() {
        return arrRoutes;
    }

    public void setArrRoutes(List<RouteModel> arrRoutes) {
        this.arrRoutes = arrRoutes;
    }

    public List<OutletModel> getArrOutlets() {
        return arrOutlets;
    }

    public void setArrOutlets(List<OutletModel> arrOutlets) {
        this.arrOutlets = arrOutlets;
    }

    public List<DemandTargetModel> getArrDemandTarget() {
        return arrDemandTarget;
    }

    public void setArrDemandTarget(List<DemandTargetModel> arrDemandTarget) {
        this.arrDemandTarget = arrDemandTarget;
    }

    public List<VanStockModel> getArrVanStock() {
        return arrVanStock;
    }

    public void setArrVanStock(List<VanStockModel> arrVanStock) {
        this.arrVanStock = arrVanStock;
    }

    public List<OutletSaleModel> getArrOutletSale() {
        return arrOutletSale;
    }

    public void setArrOutletSale(List<OutletSaleModel> arrOutletSale) {
        this.arrOutletSale = arrOutletSale;
    }

    public List<PaymentModel> getArrPayment() {
        return arrPayment;
    }

    public void setArrPayment(List<PaymentModel> arrPayment) {
        this.arrPayment = arrPayment;
    }

    public List<SaleHistoryModel> getArrSaleHistory() {
        return arrSaleHistory;
    }

    public void setArrSaleHistory(List<SaleHistoryModel> arrSaleHistory) {
        this.arrSaleHistory = arrSaleHistory;
    }
}