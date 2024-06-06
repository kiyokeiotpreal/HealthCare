package org.example.doantotnghiep.Payload.Request.hospital_request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddHospitalRequest {
    private String code;
    private String address;
    private String name;
    private String logoImg;
    private String positionDescription;
    private String processImg;
    private String positionImg;
}
