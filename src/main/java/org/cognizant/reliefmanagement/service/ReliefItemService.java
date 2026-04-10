package org.cognizant.reliefmanagement.service;
import org.cognizant.reliefmanagement.client.UserService;
import org.cognizant.reliefmanagement.dao.DistributionRepository;
import org.cognizant.reliefmanagement.dao.ReliefItemRepository;
import org.cognizant.reliefmanagement.dto.request.AuditLogDTO;
import org.cognizant.reliefmanagement.dto.request.ReliefItemRequestDTO;
import org.cognizant.reliefmanagement.dto.response.ReliefItemResponseDTO;
import org.cognizant.reliefmanagement.entity.ReliefItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class ReliefItemService {
    @Autowired
    private  ReliefItemRepository reliefItemsRepository;

    @Autowired
    private DistributionRepository distributionRepository;
    @Autowired
    private UserService userService;
    public List<ReliefItemResponseDTO> getAllReliefItem(){
        return reliefItemsRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public ReliefItemResponseDTO getReliefItemById(Integer id) {
        // 1. Find the entity
        ReliefItem item = reliefItemsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relief Item not found with ID: " + id));

        // 2. Manually map to DTO
        return mapToResponseDTO(item);
    }

    public ReliefItemResponseDTO saveReliefItem(ReliefItemRequestDTO dto){
        ReliefItem item = new ReliefItem();
        item.setType(dto.getType());
        item.setName(dto.getName());
        item.setQuantity(dto.getQuantity());
        item.setUnit(dto.getUnit());
        item.setStatus(dto.getStatus());
        item.setCreatedAt(LocalDateTime.now());
        item.setUpdatedAt(LocalDateTime.now());

        ReliefItem saved = reliefItemsRepository.save(item);
        userService.createLog(AuditLogDTO.builder()
                .userId(1)
                .action("ADD_INVENTORY")
                .resource("Item: " + saved.getName())
                .details("Added " + saved.getQuantity() + " units of " + saved.getType())
                .timestamp(LocalDateTime.now())
                .build());
        return mapToResponseDTO(saved);
    }

    private ReliefItemResponseDTO mapToResponseDTO(ReliefItem item) {
        ReliefItemResponseDTO resp = new ReliefItemResponseDTO();
        resp.setItemId(item.getItemId());
        resp.setType(item.getType());
        resp.setName(item.getName());
        resp.setQuantity(item.getQuantity());
        resp.setUnit(item.getUnit());
        resp.setStatus(item.getStatus());
        resp.setUpdatedAt(item.getUpdatedAt());
        return resp;
    }

    public void deleteReliefItem(int id) {
        // 1. Check if the item even exists
        if (!reliefItemsRepository.existsById(id)) {
            throw new RuntimeException("Relief Item not found with ID: " + id);
        }

        // This prevents the Foreign Key Constraint error
        boolean isUsed = distributionRepository.existsByItemId(id);
        if (isUsed) {
            throw new RuntimeException("Cannot delete: This item is currently assigned to a distribution record.");
        }


        reliefItemsRepository.deleteById(id);
        userService.createLog(AuditLogDTO.builder()
                .userId(1)
                .action("REMOVE_INVENTORY")
                .resource("ItemID: " + id)
                .details("Item removed from catalog.")
                .timestamp(LocalDateTime.now())
                .build());
    }

    public ReliefItemResponseDTO updateReliefItem(ReliefItemRequestDTO request) {
        ReliefItem record = reliefItemsRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("ReliefItem not found with ID: " + request.getItemId()));

        if (record == null) {
            throw new RuntimeException("ReliefItem not found");
        }
        // By explicitly setting the ID, you FORCE JPA to perform an update.
        ReliefItem updatedRecord = record.toBuilder()
                .itemId(record.getItemId()).
                name(request.getName())
                .type(request.getType())
                .quantity(request.getQuantity())
                .unit(request.getUnit())
                .status(request.getStatus())
                .updatedAt(LocalDateTime.now())
                .build();
        userService.createLog(AuditLogDTO.builder()
                .userId(1)
                .action("UPDATE_INVENTORY")
                .resource("Item: " + updatedRecord.getName())
                .details("Modified stock levels or status.")
                .timestamp(LocalDateTime.now())
                .build());

        return mapToResponseDTO(reliefItemsRepository.save(updatedRecord));
    }
}
