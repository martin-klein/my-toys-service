package com.example.mytoysapi.producer.wrapper;

import com.example.mytoysapi.common.enums.OrderTypeEnum;
import com.example.mytoysapi.core.FilterAdministration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreWrapperTest {
    @Autowired
    @InjectMocks
    private CoreWrapper coreWrapper;

    @Mock
    private FilterAdministration filterAdministration;

    @Captor
    private ArgumentCaptor<LinkedHashMap<String, OrderTypeEnum>> argumentCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void labelUrlPair() {

        String sortSpec = "Label:asc,Url:desc";
        LinkedHashMap<String, OrderTypeEnum> expected = new LinkedHashMap<>();
        expected.put("Label", OrderTypeEnum.ASC);
        expected.put("Url", OrderTypeEnum.DESC);

        coreWrapper.processMyToysData("Parent", sortSpec);

        verify(filterAdministration).processMyToysData(any(), argumentCaptor.capture());

        assertEquals(expected, argumentCaptor.getValue());
    }

    @Test
    public void labelOnly() {
        String sortSpec = "Label:desc";
        LinkedHashMap<String, OrderTypeEnum> expected = new LinkedHashMap<>();
        expected.put("Label", OrderTypeEnum.DESC);

        coreWrapper.processMyToysData("Parent", sortSpec);

        verify(filterAdministration).processMyToysData(any(), argumentCaptor.capture());

        assertEquals(expected, argumentCaptor.getValue());
    }

    @Test
    public void noOrderSpecified() {
        String sortSpec = "Label";
        LinkedHashMap<String, OrderTypeEnum> expected = new LinkedHashMap<>();
        expected.put("Label", OrderTypeEnum.NO_OP);

        coreWrapper.processMyToysData("Parent", sortSpec);

        verify(filterAdministration).processMyToysData(any(), argumentCaptor.capture());

        assertEquals(expected, argumentCaptor.getValue());
    }

    @Test
    public void noSortingSpecGiven() {

        coreWrapper.processMyToysData("Parent", null);

        verify(filterAdministration).processMyToysData(any(), argumentCaptor.capture());

        assertNull(argumentCaptor.getValue());
    }

}
