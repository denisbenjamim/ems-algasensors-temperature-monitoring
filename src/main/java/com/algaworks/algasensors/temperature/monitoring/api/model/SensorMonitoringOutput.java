package com.algaworks.algasensors.temperature.monitoring.api.model;

import java.time.OffsetDateTime;

import org.jilt.Builder;

import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;

@Builder(factoryMethod = "builder")
public final class SensorMonitoringOutput {
    private SensorId id;
    private Double lastTemperature;
    private OffsetDateTime updateAt;
    private Boolean enable;

    public SensorMonitoringOutput() {}

    public SensorMonitoringOutput(SensorId id, Double lastTemperature, OffsetDateTime updateAt, Boolean enable) {
        this.id = id;
        this.lastTemperature = lastTemperature;
        this.updateAt = updateAt;
        this.enable = enable;
    }

    public SensorId getId() {
        return id;
    }
    public void setId(SensorId id) {
        this.id = id;
    }
    public Double getLastTemperature() {
        return lastTemperature;
    }
    public void setLastTemperature(Double lastTemperature) {
        this.lastTemperature = lastTemperature;
    }
    public OffsetDateTime getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(OffsetDateTime updateAt) {
        this.updateAt = updateAt;
    }
    public Boolean getEnable() {
        return enable;
    }
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
