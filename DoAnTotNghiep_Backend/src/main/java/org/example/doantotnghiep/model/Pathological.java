package org.example.doantotnghiep.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pathological extends BaseEntity{ // Bảng Bệnh lý
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "specialistId", foreignKey = @ForeignKey(name = "fk_Specialist_Pathological"))
    @JsonManagedReference
    private Specialist specialist;

    private String name;
}
