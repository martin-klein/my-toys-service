package com.example.mytoysapi.core.impl;

import com.example.mytoysapi.consumer.MyToysApiConsumer;
import com.example.mytoysapi.consumer.model.Navigable;
import com.example.mytoysapi.consumer.model.Navigation;
import com.example.mytoysapi.core.MyToysApiTranformation;
import com.example.mytoysapi.common.enums.OrderDirectionEnum;
import com.example.mytoysapi.core.mapper.NavigationEntryMapper;
import com.example.mytoysapi.core.model.NavigationLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Implements {@link MyToysApiTranformation}.
 */
@Component
public class MyToysApiTranformationImpl implements MyToysApiTranformation {
    @Autowired
    private MyToysApiConsumer myToysApiConsumer;

    @Autowired
    private ProcFilterByNode procFilterByNode;

    @Autowired
    private NavigationEntryMapper navigationEntryMapper;

    @Autowired
    private ProcSortByOpenstackSpec<NavigationLink> procSortByOpenstackSpec;

    @Override
    public List<NavigationLink> processMyToysData(String filterLabel, LinkedHashMap<String, OrderDirectionEnum> sortSpec) {
        Navigation navigation = myToysApiConsumer.readNavigation();
        Navigable filteredEntries = navigation;
        if(filterLabel!=null) {
            filteredEntries = procFilterByNode.filter(navigation, filterLabel);
        }

        List<NavigationLink> navigationLinks = navigationEntryMapper.map(filteredEntries);

        if(sortSpec!=null) {
            return procSortByOpenstackSpec.sort(navigationLinks, sortSpec);
        } else {
            return navigationLinks;
        }
    }

}
