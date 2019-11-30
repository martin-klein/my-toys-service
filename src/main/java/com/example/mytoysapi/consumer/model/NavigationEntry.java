package com.example.mytoysapi.consumer.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Models an navigation entry that is provided by the mytoys api.
 */
public class NavigationEntry implements Serializable, Navigable {
    @NotNull
    private String type;
    @NotNull
    private String label;
    private List<NavigationEntry> children;
    private String url;

    public NavigationEntry() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<NavigationEntry> getChildren() {
        return children;
    }

    public void setChildren(List<NavigationEntry> children) {
        this.children = children;
    }

    public Boolean hasChildren() {
        return children != null && children.size()>0;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
