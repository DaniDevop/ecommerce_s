package dn.daniel.stock.top.office.store.GestionOfficeStore.Entity;

import jakarta.persistence.*;

@Entity
public class Ventes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Factures factures;
    @ManyToOne
    private Produits produits;
    private Integer qte_entrant;

    public Ventes(Integer qte_entrant) {
        this.qte_entrant = qte_entrant;
    }

    public Ventes() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Factures getFactures() {
        return factures;
    }

    public void setFactures(Factures factures) {
        this.factures = factures;
    }

    public Produits getProduits() {
        return produits;
    }

    public void setProduits(Produits produits) {
        this.produits = produits;
    }

    public Integer getQte_entrant() {
        return qte_entrant;
    }

    public void setQte_entrant(Integer qte_entrant) {
        this.qte_entrant = qte_entrant;
    }
}
