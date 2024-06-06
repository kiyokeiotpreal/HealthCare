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
public class Specialist extends BaseEntity{ // Bảng chuyên khoa
    private String name;
    private String slug;
    private String description;
    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Pathological> pathologicals;
    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<User> doctors;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "hospitalId", foreignKey = @ForeignKey(name = "fk_Specialist_Hospital"))
    @JsonManagedReference
    private Hospital hospital;

    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Clinic> clinics;

//    private int serviceTypeId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceTypeId", foreignKey = @ForeignKey(name = "fk_Specialist_ServiceType"))
    @JsonManagedReference
    private ServiceType serviceType;

}

