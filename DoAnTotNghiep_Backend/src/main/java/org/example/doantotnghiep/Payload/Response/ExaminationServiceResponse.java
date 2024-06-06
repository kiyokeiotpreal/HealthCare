package org.example.doantotnghiep.Payload.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExaminationServiceResponse {

    private String serviceTypeName;

    private String slug;

    private String slugExaminationService;

    private int id;

    private String img;

    private String name;
}
