package pl.ujbtrinity.devplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import pl.ujbtrinity.devplatform.entity.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from projects_users b where b.project_id= ?1", nativeQuery = true)
    void removeUsersFromProject(Long id);
}
