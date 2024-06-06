package org.example.doantotnghiep.Payload.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamServiceSearchResponse {
    private String avata;
    private String name;
    private String slug;
    private int serviceTypeId;
}
