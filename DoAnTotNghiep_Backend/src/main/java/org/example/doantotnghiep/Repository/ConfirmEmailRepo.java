package org.example.doantotnghiep.Repository;

import org.example.doantotnghiep.model.ConfirmEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmEmailRepo extends JpaRepository<ConfirmEmail, Integer> {
    ConfirmEmail findConfirmEmailByConfirmCode(String confirmCode);


}
