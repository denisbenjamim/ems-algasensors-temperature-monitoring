package com.algaworks.algasensors.temperature.monitoring.api.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algasensors.temperature.monitoring.api.model.SensorAlertInput;
import com.algaworks.algasensors.temperature.monitoring.api.model.SensorAlertOutput;
import com.algaworks.algasensors.temperature.monitoring.api.model.SensorAlertOutputBuilder;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorAlert;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorAlertBuilder;
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
        SensorAlert sensorAlert = findById(sensorId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return createSensorAlertOutput(sensorAlert);
    }
    
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public SensorAlertOutput createOrUpdate(@PathVariable TSID sensorId, SensorAlertInput input){
        SensorAlert sensorAlert = findById(sensorId).orElse(
            SensorAlertBuilder.builder()
                .minTemperature(input.getMinTemperature())
                .maxTemperature(input.getMaxTemperature())
            .build()
        );

        sensorAlert = repository.saveAndFlush(sensorAlert);

        return createSensorAlertOutput(sensorAlert);
    }

    private SensorAlertOutput createSensorAlertOutput(SensorAlert sensorAlert) {
        return SensorAlertOutputBuilder.builder()
            .id(sensorAlert.getId().getValue())
            .minTemperature(sensorAlert.getMinTemperature())
            .maxTemperature(sensorAlert.getMaxTemperature())
        .build();
    }

    private Optional<SensorAlert> findById(TSID sensorId) {
        return repository.findById(new SensorId(sensorId));
    }
}
