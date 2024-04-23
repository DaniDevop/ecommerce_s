package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Categories;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.CategoriesRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Categories.CategorieServiceImpl;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.StockServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class CategoriesController {


 private CategoriesRepository categoriesRepository;

 private  CategorieServiceImpl categorieService;
    public CategoriesController( CategoriesRepository categoriesRepository, CategorieServiceImpl categorieService) {

        this.categoriesRepository=categoriesRepository;
        this.categorieService = categorieService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/categoies/categorieListes")
    public String listesProduits(Model model){

            model.addAttribute("categoriesAll",this.categorieService.getAllCategories());
        model.addAttribute("categorie",new Categories());
        return "categorie/listes";
    }
    @PostMapping("/categories/addCategories")
    public String addCategories(Model model, Categories categories, RedirectAttributes redirectAttributes){

        Optional<Categories> optionalCategories=this.categoriesRepository.findByCategorie(categories.getCategorie());
        if(optionalCategories.isPresent()){
            redirectAttributes.addFlashAttribute("messages", "Categories existe déjà dans la base de données");


            return "redirect:/categoies/categorieListes";
        }
        this.categorieService.newCategories(categories);
        redirectAttributes.addFlashAttribute("messages", "Categories ajouté avec succèss");


        return "redirect:/categoies/categorieListes";
    }
    @GetMapping("/categories/detailsCategorie/{id}")
    public String details(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        Optional<Categories> optionalCategories=categoriesRepository.findById(id);
        if(optionalCategories.isPresent()){
            Categories categories=optionalCategories.get();
            model.addAttribute("categorie",categories);
            return "categorie/detail";
        }
        redirectAttributes.addFlashAttribute("messages", "La catégorie n'existe pas dans la base !");

        return null;
    }
    @GetMapping("/admin/deleteCategorie/{id}")
    public String deleteCategorie(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes){
        Optional<Categories> optionalCategories=categoriesRepository.findById(id);
        if(optionalCategories.isPresent()){
            Categories categories=optionalCategories.get();
            categoriesRepository.delete(categories);
            redirectAttributes.addFlashAttribute("messages", "La catégorie est supprimé avec success !");
            return "redirect:/categoies/categorieListes";
        }

        return null;
    }
    @PostMapping("/categories/updateCategorie")
    public String updateCategorie(@RequestBody Categories categorie, @RequestParam("id") Integer id, RedirectAttributes redirectAttributes){
        Optional<Categories> optionalCategories=categoriesRepository.findById(id);
        if(optionalCategories.isPresent()){
          categorieService.updateCategories(categorie);
            redirectAttributes.addFlashAttribute("messages", "Mise a jour effectué avec success !!");
            return "redirect:/categories/detailsCategorie/  "+id;
        }
        return null;
    }


}
