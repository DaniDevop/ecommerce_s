package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.Users;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.JwtToken;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Users;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.JwtRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Users.UserServicesImpl;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {



    private final UserServicesImpl uservice;
    private final JwtRepository jwtRepository;

    public UsersController(UserServicesImpl uservice, JwtRepository jwtRepository) {
        this.uservice = uservice;
        this.jwtRepository = jwtRepository;
    }

    @PostMapping("/newUser")
    public Users createUsers(@RequestBody Users users){


        return uservice.newUser(users);
    }

    public String login(){
        return null;
    }

    @GetMapping("/token/{id}")
    public JwtToken getTokenById(@PathVariable("id") Integer id){

        Optional<JwtToken> optionalJwtToken=jwtRepository.findById(id);
        return optionalJwtToken.orElse(null);
    }




}
