package com.beb.praticetest.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class DeviceApplicationService {
    @Autowired
    private DeviceRepository deviceRepository;


    public Device createDevice(final Device device) {
        return deviceRepository.save(device);
    }


    public Device getDeviceById(final UUID deviceId) {
        final Optional<Device> device = deviceRepository.findById(deviceId);

        if(device.isPresent()) {
            return device.get();
        }else {
            throw new ResourceNotFoundException("Could not find device with the given id");
        }

    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
}
