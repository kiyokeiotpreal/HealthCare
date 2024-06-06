package org.example.doantotnghiep.Payload.Request.user_request;

import lombok.*;
import org.example.doantotnghiep.model.Specialist;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDoctorRequest {
    private String name;
    private String email;
    private String avata;
    private String phone;
    private String description;
    private String password;
    private String specialistName;
    private String examinationServicename;
    private String position;
    private String gender;
    private double price;
}
