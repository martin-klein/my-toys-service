package com.example.mytoysapi.core;

import com.example.mytoysapi.common.enums.OrderDirectionEnum;
import com.example.mytoysapi.core.model.NavigationLink;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Offers methods for transforming the output of the mytoys api.
 */
public interface MyToysApiTranformation {
    /**
     * Filters, transforms and sorts the data provided by the mytoys api.
     *
     * @param filterLabel the label by which the content is filtered. Can be <code>null</code>.
     * @param sortSpec the sort specification. Can be <code>null</code>.
     * @return an optionally filtered and sorted {@link NavigationLink} list.
     */
    List<NavigationLink> processMyToysData(String filterLabel, LinkedHashMap<String, OrderDirectionEnum> sortSpec);
}
