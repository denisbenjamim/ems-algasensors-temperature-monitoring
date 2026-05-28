package com.algaworks.algasensors.temperature.monitoring.api.model;

import org.jilt.Builder;

@Builder(factoryMethod = "builder")
public class SensorAlertInput {
    private Double minTemperature;
    private Double maxTemperature;

    public SensorAlertInput() {}

    public SensorAlertInput(Double minTemperature, Double maxTemperature) {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
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
