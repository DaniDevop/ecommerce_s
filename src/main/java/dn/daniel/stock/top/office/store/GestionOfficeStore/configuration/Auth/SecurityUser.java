package dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Auth;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SecurityUser implements UserDetails {


    private Users users;

    public SecurityUser(Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        users.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authorities;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return this.users.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
