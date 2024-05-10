package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Client;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ClientRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Password.PasswordEncoderClient;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final String SECRET_KEY="clientSecrete";
    private final PasswordEncoderClient passwordEncoderClient;


    public ClientServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder, PasswordEncoderClient passwordEncoderClient) {
        this.clientRepository = clientRepository;


        this.passwordEncoder = passwordEncoder;
        this.passwordEncoderClient = passwordEncoderClient;
    }

    @Override
    public Client newClient(Client client){
        client.setDate_creation(LocalDate.now().toString());

        String password =this.passwordEncoderClient.passwordEncoder().encode(client.getPassword());

        client.setToken(this.passwordEncoderClient.passwordEncoder().encode("Oat_"+SECRET_KEY+"_Date"+LocalDate.now().toString()));
        client.setPassword(password);
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
    public String findByEmailAndPassword(String email, String password) {


      Optional<Client> client=clientRepository.findByNomOrEmail(email,email);
        if(client.isPresent() && this.passwordEncoder.matches(client.get().getPassword(),password)){
            return client.get().getToken();
        }
        return null;
    }

    @Override
    public Client findByEmail(String email) {
        Client optionalClient=clientRepository.findByEmail(email);
        if(optionalClient !=null){


            return  optionalClient;
        }
        return null;
    }


}
