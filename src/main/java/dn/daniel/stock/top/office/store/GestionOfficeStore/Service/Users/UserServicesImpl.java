package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Users;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.JwtToken;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Users;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.JwtRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.UsersRepository;
import org.apache.commons.logging.Log;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {
    private final UsersRepository usersRepository;
    private final JwtRepository jwtRepository;

    private final PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

    public UserServicesImpl(UsersRepository usersRepository, JwtRepository jwtRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.jwtRepository = jwtRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users newUser(Users users) {
        String password=passwordEncoder.encode(users.getPassword());
        users.setPassword(password);
        return usersRepository.save(users);
    }

    @Override
    public Users findByNameOrEmailAndPassword(String name,String email, String password) {

        Optional<Users> optionalUsers=usersRepository.findByNameOrEmailAndPassword(name,email,password);
        return optionalUsers.orElse(null);
    }

    @Override
    public Users findByEmail(String email) {
        Optional<Users> optionalUsers=usersRepository.findByEmail(email);
        return optionalUsers.orElse(null);

    }

    @Override
    public Users findByEmailForgotPassword(String email, String password,String password_confirm) {
       if(!password.equals(password_confirm)){

           return null;
       }
        Optional<Users> optionalUsers=this.usersRepository.findByEmail(email);
        if(optionalUsers.isPresent()){
            Users users=optionalUsers.get();

                String newPassword=passwordEncoder.encode(password);

                users.setPassword(newPassword);
                return this.usersRepository.save(users);

        }
        System.out.println("Ici c'est pas cablé 2");
        return null;
    }

    @Override
    public List<Users> getAllClients() {
        return usersRepository.findAll();
    }

    @Override
    public JwtToken findByToken(String token) {
        Optional<JwtToken> optionalUsers=this.jwtRepository.findByToken(token);
        return optionalUsers.orElse(null);
    }
}
