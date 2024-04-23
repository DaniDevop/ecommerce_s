package dn.daniel.stock.top.office.store.GestionOfficeStore.Entity;


import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String categorie;
    private String date_creation;
    private String date_update;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private Collection<Produits> produits=new ArrayList<>();

    public Categories() {
    }

    public Categories(String categorie) {
        this.categorie = categorie;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getDate_update() {
        return date_update;
    }

    public void setDate_update(String date_update) {
        this.date_update = date_update;
    }

    public Collection<Produits> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produits> produits) {
        this.produits = produits;
    }
}
