package com.example.mytoysapi.consumer.model;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NavigationEntryCreator {

    private NavigationEntryCreator() {
    }

    public static Navigation createNavigation() {
        Navigation navigation = new Navigation();
        navigation.setNavigationEntries(new ArrayList<>());
        return navigation;
    }

    public static NavigationEntry createNavigationEntry() {
        NavigationEntry navigationEntry = new NavigationEntry();
        navigationEntry.setChildren(new ArrayList<>());
        return navigationEntry;
    }

    public static Navigable addNodeTo(Navigable parent, String labelName) {
        NavigationEntry child = createNavigationEntry();
        child.setLabel(labelName);
        child.setType("Node");

        parent.getChildren().add(child);

        return child;
    }

    public static Navigable addLinkTo(Navigable parent, String labelName) {
        return addLinkTo(parent, labelName, null);
    }

    public static Navigable addLinkTo(Navigable parent, String labelName, String url) {
        NavigationEntry link = createNavigationEntry();
        link.setLabel(labelName);
        link.setType("Link");
        link.setUrl(url);

        parent.getChildren().add(link);
        return parent;
    }
}
