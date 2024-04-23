package dn.daniel.stock.top.office.store.GestionOfficeStore.Repository;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Produits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitsRepository extends JpaRepository<Produits,Integer> {
}
