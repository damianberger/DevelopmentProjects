package pl.ujbtrinity.devplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.ujbtrinity.devplatform.entity.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
