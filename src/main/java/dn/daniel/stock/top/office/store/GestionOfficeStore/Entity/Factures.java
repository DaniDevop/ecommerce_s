package dn.daniel.stock.top.office.store.GestionOfficeStore.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Factures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String matricule;
    private float solde;
    @Column(nullable = true)
    private String date_creation;
    @Column(nullable = true)
    private String date_update;



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

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Collection<Ventes> ventesCollection=new ArrayList<>();

    public Factures(String matricule, float solde) {
        this.matricule = matricule;
        this.solde = solde;
    }

    public Factures() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public Collection<Ventes> getVentesCollection() {
        return ventesCollection;
    }

    public void setVentesCollection(Collection<Ventes> ventesCollection) {
        this.ventesCollection = ventesCollection;
    }
}
