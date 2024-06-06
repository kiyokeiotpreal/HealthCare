package org.example.doantotnghiep.Payload.Request.auth_request;

import lombok.Data;

@Data
public class ConfirmPasswordRequest {
    private String maXacNhan;
    private String matKhauMoi;
}
