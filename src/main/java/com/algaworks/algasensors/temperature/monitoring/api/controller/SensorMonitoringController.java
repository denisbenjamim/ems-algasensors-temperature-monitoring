package com.algaworks.algasensors.temperature.monitoring.api.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algasensors.temperature.monitoring.api.model.SensorMonitoringOutput;
import com.algaworks.algasensors.temperature.monitoring.api.model.SensorMonitoringOutputBuilder;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorMonitoringBuilder;
import com.algaworks.algasensors.temperature.monitoring.domain.repository.SensorMonitoringRepository;

import io.hypersistence.tsid.TSID;

@RestController
@RequestMapping("/api/sensors/{sensorId}/monitoring")
public class SensorMonitoringController {
    private final SensorMonitoringRepository repository;

    public SensorMonitoringController(SensorMonitoringRepository repository) {
        this.repository = repository;
    }

    @DeleteMapping("/enabled")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable TSID sensorId) {
        final SensorMonitoring sensorMonitoring = findByIdOrDefault(sensorId);
        sensorMonitoring.setEnable(false);
        repository.saveAndFlush(sensorMonitoring);
    }

    @PutMapping("/enabled")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable TSID sensorId) {
        final SensorMonitoring sensorMonitoring = findByIdOrDefault(sensorId);
        sensorMonitoring.setEnable(true);
        repository.saveAndFlush(sensorMonitoring);
    }

    @GetMapping
    public SensorMonitoringOutput getDetail(@PathVariable TSID sensorId) {
        final SensorMonitoring sensorMonitoring = findByIdOrDefault(sensorId);

        return SensorMonitoringOutputBuilder.builder()
            .id(sensorMonitoring.getId())
            .enable(sensorMonitoring.getEnable())
            .lastTemperature(sensorMonitoring.getLastTemperature())
            .updateAt(sensorMonitoring.getUpdateAt())
        .build();
    }

    private SensorMonitoring findByIdOrDefault(TSID sensorId) {
        return repository.findById(new SensorId(sensorId))                
            .orElse(SensorMonitoringBuilder.builder()
                .id(new SensorId(sensorId))
                .enable(false)
                .lastTemperature(null)
                .updateAt(null)
            .build());
    }

}
