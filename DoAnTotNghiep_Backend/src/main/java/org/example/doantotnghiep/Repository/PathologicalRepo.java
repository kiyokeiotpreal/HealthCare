package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.Pathological;
import org.example.doantotnghiep.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PathologicalRepo extends JpaRepository<Pathological, Integer> {
    List<Pathological> findBySpecialist(Specialist specialist);
}
