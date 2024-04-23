package dn.daniel.stock.top.office.store.GestionOfficeStore.Auth.Filter;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Users;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.UsersRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Users.UserServicesImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UsersAuthentificationProvider implements AuthenticationProvider {


    private final UsersRepository usersRepository;

    public UsersAuthentificationProvider(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;

    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username= authentication.getName();

        String password= (String) authentication.getCredentials();

        Optional<Users> optionalUser= this.usersRepository.findByNameAndPassword( username, password);
        System.out.println("USERNAME  :" + username);
        System.out.println("PASSWORD  :" + password);
         if(optionalUser.isPresent()){
            System.out.println("Pourtant c'est cablé ");
            return new UsernamePasswordAuthenticationToken(optionalUser.get(),null,null);
        }
        System.out.println("Bad code ");
           return null;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
