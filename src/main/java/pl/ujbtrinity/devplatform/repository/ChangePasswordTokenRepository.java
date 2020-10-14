package pl.ujbtrinity.devplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ujbtrinity.devplatform.entity.ChangePasswordToken;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface ChangePasswordTokenRepository extends JpaRepository<ChangePasswordToken, Long> {
    ChangePasswordToken findByToken(String token);

    @Modifying
    @Transactional
    @Query(value = "delete from ChangePasswordToken c where c.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
