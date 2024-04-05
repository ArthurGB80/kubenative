package org.acme;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;

public class Kafka {

    @Transactional
    @Incoming("power")
    public void consumePower(DevicePower power) {
        Log.info("updated device " + power.deviceName + " with power " + power.power);
        DevicePower.persist(power);
    }

}
