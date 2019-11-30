package com.example.mytoysapi.core.impl;

import com.example.mytoysapi.common.exception.LabelNotFoundException;
import com.example.mytoysapi.consumer.model.Navigable;
import com.example.mytoysapi.consumer.model.Navigation;
import com.example.mytoysapi.consumer.model.NavigationEntry;
import com.example.mytoysapi.consumer.model.NavigationEntryCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcFilterByNodeTest {
    @Autowired
    private ProcFilterByNode procFilterByNode;

    @Test
    public void testFilterByNode() {
        Navigation navigation = NavigationEntryCreator.createNavigation();
        Navigable childProducts = NavigationEntryCreator.addNodeTo(navigation, "Child Products");
        Navigable toys = NavigationEntryCreator.addNodeTo(childProducts, "Toys");
        NavigationEntryCreator.addNodeTo(childProducts, "Cribs");
        NavigationEntryCreator.addLinkTo(toys, "Test Link");
        NavigationEntryCreator.addLinkTo(toys, "Another Test Link");

        Navigable result = procFilterByNode.filter(navigation, "Toys");

        assertEquals("Toys", result.getLabel());
        assertEquals("Test Link", result.getChildren().get(0).getLabel());
        assertEquals("Another Test Link", result.getChildren().get(1).getLabel());
        assertEquals(2, result.getChildren().size());
    }

    @Test
    public void testFilterByLeaf() {
        Navigation navigation = NavigationEntryCreator.createNavigation();
        Navigable childProducts = NavigationEntryCreator.addNodeTo(navigation, "Child Products");
        Navigable toys = NavigationEntryCreator.addNodeTo(childProducts, "Toys");
        NavigationEntryCreator.addNodeTo(childProducts, "Cribs");
        NavigationEntryCreator.addLinkTo(toys, "Test Link");
        NavigationEntryCreator.addLinkTo(toys, "Another Test Link");

        Navigable result = procFilterByNode.filter(navigation, "Another Test Link");

        assertEquals("Another Test Link", result.getLabel());
    }

    @Test
    public void testFilterByEmptyOrNull() {
        Navigation navigation = NavigationEntryCreator.createNavigation();
        Navigable childProducts = NavigationEntryCreator.addNodeTo(navigation, "Child Products");
        Navigable toys = NavigationEntryCreator.addNodeTo(childProducts, "Toys");
        NavigationEntryCreator.addNodeTo(childProducts, "Cribs");
        NavigationEntryCreator.addLinkTo(toys, "Test Link");
        NavigationEntryCreator.addLinkTo(toys, "Another Test Link");

        Navigable result = procFilterByNode.filter(navigation, null);

        Boolean resultIsNavigation = result instanceof Navigation;
        assertTrue(resultIsNavigation);

        result = procFilterByNode.filter(navigation, "");
        resultIsNavigation = result instanceof Navigation;
        assertTrue(resultIsNavigation);
    }

    @Test(expected = LabelNotFoundException.class)
    public void testFilterByNotExistingNode() {
        Navigation navigation = NavigationEntryCreator.createNavigation();
        Navigable childProducts = NavigationEntryCreator.addNodeTo(navigation, "Child Products");
        Navigable toys = NavigationEntryCreator.addNodeTo(childProducts, "Toys");
        NavigationEntryCreator.addNodeTo(childProducts, "Cribs");
        NavigationEntryCreator.addLinkTo(toys, "Test Link");
        NavigationEntryCreator.addLinkTo(toys, "Another Test Link");

        procFilterByNode.filter(navigation, "Not existing node");
    }
}
