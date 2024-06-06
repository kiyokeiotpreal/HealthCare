package org.example.doantotnghiep.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExaminationService extends BaseEntity {
    @OneToMany(mappedBy = "examinationService", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Schedule> schedules;

    private String name;

    private String shortenedName;

    private String slug;

    private String img;

    private String description;

    private double price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "specialistId", foreignKey = @ForeignKey(name = "fk_ExaminationService_Specialist"))
    @JsonManagedReference
    private Specialist specialist;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceTypeId", foreignKey = @ForeignKey(name = "fk_ExaminationService_ServiceType"))
    @JsonManagedReference
    private ServiceType serviceType;
}
