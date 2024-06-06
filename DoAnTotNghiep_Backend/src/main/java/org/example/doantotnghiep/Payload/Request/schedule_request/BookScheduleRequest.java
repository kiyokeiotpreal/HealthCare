package org.example.doantotnghiep.Payload.Request.schedule_request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookScheduleRequest {
    private int scheduleID;

    private String namePatient;

    private String phonePatient;

    private String emailPatient;

    private String genderPatient;

    private String yearPatient;

    private String reason;

    private String medicalHistoryPatient;

}
