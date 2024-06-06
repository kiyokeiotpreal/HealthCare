package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital, Integer> {
    boolean existsByName(String name);
    Hospital findByName(String name);
}
