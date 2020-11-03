package pl.ujbtrinity.devplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.ujbtrinity.devplatform.entity.ProjectInvitation;

import java.util.Set;

@Repository
public interface ProjectInvitationRepository extends JpaRepository<ProjectInvitation, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from project_invitation b where b.project_id= ?1", nativeQuery = true)
    void removeProjectInvitationsByProject(Long id);

    @Modifying
    @Transactional
    @Query(value = "select * from project_invitation b where b.project_id= ?1", nativeQuery = true)
    Set<ProjectInvitation> findByProject(Long id);

    @Modifying
    @Transactional
    @Query(value = "select * from project_invitation b where b.invited_user_id= ?1", nativeQuery = true)
    Set<ProjectInvitation> findByUser(Long id);

}
