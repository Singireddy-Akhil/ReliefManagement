package org.cognizant.reliefmanagement.dto.response;

import org.cognizant.reliefmanagement.Enum.shelterStatus;

import java.time.LocalDateTime;

public class ShelterResponseDTO {
    private int shelterId;
    private String name;
    private String location;
    private int capacity;
    private int occupancy;
    private shelterStatus status;
    private LocalDateTime updatedAt;

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    private String contactInfo;

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public shelterStatus getStatus() {
        return status;
    }

    public void setStatus(shelterStatus status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}