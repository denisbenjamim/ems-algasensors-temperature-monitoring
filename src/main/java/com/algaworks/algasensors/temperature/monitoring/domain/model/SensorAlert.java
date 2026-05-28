package com.algaworks.algasensors.temperature.monitoring.domain.model;

import org.jilt.Builder;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Builder(factoryMethod = "builder")
public class SensorAlert{
    @Id
    @AttributeOverride(
        name = "value", column = @Column(name="id", columnDefinition = "BIGINT")
    )
    private SensorId id;
    private Double minTemperature;
    private Double maxTemperature;

    public SensorAlert(){}

    public SensorAlert(SensorId id, Double minTemperature, Double maxTemperature) {
        this.id = id;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }
    public SensorId getId() {
        return id;
    }

    public void setId(SensorId id) {
        this.id = id;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    
}