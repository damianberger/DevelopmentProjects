package pl.ujbtrinity.devplatform.fixture;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.service.impl.FrameworkServiceImpl;

@Service
public class InitFrameworks {

    public void init(String frameworkName) {
        Framework framework = new Framework();
        framework.setName(frameworkName);
        frameworkService.createFramework(framework);
    }

    private final FrameworkServiceImpl frameworkService;

    public InitFrameworks(FrameworkServiceImpl frameworkService) {
        this.frameworkService = frameworkService;
    }

    public void initFrameworks() {
        init("Node.js");
        init("Angular");
        init("React");
        init(".NET");
        init("Spring");
        init("Django");
        init("Spark");
        init("Vue.js");
        init("jQuery");
        init("Ruby on Rails");
        init("Flask");
        init("Laravel");
        init("Drupal");
        init("Flutter");
        init("Apache Spark");
    }
}
