package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Product;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Produits;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProduitService {

    Produits newProduits(Produits produits,Integer categorie_id);

    List<Produits> getAllProduits();

    Produits getProductById(Integer id);


}
