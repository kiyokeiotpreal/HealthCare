package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepo extends JpaRepository<Rate, Integer> {
}
