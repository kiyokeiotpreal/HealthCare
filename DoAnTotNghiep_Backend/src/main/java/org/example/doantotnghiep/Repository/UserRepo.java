package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.ConfirmEmail;
import org.example.doantotnghiep.model.ExaminationService;
import org.example.doantotnghiep.model.Specialist;
import org.example.doantotnghiep.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    User findBySlug(String slug);

    boolean existsByEmail(String email);

    User findByConfirmEmails(ConfirmEmail confirmEmail);


    Set<User> findBySpecialist(Specialist specialist);
    Set<User> findByExaminationService(ExaminationService examinationService);
}
