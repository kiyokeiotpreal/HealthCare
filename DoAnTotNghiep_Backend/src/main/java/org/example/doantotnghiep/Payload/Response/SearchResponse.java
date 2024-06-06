package org.example.doantotnghiep.Payload.Response;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResponse {
    private List<DoctorSearchResponse> doctorSearchResponses;
    private List<ExamServiceSearchResponse> examServiceSearchResponses;
}
