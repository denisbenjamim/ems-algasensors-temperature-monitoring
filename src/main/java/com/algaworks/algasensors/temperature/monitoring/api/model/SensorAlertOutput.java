package com.algaworks.algasensors.temperature.monitoring.api.model;

import org.jilt.Builder;

import io.hypersistence.tsid.TSID;

@Builder(factoryMethod = "builder")
public class SensorAlertOutput {
    private TSID id;
    private Double minTemperature;
    private Double maxTemperature;

    public SensorAlertOutput() {}

    public SensorAlertOutput(TSID id, Double minTemperature, Double maxTemperature) {
        this.id = id;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public TSID getId() {
        return id;
    }
    public void setId(TSID id) {
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
