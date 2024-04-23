package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Users;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.JwtToken;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Users;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.JwtRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.UsersRepository;
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


    public UserServicesImpl(UsersRepository usersRepository, JwtRepository jwtRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.jwtRepository = jwtRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users newUser(Users users) {
        String password=passwordEncoder.encode(users.getPassword());
        String token= "Barrer "+passwordEncoder.encode(users.getName()+users.getEmail());
        users.setPassword(password);

        if (token.length() > 255) {
            token = token.substring(0, 255);
        }

        JwtToken jwtToken =new JwtToken(token, LocalDate.now().toString(),LocalDate.now().toString(),users);
        jwtRepository.save(jwtToken);
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
    public List<Users> getAllClients() {
        return usersRepository.findAll();
    }

    @Override
    public JwtToken findByToken(String token) {
        Optional<JwtToken> optionalUsers=this.jwtRepository.findByToken(token);
        return optionalUsers.orElse(null);
    }
}
