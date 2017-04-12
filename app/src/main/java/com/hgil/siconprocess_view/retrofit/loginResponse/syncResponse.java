package com.hgil.siconprocess_view.retrofit.loginResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 21-02-2017.
 */

public class syncResponse {
    @SerializedName("returnCode")
    @Expose
    private Boolean returnCode;
    @SerializedName("strMessage")
    @Expose
    private String strMessage;

    public Boolean getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Boolean returnCode) {
        this.returnCode = returnCode;
    }

    public String getStrMessage() {
        return strMessage;
    }

    public void setStrMessage(String strMessage) {
        this.strMessage = strMessage;
    }

}
