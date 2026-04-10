package org.cognizant.reliefmanagement.client;

import org.cognizant.reliefmanagement.dto.request.AuditLogDTO;
import org.cognizant.reliefmanagement.dto.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.module.Configuration;

@FeignClient(name = "USERCITIZENMANAGEMENT",configuration = FeignClientInterceptor.class)
public interface UserService {
    @GetMapping("/api/users/getByUserId/{id}")
    UserDto findById(@PathVariable Integer id);
    @PostMapping("/api/logs/CreateLog")
    AuditLogDTO createLog(@RequestBody AuditLogDTO auditLogDTO);
}
