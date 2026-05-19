package com.algaworks.algasensors.temperature.monitoring.domain.model;

import java.time.OffsetDateTime;

import org.jilt.Builder;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Builder(factoryMethod = "builder")
public class SensorMonitoring {
    @Id
    @AttributeOverride(
        name = "value", column = @Column(name="id", columnDefinition = "BIGINT")
    )
    private SensorId id;
    private Double lastTemperature;
    private OffsetDateTime updateAt;
    private Boolean enable;

    public SensorMonitoring(){}

    public SensorMonitoring(SensorId id, Double lastTemperature, OffsetDateTime updateAt, Boolean enable) {
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
