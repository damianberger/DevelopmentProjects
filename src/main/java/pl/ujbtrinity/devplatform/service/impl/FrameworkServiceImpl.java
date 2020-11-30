package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.repository.FrameworkRepository;
import pl.ujbtrinity.devplatform.service.FrameworkService;

import java.util.List;

@Service
public class FrameworkServiceImpl implements FrameworkService {

    private final FrameworkRepository frameworkRepository;

    public FrameworkServiceImpl(FrameworkRepository FrameworkRepository) {
        this.frameworkRepository = FrameworkRepository;
    }

    @Override
    public void createFramework(Framework Framework) {
        frameworkRepository.save(Framework);
    }

    @Override
    public void updateFramework(Framework Framework) {
        Framework frFromDB = frameworkRepository.getOne(Framework.getId());
        frFromDB.setName(Framework.getName());
        frameworkRepository.save(frFromDB);
    }

    @Override
    public List<Framework> findAll() {
        return frameworkRepository.findAll();
    }

    @Override
    public Framework readFramework(long id) {
        return frameworkRepository.getOne(id);
    }

    @Override
    public boolean existsByName(String name) {
        return frameworkRepository.existsFrameworkByName(name);
    }

    @Override
    public void deleteById(Long id) {
        frameworkRepository.deleteById(id);
    }
}
