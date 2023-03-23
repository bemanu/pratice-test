package com.beb.practicetest.device;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class DeviceTest {


    @Test
    void constructorTest(){
        final Device device = new Device("yyy",56,"tttt",56.8);

        assertNotNull(device);
        assertEquals(device.getFieldA(),56);
        assertEquals(device.getFieldB(),"tttt");
        assertEquals(device.getFieldC(),56.8);
        assertNotNull(device.getEventDateTime());


    }

    @Test
    void testToString(){
        final Device device = new Device("yyy",68,"xxx",123.45);
        device.setEventDateTime(LocalDateTime.of(2014,05,12,05,9,48));

        final String expected = "Device{deviceId=null, recordType='yyy', eventDateTime=2014-05-12T05:09:48, fieldA=68, fieldB='xxx', fieldC=123.45}";
        assertEquals(expected,device.toString());
    }

}