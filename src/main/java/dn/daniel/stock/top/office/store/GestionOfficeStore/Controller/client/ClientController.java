package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.client;


import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ClientRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.CommandesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {


    private CommandesRepository commandesRepository;
    private ClientRepository clientRepository;


    public ClientController(CommandesRepository commandesRepository, ClientRepository clientRepository) {
        this.commandesRepository = commandesRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/client/clientAll")
    private String listeClients(Model model){

        model.addAttribute("commandesAll",commandesRepository.findAll());
        return "commandes/commandes";
    }

}





