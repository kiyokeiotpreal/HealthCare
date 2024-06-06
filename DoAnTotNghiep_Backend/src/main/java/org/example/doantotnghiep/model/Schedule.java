package org.example.doantotnghiep.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule extends BaseEntity{
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "doctorId", foreignKey = @ForeignKey(name = "fk_Doctor_Schedule"))
    @JsonManagedReference
    private User doctor;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private boolean isEmpty;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "clinicId", foreignKey = @ForeignKey(name = "fk_Schedule_Clinic"))
    @JsonManagedReference
    private Clinic clinic;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "examinationServiceId", foreignKey = @ForeignKey(name = "fk_Schedule_ExaminationService"))
    @JsonManagedReference
    private ExaminationService examinationService;

    private String namePatient;

    private String phonePatient;

    private String emailPatient;

    private String genderPatient;

    private String yearPatient;

    private String reason;

    private String medicalHistoryPatient;

}
