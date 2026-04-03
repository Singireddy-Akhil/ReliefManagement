package org.cognizant.reliefmanagement.dto.response;

import lombok.Data;
import org.cognizant.reliefmanagement.Enum.reliefStatus;
import org.cognizant.reliefmanagement.Enum.type;

import java.time.LocalDateTime;


@Data
public class ReliefItemResponseDTO {
    private Integer itemId;
    private type type;
    private String name;
    private Integer quantity;
    private String unit;
    private reliefStatus status;
    private LocalDateTime updatedAt;

    // Getters and Setters
    public Integer getItemId() { return itemId; }
    public void setItemId(Integer itemId) { this.itemId = itemId; }
    public type getType() { return type; }
    public void setType(type type) { this.type = type; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public reliefStatus getStatus() { return status; }
    public void setStatus(reliefStatus status) { this.status = status; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}