package pl.ujbtrinity.devplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ujbtrinity.devplatform.entity.Role;
import pl.ujbtrinity.devplatform.repository.RoleRepository;
import pl.ujbtrinity.devplatform.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        this.roleRepository.save(role);
    }

    @Override
    public Role findOneByName(String roleName) {
        return this.roleRepository.findByName(roleName);
    }

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }
}