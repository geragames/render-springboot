package com.fich.sarh.auth.Application.ports.output.persistence;




public interface UserSendEmailResetPasswordSpiPort {

    void sendEmailResetPassword(String newPassword, String email) ;
}
