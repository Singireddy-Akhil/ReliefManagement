package org.cognizant.reliefmanagement.dto.response;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class CitizenDto {

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
    public enum CitizenStatus {
        ACTIVE,
        INACTIVE,
        VERIFIED,
        PENDING
    }

    private Integer citizenId;

//    @Column(name = "Name", nullable = false)
    private String name;

//    @Column(name = "DOB")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender")
    private Gender gender;

//    @Column(name = "Address", length = 500)
    private String address;

//    @Column(name = "ContactInfo")
    private String contactInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private CitizenStatus status;

    private Integer userId;
}
