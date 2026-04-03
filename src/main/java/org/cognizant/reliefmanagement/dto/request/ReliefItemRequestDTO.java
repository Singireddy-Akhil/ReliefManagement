package org.cognizant.reliefmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.cognizant.reliefmanagement.Enum.reliefStatus;
import org.cognizant.reliefmanagement.Enum.type;

@Data
public class ReliefItemRequestDTO {
    private Integer ItemId;

    @NotNull(message="Type cannot be blank")
    private type type;

    @NotBlank(message="Name cannot be blank")
    private String name;

    @PositiveOrZero(message = "Quantity must be at least 0")
    @NotNull(message = "Quantity cannot be Empty")
    private Integer quantity;

    @NotNull(message = " Unit must contain a value")
    private String unit;

    @NotNull(message = "Status must have a value")
    private reliefStatus status;



//    public Integer getItemId() {
//        return ItemId;
//    }
//    public void setItemId(Integer itemId) {
//        ItemId = itemId;
//    }
//    public type getType() { return type; }
//    public void setType(type type) { this.type = type; }
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//    public Integer getQuantity() { return quantity; }
//    public void setQuantity(Integer quantity) { this.quantity = quantity; }
//    public String getUnit() { return unit; }
//    public void setUnit(String unit) { this.unit = unit; }
//    public reliefStatus getStatus() { return status; }
//    public void setStatus(reliefStatus status) { this.status = status; }
}