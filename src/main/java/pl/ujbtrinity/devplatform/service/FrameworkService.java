package pl.ujbtrinity.devplatform.service;

import pl.ujbtrinity.devplatform.entity.Framework;

import java.util.List;

public interface FrameworkService {
    void createFramework (Framework framework);
    void updateFramework (Framework framework);
    List<Framework> findAll();
    Framework readFramework(long id);
    boolean existsByName(String name);
    void deleteById (Long id);
}
