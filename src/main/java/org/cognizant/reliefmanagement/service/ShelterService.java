package org.cognizant.reliefmanagement.service;

import org.cognizant.reliefmanagement.client.UserService;
import org.cognizant.reliefmanagement.dao.ShelterRepository;
import org.cognizant.reliefmanagement.dto.request.AuditLogDTO;
import org.cognizant.reliefmanagement.dto.request.ShelterRequestDTO;
import org.cognizant.reliefmanagement.dto.response.ShelterResponseDTO;
import org.cognizant.reliefmanagement.entity.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;
    @Autowired
    private UserService userService;

    // 1. Save a new shelter using RequestDTO
    public ShelterResponseDTO addShelter(ShelterRequestDTO dto) {
        // Map DTO to Entity (Matching your Capitalized Entity fields)
        Shelter shelter = new Shelter();
        shelter.setName(dto.getName());
        shelter.setLocation(dto.getLocation());
        shelter.setLatitude(dto.getLatitude());
        shelter.setLongitude(dto.getLongitude());
        shelter.setCapacity(dto.getCapacity());
        shelter.setOccupancy(dto.getOccupancy());
        shelter.setStatus(dto.getStatus());
        shelter.setContactInfo(dto.getContactInfo());

        // Handle the timestamps that were "missing" from the DTO
        shelter.setCreatedAt(LocalDateTime.now());
        shelter.setUpdatedAt(LocalDateTime.now());

        // Save Entity to DB
        Shelter savedShelter = shelterRepository.save(shelter);

        // Convert saved Entity back to ResponseDTO
        userService.createLog(AuditLogDTO.builder()
                .userId(1)
                .action("ADD_SHELTER")
                .resource("Shelter: " + savedShelter.getName())
                .details("Created shelter at " + savedShelter.getLocation() + " with capacity " + savedShelter.getCapacity())
                .timestamp(LocalDateTime.now())
                .build());
        return mapToResponseDTO(savedShelter);
    }

    // 2. Get all shelters converted to ResponseDTOs
    public List<ShelterResponseDTO> getAllShelters() {
        List<Shelter> shelters = shelterRepository.findAll();

        // Convert the list of Entities to a list of DTOs
        return shelters.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public ShelterResponseDTO getShelterById(Integer id) {
        // 1. Fetch from DB
        Shelter shelter = shelterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelter not found with ID: " + id));

        // 2. Manual Mapping (Entity -> DTO)
        return mapToResponseDTO(shelter);
    }

    // Helper Method: Map Entity -> ResponseDTO
    private ShelterResponseDTO mapToResponseDTO(Shelter shelter) {
        ShelterResponseDTO response = new ShelterResponseDTO();

        response.setShelterId(shelter.getShelterId());
        response.setName(shelter.getName());
        response.setLocation(shelter.getLocation());
        response.setStatus(shelter.getStatus());
        response.setContactInfo(shelter.getContactInfo());
        response.setCapacity(shelter.getCapacity());
        response.setOccupancy(shelter.getOccupancy());

        // Logical Calculation: Capacity - Occupancy

        response.setUpdatedAt(shelter.getUpdatedAt());

        return response;
    }


    public ShelterResponseDTO updateShelters(ShelterRequestDTO request) {
        Shelter record = shelterRepository.findById(request.getShelterId()).orElseThrow(()->new RuntimeException("Shelter not found"));


        Shelter updatedRecord = record.toBuilder()
                .capacity(request.getCapacity())
                .contactInfo(request.getContactInfo())
                .location(request.getLocation())
                .occupancy(request.getOccupancy())
                .status(request.getStatus())
                .build();
        Shelter savedRecord = shelterRepository.save(updatedRecord);
        userService.createLog(AuditLogDTO.builder()
                .userId(1)
                .action("UPDATE_SHELTER")
                .resource("ShelterID: " + savedRecord.getShelterId())
                .details("Updated occupancy or capacity details.")
                .timestamp(LocalDateTime.now())
                .build());
        return mapToResponseDTO(savedRecord);
    }

    public void deleteShelter(Integer id) {
        // 1. Check if it exists
        if (!shelterRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete: Shelter not found with ID: " + id);
        }

        // 2. Perform the deletion
        shelterRepository.deleteById(id);
        userService.createLog(AuditLogDTO.builder()
                .userId(1)
                .action("DELETE_SHELTER")
                .resource("ShelterID: " + id)
                .details("Shelter record decommissioned.")
                .timestamp(LocalDateTime.now())
                .build());
    }

}