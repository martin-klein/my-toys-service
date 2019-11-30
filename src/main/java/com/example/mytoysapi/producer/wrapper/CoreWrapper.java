package com.example.mytoysapi.producer.wrapper;

import com.example.mytoysapi.common.enums.OrderTypeEnum;
import com.example.mytoysapi.core.FilterAdministration;
import com.example.mytoysapi.core.model.NavigationLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class CoreWrapper {

    @Autowired
    private FilterAdministration filterAdministration;

    public List<NavigationLink> processMyToysData(String parentLabel, String sortSpec) {
        return filterAdministration.processMyToysData(parentLabel, parseSortSpec(sortSpec));
    }

    private LinkedHashMap<String, OrderTypeEnum> parseSortSpec(String sortSpec) {
        LinkedHashMap<String, OrderTypeEnum> mapa = new LinkedHashMap<>();
        Collections.list(new StringTokenizer(sortSpec, ","))
                .forEach(token -> mapToPair(mapa, (String)token));
        return mapa;
    }

    private void mapToPair(LinkedHashMap<String, OrderTypeEnum> mapa, String sortSpec) {
        String[] sortSpecArr = sortSpec.split(":");
        OrderTypeEnum orderTypeEnum = OrderTypeEnum.NO_OP;
        if(sortSpecArr.length > 1) {
            orderTypeEnum = OrderTypeEnum.valueOf(sortSpecArr[1].toUpperCase());
        }
        mapa.put(sortSpecArr[0], orderTypeEnum);
    }
}
