package com.example.mytoysapi.consumer.model;

import java.util.List;

public interface Navigable {
    List<NavigationEntry> getChildren();
    String getLabel();
    String getUrl();
    Boolean hasChildren();
}
