package com.fich.sarh.auth.Infrastructure.adapter.input.rest.controller;

import com.fich.sarh.auth.Application.ports.output.persistence.UserDetailsSpiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserResetPasswordSpiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserSendEmailResetPasswordSpiPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/admin")
@RestController
public class AdminController {


    private final UserResetPasswordSpiPort userResetPasswordSpiPort;

    public AdminController( UserResetPasswordSpiPort userResetPasswordSpiPort) {

        this.userResetPasswordSpiPort = userResetPasswordSpiPort;
    }
    @PreAuthorize("hasAnyRole('ADMIN','DEVELOPER')")
    @PostMapping("user/{id}/reset-password")
    public ResponseEntity<?> resetPassword(@PathVariable Long id){
        return userResetPasswordSpiPort.resetPasswordByAdmin(id)
                .map(temp ->

                    ResponseEntity.ok().body("TempPassword: " + temp)  )
                .orElseGet(()-> ResponseEntity.notFound().build());
    }


}
