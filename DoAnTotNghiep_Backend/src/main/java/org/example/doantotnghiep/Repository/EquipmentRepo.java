package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {
}
