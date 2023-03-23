package com.beb.practicetest.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class Controller {
    @Autowired
    private DeviceApplicationService deviceService;

    @PostMapping("api/v1/device")
    public ResponseEntity<Device> getDeviceId(@Validated @RequestBody final Device request) {
        return ResponseEntity.ok().body(this.deviceService.createDevice(request));
    }

    @GetMapping("/api/v1/{id}/echo")
    @ResponseBody
    public ResponseEntity<List<Device>> getDeviceId(@PathVariable final long id) {
        System.out.println("id:"+ id);
        try {
            final List<Device> device=  deviceService.getAllDevices();
            return ResponseEntity.ok().body(device);
        }
        catch (final Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("api/v1/{id}/device")
    @ResponseBody
    public ResponseEntity<String> getDevice(@PathVariable final UUID id) {
        final Device returnedDevice = deviceService.getDeviceById(id);
        return ResponseEntity.ok().body(returnedDevice.getDeviceId().toString());
    }

    @GetMapping("api/v1/nocontent")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void nonContentFound() {

    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void badRequest() {


    }
}
