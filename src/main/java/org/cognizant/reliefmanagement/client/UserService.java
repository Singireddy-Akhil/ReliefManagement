package org.cognizant.reliefmanagement.client;

import org.cognizant.reliefmanagement.dto.response.UserDto;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USERCITIZENMANAGEMENT")
public interface UserService {
    @GetMapping("/api/users/getByUserId/{id}")
    UserDto findById(@PathVariable Integer id);
}
