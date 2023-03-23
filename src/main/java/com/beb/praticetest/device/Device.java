package com.beb.praticetest.device;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="devices")
@EntityListeners(AuditListener.class)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID deviceId;

    @NonNull
    @Column(name = "recordtype")
    private String recordType;
    @CreationTimestamp
    @Column(name = "eventdatetime")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime eventDateTime;

    @NonNull
    @Column(name = "fielda")
    private int fieldA ;

    @Column(name = "fieldb")
    private String fieldB ;

    @Column(name = "fieldc")
    private double fieldC ;

    public Device() {
    }

    public Device(final String recordType,final int fieldA, final String fieldB, final double fieldC) {
        this.recordType = recordType;
        this.eventDateTime = LocalDateTime.now();
        this.fieldA = fieldA;
        this.fieldB = fieldB;
        this.fieldC = fieldC;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    @NonNull
    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(@NonNull String recordType) {
        this.recordType = recordType;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public int getFieldA() {
        return fieldA;
    }

    public void setFieldA(int fieldA) {
        this.fieldA = fieldA;
    }

    public String getFieldB() {
        return fieldB;
    }

    public void setFieldB(String fieldB) {
        this.fieldB = fieldB;
    }

    public double getFieldC() {
        return fieldC;
    }

    public void setFieldC(double fieldC) {
        this.fieldC = fieldC;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return fieldA == device.fieldA && Double.compare(device.fieldC, fieldC) == 0 && Objects.equals(deviceId, device.deviceId) && recordType.equals(device.recordType) && Objects.equals(eventDateTime, device.eventDateTime) && Objects.equals(fieldB, device.fieldB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, recordType, eventDateTime, fieldA, fieldB, fieldC);
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId=" + deviceId +
                ", recordType='" + recordType + '\'' +
                ", eventDateTime=" + eventDateTime +
                ", fieldA=" + fieldA +
                ", fieldB='" + fieldB + '\'' +
                ", fieldC=" + fieldC +
                '}';
    }
}
