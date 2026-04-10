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
@RequestMapping("/api/shelters")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    // 1. GET: http://localhost:1234/Shelters
    // Now returns a List of ResponseDTOs instead of Entities
    @GetMapping("/getShelters")
    public ResponseEntity<List<ShelterResponseDTO>> getShelters() {
        List<ShelterResponseDTO> responseList = shelterService.getAllShelters();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ShelterResponseDTO> getById(@PathVariable Integer id) {
        // Calling the service to get the data
        ShelterResponseDTO response = shelterService.getShelterById(id);
        return ResponseEntity.ok(response);
    }

    // 2. POST: http://localhost:1234/Shelters
    // Receives RequestDTO and returns ResponseDTO
    @PostMapping("/createShelter")
    public ResponseEntity<ShelterResponseDTO> createShelter(@RequestBody @Valid ShelterRequestDTO requestDto) {
        ShelterResponseDTO response = shelterService.addShelter(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/updateShelter/{id}")
    public ResponseEntity<ShelterResponseDTO> updateShelter(@PathVariable Integer id ,@RequestBody ShelterRequestDTO requestDTO){
       requestDTO.setShelterId(id);
        ShelterResponseDTO response=shelterService.updateShelters(requestDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/deleteShelter/{id}")
    public ResponseEntity<String> deleteShelter(@PathVariable Integer id) {
        shelterService.deleteShelter(id);
        return ResponseEntity.ok("Shelter with ID " + id + " has been deleted successfully.");
    }

}