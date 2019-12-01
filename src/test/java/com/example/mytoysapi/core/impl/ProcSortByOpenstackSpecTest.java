package com.example.mytoysapi.core.impl;

import com.example.mytoysapi.common.enums.OrderDirectionEnum;
import com.example.mytoysapi.core.model.NavigationLink;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcSortByOpenstackSpecTest {
    @Autowired
    private ProcSortByOpenstackSpec<NavigationLink> procSortByOpenstackSpec;

    @Test
    public void sortNavigationEntriesLabelAsc() {
        List<NavigationLink> links = getDummyNavigationLinks();

        LinkedHashMap<String, OrderDirectionEnum> orderSpec = new LinkedHashMap<>();
        orderSpec.put("label", OrderDirectionEnum.ASC);

        List<NavigationLink> result = procSortByOpenstackSpec.sort(links, orderSpec);

        assertEquals("Child Products - Cribs - Yet Another Test Link", result.get(0).getLabel());
        assertEquals("Child Products - Toys - Another Test Link", result.get(1).getLabel());
        assertEquals("Child Products - Toys - Test Link", result.get(2).getLabel());
    }

    @Test
    public void sortNavigationEntriesUrlDesc() {
        List<NavigationLink> links = getDummyNavigationLinks();

        LinkedHashMap<String, OrderDirectionEnum> orderSpec = new LinkedHashMap<>();
        orderSpec.put("url", OrderDirectionEnum.DESC);

        List<NavigationLink> result = procSortByOpenstackSpec.sort(links, orderSpec);

        assertEquals("Child Products - Cribs - Yet Another Test Link", result.get(0).getLabel());
        assertEquals("Child Products - Toys - Test Link", result.get(1).getLabel());
        assertEquals("Child Products - Toys - Another Test Link", result.get(2).getLabel());
    }

    @Test
    public void sortNavigationEntriesLabelDescUrlAsc() {
        List<NavigationLink> links = getDummyNavigationLinks();
        NavigationLink link = new NavigationLink();
        link.setLabel("Child Products - Toys - Test Link");
        link.setUrl("Url20");
        links.add(link);

        LinkedHashMap<String, OrderDirectionEnum> orderSpec = new LinkedHashMap<>();
        orderSpec.put("label", OrderDirectionEnum.DESC);
        orderSpec.put("url", OrderDirectionEnum.ASC);

        List<NavigationLink> result = procSortByOpenstackSpec.sort(links, orderSpec);

        assertEquals("Child Products - Toys - Test Link", result.get(0).getLabel());
        assertEquals("Url2", result.get(0).getUrl());
        assertEquals("Child Products - Toys - Test Link", result.get(1).getLabel());
        assertEquals("Url20", result.get(1).getUrl());
        assertEquals("Child Products - Toys - Another Test Link", result.get(2).getLabel());
        assertEquals("Child Products - Cribs - Yet Another Test Link", result.get(3).getLabel());
    }

    private List<NavigationLink> getDummyNavigationLinks() {
        NavigationLink expectedLink1 = new NavigationLink();
        expectedLink1.setLabel("Child Products - Toys - Test Link");
        expectedLink1.setUrl("Url2");

        NavigationLink expectedLink2 = new NavigationLink();
        expectedLink2.setLabel("Child Products - Toys - Another Test Link");
        expectedLink2.setUrl("Url1");

        NavigationLink expectedLink3 = new NavigationLink();
        expectedLink3.setLabel("Child Products - Cribs - Yet Another Test Link");
        expectedLink3.setUrl("Url3");

        List<NavigationLink> links = new ArrayList<>();
        links.add(expectedLink1);
        links.add(expectedLink2);
        links.add(expectedLink3);
        return links;
    }
}
