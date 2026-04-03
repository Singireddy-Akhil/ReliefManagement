package org.cognizant.reliefmanagement.dto.response;

import java.time.LocalDateTime;

public class DistributionResponseDTO {
    private Integer distributionId;
    private Integer itemId;
    private Integer citizenId;  // ADDED
    private Integer officerId;  // ADDED
    private Integer quantity;
    private String status;      // CHANGED to String for better compatibility
    private LocalDateTime date;
    private String notes;

    // Getters and Setters
    public Integer getDistributionId() { return distributionId; }
    public void setDistributionId(Integer distributionId) { this.distributionId = distributionId; }

    public Integer getItemId() { return itemId; }
    public void setItemId(Integer itemId) { this.itemId = itemId; }

    public Integer getCitizenId() { return citizenId; } // ADDED
    public void setCitizenId(Integer citizenId) { this.citizenId = citizenId; } // ADDED

    public Integer getOfficerId() { return officerId; } // ADDED
    public void setOfficerId(Integer officerId) { this.officerId = officerId; } // ADDED

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getStatus() { return status; } // CHANGED
    public void setStatus(String status) { this.status = status; } // CHANGED

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}