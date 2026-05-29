package com.algaworks.algasensors.temperature.monitoring.api.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    final SensorAlertRepository repository;

    public SensorAlertController(SensorAlertRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SensorAlertOutput getAlerta(@PathVariable TSID sensorId) {
        SensorAlert sensorAlert = findrByIdOrResponseNotFound(sensorId);
        return createSensorAlertOutput(sensorAlert);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public SensorAlertOutput createOrUpdate(@PathVariable TSID sensorId, @RequestBody SensorAlertInput input) {
        SensorAlert sensorAlert = getSensorAlertCreateOrUpdate(sensorId, input);

        sensorAlert = repository.saveAndFlush(sensorAlert);

        return createSensorAlertOutput(sensorAlert);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSensorAlert(@PathVariable TSID sensorId) {
        SensorAlert sensorAlert = findrByIdOrResponseNotFound(sensorId);

        repository.delete(sensorAlert);
    }

    private SensorAlert getSensorAlertCreateOrUpdate(TSID sensorId, SensorAlertInput input) {
        SensorAlert orElse = findById(sensorId).orElse(
                SensorAlertBuilder.builder()
                    .minTemperature(input.getMinTemperature())
                    .maxTemperature(input.getMaxTemperature())
                .build());
        return orElse;
    }

    private SensorAlert findrByIdOrResponseNotFound(TSID sensorId) {
        return findById(sensorId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private SensorAlertOutput createSensorAlertOutput(SensorAlert sensorAlert) {
        return SensorAlertOutputBuilder.builder()
            .id(sensorAlert.getId().getValue())
            .minTemperature(sensorAlert.getMinTemperature())
            .maxTemperature(sensorAlert.getMaxTemperature())
        .build();
    }

    private Optional<SensorAlert> findById(TSID sensorId) {
        Optional<SensorAlert> byId = repository.findById(new SensorId(sensorId));
        return byId;
    }
}
