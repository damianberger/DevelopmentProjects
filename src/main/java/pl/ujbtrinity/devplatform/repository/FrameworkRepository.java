package pl.ujbtrinity.devplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ujbtrinity.devplatform.entity.Framework;

@Repository
public interface FrameworkRepository extends JpaRepository<Framework, Long> {
}
