package com.mymultiselect.multselect;

import java.io.Serializable;

public abstract class ModelMultiSelect implements Serializable {


    private String name;

    private boolean isChecked;

    public ModelMultiSelect(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return name;
    }
}
