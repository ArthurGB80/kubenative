package org.acme;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;

public class Kafka {

    @Transactional
    @Incoming("power")
    public void consumePower(String message) { 
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DevicePower power = objectMapper.readValue(message, DevicePower.class); 
            Log.info("updated device " + power.getDeviceName() + " with power " + power.getPower());
            PanacheEntityBase.persist(power);
        } catch (Exception e) {
            Log.error("Error processing message", e);
        }
    }
}
