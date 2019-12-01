package com.example.mytoysapi.core.mapper;

import com.example.mytoysapi.consumer.model.Navigable;
import com.example.mytoysapi.consumer.model.NavigationEntry;
import com.example.mytoysapi.core.model.NavigationLink;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps a {@link Navigable} to a list of {@link NavigationLink}.
 */
@Component
public class NavigationEntryMapper {
    public List<NavigationLink> map(Navigable source) {
        StringBuilder stringBuilder = new StringBuilder();
        List<NavigationLink> links = new ArrayList<>();
        buildCompleteLeafPaths(source, stringBuilder, links);
        return links;
    }

    private void buildCompleteLeafPaths(Navigable elem, StringBuilder path, List<NavigationLink> links) {
        final int pathLen = path.length();
        if (pathLen != 0) {
            path.append(" - ");
        }
        path.append(elem.getLabel());
        if (elem.hasChildren()) {
            for (NavigationEntry child : elem.getChildren()) {
                buildCompleteLeafPaths(child, path, links);
            }
        } else {
            NavigationLink navigationLink = new NavigationLink();
            navigationLink.setLabel(removeRoot(path));
            navigationLink.setUrl(elem.getUrl());
            links.add(navigationLink);
        }
        path.setLength(pathLen);
    }

    private String removeRoot(StringBuilder completePathString) {
        return completePathString.substring(completePathString.indexOf("-") + 2);
    }
}
