package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Product;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Categories;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Fournisseurs;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Produits;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.CategoriesRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.FournisseursRespository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ProduitsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {


    private ProduitsRepository produitsRepository;
    private CategoriesRepository categoriesRepository;
    private  FournisseursRespository fournisseursRespository;


    public ProduitServiceImpl(ProduitsRepository produitsRepository,
                              CategoriesRepository categoriesRepository,

                              FournisseursRespository fournisseursRespository) {
        this.produitsRepository = produitsRepository;
        this.categoriesRepository = categoriesRepository;
        this.fournisseursRespository = fournisseursRespository;
    }

    @Override
    public Produits newProduits(Produits produits,Integer categorie_id) {
        Optional<Categories> optionalCategories =categoriesRepository.findById(categorie_id);
        if(optionalCategories.isPresent()){
            produits.setCategories(optionalCategories.get());
            produits.setDate_creation(LocalDate.now().toString());
            return produitsRepository.save(produits);
        }
        return null;
    }

    @Override
    public List<Produits> getAllProduits() {
        return produitsRepository.findAll();
    }

    @Override
    public Produits getProductById(Integer id)  {
        Optional<Produits> optionalProduits=produitsRepository.findById(id);
        return optionalProduits.orElse(null);

    }



    @Override
    public Produits updateProduit(Integer categorie_id, Integer fournisser_id, Produits produits) {
        Optional<Categories> optionalCategories =categoriesRepository.findById(categorie_id);
        Optional<Produits> optionalProduits =this.produitsRepository.findById(produits.getId());
        Optional<Fournisseurs> optionalFournisseurs =this.fournisseursRespository.findById(fournisser_id);

        if(optionalCategories.isPresent() && optionalFournisseurs.isPresent() && optionalProduits.isPresent()){
            produits.setCategories(optionalCategories.get());

            produits.setFournisseurs(optionalFournisseurs.get());

            Produits product = optionalProduits.get();
            product.setFournisseurs(optionalFournisseurs.get());
            product.setDate_update(LocalDate.now().toString());
            product.setCategories(optionalCategories.get());
            return produitsRepository.save(product);
        }
        return null;
    }
}
