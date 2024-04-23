package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Commandes;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Commandes;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ClientRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.CommandesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Service
@Transactional
public class CommandeServiceImpl implements CommandeService {


    private CommandesRepository commandesRepository;

    private ClientRepository clientRepository;

    public CommandeServiceImpl(CommandesRepository commandesRepository,ClientRepository clientRepository) {
        this.commandesRepository = commandesRepository;
        this.clientRepository=clientRepository;
    }

    @Override
    public Commandes newCommandes(Commandes commandes, Integer client_id) {
        Optional<Client> optionalClient=clientRepository.findById(client_id);
        if(optionalClient.isPresent()){
            commandes.setClient(optionalClient.get());
            commandes.setMatricule(UUID.randomUUID().toString());
            commandes.setDate_creation(LocalDate.now().toString());
            return commandesRepository.save(commandes);
        }
        return null;
    }

    @Override
    public List<Commandes> getAllCommandes() {
        return commandesRepository.findAll();
    }

    @Override
    public Commandes getClientById(Integer id) {
        Optional<Commandes> optionalCommandes=commandesRepository.findById(id);

        return optionalCommandes.orElse(null);
    }

    @Override
    public Commandes updateCommandes(Commandes commandes,Integer client_id) {
        if(commandes.getClient()==null){
            return null;
        }
        Optional<Client> optionalClient=clientRepository.findById(client_id);
        if(optionalClient.isPresent()) {
            commandes.setClient(optionalClient.get());
            commandes.setDate_creation(LocalDate.now().toString());
            return commandesRepository.save(commandes);
        }
         return  null;
    }

}
