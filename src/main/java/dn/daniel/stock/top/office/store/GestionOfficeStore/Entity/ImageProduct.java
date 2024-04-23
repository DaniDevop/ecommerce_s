package dn.daniel.stock.top.office.store.GestionOfficeStore.Entity;

import jakarta.persistence.*;

@Entity
public class ImageProduct {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileOne;
    private String fileSecond;
    private String fileThird;

    @ManyToOne
    private Produits produits;

    public ImageProduct() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileOne() {
        return fileOne;
    }

    public void setFileOne(String fileOne) {
        this.fileOne = fileOne;
    }

    public String getFileSecond() {
        return fileSecond;
    }

    public void setFileSecond(String fileSecond) {
        this.fileSecond = fileSecond;
    }

    public String getFileThird() {
        return fileThird;
    }

    public void setFileThird(String fileThird) {
        this.fileThird = fileThird;
    }

    public Produits getProduits() {
        return produits;
    }

    public void setProduits(Produits produits) {
        this.produits = produits;
    }
}
