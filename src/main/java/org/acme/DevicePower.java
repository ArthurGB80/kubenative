package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class DevicePower extends PanacheEntity {
    public String deviceName;
    public int power;    
}
