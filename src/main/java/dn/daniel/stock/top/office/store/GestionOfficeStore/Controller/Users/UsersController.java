package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.Users;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.RestController.Helpers.UserData;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Users;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.UsersRepository;
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


@Controller
public class UsersController {



    private final UserServicesImpl userServices;
    private final UsersRepository usersRepository;

    public UsersController(UserServicesImpl userServices, UsersRepository usersRepository) {
        this.userServices = userServices;

        this.usersRepository = usersRepository;
    }

    @GetMapping("/login")
    public String login(){
        return "login";

    }

    @GetMapping("/loginTest")
    public String loginTest(){
        return "login/index";
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
    public String forgotPasswordUsers(@RequestParam String email,@RequestParam String password,@RequestParam String password_confirm,RedirectAttributes attributes){

        Users users=this.userServices.findByEmailForgotPassword(email,  password, password_confirm);
        if(users != null){

            attributes.addFlashAttribute("success","Informations valide mot de passe modifie");
            System.out.println(email);
            return  "redirect:/login";
        }
        attributes.addFlashAttribute("error","user not found or information incorect");

        return  "redirect:/forgotPassword";
    }


    @GetMapping("/addUsers")
    public String addUserCompte(Model model){
        UserData user= new UserData();
        model.addAttribute("user",user);
        return "/admin/addUser";}

    @PostMapping("/registerUser")
    public String registerUsers(Model model,UserData user,@RequestParam String email,@RequestParam String password,@RequestParam String password_confirm ,RedirectAttributes attributes){
        Users users=this.userServices.findByEmail(user.getEmail());
        if(users !=null){
            attributes.addFlashAttribute("errorEmail","L email existe déjà dans la base de donées");
            return  "redirect:/addUsers";
        }
        Users users1=new Users(user.getUsername(),user.getEmail(),user.getPassword());
        this.userServices.newUser(users1);
        attributes.addFlashAttribute("succesRegister","Compte utilisateur crée avec succès !");
        return  "redirect:/addUsers";
    }






}
