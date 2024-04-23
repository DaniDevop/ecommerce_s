package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Commandes;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Commandes;

import java.util.List;

public interface CommandeService {


    Commandes newCommandes(Commandes commandes,Integer client_id);

    List<Commandes> getAllCommandes();

    Commandes getClientById(Integer id);

    Commandes updateCommandes(Commandes commandes,Integer client_id);



}
