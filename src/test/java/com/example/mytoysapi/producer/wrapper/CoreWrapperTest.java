package com.example.mytoysapi.producer.wrapper;

import com.example.mytoysapi.common.enums.OrderDirectionEnum;
import com.example.mytoysapi.core.MyToysApiTranformation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MyToysApiTranformation myToysApiTranformation;

    @Captor
    private ArgumentCaptor<LinkedHashMap<String, OrderDirectionEnum>> argumentCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void labelUrlPair() {

        String sortSpec = "Label:asc,Url:desc";
        LinkedHashMap<String, OrderDirectionEnum> expected = new LinkedHashMap<>();
        expected.put("Label", OrderDirectionEnum.ASC);
        expected.put("Url", OrderDirectionEnum.DESC);

        coreWrapper.processMyToysData("Parent", sortSpec);

        verify(myToysApiTranformation).processMyToysData(any(), argumentCaptor.capture());

        assertEquals(expected, argumentCaptor.getValue());
    }

    @Test
    public void labelOnly() {
        String sortSpec = "Label:desc";
        LinkedHashMap<String, OrderDirectionEnum> expected = new LinkedHashMap<>();
        expected.put("Label", OrderDirectionEnum.DESC);

        coreWrapper.processMyToysData("Parent", sortSpec);

        verify(myToysApiTranformation).processMyToysData(any(), argumentCaptor.capture());

        assertEquals(expected, argumentCaptor.getValue());
    }

    @Test
    public void noOrderSpecified() {
        String sortSpec = "Label";
        LinkedHashMap<String, OrderDirectionEnum> expected = new LinkedHashMap<>();
        expected.put("Label", OrderDirectionEnum.NO_OP);

        coreWrapper.processMyToysData("Parent", sortSpec);

        verify(myToysApiTranformation).processMyToysData(any(), argumentCaptor.capture());

        assertEquals(expected, argumentCaptor.getValue());
    }

    @Test
    public void noSortingSpecGiven() {

        coreWrapper.processMyToysData("Parent", null);

        verify(myToysApiTranformation).processMyToysData(any(), argumentCaptor.capture());

        assertNull(argumentCaptor.getValue());
    }

}
