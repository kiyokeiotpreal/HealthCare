package org.example.doantotnghiep.Payload.Response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleResponse {
    private String namePatient;

    private String phonePatient;

    private String emailPatient;

    private String genderPatient;

    private String yearPatient;

    private String reason;

    private String medicalHistoryPatient;

    private int id;
    private String dayofWeek;
    private String date;
    private String time;
    private boolean isEmpty;

    public String getDateTimeKey() {
        return date + " " + time.split("-")[0];
    }
}
