package org.example.doantotnghiep.Payload.Request.auth_request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfirmCodeRequest {
    private String confirmCode;
}
