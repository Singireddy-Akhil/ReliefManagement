package org.cognizant.reliefmanagement.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.cognizant.reliefmanagement.Enum.shelterStatus;

@Data
public class ShelterRequestDTO {
    private Integer shelterId;
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private Double longitude;

    @DecimalMin(value = "1", message = "Quantity must be at least 1")
    @NotNull(message = "Capacity cannot be null")
    private int capacity;

    @Positive(message = "Occupancy cannot be negative")
    @NotNull(message = "Occupancy cannot be Empty")
    private int occupancy;

    @NotNull(message = "Status cannot be Empty")
    private shelterStatus status;

    @NotNull(message = "Contact Info cannot be empty")
    @Pattern(regexp = ".*@.*", message = "Contact Info must contain the '@' symbol")
    private String contactInfo;

}