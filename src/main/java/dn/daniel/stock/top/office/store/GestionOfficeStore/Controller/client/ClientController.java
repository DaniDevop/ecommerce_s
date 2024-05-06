package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.client;


import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.JwtToken;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ClientRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.CommandesRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Client.ClientServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/client/")
public class ClientController {


    private CommandesRepository commandesRepository;
    private ClientRepository clientRepository;

    private final ClientServiceImpl clientService;

    public ClientController(CommandesRepository commandesRepository, ClientRepository clientRepository, ClientServiceImpl clientService) {
        this.commandesRepository = commandesRepository;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @GetMapping("/client/clientAll")
    private String listeClients(Model model){

        model.addAttribute("commandesAll",commandesRepository.findAll());
        return "commandes/commandes";
    }

    @PostMapping("/create-compte")
    public Map<String, Object> createCompte(@RequestBody Client client) {
        Map<String, Object> response = new HashMap<>();

        Optional<Client> optionalClient = clientRepository.findByEmail(client.getEmail());
        if (optionalClient.isPresent()) {
            // Client with this email already exists
            response.put("message", "L'email existe déjà dans la base de données");
            response.put("client", optionalClient.get());
        }
            // Create the new client account
            Client newClient = clientRepository.save(client);
            JwtToken jwtToken =new JwtToken(client);
            clientService.createToken(newClient,jwtToken);
            response.put("message", "Compte créé avec succès");
            response.put("client", jwtToken);


        return response;
    }


}





