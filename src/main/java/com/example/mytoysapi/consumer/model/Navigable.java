package com.example.mytoysapi.consumer.model;

import java.util.List;

/**
 * Offers an common interface for {@link Navigation} and {@link NavigationEntry} that makes algorithmic handling in the core easier.
 */
public interface Navigable {
    List<NavigationEntry> getChildren();

    String getLabel();

    String getUrl();

    Boolean hasChildren();
}
