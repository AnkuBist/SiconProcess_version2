package com.hgil.siconprocess.adapter.productSelection;

import java.io.Serializable;

/**
 * Created by mohan.giri on 04-02-2017.
 */

public class ProductSelectModel implements Serializable {

    private String item_id;
    private String item_name;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
}
