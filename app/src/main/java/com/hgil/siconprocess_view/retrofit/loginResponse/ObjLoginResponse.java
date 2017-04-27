package com.hgil.siconprocess_view.retrofit.loginResponse;

/**
 * Created by mohan.giri on 24-01-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.DemandTargetModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.ItemDetailModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletRemarkModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.PlanModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteRemarkModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.SHOutletItemSaleModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.SHRouteVanLoadingModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.SaleHistoryModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.TodaySaleModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.VanStockModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.ZoneModel;

import java.util.List;

public class ObjLoginResponse {
    @SerializedName("arrZones")
    @Expose
    private List<ZoneModel> arrZones;
    @SerializedName("arrRoutes")
    @Expose
    private List<RouteModel> arrRoutes;
    @SerializedName("arrOutlets")
    @Expose
    private List<OutletModel> arrOutlets;
    @SerializedName("arrDemandTarget")
    @Expose
    private List<DemandTargetModel> arrDemandTarget;
    @SerializedName("arrVanStock")
    @Expose
    private List<VanStockModel> arrVanStock;
    /* @SerializedName("arrOutletSale")
     @Expose
     private List<OutletSaleModel> arrOutletSale;*/
   /* @SerializedName("arrPayment")
    @Expose
    private List<PaymentModel> arrPayment;*/
    @SerializedName("arrSaleHistory")
    @Expose
    private List<SaleHistoryModel> arrSaleHistory;
    @SerializedName("arrRouteRemark")
    @Expose
    private List<RouteRemarkModel> arrRouteRemark;
    @SerializedName("arrRemark")
    @Expose
    private List<OutletRemarkModel> arrRemark;
    @SerializedName("arrPlan")
    @Expose
    private List<PlanModel> arrPlan;
    @SerializedName("arrTodaySale")
    @Expose
    private List<TodaySaleModel> arrTodaySale;
    @SerializedName("arrItemDetail")
    @Expose
    private List<ItemDetailModel> arrItemDetail;
    @SerializedName("arrSHVanLoading")
    @Expose
    private List<SHRouteVanLoadingModel> arrSHVanLoading;
    @SerializedName("arrSHOutletSale")
    @Expose
    private List<SHOutletItemSaleModel> arrSHOutletSale;

    public List<ZoneModel> getArrZones() {
        return arrZones;
    }

    public void setArrZones(List<ZoneModel> arrZones) {
        this.arrZones = arrZones;
    }

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

    /*public List<OutletSaleModel> getArrOutletSale() {
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
    }*/

    public List<SaleHistoryModel> getArrSaleHistory() {
        return arrSaleHistory;
    }

    public void setArrSaleHistory(List<SaleHistoryModel> arrSaleHistory) {
        this.arrSaleHistory = arrSaleHistory;
    }

    public List<RouteRemarkModel> getArrRouteRemark() {
        return arrRouteRemark;
    }

    public void setArrRouteRemark(List<RouteRemarkModel> arrRouteRemark) {
        this.arrRouteRemark = arrRouteRemark;
    }

    public List<OutletRemarkModel> getArrRemark() {
        return arrRemark;
    }

    public void setArrRemark(List<OutletRemarkModel> arrRemark) {
        this.arrRemark = arrRemark;
    }

    public List<PlanModel> getArrPlan() {
        return arrPlan;
    }

    public void setArrPlan(List<PlanModel> arrPlan) {
        this.arrPlan = arrPlan;
    }

    public List<TodaySaleModel> getArrTodaySale() {
        return arrTodaySale;
    }

    public void setArrTodaySale(List<TodaySaleModel> arrTodaySale) {
        this.arrTodaySale = arrTodaySale;
    }

    public List<ItemDetailModel> getArrItemDetail() {
        return arrItemDetail;
    }

    public void setArrItemDetail(List<ItemDetailModel> arrItemDetail) {
        this.arrItemDetail = arrItemDetail;
    }

    public List<SHRouteVanLoadingModel> getArrSHVanLoading() {
        return arrSHVanLoading;
    }

    public void setArrSHVanLoading(List<SHRouteVanLoadingModel> arrSHVanLoading) {
        this.arrSHVanLoading = arrSHVanLoading;
    }

    public List<SHOutletItemSaleModel> getArrSHOutletSale() {
        return arrSHOutletSale;
    }

    public void setArrSHOutletSale(List<SHOutletItemSaleModel> arrSHOutletSale) {
        this.arrSHOutletSale = arrSHOutletSale;
    }
}