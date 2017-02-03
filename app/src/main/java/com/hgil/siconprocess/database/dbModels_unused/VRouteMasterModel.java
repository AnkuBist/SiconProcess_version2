/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hgil.siconprocess.database.dbModels_unused;

import java.util.ArrayList;

/**
 *
 * @author mohan.giri
 */
public class VRouteMasterModel {

    //private long Rec_Id;     //.............not generated
    private String Sub_Company_id;  //null
    private String Depot_id;    //null
    private String SubDepot_id;
    private String Route_Id;    //null
    private String Route_Name;  //null
    private String Route_Description;   //null
    private String Sale_Date_Parameter; //null
    private String Loading_Type;    //null
    private int TCC;    //null
    private String MainDepot;   //null
    private int Flag;   //null

    // add customers to route
    private ArrayList<VCustomerRouteMapping> arrCustomerRouteMap;

    public ArrayList<VCustomerRouteMapping> getArrCustomerRouteMap() {
        return arrCustomerRouteMap;
    }

    public void setArrCustomerRouteMap(ArrayList<VCustomerRouteMapping> arrCustomerRouteMap) {
        this.arrCustomerRouteMap = arrCustomerRouteMap;
    }

    // get all price groups
    private ArrayList<VPriceGroupMasterModel> arrGroupPrice;

    public ArrayList<VPriceGroupMasterModel> getArrGroupPrice() {
        return arrGroupPrice;
    }

    public void setArrGroupPrice(ArrayList<VPriceGroupMasterModel> arrGroupPrice) {
        this.arrGroupPrice = arrGroupPrice;
    }
    
    // get route credit opening
    private ArrayList<CreditOpeningMasterModel> arrCreditOpening;

    public ArrayList<CreditOpeningMasterModel> getArrCreditOpening() {
        return arrCreditOpening;
    }

    public void setArrCreditOpening(ArrayList<CreditOpeningMasterModel> arrCreditOpening) {
        this.arrCreditOpening = arrCreditOpening;
    }
    
    // get customer crate opening
    private ArrayList<CrateOpeningMasterModel> arrCrateOpening;

    public ArrayList<CrateOpeningMasterModel> getArrCrateOpening() {
        return arrCrateOpening;
    }

    public void setArrCrateOpening(ArrayList<CrateOpeningMasterModel> arrCrateOpening) {
        this.arrCrateOpening = arrCrateOpening;
    }
    
    // crate collectin details
    private ArrayList<VCrateCollectionMasterModel> arrCrateCollection;

    public ArrayList<VCrateCollectionMasterModel> getArrCrateCollection() {
        return arrCrateCollection;
    }

    public void setArrCrateCollection(ArrayList<VCrateCollectionMasterModel> arrCrateCollection) {
        this.arrCrateCollection = arrCrateCollection;
    }

    // get route invoice details
    private ArrayList<VDepotInvoiceMasterModel> arrInvoiceDetails;

    public ArrayList<VDepotInvoiceMasterModel> getArrInvoiceDetails() {
        return arrInvoiceDetails;
    }

    public void setArrInvoiceDetails(ArrayList<VDepotInvoiceMasterModel> arrInvoiceDetails) {
        this.arrInvoiceDetails = arrInvoiceDetails;
    }
    
    // master demand target
    private ArrayList<DemandTargetMasterModel> arrDemandTarget;

    public ArrayList<DemandTargetMasterModel> getArrDemandTarget() {
        return arrDemandTarget;
    }

    public void setArrDemandTarget(ArrayList<DemandTargetMasterModel> arrDemandTarget) {
        this.arrDemandTarget = arrDemandTarget;
    }
    
    // master fixed sample 
    private ArrayList<FixedSampleMasterModel> arrFixedSample;

    public ArrayList<FixedSampleMasterModel> getArrFixedSample() {
        return arrFixedSample;
    }

    public void setArrFixedSample(ArrayList<FixedSampleMasterModel> arrFixedSample) {
        this.arrFixedSample = arrFixedSample;
    }
    
    // master rejection target
    private ArrayList<RejectionTargetMasterModel> arrRejectionTarget;

    public ArrayList<RejectionTargetMasterModel> getArrRejectionTarget() {
        return arrRejectionTarget;
    }

    public void setArrRejectionTarget(ArrayList<RejectionTargetMasterModel> arrRejectionTarget) {
        this.arrRejectionTarget = arrRejectionTarget;
    }
    
    private ArrayList<DepotEmployeeMasterModel> arrEmployees;

    public ArrayList<DepotEmployeeMasterModel> getArrEmployees() {
        return arrEmployees;
    }

    public void setArrEmployees(ArrayList<DepotEmployeeMasterModel> arrEmployees) {
        this.arrEmployees = arrEmployees;
    }
    
    /*   public long getRec_Id() {
        return Rec_Id;
    }

    public void setRec_Id(long Rec_Id) {
        this.Rec_Id = Rec_Id;
    }*/
    public String getSub_Company_id() {
        return Sub_Company_id;
    }

    public void setSub_Company_id(String Sub_Company_id) {
        this.Sub_Company_id = Sub_Company_id;
    }

    public String getDepot_id() {
        return Depot_id;
    }

    public void setDepot_id(String Depot_id) {
        this.Depot_id = Depot_id;
    }

    public String getSubDepot_id() {
        return SubDepot_id;
    }

    public void setSubDepot_id(String SubDepot_id) {
        this.SubDepot_id = SubDepot_id;
    }

    public String getRoute_Id() {
        return Route_Id;
    }

    public void setRoute_Id(String Route_Id) {
        this.Route_Id = Route_Id;
    }

    public String getRoute_Name() {
        return Route_Name;
    }

    public void setRoute_Name(String Route_Name) {
        this.Route_Name = Route_Name;
    }

    public String getRoute_Description() {
        return Route_Description;
    }

    public void setRoute_Description(String Route_Description) {
        this.Route_Description = Route_Description;
    }

    public String getSale_Date_Parameter() {
        return Sale_Date_Parameter;
    }

    public void setSale_Date_Parameter(String Sale_Date_Parameter) {
        this.Sale_Date_Parameter = Sale_Date_Parameter;
    }

    public String getLoading_Type() {
        return Loading_Type;
    }

    public void setLoading_Type(String Loading_Type) {
        this.Loading_Type = Loading_Type;
    }

    public int getTCC() {
        return TCC;
    }

    public void setTCC(int TCC) {
        this.TCC = TCC;
    }

    public String getMainDepot() {
        return MainDepot;
    }

    public void setMainDepot(String MainDepot) {
        this.MainDepot = MainDepot;
    }

    public int getFlag() {
        return Flag;
    }

    public void setFlag(int Flag) {
        this.Flag = Flag;
    }

}
