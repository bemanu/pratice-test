package com.beb.praticetest.device;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Device device ;

    @Test
    public void whenPostDevice_thenReturnStatusCreatedAndDevice() throws Exception {
        device =  new Device("xxx",68,"xxx",123.45);
        mvc.perform(post("/api/v1/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(device)))
                .andExpect(status().isOk())

                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));


    }

    @Test
    public void whenGetDeviceWithInvalidId_thenReturnNotFoundReturned() throws Exception {
        mvc.perform(get("/api/v1/78988/echo")
                        .contentType(MediaType.APPLICATION_JSON).contentType(String.valueOf(Device.class)))
                .andExpect(status().isOk());


    }
    @Test
    public void whenGetNoContent_thenReturnNoContentResponse() throws Exception {

        mvc.perform(get("/api/v1/nocontent")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    @Test
    public void whenGetDevice_thenReturnStatusOKAndDevice() throws Exception {

        JsonMapper jsonMapper = new JsonMapper();
        jsonMapper.registerModule(new JavaTimeModule());


        device =  new Device("xxx",68,"xxx",123.45);
       MvcResult response = mvc.perform(post("/api/v1/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(device)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andReturn();

        String jsonString = response.getResponse().getContentAsString();
        Device createdDevice = new ObjectMapper().readValue(jsonString, Device.class);
        UUID deviceId = createdDevice.getDeviceId();

        MvcResult getResponse =  mvc.perform(get("/api/v1/"+deviceId+"/device")
                        . contentType(MediaType.APPLICATION_JSON).contentType(String.valueOf(String.class)))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    public void whenGetRandomURI_thenReturnStatusBadRequest() throws Exception {

        mvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }
}