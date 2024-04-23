package dn.daniel.stock.top.office.store.GestionOfficeStore.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String email;
    private String tel;
    @ManyToOne
    private Adresse adresse;
    private String password;

    public Client() {
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    private String date_creation;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Collection<Commandes>commandes=new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Collection<Factures> factures=new ArrayList<>();




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Client(String nom, String email, String tel,  String password) {
        this.nom = nom;
        this.email = email;
        this.tel = tel;

        this.password = password;
    }

    public Client(String nom, String tel) {
        this.nom = nom;
        this.tel = tel;
    }

    public Client(String nom, String email, String tel) {
        this.nom = nom;
        this.email = email;
        this.tel = tel;
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Collection<Commandes> getCommandes() {
        return commandes;
    }

    public void setCommandes(Collection<Commandes> commandes) {
        this.commandes = commandes;
    }

    public Collection<Factures> getFactures() {
        return factures;
    }

    public void setFactures(Collection<Factures> factures) {
        this.factures = factures;
    }
}
