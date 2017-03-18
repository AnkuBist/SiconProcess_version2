package com.hgil.siconprocess.retrofit.loginResponse.dbModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 18-03-2017.
 */

public class CustomerInfoModel {

    @SerializedName("Customer_Id")
    @Expose
    private String customerId;
    @SerializedName("Customer_Name")
    @Expose
    private String customerName;
    @SerializedName("C_Type")
    @Expose
    private String cType;
    @SerializedName("Customer_Billing_Address")
    @Expose
    private String customerBillingAddress;
    @SerializedName("Depot_id")
    @Expose
    private String depotId;
    @SerializedName("Sub_Company_id")
    @Expose
    private String subCompanyId;
    @SerializedName("INVOICEACCOUNT")
    @Expose
    private String iNVOICEACCOUNT;
    @SerializedName("CUSTGROUP")
    @Expose
    private String cUSTGROUP;
    @SerializedName("LINEDISC")
    @Expose
    private String lINEDISC;
    @SerializedName("PAYMTERMID")
    @Expose
    private String pAYMTERMID;
    @SerializedName("CASHDISC")
    @Expose
    private String cASHDISC;
    @SerializedName("SALESGROUP")
    @Expose
    private String sALESGROUP;
    @SerializedName("BLOCKED")
    @Expose
    private Integer bLOCKED;
    @SerializedName("PRICEGROUP")
    @Expose
    private String pRICEGROUP;
    @SerializedName("MULTILINEDISC")
    @Expose
    private String mULTILINEDISC;
    @SerializedName("TAXGROUP")
    @Expose
    private String tAXGROUP;
    @SerializedName("COMMISSIONGROUP")
    @Expose
    private String cOMMISSIONGROUP;
    @SerializedName("OURACCOUNTNUM")
    @Expose
    private String oURACCOUNTNUM;
    @SerializedName("NAMEALIAS")
    @Expose
    private String nAMEALIAS;
    @SerializedName("SH_NAME")
    @Expose
    private String sHNAME;
    @SerializedName("ACCOUNTNUM")
    @Expose
    private String aCCOUNTNUM;
    @SerializedName("CUSTCLASSIFICATIONID")
    @Expose
    private String cUSTCLASSIFICATIONID;
    @SerializedName("ContactNo")
    @Expose
    private String contactNo;
    @SerializedName("flag")
    @Expose
    private Integer flag;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCType() {
        return cType;
    }

    public void setCType(String cType) {
        this.cType = cType;
    }

    public String getCustomerBillingAddress() {
        return customerBillingAddress;
    }

    public void setCustomerBillingAddress(String customerBillingAddress) {
        this.customerBillingAddress = customerBillingAddress;
    }

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

    public String getSubCompanyId() {
        return subCompanyId;
    }

    public void setSubCompanyId(String subCompanyId) {
        this.subCompanyId = subCompanyId;
    }

    public String getINVOICEACCOUNT() {
        return iNVOICEACCOUNT;
    }

    public void setINVOICEACCOUNT(String iNVOICEACCOUNT) {
        this.iNVOICEACCOUNT = iNVOICEACCOUNT;
    }

    public String getCUSTGROUP() {
        return cUSTGROUP;
    }

    public void setCUSTGROUP(String cUSTGROUP) {
        this.cUSTGROUP = cUSTGROUP;
    }

    public String getLINEDISC() {
        return lINEDISC;
    }

    public void setLINEDISC(String lINEDISC) {
        this.lINEDISC = lINEDISC;
    }

    public String getPAYMTERMID() {
        return pAYMTERMID;
    }

    public void setPAYMTERMID(String pAYMTERMID) {
        this.pAYMTERMID = pAYMTERMID;
    }

    public String getCASHDISC() {
        return cASHDISC;
    }

    public void setCASHDISC(String cASHDISC) {
        this.cASHDISC = cASHDISC;
    }

    public String getSALESGROUP() {
        return sALESGROUP;
    }

    public void setSALESGROUP(String sALESGROUP) {
        this.sALESGROUP = sALESGROUP;
    }

    public Integer getBLOCKED() {
        return bLOCKED;
    }

    public void setBLOCKED(Integer bLOCKED) {
        this.bLOCKED = bLOCKED;
    }

    public String getPRICEGROUP() {
        return pRICEGROUP;
    }

    public void setPRICEGROUP(String pRICEGROUP) {
        this.pRICEGROUP = pRICEGROUP;
    }

    public String getMULTILINEDISC() {
        return mULTILINEDISC;
    }

    public void setMULTILINEDISC(String mULTILINEDISC) {
        this.mULTILINEDISC = mULTILINEDISC;
    }

    public String getTAXGROUP() {
        return tAXGROUP;
    }

    public void setTAXGROUP(String tAXGROUP) {
        this.tAXGROUP = tAXGROUP;
    }

    public String getCOMMISSIONGROUP() {
        return cOMMISSIONGROUP;
    }

    public void setCOMMISSIONGROUP(String cOMMISSIONGROUP) {
        this.cOMMISSIONGROUP = cOMMISSIONGROUP;
    }

    public String getOURACCOUNTNUM() {
        return oURACCOUNTNUM;
    }

    public void setOURACCOUNTNUM(String oURACCOUNTNUM) {
        this.oURACCOUNTNUM = oURACCOUNTNUM;
    }

    public String getNAMEALIAS() {
        return nAMEALIAS;
    }

    public void setNAMEALIAS(String nAMEALIAS) {
        this.nAMEALIAS = nAMEALIAS;
    }

    public String getSHNAME() {
        return sHNAME;
    }

    public void setSHNAME(String sHNAME) {
        this.sHNAME = sHNAME;
    }

    public String getACCOUNTNUM() {
        return aCCOUNTNUM;
    }

    public void setACCOUNTNUM(String aCCOUNTNUM) {
        this.aCCOUNTNUM = aCCOUNTNUM;
    }

    public String getCUSTCLASSIFICATIONID() {
        return cUSTCLASSIFICATIONID;
    }

    public void setCUSTCLASSIFICATIONID(String cUSTCLASSIFICATIONID) {
        this.cUSTCLASSIFICATIONID = cUSTCLASSIFICATIONID;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

}