package org.cognizant.reliefmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be greater than zero")
    private Integer userId;

    @NotBlank(message = "Action cannot be blank")
    private String action;

    private String resource;

    private LocalDateTime timestamp;

    private String ipAddress;

    private String details;

    // Explicit getters & setters
}
