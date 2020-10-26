package pl.ujbtrinity.devplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.ujbtrinity.devplatform.dto.projectDto.ProjectSearchRequestedDto;
import pl.ujbtrinity.devplatform.dto.projectDto.ProjectViewDto;
import pl.ujbtrinity.devplatform.entity.Project;

import java.util.List;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("select p from Project p left join Technology t on t.name = :tName left join Framework f on f.name = :fName")
    List<Project> findAllByTechnologiesUsedAndFrameworksUsed (@Param("tName") String tech, @Param("fName") String frame);
}
