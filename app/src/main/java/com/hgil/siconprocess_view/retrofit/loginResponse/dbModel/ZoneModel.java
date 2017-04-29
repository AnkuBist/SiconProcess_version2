package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 27-04-2017.
 */

public class ZoneModel {

    @SerializedName("zoneName")
    @Expose
    private String zoneName;
    @SerializedName("depotId")
    @Expose
    private String depotId;
    @SerializedName("depotName")
    @Expose
    private String depotName;
    @SerializedName("zoneSequence")
    @Expose
    private Integer zoneSequence;

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

    public Integer getZoneSequence() {
        return zoneSequence;
    }

    public void setZoneSequence(Integer zoneSequence) {
        this.zoneSequence = zoneSequence;
    }

}

