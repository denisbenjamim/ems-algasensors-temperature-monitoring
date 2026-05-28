package com.algaworks.algasensors.temperature.monitoring.domain.model;

import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class TemperatureLogId {
    private UUID value;

    public TemperatureLogId() {
    }

    public TemperatureLogId(UUID value) {
        this.value = value;
    }

     public TemperatureLogId(String value) {
        this.value = UUID.fromString(value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TemperatureLogId other = (TemperatureLogId) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public UUID getValue() {
        return value;
    }

    public void setValue(UUID value) {
        this.value = value;
    }
   
}
