package com.hgil.siconprocess_view.adapter.routeList;

/**
 * Created by mohan.giri on 10-04-2017.
 */

public class RouteListModel {

    private String route_name;
    private String route_id;
    private int route_leftover;
    private int route_total_loading;
    private int route_productive_calls;
    private int route_target_calls;
    private int route_rej_prct;
    private int routeCloseStatus;

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public int getRoute_leftover() {
        return route_leftover;
    }

    public void setRoute_leftover(int route_leftover) {
        this.route_leftover = route_leftover;
    }

    public int getRoute_total_loading() {
        return route_total_loading;
    }

    public void setRoute_total_loading(int route_total_loading) {
        this.route_total_loading = route_total_loading;
    }

    public int getRoute_productive_calls() {
        return route_productive_calls;
    }

    public void setRoute_productive_calls(int route_productive_calls) {
        this.route_productive_calls = route_productive_calls;
    }

    public int getRoute_target_calls() {
        return route_target_calls;
    }

    public void setRoute_target_calls(int route_target_calls) {
        this.route_target_calls = route_target_calls;
    }

    public int getRoute_rej_prct() {
        return route_rej_prct;
    }

    public void setRoute_rej_prct(int route_rej_prct) {
        this.route_rej_prct = route_rej_prct;
    }

    public int getRouteCloseStatus() {
        return routeCloseStatus;
    }

    public void setRouteCloseStatus(int routeCloseStatus) {
        this.routeCloseStatus = routeCloseStatus;
    }
}
