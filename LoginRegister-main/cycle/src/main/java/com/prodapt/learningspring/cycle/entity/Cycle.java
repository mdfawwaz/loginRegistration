package com.prodapt.learningspring.cycle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import lombok.Data;

@Entity
@Data
public class Cycle {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    public Long id;

    public String name;

    public boolean available = true;
    
    public String status; // Add the status field

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
        this.status = available ? "Available" : "Rented";
    }

}

