package com.example.mytoysapi.consumer.model;

import java.io.Serializable;
import java.util.List;

public class Navigation implements Serializable, Navigable {

    private List<NavigationEntry> navigationEntries;

    public List<NavigationEntry> getNavigationEntries() {
        return navigationEntries;
    }

    public void setNavigationEntries(List<NavigationEntry> navigationEntries) {
        this.navigationEntries = navigationEntries;
    }

    @Override
    public List<NavigationEntry> getChildren() {
        return navigationEntries;
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public String getUrl() { return null; }

    @Override
    public Boolean hasChildren() {
        return getChildren()!=null && getChildren().size()>0;
    }
}
