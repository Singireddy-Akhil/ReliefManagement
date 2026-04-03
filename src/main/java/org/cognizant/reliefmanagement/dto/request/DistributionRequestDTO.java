package org.cognizant.reliefmanagement.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class DistributionRequestDTO {

    private Integer distributionId;

    @Positive(message="Item ID cant be negative and cannot have decimals")
    @NotNull(message = "Item ID cannot be Empty")
    private Integer itemId;

    @Positive(message="Citizen ID cant be negative and cannot have decimals")
    @NotNull(message = "Citizen ID cannot be Empty")
    private Integer citizenId;

    @Positive(message="Officer ID cant be negative and cannot have decimals")
    @NotNull(message = "Officer ID cannot be Empty")
    private Integer officerId;

    @PositiveOrZero(message = "Quantity must be at least 0")
    @NotNull(message = "Quantity cannot be Empty")
    private Integer quantity;

    @NotBlank(message = "Notes cannot be null")
    private String notes;

    // Receiving status as a String is much more stable for JSON input
    @NotNull(message = "Please provide a valid status")
    private String status;

    // Standard Getters and Setters
//    public Integer getDistributionId() { return distributionId; }
//    public void setDistributionId(Integer distributionId) { this.distributionId = distributionId; }
//
//    public Integer getItemId() { return itemId; }
//    public void setItemId(Integer itemId) { this.itemId = itemId; }
//
//    public Integer getCitizenId() { return citizenId; }
//    public void setCitizenId(Integer citizenId) { this.citizenId = citizenId; }
//
//    public Integer getOfficerId() { return officerId; }
//    public void setOfficerId(Integer officerId) { this.officerId = officerId; }
//
//    public Integer getQuantity() { return quantity; }
//    public void setQuantity(Integer quantity) { this.quantity = quantity; }
//
//    public String getNotes() { return notes; }
//    public void setNotes(String notes) { this.notes = notes; }
//
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
}