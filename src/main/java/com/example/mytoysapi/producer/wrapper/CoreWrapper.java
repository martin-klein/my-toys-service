package com.example.mytoysapi.producer.wrapper;

import com.example.mytoysapi.common.enums.OrderDirectionEnum;
import com.example.mytoysapi.core.MyToysApiTranformation;
import com.example.mytoysapi.core.model.NavigationLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Transforms the url input to a logical format that can be used by the core component.
 */
@Component
public class CoreWrapper {

    @Autowired
    private MyToysApiTranformation myToysApiTranformation;

    public List<NavigationLink> processMyToysData(String parentLabel, String sortSpec) {
        return myToysApiTranformation.processMyToysData(parentLabel, parseSortSpec(sortSpec));
    }

    private LinkedHashMap<String, OrderDirectionEnum> parseSortSpec(String sortSpec) {
        if(sortSpec==null) {
            return null;
        }
        LinkedHashMap<String, OrderDirectionEnum> sortSpecAsMap = new LinkedHashMap<>();
        Collections.list(new StringTokenizer(sortSpec, ","))
                .forEach(token -> mapToPair(sortSpecAsMap, (String)token));
        return sortSpecAsMap;
    }

    private void mapToPair(LinkedHashMap<String, OrderDirectionEnum> sortSpecAsMap, String sortSpec) {
        String[] sortSpecArr = sortSpec.split(":");
        OrderDirectionEnum orderDirectionEnum = OrderDirectionEnum.NO_OP;
        if(sortSpecArr.length > 1) {
            orderDirectionEnum = OrderDirectionEnum.valueOf(sortSpecArr[1].toUpperCase());
        }
        sortSpecAsMap.put(sortSpecArr[0], orderDirectionEnum);
    }
}
