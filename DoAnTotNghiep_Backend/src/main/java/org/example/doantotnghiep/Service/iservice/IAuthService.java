package org.example.doantotnghiep.Service.iservice;

import org.example.doantotnghiep.Payload.Request.auth_request.ChangePasswordRequest;
import org.example.doantotnghiep.Payload.Request.auth_request.ConfirmForgotPasswordRequest;
import org.example.doantotnghiep.Payload.Request.auth_request.LoginRequest;
import org.example.doantotnghiep.Payload.Request.auth_request.RegisterRequest;
import org.example.doantotnghiep.Payload.Response.LoginResponse;

public interface IAuthService {
    void register(RegisterRequest registerRequest) throws Exception;

    LoginResponse login(LoginRequest loginRequest) throws Exception;

    String changePassWord(ChangePasswordRequest changePasswordRequest) throws Exception;

    void requestForgotPassword(String email) throws Exception;

    void confirmForgotPassword(ConfirmForgotPasswordRequest confirmForgotPasswordRequest) throws Exception;

    void sendConfirmEmail(String email);
    boolean confirmEmail(String confirmCode) throws Exception;
}
