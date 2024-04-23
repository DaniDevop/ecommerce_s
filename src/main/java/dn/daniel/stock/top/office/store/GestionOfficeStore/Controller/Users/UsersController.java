package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.Users;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.RestController.Helpers.UserData;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UsersController {



    private final UserServicesImpl userServices;

    public UsersController(UserServicesImpl userServices) {
        this.userServices = userServices;

    }

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

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model){
        UserData user=new UserData();
        model.addAttribute("user",user);
        return "forgot-password";
    }


    @PostMapping("/forgotPasswordUser")
    public String forgotPasswordUsers(Model model,UserData user,RedirectAttributes attributes){

        Users users=this.userServices.findByEmailForgotPassword(user.getEmail(),user.getPassword(),user.getPassword_confirm());
        if(users != null){

            attributes.addFlashAttribute("success","Informations valide mot de passe modifie");
            return  "redirect:/forgotPassword";
        }
        attributes.addFlashAttribute("error","user not found or information incorect");
        System.out.println(user.getEmail() + user.getPassword() + user.getPassword_confirm());
        return  "redirect:/forgotPassword";
    }








}
