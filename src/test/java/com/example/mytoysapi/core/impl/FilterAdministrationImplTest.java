package com.example.mytoysapi.core.impl;

import com.example.mytoysapi.common.enums.OrderTypeEnum;
import com.example.mytoysapi.consumer.MyToysApiConsumer;
import com.example.mytoysapi.consumer.model.Navigable;
import com.example.mytoysapi.consumer.model.Navigation;
import com.example.mytoysapi.consumer.model.NavigationEntryCreator;
import com.example.mytoysapi.core.FilterAdministration;
import com.example.mytoysapi.core.model.NavigationLink;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterAdministrationImplTest {

    @Mock
    private MyToysApiConsumer myToysApiConsumerMock;

    @Autowired
    @InjectMocks
    private FilterAdministrationImpl filterAdministration;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void filterAdministrationTest() {
        Navigation testNavigation = NavigationEntryCreator.createNavigation();
        Navigable childProducts = NavigationEntryCreator.addNodeTo(testNavigation, "Child Products");
        Navigable toys = NavigationEntryCreator.addNodeTo(childProducts, "Toys");
        Navigable cribs = NavigationEntryCreator.addNodeTo(childProducts, "Cribs");
        NavigationEntryCreator.addLinkTo(toys, "Test Link", "Url1");
        NavigationEntryCreator.addLinkTo(toys, "Another Test Link", "Url2");
        NavigationEntryCreator.addLinkTo(cribs, "Yet Another Test Link", "Url3");

        NavigationLink expectedLink1 = new NavigationLink();
        expectedLink1.setLabel("Child Products - Toys - Test Link");
        expectedLink1.setUrl("Url1");

        NavigationLink expectedLink2 = new NavigationLink();
        expectedLink2.setLabel("Child Products - Toys - Another Test Link");
        expectedLink2.setUrl("Url2");

        NavigationLink expectedLink3 = new NavigationLink();
        expectedLink3.setLabel("Child Products - Cribs - Yet Another Test Link");
        expectedLink3.setUrl("Url3");

        LinkedHashMap<String, OrderTypeEnum> sortSpec = new LinkedHashMap<>();
        sortSpec.put("label", OrderTypeEnum.ASC);

        when(myToysApiConsumerMock.readNavigation()).thenReturn(testNavigation);

        List<NavigationLink> result = filterAdministration.processMyToysData("Toys", sortSpec);

        assertEquals("Child Products - Cribs - Yet Another Test Link", result.get(0).getLabel());
        assertEquals("Url3", result.get(0).getUrl());
        assertEquals("Child Products - Toys - Another Test Link", result.get(1).getLabel());
        assertEquals("Url2", result.get(1).getUrl());
        assertEquals("Child Products - Toys - Test Link", result.get(2).getLabel());
        assertEquals("Url1", result.get(2).getUrl());
    }


}
