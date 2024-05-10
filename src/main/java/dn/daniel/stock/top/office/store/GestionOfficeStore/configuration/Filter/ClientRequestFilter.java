package dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Filter;


import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ClientRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Client.ClientService;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Client.ClientServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class ClientRequestFilter  extends OncePerRequestFilter {

    private final ClientRepository clientRepository;
    private final JwtClientService jwtClientService;

    public ClientRequestFilter(ClientRepository clientRepository, JwtClientService jwtClientService) {
        this.clientRepository = clientRepository;
        this.jwtClientService = jwtClientService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {



        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header ==null|| !header.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.substring(7);

        Optional<Client> optionalClient = this.clientRepository.findByToken(token);
        if(optionalClient.isPresent()){


                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(optionalClient.get(),null,null);
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);



        }


        filterChain.doFilter(request, response);

    }
}
