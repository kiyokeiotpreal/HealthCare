package org.example.doantotnghiep.Payload.Request.auth_request;

import lombok.Data;

@Data
public class ConfirmForgotPasswordRequest {
    private String confirmCode;
    private String matKhauMoi;
    private String xacNhanMatKhau;
}
