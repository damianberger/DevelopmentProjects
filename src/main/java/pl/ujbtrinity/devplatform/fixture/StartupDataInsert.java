package pl.ujbtrinity.devplatform.fixture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class StartupDataInsert implements CommandLineRunner {

    private final InitTechnologies initTechnologies;
    private final InitFrameworks initFrameworks;
    private final InitRoles initRoles;
    private final InitSuperAdminAccount initAdminAccount;

    public StartupDataInsert(InitTechnologies initTechnologies, InitFrameworks initFrameworks, InitRoles initRoles, InitSuperAdminAccount initAdminAccount) {
        this.initTechnologies = initTechnologies;
        this.initFrameworks = initFrameworks;
        this.initRoles = initRoles;
        this.initAdminAccount = initAdminAccount;
    }

    @Override
    public void run(String... args) throws Exception {
        initTechnologies.intiTechnologies();
        initFrameworks.initFrameworks();
        initRoles.initRoles();
        initAdminAccount.initSuperAdmin();

    }
}
