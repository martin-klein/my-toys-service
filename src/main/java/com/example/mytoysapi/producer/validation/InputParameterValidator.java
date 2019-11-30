package com.example.mytoysapi.producer.validation;

import com.example.mytoysapi.common.exception.InvalidSortParameterException;
import com.example.mytoysapi.common.exception.UnhealthyUpstreamServiceException;
import com.example.mytoysapi.core.impl.ProcFilterByNode;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static com.example.mytoysapi.common.constants.Messages.ILLEGAL_SORT_SPEC_CODE;
import static com.example.mytoysapi.common.constants.Messages.ILLEGAL_SORT_SPEC_MSG;

@Component
public class InputParameterValidator {

    @Autowired
    private Environment env;

    public void validateSortParameter(String sortParam) {
        if(!sortSpecValid(sortParam)) {
            String errorMessage = env.getProperty(ILLEGAL_SORT_SPEC_MSG);
            int errorCode = Integer.parseInt(env.getProperty(ILLEGAL_SORT_SPEC_CODE));
            LoggerFactory.getLogger(ProcFilterByNode.class).error(errorMessage);
            throw new InvalidSortParameterException(errorCode, errorMessage);
        }
    }

    private boolean sortSpecValid(String sortParam) {
        return sortParam==null || sortParam.isEmpty() || sortParam.matches("(((label|url)(:(asc|desc))?(,)?)+)");
    }
}
