package com.algaworks.algasensors.temperature.monitoring.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algasensors.temperature.monitoring.api.model.SensorAlertOutput;
import com.algaworks.algasensors.temperature.monitoring.api.model.SensorAlertOutputBuilder;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorAlert;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.repository.SensorAlertRepository;

import io.hypersistence.tsid.TSID;

@RestController
@RequestMapping("/api/sensors/{sensorId}/alert")
public class SensorAlertController {

    SensorAlertRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SensorAlertOutput getAlerta(@PathVariable TSID sensorId){
        SensorAlert sensorAlert = repository.findById(new SensorId(sensorId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return SensorAlertOutputBuilder.builder()
            .id(sensorAlert.getId().getValue())
            .minTemperature(sensorAlert.getMinTemperature())
            .maxTemperature(sensorAlert.getMaxTemperature())
        .build();
    }
    
}
