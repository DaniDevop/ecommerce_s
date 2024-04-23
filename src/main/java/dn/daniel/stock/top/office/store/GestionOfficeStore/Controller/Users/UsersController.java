package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.Users;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.JwtToken;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Users;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.JwtRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Users.UserServicesImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class UsersController {




    @GetMapping("/login")
    public String login(){
        return "login"
                ;
    }
    @GetMapping("/logotUser")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication); // <= This is the call you are looking for.
        }
        return "redirect:/login";
    }






}
