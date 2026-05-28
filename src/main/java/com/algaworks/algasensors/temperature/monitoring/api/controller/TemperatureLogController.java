package com.algaworks.algasensors.temperature.monitoring.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algasensors.temperature.monitoring.api.model.TemperatureLogOutput;
import com.algaworks.algasensors.temperature.monitoring.api.model.TemperatureLogOutputBuilder;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.model.TemperatureLog;
import com.algaworks.algasensors.temperature.monitoring.domain.repository.TemperatureLogRepository;

import io.hypersistence.tsid.TSID;

@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures")
public class TemperatureLogController {
    private final TemperatureLogRepository repository;

    public TemperatureLogController(TemperatureLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<TemperatureLogOutput> search(@PathVariable TSID sensorId, @PageableDefault Pageable pageable){
        Page<TemperatureLog> allBySensorId = repository.findAllBySensorId(new SensorId(sensorId), pageable);

        return allBySensorId.map(s -> TemperatureLogOutputBuilder.builder()
            .id(s.getId().getValue())
            .value(s.getValue())
            .registeredAt(s.getRegisteredAt())
            .sensorId(s.getSensorId().getValue())
        .build());
    }
}
