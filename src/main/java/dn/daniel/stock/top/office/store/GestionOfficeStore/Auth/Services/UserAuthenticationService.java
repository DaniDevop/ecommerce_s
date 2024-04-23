package dn.daniel.stock.top.office.store.GestionOfficeStore.Auth.Services;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Auth.SecurityUser;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserAuthenticationService implements UserDetailsService {


    private final UsersRepository usersRepository;

    public UserAuthenticationService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmailOrName(username,username)
                .map(user -> new SecurityUser(user))
                .orElseThrow(()->new UsernameNotFoundException("Information is not valid "));
    }
}
