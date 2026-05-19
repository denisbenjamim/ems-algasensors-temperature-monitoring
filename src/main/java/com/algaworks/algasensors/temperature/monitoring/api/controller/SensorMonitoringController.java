package com.algaworks.algasensors.temperature.monitoring.api.controller;

import java.time.OffsetDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algasensors.temperature.monitoring.api.model.SensorMonitoringBuilder;
import com.algaworks.algasensors.temperature.monitoring.api.model.SensorMonitoringOutput;
import com.algaworks.algasensors.temperature.monitoring.api.model.SensorMonitoringOutputBuilder;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.algaworks.algasensors.temperature.monitoring.domain.repository.SensorMonitoringRepository;

import io.hypersistence.tsid.TSID;

@RestController
@RequestMapping("/api/sensors/{sensorId}/monitoring")
public class SensorMonitoringController {
    private final SensorMonitoringRepository repository;

    public SensorMonitoringController(SensorMonitoringRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public SensorMonitoringOutput getDetail(@PathVariable TSID sensorId){
        final SensorMonitoring sensorMonitoring = repository.findById(new SensorId(sensorId))
            .orElse(SensorMonitoringBuilder.sensorMonitoring()
                .id(new SensorId(sensorId))
                .enable(false)
                .lastTemperature(null)
                .updateAt(OffsetDateTime.now())
            .build());

        return SensorMonitoringOutputBuilder.builder()
            .id(sensorMonitoring.getId())
            .enable(sensorMonitoring.getEnable())
            .lastTemperature(sensorMonitoring.getLastTemperature())
            .updateAt(sensorMonitoring.getUpdateAt())
        .build();
    }
    
}
