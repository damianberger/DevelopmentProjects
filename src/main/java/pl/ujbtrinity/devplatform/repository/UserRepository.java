package pl.ujbtrinity.devplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ujbtrinity.devplatform.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername (String username);

    boolean existsUserByUsername(String username);
}
