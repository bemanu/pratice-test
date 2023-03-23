package com.beb.praticetest.device;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceApplicationServiceTest {
    @Mock
    private DeviceRepository deviceRepo;
    @InjectMocks
    private DeviceApplicationService applicationService;

    @Test
    void givenDevice_whenGetSingle_ThenDeviceReturn(){
        final Device device = new Device("xxx",68,"xxx",123.45);
        when(deviceRepo.findById(any())).thenReturn(Optional.of(device));
        final Device returnDevice = applicationService.getDeviceById(any());
        assertNotNull(returnDevice);
        assertEquals(device.getRecordType(),"xxx");
        assertEquals(device.getFieldA(),68);
        assertEquals(device.getFieldB(),"xxx");
        assertEquals(device.getFieldC(),123.45);
        assertNotNull(device.getEventDateTime());

    }

    @Test
    void givenDevice_whenGetAll_ThenAllDevicesAreReturn(){
        final Device device1 = new Device("xxx",68,"xxx",123.45);
        final Device device2 = new Device("yyy",123,"xjjjxx",6789.99);
        final Device device3 = new Device("kkk",3545,"nnnn",5676.76);
        when(deviceRepo.findAll()).thenReturn(List.of(device1,device2,device3));
        final List<Device> returnDevice = applicationService.getAllDevices();

        assertEquals(returnDevice.size(),3);

        assertEquals(returnDevice.get(0).getRecordType(),"xxx");
        assertEquals(returnDevice.get(0).getFieldA(),68);
        assertEquals(returnDevice.get(0).getFieldB(),"xxx");
        assertEquals(returnDevice.get(0).getFieldC(),123.45);
        assertNotNull(returnDevice.get(0).getEventDateTime());

        assertEquals(returnDevice.get(1).getRecordType(),"yyy");
        assertEquals(returnDevice.get(1).getFieldA(),123);
        assertEquals(returnDevice.get(1).getFieldB(),"xjjjxx");
        assertEquals(returnDevice.get(1).getFieldC(),6789.99);
        assertNotNull(returnDevice.get(1).getEventDateTime());

        assertEquals(returnDevice.get(2).getRecordType(),"kkk");
        assertEquals(returnDevice.get(2).getFieldA(),3545);
        assertEquals(returnDevice.get(2).getFieldB(),"nnnn");
        assertEquals(returnDevice.get(2).getFieldC(),5676.76);
        assertNotNull(returnDevice.get(2).getEventDateTime());

    }

    @Test
    void givenDevice_whenGettingDeviceByRandomId_ThenException(){
        when(deviceRepo.findById(any())).thenReturn(Optional.empty());
         assertThrows(ResourceNotFoundException.class,()->applicationService.getDeviceById(UUID.randomUUID()));
    }

}