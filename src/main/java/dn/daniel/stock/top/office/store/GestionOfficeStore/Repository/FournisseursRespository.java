package dn.daniel.stock.top.office.store.GestionOfficeStore.Repository;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Fournisseurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FournisseursRespository extends JpaRepository<Fournisseurs,Integer> {


    Optional<Fournisseurs> findByNomOrEmail(String nom,String email);
}
