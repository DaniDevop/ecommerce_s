package dn.daniel.stock.top.office.store.GestionOfficeStore.Entity;

import jakarta.persistence.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Users  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Roles>roles=new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<JwtToken> jwtTokens=new ArrayList<>();

    public Users() {
    }

    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Roles> roles) {
        this.roles = roles;
    }




    public Collection<JwtToken> getJwtTokens() {
        return jwtTokens;
    }

    public void setJwtTokens(Collection<JwtToken> jwtTokens) {
        this.jwtTokens = jwtTokens;
    }


}
