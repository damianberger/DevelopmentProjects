package pl.ujbtrinity.devplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.ujbtrinity.devplatform.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername (String username);

    boolean existsUserByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "delete from user_projects b where b.project_id= ?1", nativeQuery = true)
    void removeUsersFromProject(Long id);
}
