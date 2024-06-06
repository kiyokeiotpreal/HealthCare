package org.example.doantotnghiep.Payload.Request.specialist_request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddSpecialistRequest {
    private String name;
    private String description;

    private String hospitalName;
}
