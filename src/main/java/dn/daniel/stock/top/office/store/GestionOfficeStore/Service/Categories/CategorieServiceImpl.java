package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Categories;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Categories;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.CategoriesRepository;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ProduitsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {

    private CategoriesRepository categoriesRepository;


    public CategorieServiceImpl( CategoriesRepository categoriesRepository) {

        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public Categories newCategories(Categories categories) {
        categories.setDate_creation(LocalDate.now().toString());
        return categoriesRepository.save(categories);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public Categories getCategoriesById(Integer id) {
        Optional<Categories> optionalCategories=categoriesRepository.findById(id);
        return optionalCategories.orElse(null);
    }

    @Override
    public Categories updateCategories(Categories categories) {
        if(categories.getId() ==null){
            return null;
        }

        categories.setDate_update(LocalDate.now().toString());
        return categoriesRepository.save(categories);
    }

    @Override
    public int deleteCategorie(Integer id) {
        return 0;
    }
}
