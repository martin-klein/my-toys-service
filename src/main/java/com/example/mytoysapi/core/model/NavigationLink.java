package com.example.mytoysapi.core.model;

import javax.validation.constraints.NotNull;

/**
 * Represents a label url pair.
 */
public class NavigationLink {

    @NotNull
    private String label;
    @NotNull
    private String url;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
