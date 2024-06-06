package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.ExaminationService;
import org.example.doantotnghiep.model.Schedule;
import org.example.doantotnghiep.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
    Set<Schedule> findByDoctor(User doctor);

    Set<Schedule> findByExaminationService(ExaminationService examinationService);
}
