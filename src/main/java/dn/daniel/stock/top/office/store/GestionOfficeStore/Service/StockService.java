package dn.daniel.stock.top.office.store.GestionOfficeStore.Service;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.*;

import java.util.List;

public interface StockService {

    Produits addProduits(Produits produits);
    Categories addCategories(Categories categories);
    Fournisseurs addFounisseurs(Fournisseurs fournisseurs);
    Factures addFactures(Factures factures);
    Ventes addVentes(Ventes ventes);

    Client addClient(Client client);

    Commandes addCommandes(Commandes commandes);


    void addCategoriesToProduct(Integer idCategorie,Integer produit_id);
    void addFournisseursToProduits(Integer fournisseur_id,Integer produit_id);
    void addCommandeToClient(Integer client_id,Integer commandes_id);


     void addFacturesToVentes(Integer id_factures,Integer qte_commande,Integer id_ventes);
    List<Produits> getAllProduits();
    List<Categories> getAllCategories();
    List<Factures> getAllFactures();
    List<Ventes> getAllVentes();
    List<Fournisseurs> getAllFournisseurs();
    List<Client> getAllClients();
    List<Commandes> getAllCommandes();

}