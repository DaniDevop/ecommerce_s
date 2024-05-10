package dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Services;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ClientAuthAuthentication implements Authentication {


    private Client client;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return this.client.getNom();
    }

    @Override
    public Object getDetails() {
        return client.getEmail();
    }

    @Override
    public Object getPrincipal() {
        return this.client;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return this.client.getNom();
    }
}
