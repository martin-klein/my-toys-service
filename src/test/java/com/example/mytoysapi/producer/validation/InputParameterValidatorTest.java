package com.example.mytoysapi.producer.validation;

import com.example.mytoysapi.common.exception.InvalidSortParameterException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InputParameterValidatorTest {
    @Autowired
    private InputParameterValidator inputParameterValidator;

    @Test
    public void validInput() {
        String labelAscUrlDesc = "label:asc,url:desc";
        inputParameterValidator.validateSortParameter(labelAscUrlDesc);

        String label = "label";
        inputParameterValidator.validateSortParameter(label);

        String url = "url:asc";
        inputParameterValidator.validateSortParameter(url);

        String urlAscLabelDesc = "url:asc,label:desc";
        inputParameterValidator.validateSortParameter(urlAscLabelDesc);
    }

    @Test(expected = InvalidSortParameterException.class)
    public void illegalSortCriterium() {
        String illegalSortCriterium = "Test:asc";
        inputParameterValidator.validateSortParameter(illegalSortCriterium);
    }

    @Test(expected = InvalidSortParameterException.class)
    public void missingColon() {
        String missingColon = "Labelasc";
        inputParameterValidator.validateSortParameter(missingColon);
    }

    /**
     * TODO: Improve Regex
     */
    @Test(expected = InvalidSortParameterException.class)
    @Ignore
    public void missingComma() {
        String missingComma = "Label:ascUrl:desc";
        inputParameterValidator.validateSortParameter(missingComma);
    }

    @Test(expected = InvalidSortParameterException.class)
    public void illegalOrderDirection() {
        String illegalOrderDirection = "Url:test";
        inputParameterValidator.validateSortParameter(illegalOrderDirection);
    }

    /**
     * Sort param may be empty. No exception expected.
     */
    @Test
    public void sortSpecificationEmpty() {
        String empty = "";
        inputParameterValidator.validateSortParameter(empty);
    }

    /**
     * Sort param may be null. No exception expected.
     */
    @Test
    public void sortSpecificationNull() {
        inputParameterValidator.validateSortParameter(null);
    }
}
