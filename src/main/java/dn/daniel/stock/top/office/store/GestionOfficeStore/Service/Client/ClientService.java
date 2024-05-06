package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Client;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.JwtToken;

import java.util.List;
import java.util.Optional;

public interface ClientService {


    Client newClient(Client client);

    List<Client> getAllClients();

    int deleteClient(Integer id);
    Client getClientsById(Integer id);

    Client updateClient(Client client);

    Client findByEmailAndPassword(String email,String password);
    JwtToken createToken(Client client,JwtToken jwtToken);

}
