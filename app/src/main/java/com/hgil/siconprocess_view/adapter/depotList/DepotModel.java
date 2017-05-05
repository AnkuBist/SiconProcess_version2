package com.hgil.siconprocess_view.adapter.depotList;

/**
 * Created by mohan.giri on 19-04-2017.
 */

public class DepotModel {

    private String depot_id;
    private String depot_name;
    private int depot_leftover;
    private int depot_rej_prct;

    public String getDepot_id() {
        return depot_id;
    }

    public void setDepot_id(String depot_id) {
        this.depot_id = depot_id;
    }

    public String getDepot_name() {
        return depot_name;
    }

    public void setDepot_name(String depot_name) {
        this.depot_name = depot_name;
    }

    public int getDepot_leftover() {
        return depot_leftover;
    }

    public void setDepot_leftover(int depot_leftover) {
        this.depot_leftover = depot_leftover;
    }

    public int getDepot_rej_prct() {
        return depot_rej_prct;
    }

    public void setDepot_rej_prct(int depot_rej_prct) {
        this.depot_rej_prct = depot_rej_prct;
    }
}
