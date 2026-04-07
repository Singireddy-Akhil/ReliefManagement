package org.cognizant.reliefmanagement.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public enum Role{
        CITIZEN, OFFICER, MANAGER, ADMIN, COMPLIANCE, AUDITOR
    }
    public enum UserStatus {
        ACTIVE, INACTIVE, SUSPENDED;

    }
    private Integer userId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

    private String phone;

    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private Integer citizenId;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
