package org.cognizant.reliefmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.cognizant.reliefmanagement.Enum.shelterStatus;

import java.time.LocalDateTime;

@Getter // Lombok generates all getters
@Setter // Lombok generates all setters
@Entity
@Table(name="Shelter")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shelterId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Location")
    private String location;

    @Column(name = "Latitude")
    private Double latitude;

    @Column(name = "Longitude")
    private Double longitude;

    @Column(name = "Capacity")
    private int capacity;

    @Column(name = "Occupancy")
    private int occupancy;

    @Column(name = "Status")
    private shelterStatus status;

    @Column(name = "ContactInfo")
    private String contactInfo;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @Column(name= "UpdatedAt")
    private LocalDateTime updatedAt;
}
