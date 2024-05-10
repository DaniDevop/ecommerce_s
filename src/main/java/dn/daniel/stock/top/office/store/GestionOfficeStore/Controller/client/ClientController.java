    package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.client;


    import com.nimbusds.jose.JOSEException;
    import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
    import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ClientRepository;
    import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.CommandesRepository;
    import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Client.ClientServiceImpl;
    import dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Filter.JwtClientService;
    import dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Password.PasswordEncoderClient;
    import org.springframework.http.HttpStatus;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.Optional;


    @RestController
    @RequestMapping("/client/")
    public class ClientController {


        private CommandesRepository commandesRepository;
        private ClientRepository clientRepository;

        private final ClientServiceImpl clientService;
        private final PasswordEncoderClient passwordEncoder;
        private final JwtClientService jwtClientService;

        public ClientController(CommandesRepository commandesRepository, ClientRepository clientRepository,

                                ClientServiceImpl clientService, PasswordEncoderClient passwordEncoder, JwtClientService jwtClientService) {
            this.commandesRepository = commandesRepository;
            this.clientRepository = clientRepository;


            this.clientService = clientService;
            this.passwordEncoder = passwordEncoder;
            this.jwtClientService = jwtClientService;
        }

        @GetMapping("/clientAll")
        private List<Client> listeClients(Model model){

           return clientService.getAllClients();
        }

        @PostMapping("/create-compte")
        @ResponseStatus(HttpStatus.CREATED)
        public Map<String, Object> createCompte(@RequestBody Client client) {
            Map<String, Object> response = new HashMap<>();

            Client optionalClient = clientRepository.findByEmail(client.getEmail());
            if (optionalClient !=null) {
                // Client with this email already exists
                response.put("message", "L'email existe déjà dans la base de données");
                response.put("client", null);
            }
                // Create the new client account
            Client newClient = this.clientService.newClient(client);


                response.put("message", "Compte créé avec succès");
                response.put("client", newClient);


            return response;
        }

        @PostMapping("/loginClient")
        @ResponseStatus(HttpStatus.OK)
        public Map<String,Object> login(@RequestBody ClientRequest clientRequest) throws JOSEException {
            String username = clientRequest.getUsername();
            String password = clientRequest.getPassword();
            Map<String,Object> listeCLient=new HashMap<>();


            Optional<Client> clientOptional=this.clientRepository.findByNomOrEmail(username,username);
            if(clientOptional.isPresent() ){

                Client client=clientOptional.get();
                if(passwordEncoder.passwordEncoder().matches(password,client.getPassword())){
                    listeCLient.put("Succès",client.getToken());
                }


            }else {
                listeCLient.put("Informations incorrect",null);
            }

            return listeCLient;

        }


    }





