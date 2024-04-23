package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.RestController;


import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Commandes;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.CommandesRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Commandes.CommandeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/commandes/")
public class CommandesController {


    private final CommandeServiceImpl commandeService;
    private final CommandesRepository commandesRepository;

    public CommandesController(CommandeServiceImpl commandeService, CommandesRepository commandesRepository) {
        this.commandeService = commandeService;
        this.commandesRepository = commandesRepository;
    }


    @PostMapping("/addCommandes/{client_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCommandes(@RequestBody Commandes commandes,@PathVariable("client_id") Integer client_id){
        return  ResponseEntity.status(HttpStatus.CREATED).body(this.commandeService.newCommandes(commandes,client_id));
    }

    @GetMapping("/commandesBy/{id}")
    public ResponseEntity<Commandes> getCommandesById(@PathVariable("id")Integer id){

        Optional<Commandes>optionalCommandes=commandesRepository.findById(id);
        if(optionalCommandes.isPresent()){

            return ResponseEntity.ok(optionalCommandes.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



    @GetMapping("/commandesListes")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String,Object> getAllCommandesMap(){
         HashMap<String,Object> response=new HashMap<>();
         response.put("data",commandeService.getAllCommandes());
        return response;
    }



}
