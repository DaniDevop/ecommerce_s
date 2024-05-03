package dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Auth;;
import dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Services.UserAuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

        httpSecurity.authorizeHttpRequests(http->http.anyRequest().permitAll()  );
        httpSecurity.formLogin(login->login.loginPage("/login").permitAll());
        httpSecurity.logout(
                logout->logout.invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"
                                ).logoutUrl("/logotUser").permitAll());

        return httpSecurity.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
