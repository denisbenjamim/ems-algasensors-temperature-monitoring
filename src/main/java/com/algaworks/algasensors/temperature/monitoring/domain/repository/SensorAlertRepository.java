package com.algaworks.algasensors.temperature.monitoring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorAlert;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;

public interface SensorAlertRepository extends JpaRepository<SensorAlert, SensorId> {
    
}
