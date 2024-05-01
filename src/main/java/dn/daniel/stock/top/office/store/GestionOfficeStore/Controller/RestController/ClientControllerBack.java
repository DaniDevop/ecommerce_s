package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.RestController;


import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ProduitsRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/client/")
public class ClientControllerBack {


    private final  ClientService clientService;
    private final ProduitsRepository produitsRepository;

    public ClientControllerBack(ClientService clientService, ProduitsRepository produitsRepository) {
        this.clientService = clientService;
        this.produitsRepository = produitsRepository;
    }





    @GetMapping("/clientAll")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String,Object> getAllCommandesMap(){
        HashMap<String,Object> response=new HashMap<>();
        response.put("data",clientService.getAllClients());
        return response;
    }

    @GetMapping("/productAll")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String,Object> getAllProduits(){
        HashMap<String,Object> response=new HashMap<>();
        response.put("produits",this.produitsRepository.findAll());
        return response;
    }



    @PostMapping("/addClient")
    public ResponseEntity<Client> addClients(@RequestBody Client client){

        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.newClient(client));
    }


    @GetMapping("/getClientBy/{id}")
    public ResponseEntity<?> getClientsById(@PathVariable("id") Integer id){

        try {
            Client client = clientService.getClientsById(id);
            if (client != null) {
                return ResponseEntity.ok(client);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching client details.");
        }
    }


    @PutMapping("/updateClient")
    public ResponseEntity<Client> updateClients(@RequestBody Client client){

        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.updateClient(client));
    }









}
