package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.Helpers;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm  {


    private Integer produit_id;


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
}
