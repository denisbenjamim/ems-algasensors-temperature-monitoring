package com.algaworks.algasensors.temperature.monitoring.api.config.jackson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.hypersistence.tsid.TSID;

@Configuration
public class TSIDJacksonConfig {

    @Bean
    Module tsidModule(){
        return  new SimpleModule()
        .addSerializer(TSID.class, new TSIDToStringSerializer());
    }
}
