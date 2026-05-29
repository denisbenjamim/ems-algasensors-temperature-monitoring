package com.algaworks.algasensors.temperature.monitoring.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.algaworks.algasensors.temperature.monitoring.api.config.jackson.TSIDJacksonConfig;
import com.algaworks.algasensors.temperature.monitoring.api.model.SensorAlertInput;
import com.algaworks.algasensors.temperature.monitoring.api.model.SensorAlertInputBuilder;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorAlert;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorAlertBuilder;
import com.algaworks.algasensors.temperature.monitoring.domain.model.SensorId;
import com.algaworks.algasensors.temperature.monitoring.domain.repository.SensorAlertRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.hypersistence.tsid.TSID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

@WebMvcTest(SensorAlertController.class)
@Import(TSIDJacksonConfig.class)
public class SensorAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private SensorAlertRepository repository;

    @Test
    void deveRetornar200QuandoAlertaExistir() throws Exception {
        final TSID tsid = TSID.fast();
        final SensorId sensorId = new SensorId(tsid);
        final SensorAlert output = SensorAlertBuilder.builder()
                .id(sensorId)
                .minTemperature(20.0)
                .maxTemperature(45.0)
                .build();

        when(repository.findById(sensorId)).thenReturn(Optional.of(output));

        mockMvc.perform(
            get("/api/sensors/{sensorId}/alert", tsid.toString()))
        .andExpect(status().isOk());
    }

    @Test
    void deveRetornar404QuandoAlertaNaoExistir() throws Exception {
        final TSID tsid = TSID.fast();
        final SensorId sensorId = new SensorId(tsid);

        when(repository.findById(sensorId)).thenReturn(Optional.empty());

        mockMvc.perform(
            get("/api/sensors/{sensorId}/alert", tsid.toString()))
        .andExpect(status().isNotFound());
    }

    @Test
    void deveCriarAlertaQuandoNaoExistir() throws Exception {
        final TSID tsid = TSID.fast();
        final SensorId sensorId = new SensorId(tsid);
        final SensorAlertInput input = SensorAlertInputBuilder.builder()
            .minTemperature(20.0)
            .maxTemperature(45.0)
        .build();

        when(repository.findById(sensorId)).thenReturn(Optional.empty());

        when(repository.saveAndFlush(any(SensorAlert.class))).thenReturn(SensorAlertBuilder.builder()
            .id(sensorId)
            .minTemperature(input.getMinTemperature())
            .maxTemperature(input.getMaxTemperature())
        .build());

        mockMvc.perform(
            put("/api/sensors/{sensorId}/alert", tsid.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(input)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(sensorId.toString()))
            .andExpect(jsonPath("$.minTemperature").value(input.getMinTemperature()))
            .andExpect(jsonPath("$.maxTemperature").value(input.getMaxTemperature()))
            .andReturn();
        verify(repository).saveAndFlush(any(SensorAlert.class));
    }

    @Test
    void deveAtualizarAlertaCasoExista() throws Exception {
        final TSID tsid = TSID.fast();
        final SensorId sensorId = new SensorId(tsid);

        final SensorAlert sensorAlert = SensorAlertBuilder.builder()
            .id(sensorId)
            .minTemperature(20.0)
            .maxTemperature(45.0)
        .build();

        final SensorAlertInput input = SensorAlertInputBuilder.builder()
            .minTemperature(60.0)
            .maxTemperature(85.0)
        .build();

        when(repository.findById(sensorId)).thenReturn(Optional.of(sensorAlert));

        when(repository.saveAndFlush(any(SensorAlert.class))).thenReturn(SensorAlertBuilder.builder()
            .id(sensorId)
            .minTemperature(input.getMinTemperature())
            .maxTemperature(input.getMaxTemperature())
        .build());

        mockMvc.perform(
            put("/api/sensors/{sensorId}/alert", tsid.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(input)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(sensorId.toString()))
            .andExpect(jsonPath("$.minTemperature").value(input.getMinTemperature()))
            .andExpect(jsonPath("$.maxTemperature").value(input.getMaxTemperature()))
        .andReturn();
        
        verify(repository).saveAndFlush(any(SensorAlert.class));
    }

    @Test
    void deveDeletarAlerta() throws Exception {
        final TSID tsid = TSID.fast();
        final SensorId sensorId = new SensorId(tsid);

        when(repository.findById(sensorId)).thenReturn(Optional.of(
            SensorAlertBuilder.builder()
                .id(sensorId)
                .minTemperature(20.0)
                .maxTemperature(45.0)
            .build()
        ));

         mockMvc.perform(
            delete("/api/sensors/{sensorId}/alert", tsid.toString())
        ).andExpect(status().isNoContent());

        verify(repository).delete(any(SensorAlert.class));
    }

    @Test
    void deveRetornar404CasoAlertaNaoExistaDuranteDelete() throws Exception {
        final TSID tsid = TSID.fast();
        final SensorId sensorId = new SensorId(tsid);

        when(repository.findById(sensorId)).thenReturn(Optional.of(
            SensorAlertBuilder.builder()
                .id(sensorId)
                .minTemperature(20.0)
                .maxTemperature(45.0)
            .build()
        ));

         mockMvc.perform(
            delete("/api/sensors/{sensorId}/alert", tsid.toString())
        ).andExpect(status().isNoContent());
    }
}
