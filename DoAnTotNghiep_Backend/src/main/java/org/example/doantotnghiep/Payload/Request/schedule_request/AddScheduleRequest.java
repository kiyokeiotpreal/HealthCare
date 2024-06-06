package org.example.doantotnghiep.Payload.Request.schedule_request;

import lombok.*;
import org.example.doantotnghiep.model.Clinic;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddScheduleRequest {
    private String emailDoctor;
    private String date;
    private String startTime;
    private String endTime;
    private int clinicId;
}
