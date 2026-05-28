package com.algaworks.algasensors.temperature.monitoring.domain.model;

import java.time.OffsetDateTime;

import org.jilt.Builder;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
@Builder(factoryMethod = "builder")
public class TemperatureLog {

    @Id
    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "uuid"))
    private TemperatureLogId id;

    @Column(name = "\"value\"")
    private Double value;

    private OffsetDateTime registeredAt;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "sensor_id", columnDefinition = "bigint"))
    private SensorId sensorId;

    public TemperatureLog() {}

    public TemperatureLog(TemperatureLogId id, Double value, OffsetDateTime registeredAt, SensorId sensorId) {
        this.id = id;
        this.value = value;
        this.registeredAt = registeredAt;
        this.sensorId = sensorId;
    }

    public TemperatureLogId getId() {
        return id;
    }

    public void setId(TemperatureLogId id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public OffsetDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(OffsetDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public SensorId getSensorId() {
        return sensorId;
    }

    public void setSensorId(SensorId sensorId) {
        this.sensorId = sensorId;
    }


    
    
    
}
