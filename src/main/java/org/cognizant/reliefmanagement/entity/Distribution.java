package org.cognizant.reliefmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.cognizant.reliefmanagement.Enum.distributionStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "distribution")
@Data                       // Generates getters, setters, toString, etc.
@NoArgsConstructor          // Required by JPA
@AllArgsConstructor// Required by @Builder
@Builder(toBuilder = true)
public class Distribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DistributionID") // CRITICAL: Matches your SQL PK exactly
    private Integer distributionId;

    @Column(name = "ItemID", nullable = false)
//    @ManyToOne
//    @JoinColumn(name = "reliefItem")
    private Integer itemId;

    @Column(name = "CitizenID")
    private Integer citizenId;

    @Column(name = "OfficerID")
      private Integer officerId;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Date") // CRITICAL: Matches your SQL 'Date' column
    private LocalDateTime date;

    @Column(name = "Status") // CRITICAL: Matches your SQL 'Status' column
    @Enumerated(EnumType.STRING)
    private distributionStatus status;

    @Column(name = "notes") // Matches your lowercase 'notes' in schema
    private String notes;

}