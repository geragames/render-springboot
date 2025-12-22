package com.fich.sarh.auth.Application.ports.output.persistence;

import java.util.Optional;

public interface UserResetPasswordSpiPort {
    Optional<String> resetPasswordByAdmin(Long userId);
    boolean changePassword(Long userId, String currentPassword, String newPassword);

}
