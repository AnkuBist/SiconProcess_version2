package com.hgil.siconprocess.retrofit.loginResponse.dbModels;

/**
 * Created by mohan.giri on 24-01-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeModel {

    @SerializedName("Paycode")
    @Expose
    private String paycode;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Etype")
    @Expose
    private String etype;
    @SerializedName("Designation")
    @Expose
    private String designation;
    @SerializedName("Sub_Company_id")
    @Expose
    private String subCompanyId;
    @SerializedName("Depot_id")
    @Expose
    private String depotId;
    @SerializedName("NewPaycode")
    @Expose
    private String newPaycode;
    @SerializedName("Flag")
    @Expose
    private String flag;

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEtype() {
        return etype;
    }

    public void setEtype(String etype) {
        this.etype = etype;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSubCompanyId() {
        return subCompanyId;
    }

    public void setSubCompanyId(String subCompanyId) {
        this.subCompanyId = subCompanyId;
    }

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

    public String getNewPaycode() {
        return newPaycode;
    }

    public void setNewPaycode(String newPaycode) {
        this.newPaycode = newPaycode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
