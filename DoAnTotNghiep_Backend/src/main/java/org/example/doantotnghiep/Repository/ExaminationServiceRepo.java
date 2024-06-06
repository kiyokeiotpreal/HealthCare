package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.ExaminationService;
import org.example.doantotnghiep.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ExaminationServiceRepo extends JpaRepository<ExaminationService, Integer> {
    List<ExaminationService> findByServiceType(ServiceType serviceType);

    ExaminationService findByName(String name);
    ExaminationService findBySlug(String slug);
}
