package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Client;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import java.util.List;


public interface ClientService {


    Client newClient(Client client);

    List<Client> getAllClients();

    int deleteClient(Integer id);
    Client getClientsById(Integer id);

    Client updateClient(Client client);

    String findByEmailAndPassword(String email,String password);
    Client findByEmail(String email);


}
