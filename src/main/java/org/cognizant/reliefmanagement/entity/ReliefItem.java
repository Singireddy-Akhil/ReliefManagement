package org.cognizant.reliefmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.cognizant.reliefmanagement.Enum.reliefStatus;
import org.cognizant.reliefmanagement.Enum.type;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="reliefitem")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReliefItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ItemId", nullable=false, unique=true)
    private int itemId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Added this to prevent mapping errors with Enums
    private type type;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer quantity;

    @Column
    private String unit;

    @Column
    @Enumerated(EnumType.STRING)
    private reliefStatus status;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;
}