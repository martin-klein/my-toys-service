package com.example.mytoysapi.core.impl;

import com.example.mytoysapi.consumer.impl.MyToysApiConsumerImpl;
import com.example.mytoysapi.consumer.model.Navigable;
import com.example.mytoysapi.consumer.model.Navigation;
import com.example.mytoysapi.core.FilterAdministration;
import com.example.mytoysapi.common.enums.OrderTypeEnum;
import com.example.mytoysapi.core.mapper.NavigationEntryMapper;
import com.example.mytoysapi.core.model.NavigationLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

@Component
public class FilterAdministrationImpl implements FilterAdministration {
    @Autowired
    private MyToysApiConsumerImpl myToysApiConsumer;

    @Autowired
    private ProcFilterByNode procFilterByNode;

    @Autowired
    private NavigationEntryMapper navigationEntryMapper;

    @Autowired
    private ProcSortByOpenstackSpec<NavigationLink> procSortByOpenstackSpec;

    @Override
    public List<NavigationLink> processMyToysData(String filterLabel, LinkedHashMap<String, OrderTypeEnum> sortSpec) {
        Navigation navigation = myToysApiConsumer.readNavigation();
        Navigable filteredEntries = procFilterByNode.filter(navigation, filterLabel);
        List<NavigationLink> navigationLinks = navigationEntryMapper.map(filteredEntries);
        return procSortByOpenstackSpec.sort(navigationLinks, sortSpec);
    }
}
