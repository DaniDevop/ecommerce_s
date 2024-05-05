package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Client;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client newClient(Client client){
        client.setDate_creation(LocalDate.now().toString());
        Optional<Client> optionalClient=clientRepository.findByEmail(client.getEmail());
        if(optionalClient.isPresent()){
            return null;
        }
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public int deleteClient(Integer id) {
        Optional<Client> optionalClient=clientRepository.findById(id);
        if(optionalClient.isPresent()){
            Client client=optionalClient.get();
            clientRepository.delete(client);
            return 201;
        }
        return 400;
    }

    @Override
    public Client getClientsById(Integer id) {
        Optional<Client> optionalClient=clientRepository.findById(id);
        if(optionalClient.isPresent()){
            return optionalClient.get();
        }
        return null;
    }

    @Override
    public Client updateClient(Client client) {
        if( client.getId() == null){
             return null;
        }
        return clientRepository.save(client);
    }

    @Override
    public Client findByEmailAndPassword(String email, String password) {

        Optional<Client>optionalClient=clientRepository.findByNomOrEmailAndPassword(email,email,password);
        if(optionalClient.isPresent()){
            Client client=optionalClient.get();

            return  client;
        }
        return null;
    }
}
