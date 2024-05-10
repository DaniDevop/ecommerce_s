package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.Helpers;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Categories;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Fournisseurs;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import org.springframework.web.multipart.MultipartFile;

public class ProductForm  {


    private Integer produit_id;

    private String designation;

    private float prix_vente;

    @Column(nullable = true)
    private Integer stock;

    @Column(nullable = true)
    private String date_creation;
    @Column(nullable = true)
    private String date_update;



    private MultipartFile productImageFirst;
    private MultipartFile productImageSecond;
    private MultipartFile productImageThird;


    public ProductForm() {
    }

    public Integer getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(Integer produit_id) {
        this.produit_id = produit_id;
    }

    public MultipartFile getProductImageFirst() {
        return productImageFirst;
    }

    public void setProductImageFirst(MultipartFile productImageFirst) {
        this.productImageFirst = productImageFirst;
    }

    public MultipartFile getProductImageSecond() {
        return productImageSecond;
    }

    public void setProductImageSecond(MultipartFile productImageSecond) {
        this.productImageSecond = productImageSecond;
    }

    public MultipartFile getProductImageThird() {
        return productImageThird;
    }

    public void setProductImageThird(MultipartFile productImageThird) {
        this.productImageThird = productImageThird;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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
}
