package org.example.doantotnghiep.Payload.Request.auth_request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String retypePassword;
    private String address;
    private String phone;
    private String gender;
}
