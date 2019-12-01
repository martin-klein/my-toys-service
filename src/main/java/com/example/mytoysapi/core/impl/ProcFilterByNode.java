package com.example.mytoysapi.core.impl;

import com.example.mytoysapi.common.exception.LabelNotFoundException;
import com.example.mytoysapi.consumer.model.Navigable;
import com.example.mytoysapi.consumer.model.Navigation;
import com.example.mytoysapi.consumer.model.NavigationEntry;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static com.example.mytoysapi.common.constants.Messages.NOT_EXISTING_LABEL_CODE;
import static com.example.mytoysapi.common.constants.Messages.NOT_EXISTING_LABEL_MSG;

/**
 * A procedure that filters navigation entries by the provided filter label.
 */
@Component
public class ProcFilterByNode {

    @Autowired
    private Environment env;

    public Navigable filter(Navigation navigation, String filterLabel) {
        if(filterLabel == null || filterLabel.isEmpty()) {
            return navigation;
        }

        Navigable result = search(navigation, filterLabel);

        if(result == null) {
            //The label does not exist.
            logAndThrow();
        }

        return result;
    }

    private Navigable search(Navigable navigable, String filterLabel) {
        Navigable result = null;
        if(navigable.getLabel()!=null && navigable.getLabel().equals(filterLabel)) {
            return navigable;
        } else {
            if (navigable.hasChildren()) {
                for(NavigationEntry child : navigable.getChildren()) {
                    result = search(child, filterLabel);
                    if(result != null) {
                        return result;
                    }
                }
            }
        }
        return null;
    }

    private void logAndThrow() {
        String errorMessage = env.getProperty(NOT_EXISTING_LABEL_MSG);
        int errorCode = Integer.parseInt(env.getProperty(NOT_EXISTING_LABEL_CODE));
        LoggerFactory.getLogger(ProcFilterByNode.class).error(errorMessage);
        throw new LabelNotFoundException(errorCode, errorMessage);
    }
}
