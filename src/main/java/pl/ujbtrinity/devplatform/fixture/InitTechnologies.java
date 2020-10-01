package pl.ujbtrinity.devplatform.fixture;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.Technology;
import pl.ujbtrinity.devplatform.service.impl.TechnologyServiceImpl;

@Service
public class InitTechnologies {

    public void init(String technologyName) {
        Technology technology = new Technology();
        technology.setName(technologyName);
        boolean existTechnology = technologyService.existsByName(technologyName);
        if (!existTechnology) {
            technologyService.createTechnology(technology);
        }
    }

    private final TechnologyServiceImpl technologyService;

    public InitTechnologies(TechnologyServiceImpl technologyService) {
        this.technologyService = technologyService;
    }

    public void intiTechnologies() {
        init("Python");
        init("JavaScript");
        init("Java");
        init("C#");
        init("C");
        init("C++");
        init("R");
        init("Swift");
        init("PHP");
        init("Dart");
        init("Kotlin");
        init("MATLAB");
        init("Perl");
        init("Ruby");
        init("Rust");
        init("Scala");
        init("Elixir");
        init("Clojure");
        init("WebAssembly");
        init("Assembly");
        init("HTML/CSS");
    }
}
