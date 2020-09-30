package pl.ujbtrinity.devplatform.service;

import pl.ujbtrinity.devplatform.entity.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);
    Role findOneByName(String roleName);
    List<Role> findAll();
}