package dn.daniel.stock.top.office.store.GestionOfficeStore.Auth;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Auth.Filter.UsersAuthentificationProvider;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Auth.Services.UserAuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {




    private final UserAuthenticationService userAuthenticationService;

    public SecurityConfig(UserAuthenticationService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{


        httpSecurity.userDetailsService(this.userAuthenticationService);
       // httpSecurity.authorizeHttpRequests(request->request.requestMatchers("/login").permitAll());
        httpSecurity.authorizeHttpRequests(http->http.anyRequest().permitAll());
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
