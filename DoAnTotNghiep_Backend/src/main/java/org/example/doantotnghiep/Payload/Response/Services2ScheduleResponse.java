package org.example.doantotnghiep.Payload.Response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Services2ScheduleResponse {
    private String avata;
    private String name;
    private String shortenedName;
    private String description;
    private double price;
    private String clinicName;
    private String addressClinic;
    private String serviceTypeName;
    private String examinationServiceName;
    private Set<ScheduleResponse> scheduleResponses;
}
