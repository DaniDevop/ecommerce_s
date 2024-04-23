package dn.daniel.stock.top.office.store.GestionOfficeStore.Repository;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Factures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturesRepository extends JpaRepository<Factures,Integer> {
}
