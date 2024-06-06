package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepo extends JpaRepository<ServiceType, Integer> {
    ServiceType findByName(String name);
    ServiceType findBySlug(String slug);

}
