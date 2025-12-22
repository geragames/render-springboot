package com.fich.sarh.auth.Infrastructure.adapter.input.rest.controller;

import com.fich.sarh.auth.Application.ports.output.persistence.RoleRetrieveSpiPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleRetrieveSpiPort roleRetrieveSpiPort;

    public RoleController(RoleRetrieveSpiPort roleRetrieveSpiPort) {
        this.roleRetrieveSpiPort = roleRetrieveSpiPort;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("all")
    ResponseEntity<?> findAll() {
       return ResponseEntity.ok().body(this.roleRetrieveSpiPort.fetchAllRole());
    }
}
