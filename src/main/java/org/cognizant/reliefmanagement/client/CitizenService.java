package org.cognizant.reliefmanagement.client;

import org.cognizant.reliefmanagement.dto.response.CitizenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CitizenService")
public interface CitizenService {
    @GetMapping("/{id}")
    CitizenDto findById(@PathVariable Integer id);
}
