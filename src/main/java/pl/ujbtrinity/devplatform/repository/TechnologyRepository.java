package pl.ujbtrinity.devplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ujbtrinity.devplatform.entity.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
