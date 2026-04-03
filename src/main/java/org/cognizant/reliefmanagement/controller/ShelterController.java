package org.cognizant.reliefmanagement.controller;

import jakarta.validation.Valid;
import org.cognizant.reliefmanagement.dto.request.ShelterRequestDTO;
import org.cognizant.reliefmanagement.dto.response.ShelterResponseDTO;
import org.cognizant.reliefmanagement.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    // 1. GET: http://localhost:1234/Shelters
    // Now returns a List of ResponseDTOs instead of Entities
    @GetMapping("/Shelters/getShelters")
    public ResponseEntity<List<ShelterResponseDTO>> getShelters() {
        List<ShelterResponseDTO> responseList = shelterService.getAllShelters();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/Shelters/getById/{id}")
    public ResponseEntity<ShelterResponseDTO> getById(@PathVariable Integer id) {
        // Calling the service to get the data
        ShelterResponseDTO response = shelterService.getShelterById(id);
        return ResponseEntity.ok(response);
    }

    // 2. POST: http://localhost:1234/Shelters
    // Receives RequestDTO and returns ResponseDTO
    @PostMapping("/Shelters/createShelter")
    public ResponseEntity<ShelterResponseDTO> createShelter(@RequestBody @Valid ShelterRequestDTO requestDto) {
        ShelterResponseDTO response = shelterService.addShelter(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/Shelters/updateShelter")
    public ResponseEntity<ShelterResponseDTO> updateShelter(@RequestBody ShelterRequestDTO requestDTO){
        ShelterResponseDTO response=shelterService.updateShelters(requestDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/Shelters/deleteShelter/{id}")
    public ResponseEntity<String> deleteShelter(@PathVariable Integer id) {
        shelterService.deleteShelter(id);
        return ResponseEntity.ok("Shelter with ID " + id + " has been deleted successfully.");
    }

}