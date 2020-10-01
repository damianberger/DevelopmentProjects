package pl.ujbtrinity.devplatform.fixture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class StartupDataInsert implements CommandLineRunner {

    private final InitTechnologies initTechnologies;
    private final InitFrameworks initFrameworks;

    public StartupDataInsert(InitTechnologies initTechnologies, InitFrameworks initFrameworks) {
        this.initTechnologies = initTechnologies;
        this.initFrameworks = initFrameworks;
    }

    @Override
    public void run(String... args) throws Exception {
        initTechnologies.intiTechnologies();
        initFrameworks.initFrameworks();
    }
}
