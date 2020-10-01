package pl.ujbtrinity.devplatform.fixture;

import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.Framework;
import pl.ujbtrinity.devplatform.entity.Role;
import pl.ujbtrinity.devplatform.service.impl.FrameworkServiceImpl;
import pl.ujbtrinity.devplatform.service.impl.RoleServiceImpl;

@Service
public class InitRoles {

    private final RoleServiceImpl roleService;

    public InitRoles(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }


    public void init(String rolesName) {
        Role role = new Role();
        role.setName(rolesName);
        boolean existRole = roleService.existsByName(rolesName);
        if (!existRole) {
            roleService.save(role);
        }

    }
    public void initRoles(){
        init("ROLE_DEVELOPER");
        init("ROLE_CUSTOMER");
        init("ROLE_ADMIN");
        init("ROLE_SUPER_ADMIN");
    }
}


