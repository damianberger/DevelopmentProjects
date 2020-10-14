package pl.ujbtrinity.devplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ujbtrinity.devplatform.entity.ResetPasswordToken;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    ResetPasswordToken findByToken(String token);

    @Modifying
    @Transactional
    @Query(value = "delete from ResetPasswordToken r where r.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
