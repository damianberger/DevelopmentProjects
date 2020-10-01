package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.repository.FrameworkRepository;
import pl.ujbtrinity.devplatform.service.FrameworkService;

import java.util.List;

@Service
public class FrameworkServiceImpl implements FrameworkService {

    private final FrameworkRepository FrameworkRepository;

    public FrameworkServiceImpl(FrameworkRepository FrameworkRepository) {
        this.FrameworkRepository = FrameworkRepository;
    }

    @Override
    public void createFramework(Framework Framework) {
        FrameworkRepository.save(Framework);
    }

    @Override
    public void updateFramework(Framework Framework) {
        Framework frFromDB = FrameworkRepository.getOne(Framework.getId());
        frFromDB.setName(Framework.getName());
        FrameworkRepository.save(frFromDB);
    }

    @Override
    public List<Framework> FindAll() {
        return FrameworkRepository.findAll();
    }

    @Override
    public Framework readFramework(long id) {
        return FrameworkRepository.getOne(id);
    }
}
