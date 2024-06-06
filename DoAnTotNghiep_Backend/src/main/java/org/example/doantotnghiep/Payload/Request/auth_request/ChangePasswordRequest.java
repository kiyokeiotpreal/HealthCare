package org.example.doantotnghiep.Payload.Request.auth_request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String retypePassword;
}
