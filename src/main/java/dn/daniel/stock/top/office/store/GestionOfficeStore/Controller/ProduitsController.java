package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Controller.Helpers.ProductForm;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Produits;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ProduitsRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Product.ProduitServiceImpl;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.StockServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class ProduitsController {


    private final StockServiceImpl applicationService;
    private ProduitsRepository produitsRepository;
    private  ProduitServiceImpl produitService;

    public ProduitsController(StockServiceImpl applicationService, ProduitServiceImpl produitService , ProduitsRepository produitsRepository) {
        this.applicationService = applicationService;
        this.produitsRepository= produitsRepository;
        this.produitService=produitService;
    }

            @GetMapping("/product/produitAll")
    public String getAllProduit(Model model){
        Produits produits=new Produits();
        model.addAttribute("categorieAll",applicationService.getAllCategories());
        model.addAttribute("fournisseurAll",applicationService.getAllFournisseurs());
        model.addAttribute("produitAll",this.produitService.getAllProduits());
        model.addAttribute("produit",produits);
        return "produit/produits";
    }

    @PostMapping("/product/addProduit")

    public String addProduit(Model model, Produits produits,
                                @RequestParam("categorie_id")Integer categorie_id,
                                     @RequestParam("fournisseur_id") Integer fournisseur_id,
                             RedirectAttributes redirectAttributes){

        this.produitService.newProduits(produits,categorie_id);

        applicationService.addFournisseursToProduits(fournisseur_id, produits.getId());
        redirectAttributes.addFlashAttribute("addProductSuccess", "Produit a été crée avec success !");
        return "redirect:/product/produitAll";
    }

    @GetMapping("/product/detailsProduit/{id}")
    public String detailsProduit(@PathVariable("id")Integer id, Model model,
                                 RedirectAttributes redirectAttributes){

        Optional<Produits> optionalProduits=produitsRepository.findById(id);
        if(optionalProduits.isPresent()){
            Produits produits=optionalProduits.get();

            model.addAttribute("produit",produits);
            model.addAttribute("productImage",new ProductForm());
            model.addAttribute("categorieAll",applicationService.getAllCategories());
            model.addAttribute("fournisseurAll",applicationService.getAllFournisseurs());
            return "produit/details";

        }
        redirectAttributes.addFlashAttribute("messages", "Produit inexistant dans le base de données");
        return "redirect:/product/produitAll";
    }

    @PostMapping("/product/updateProduits")
    public String updateProduit(Model model, Produits produits,
                                @RequestParam("id")Integer id,
                                 ProductForm productForm,
                                @RequestParam("categorie_id")Integer categorie_id,
                                @RequestParam("fournisseur_id")Integer fournisseur_id,
                                RedirectAttributes redirectAttributes) throws IOException  {
        Optional<Produits> optionalProduits=produitsRepository.findById(id);
        if(optionalProduits.isPresent()){
            Produits product = optionalProduits.get();


         if(productForm.getProductImageFirst() !=null &&  !productForm.getProductImageFirst().isEmpty()){
             handleProductImage(productForm.getProductImageFirst());
             product.setImage_first(productForm.getProductImageFirst().getOriginalFilename());
             System.out.println("Image one upload with success");


         }if ( productForm.getProductImageSecond() !=null && !productForm.getProductImageSecond().isEmpty()){
                handleProductImage(productForm.getProductImageSecond());
                product.setImage_second(productForm.getProductImageSecond().getOriginalFilename());
                System.out.println("Image two upload with success");

            }
            if ( productForm.getProductImageThird() !=null && !productForm.getProductImageThird().isEmpty()) {
                handleProductImage(productForm.getProductImageThird());
                product.setImage_third(productForm.getProductImageThird().getOriginalFilename());
                System.out.println("Image third upload with success");
            }
            this.produitService.updateProduit(categorie_id,fournisseur_id,product);
            redirectAttributes.addFlashAttribute("updateProductSucces", "Produit Modififer avec success");
            return "redirect:/product/detailsProduit/"+produits.getId();
        }

        return null;



    }


    private String handleProductImage(MultipartFile file) throws IOException {
            file.transferTo(new File("C:\\Users\\mabod\\Desktop\\Daniel\\Daniel\\GestionOfficeStore\\GestionOfficeStore\\src\\main\\resources\\static\\product\\" + file.getOriginalFilename()));
            return file.getOriginalFilename();
    }


}
