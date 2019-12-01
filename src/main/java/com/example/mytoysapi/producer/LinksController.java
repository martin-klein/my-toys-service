package com.example.mytoysapi.producer;

import com.example.mytoysapi.common.exception.MyToysServiceException;
import com.example.mytoysapi.core.model.NavigationLink;
import com.example.mytoysapi.producer.validation.InputParameterValidator;
import com.example.mytoysapi.producer.wrapper.CoreWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Controller for the "/links" resource.
 */
@RestController
@RequestMapping("/links")
public class LinksController {

    @Autowired
    private CoreWrapper coreWrapper;

    @Autowired
    private InputParameterValidator inputParameterValidator;

    @RequestMapping
    public List<NavigationLink> getMyToysLinks(@RequestParam(value = "parent", required = false) String parentLabel,
                                               @RequestParam(value = "sort", required = false) String sortSpec) throws MyToysServiceException {
        inputParameterValidator.validateSortParameter(sortSpec);
        return coreWrapper.processMyToysData(parentLabel, sortSpec);
    }
}
