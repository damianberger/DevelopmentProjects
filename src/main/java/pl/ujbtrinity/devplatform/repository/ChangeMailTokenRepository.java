package pl.ujbtrinity.devplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ujbtrinity.devplatform.entity.ChangeMailToken;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface ChangeMailTokenRepository extends JpaRepository<ChangeMailToken, Long> {
    ChangeMailToken findByToken(String token);

    @Modifying
    @Transactional
    @Query(value = "delete from ChangeMailToken m where m.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
