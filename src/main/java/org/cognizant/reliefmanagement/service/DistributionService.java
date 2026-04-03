package org.cognizant.reliefmanagement.service;

import jakarta.transaction.Transactional;
import org.cognizant.reliefmanagement.Enum.distributionStatus;
import org.cognizant.reliefmanagement.dao.DistributionRepository;
import org.cognizant.reliefmanagement.dto.request.DistributionRequestDTO;
import org.cognizant.reliefmanagement.dto.response.DistributionResponseDTO;
import org.cognizant.reliefmanagement.entity.Distribution;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistributionService {

    private final DistributionRepository distributionRepository;

    public DistributionService(DistributionRepository distributionRepository) {
        this.distributionRepository = distributionRepository;
    }


    public List<DistributionResponseDTO> getAllDistributions() {
        return distributionRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public DistributionResponseDTO getDistributionById(Integer id) {
        // 1. Fetch the entity
        Distribution distribution = distributionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Distribution record not found with ID: " + id));

        // 2. Map to DTO manually
        return mapToResponseDTO(distribution);
    }

    @Transactional
    public DistributionResponseDTO saveDistribution(DistributionRequestDTO dto) {
        // Use the Builder for consistency and null-safety
        Distribution distribution = Distribution.builder()
                .itemId(dto.getItemId())
                .citizenId(dto.getCitizenId())
                .officerId(dto.getOfficerId())
                .quantity(dto.getQuantity() != null ? dto.getQuantity() : 0)
                .notes(dto.getNotes())
                .date(LocalDateTime.now())
                .status(parseStatus(dto.getStatus())) // Safe conversion
                .build();

        Distribution saved = distributionRepository.save(distribution);
        return mapToResponseDTO(saved);
    }

    @Transactional
    public DistributionResponseDTO updateDistribution(DistributionRequestDTO request) {
        // 1. Fetch the existing record (using Integer ID from DTO)
        Distribution existingRecord = distributionRepository.findById(request.getDistributionId())
                .orElseThrow(() -> new RuntimeException("Distribution record not found for ID: " + request.getDistributionId()));

        // 2. Use .toBuilder() to modify only what's provided in the request
        Distribution updatedEntity = existingRecord.toBuilder()
                .distributionId(existingRecord.getDistributionId()) // Keep original ID
                .itemId(request.getItemId() != null ? request.getItemId() : existingRecord.getItemId())
                .citizenId(request.getCitizenId() != null ? request.getCitizenId() : existingRecord.getCitizenId())
                .officerId(request.getOfficerId() != null ? request.getOfficerId() : existingRecord.getOfficerId())
                .quantity(request.getQuantity() != null ? request.getQuantity() : existingRecord.getQuantity())
                .notes(request.getNotes() != null ? request.getNotes() : existingRecord.getNotes())
                .status(request.getStatus() != null ? parseStatus(request.getStatus()) : existingRecord.getStatus())
                .build();

        Distribution savedRecord = distributionRepository.save(updatedEntity);
        return mapToResponseDTO(savedRecord);
    }

    // Helper method to map Entity to ResponseDTO
    private DistributionResponseDTO mapToResponseDTO(Distribution distribution) {
        DistributionResponseDTO resp = new DistributionResponseDTO();
        resp.setDistributionId(distribution.getDistributionId());
        resp.setItemId(distribution.getItemId());
        resp.setCitizenId(distribution.getCitizenId());
        resp.setOfficerId(distribution.getOfficerId());
        resp.setQuantity(distribution.getQuantity());
        resp.setNotes(distribution.getNotes());
        resp.setDate(distribution.getDate());
        // Convert Enum back to String for the Response
        resp.setStatus(distribution.getStatus() != null ? distribution.getStatus().name() : null);
        return resp;
    }

    public void deleteDistribution(Integer id) {
        if (!distributionRepository.existsById(id)) {
            throw new RuntimeException("Distribution record not found with ID: " + id);
        }
        distributionRepository.deleteById(id);
    }

    // Helper to safely parse Status from String to Enum
    private distributionStatus parseStatus(String statusStr) {
        if (statusStr == null || statusStr.isEmpty()) {
            return distributionStatus.PENDING; // Default fallback
        }
        try {
            return distributionStatus.valueOf(statusStr);
        } catch (IllegalArgumentException e) {
            // Handle case where user sends "pending" instead of "Pending"
            return distributionStatus.PENDING;
        }
    }
}