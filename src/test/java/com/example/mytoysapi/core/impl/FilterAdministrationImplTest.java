package com.example.mytoysapi.core.impl;

import com.example.mytoysapi.common.enums.OrderTypeEnum;
import com.example.mytoysapi.consumer.MyToysApiConsumer;
import com.example.mytoysapi.consumer.model.Navigable;
import com.example.mytoysapi.consumer.model.Navigation;
import com.example.mytoysapi.consumer.model.NavigationEntryCreator;
import com.example.mytoysapi.core.FilterAdministration;
import com.example.mytoysapi.core.model.NavigationLink;
import org.apache.catalina.core.ApplicationContextFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
    public void setup() {
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

        LinkedHashMap<String, OrderTypeEnum> sortSpec = new LinkedHashMap<>();
        sortSpec.put("label", OrderTypeEnum.ASC);

        when(myToysApiConsumerMock.readNavigation()).thenReturn(testNavigation);

        List<NavigationLink> result = filterAdministration.processMyToysData("Child Products", sortSpec);

        assertEquals("Cribs - Yet Another Test Link", result.get(0).getLabel());
        assertEquals("Url3", result.get(0).getUrl());
        assertEquals("Toys - Another Test Link", result.get(1).getLabel());
        assertEquals("Url2", result.get(1).getUrl());
        assertEquals("Toys - Test Link", result.get(2).getLabel());
        assertEquals("Url1", result.get(2).getUrl());

    }

}
