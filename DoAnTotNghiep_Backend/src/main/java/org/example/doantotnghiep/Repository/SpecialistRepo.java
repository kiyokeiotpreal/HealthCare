package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialistRepo extends JpaRepository<Specialist, Integer> {
    Specialist findByName(String name);
    Specialist findBySlug(String slug);
    boolean existsByNameAndHospitalName(String name, String hospitalName);
}
