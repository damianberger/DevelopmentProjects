package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.Technology;
import pl.ujbtrinity.devplatform.repository.TechnologyRepository;
import pl.ujbtrinity.devplatform.service.TechnologyService;

import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;

    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public void createTechnology(Technology technology) {
        technologyRepository.save(technology);
    }

    @Override
    public void updateTechnology(Technology technology) {
        Technology techFromDB = technologyRepository.getOne(technology.getId());
        techFromDB.setName(technology.getName());
        technologyRepository.save(techFromDB);
    }

    @Override
    public List<Technology> findAll() {
        return technologyRepository.findAll();
    }

    @Override
    public Technology readTechnology(long id) {
        return technologyRepository.getOne(id);
    }

    @Override
    public boolean existsByName(String name) {
        return technologyRepository.existsTechnologyByName(name);
    }

    @Override
    public void deleteById(Long id) {
        technologyRepository.deleteById(id);
    }
}
