package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Categories;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Categories;

import java.util.List;
import java.util.Optional;

public interface CategorieService {



    Categories newCategories(Categories categories);

    List<Categories> getAllCategories();

    Categories getCategoriesById(Integer id);

    Categories updateCategories(Categories categories);

    int deleteCategorie(Integer id);
}
