package com.example.mytoysapi.core.mapper;

import com.example.mytoysapi.consumer.model.Navigable;
import com.example.mytoysapi.consumer.model.Navigation;
import com.example.mytoysapi.consumer.model.NavigationEntry;
import com.example.mytoysapi.consumer.model.NavigationEntryCreator;
import com.example.mytoysapi.core.model.NavigationLink;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NavigationEntryMapperTest {
    @Autowired
    private NavigationEntryMapper navigationEntryMapper;

    @Test
    public void testMapNavigationTreeStructure() {
        Navigation navigation = NavigationEntryCreator.createNavigation();
        Navigable childProducts = NavigationEntryCreator.addNodeTo(navigation, "Child Products");
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

        List<NavigationLink> result = navigationEntryMapper.map(navigation);

        assertEquals(expectedLink1.getLabel(), result.get(0).getLabel());
        assertEquals(expectedLink1.getUrl(), result.get(0).getUrl());
        assertEquals(expectedLink2.getLabel(), result.get(1).getLabel());
        assertEquals(expectedLink2.getUrl(), result.get(1).getUrl());
        assertEquals(expectedLink3.getLabel(), result.get(2).getLabel());
        assertEquals(expectedLink3.getUrl(), result.get(2).getUrl());
    }

    @Test
    public void testMapLeaf() {
        Navigation navigation = NavigationEntryCreator.createNavigation();
        NavigationEntryCreator.addLinkTo(navigation, "Test Link", "Url1");

        List<NavigationLink> result = navigationEntryMapper.map(navigation);
        NavigationLink expectedNavigation = new NavigationLink();
        expectedNavigation.setLabel("Test Link");
        expectedNavigation.setUrl("Url1");

        assertEquals(expectedNavigation.getLabel(), result.get(0).getLabel());
        assertEquals(expectedNavigation.getUrl(), result.get(0).getUrl());
    }
}
