package org.example.doantotnghiep.Payload.Response;

import lombok.*;
import org.example.doantotnghiep.model.Schedule;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponse {
    private String avata;
    private String name;
    private String position;
    private String description;
    private double price;
    private String clinicName;
    private String addressClinic;
    private String serviceTypeName;
    private List<String> pathologicals;
    private String examinationServicename;
    private Set<ScheduleResponse> scheduleResponses;
}
