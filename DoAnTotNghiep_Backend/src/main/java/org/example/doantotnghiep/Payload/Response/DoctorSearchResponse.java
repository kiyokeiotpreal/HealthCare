package org.example.doantotnghiep.Payload.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorSearchResponse {
    private String avata;
    private String name;
    private String position;
    private String specialistName;
    private String slug;
    private String email;
    private String address;
    private String phone;
    private String gender;
    private String description;
    private double price;
}
