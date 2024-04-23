package dn.daniel.stock.top.office.store.GestionOfficeStore.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Produits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String designation;

    private float prix_vente;

    @Column(nullable = true)
    private Integer stock;

    @ManyToOne
    private Categories categories;
    @ManyToOne
    private Fournisseurs fournisseurs;
    @Column(nullable = true)
    private String date_creation;
    @Column(nullable = true)
    private String date_update;

    private String image_first;
    private String image_second;
    private String image_third;


    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Collection<Ventes>ventes=new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Collection<ImageProduct>imageProducts =new ArrayList<>();

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Produits() {
    }

    public Produits(String designation, float prix_achat, float prix_vente, Integer stock) {
        this.designation = designation;
        this.prix_vente = prix_vente;
        this.stock = stock;
    }

    public Produits(String designation, float prix_achat, float prix_vente) {
        this.designation = designation;
        this.prix_vente = prix_vente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Fournisseurs getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(Fournisseurs fournisseurs) {
        this.fournisseurs = fournisseurs;
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

    public Collection<Ventes> getVentes() {
        return ventes;
    }

    public void setVentes(Collection<Ventes> ventes) {
        this.ventes = ventes;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public float getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(float prix_vente) {
        this.prix_vente = prix_vente;
    }

    public Collection<ImageProduct> getImageProducts() {
        return imageProducts;
    }

    public void setImageProducts(Collection<ImageProduct> imageProducts) {
        this.imageProducts = imageProducts;
    }

    public String getImage_first() {
        return image_first;
    }

    public void setImage_first(String image_first) {
        this.image_first = image_first;
    }

    public String getImage_second() {
        return image_second;
    }

    public void setImage_second(String image_second) {
        this.image_second = image_second;
    }

    public String getImage_third() {
        return image_third;
    }

    public void setImage_third(String image_third) {
        this.image_third = image_third;
    }
}
