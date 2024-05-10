package dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Auth;;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Client.ClientServiceImpl;
import dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Filter.ClientRequestFilter;
import dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Filter.JwtClientService;
import dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Services.UserAuthenticationService;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {




    private final UserAuthenticationService userAuthenticationService;
    private final ClientRequestFilter clientRequestFilter;

    public SecurityConfig(UserAuthenticationService userAuthenticationService, ClientRequestFilter clientRequestFilter) {
        this.userAuthenticationService = userAuthenticationService;

        this.clientRequestFilter = clientRequestFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{


        httpSecurity.userDetailsService(this.userAuthenticationService);

        httpSecurity.authorizeHttpRequests(http->http.requestMatchers("/client/clientAll").authenticated() .anyRequest().permitAll()  );
        httpSecurity.addFilterAfter(clientRequestFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.csrf(csrf->csrf.disable());
        httpSecurity.formLogin(login->login.loginPage("/login").permitAll());


        return httpSecurity.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
