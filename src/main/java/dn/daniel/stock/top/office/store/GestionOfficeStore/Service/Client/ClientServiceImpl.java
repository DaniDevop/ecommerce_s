package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Client;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.JwtToken;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ClientRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.JwtRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtRepository jwtRepository;

    public ClientServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder, JwtRepository jwtRepository) {
        this.clientRepository = clientRepository;

        this.passwordEncoder = passwordEncoder;
        this.jwtRepository = jwtRepository;
    }

    @Override
    public Client newClient(Client client){
        client.setDate_creation(LocalDate.now().toString());
        Optional<Client> optionalClient=clientRepository.findByEmail(client.getEmail());
        if(optionalClient.isPresent()){
            return null;
        }
        client.setPassword(passwordEncoder.encode(client.getPassword()));
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

    @Override
    public JwtToken createToken(Client client,JwtToken jwtToken) {
        String token=passwordEncoder.encode("Oat_"+client.getNom());

        jwtToken.setDate_expired("");
        jwtToken.setToken(token);
        return this.jwtRepository.save(jwtToken);
    }
}
