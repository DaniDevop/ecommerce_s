package dn.daniel.stock.top.office.store.GestionOfficeStore.Controller;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Fournisseurs;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.FournisseursRespository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Service.StockServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class FournisseursController {


    private final StockServiceImpl stockService;
    private FournisseursRespository fournisseursRespository;
    public FournisseursController(StockServiceImpl stockService,FournisseursRespository fournisseursRespository){
        this.stockService=stockService;
        this.fournisseursRespository=fournisseursRespository;
    }

    @GetMapping("/fournisseurs/listesFournisseur")
    public String listesFournisseurs(Model model){

        model.addAttribute("fournisseurAll",stockService.getAllFournisseurs());
        model.addAttribute("fournisseur",new Fournisseurs());
        model.addAttribute("fournisseurNumber",this.fournisseursRespository.count());


        return "fournisseur/listes";
    }


    @PostMapping("/fournisseurs/addFournisseur")
    public String newFournisseur(Model model, Fournisseurs fournisseurs,
                                 RedirectAttributes redirectAttributes) {

       Optional<Fournisseurs>optionalFournisseurs=this.fournisseursRespository.findByNomOrEmail(fournisseurs.getNom(),fournisseurs.getEmail());
       if(optionalFournisseurs.isPresent()){
           redirectAttributes.addFlashAttribute("errorFournisseur", "L email ou le numéro de téléphone déjà existant");
           return "redirect:/fournisseurs/listesFournisseur";

       }
        stockService.addFounisseurs(fournisseurs);
        redirectAttributes.addFlashAttribute("addFournisseur", "Fournisseur a été créée avec succès!");
        return "redirect:/fournisseurs/listesFournisseur";
    }

    @GetMapping("/fournisseur/detailsFournisseur/{id}")
    public String detailsFournisseur(@PathVariable("id") Integer id, Model model) {
        Optional<Fournisseurs> optionalFournisseurs = this.fournisseursRespository.findById(id);
        if (optionalFournisseurs.isPresent()) {
            Fournisseurs fournisseurs = optionalFournisseurs.get();
            model.addAttribute("user", fournisseurs);
            return "fournisseur/detail";
        }
        return null;
    }

        @PostMapping("/fournisseur/updateFournisseur")
    public String updateFournisseur(Model model, Fournisseurs fournisseurs,
                                    RedirectAttributes redirectAttributes,
                                    @RequestParam("id") Integer id) {

        Optional<Fournisseurs> optionalFournisseurs = this.fournisseursRespository.findById(id);
        if (optionalFournisseurs.isPresent()) {
            Fournisseurs fournisseur = optionalFournisseurs.get();
            fournisseur.setNom(fournisseurs.getNom());
            fournisseur.setTel(fournisseurs.getTel());
            fournisseur.setEmail(fournisseurs.getEmail());
            fournisseur.setAdresse(fournisseurs.getAdresse());
            stockService.addFounisseurs(fournisseurs);
            redirectAttributes.addFlashAttribute("succesFournisseur", "Mise à jour a été éffectué avec succès!");
            return "redirect:/fournisseur/detailsFournisseur/" + fournisseurs.getId();
        }
        return null;
    }

    @GetMapping("/fournisseur/deleteFournisseur/{id}")
    public String deleteFournisseur(@PathVariable("id")Integer id,RedirectAttributes redirectAttributes){
        Optional<Fournisseurs>optionalFournisseurs=this.fournisseursRespository.findById(id);
        if(optionalFournisseurs.isPresent()) {
            Fournisseurs fournisseurs=optionalFournisseurs.get();
            this.fournisseursRespository.delete(fournisseurs);
            redirectAttributes.addFlashAttribute("deleteFournisseur", "Fournisseur supprimé avec succès!");
            return "redirect:/fournisseurs/listesFournisseur";
        }
        return null;
    }
}
