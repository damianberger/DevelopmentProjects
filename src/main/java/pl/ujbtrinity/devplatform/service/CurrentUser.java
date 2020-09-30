package pl.ujbtrinity.devplatform.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final pl.ujbtrinity.devplatform.entity.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.ujbtrinity.devplatform.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    public pl.ujbtrinity.devplatform.entity.User getUser() {
        return user;
    }
}
