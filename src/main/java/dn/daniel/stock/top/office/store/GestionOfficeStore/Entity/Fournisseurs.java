package dn.daniel.stock.top.office.store.GestionOfficeStore.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Fournisseurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = true)
    private String nom;
    @Column(unique = true,nullable = true)
    private String email;
    @Column(unique = true,nullable = true)
    private String tel;
    @Column(unique = true,nullable = true)
    private String adresse;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Collection<Produits>produits=new ArrayList<>();

    public Fournisseurs() {
    }

    public Fournisseurs(String nom, String tel) {
        this.nom = nom;
        this.tel = tel;
    }

    public Fournisseurs(String nom, String email, String tel) {
        this.nom = nom;
        this.email = email;
        this.tel = tel;
    }

    public Fournisseurs(String nom, String email, String tel, String adresse) {
        this.nom = nom;
        this.email = email;
        this.tel = tel;
        this.adresse = adresse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Collection<Produits> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produits> produits) {
        this.produits = produits;
    }
}
