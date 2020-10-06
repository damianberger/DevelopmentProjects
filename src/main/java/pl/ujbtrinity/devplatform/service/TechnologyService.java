package pl.ujbtrinity.devplatform.service;

import pl.ujbtrinity.devplatform.entity.Technology;

import java.util.List;

public interface TechnologyService {
    void createTechnology (Technology technology);
    void updateTechnology (Technology technology);
    List<Technology> findAll();
    Technology readTechnology(long id);
    boolean existsByName(String name);
}
