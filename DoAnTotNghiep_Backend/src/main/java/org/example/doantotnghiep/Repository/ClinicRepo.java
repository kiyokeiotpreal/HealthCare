package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepo extends JpaRepository<Clinic, Integer> {
}
