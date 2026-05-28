package com.algaworks.algasensors.temperature.monitoring.api.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.jilt.Builder;

import io.hypersistence.tsid.TSID;

@Builder(factoryMethod = "builder")
public class TemperatureLogOutput {
    private UUID id;
    private TSID sensorId;
    private OffsetDateTime registeredAt;
    private Double value;

    public TemperatureLogOutput(){}

    public TemperatureLogOutput(UUID id, TSID sensorId, OffsetDateTime registeredAt, Double value) {
        this.id = id;
        this.sensorId = sensorId;
        this.registeredAt = registeredAt;
        this.value = value;
    }

    @Override
    public String toString() {
        return "TemperatureLogOutput [id=" + id + ", sensorId=" + sensorId + ", registeredAt=" + registeredAt
                + ", value=" + value + "]";
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public TSID getSensorId() {
        return sensorId;
    }
    public void setSensorId(TSID sensorId) {
        this.sensorId = sensorId;
    }
    public OffsetDateTime getRegisteredAt() {
        return registeredAt;
    }
    public void setRegisteredAt(OffsetDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    
}
