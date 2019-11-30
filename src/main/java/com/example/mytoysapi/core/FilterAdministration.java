package com.example.mytoysapi.core;

import com.example.mytoysapi.common.enums.OrderTypeEnum;
import com.example.mytoysapi.core.model.NavigationLink;

import java.util.LinkedHashMap;
import java.util.List;

public interface FilterAdministration {
    List<NavigationLink> processMyToysData(String filterLabel, LinkedHashMap<String, OrderTypeEnum> sortSpec);
}
