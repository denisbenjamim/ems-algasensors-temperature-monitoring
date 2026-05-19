package com.algaworks.algasensors.temperature.monitoring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorMonitoring;


public interface SensorMonitoringRepository extends JpaRepository<SensorMonitoring, SensorId> {
    
}
