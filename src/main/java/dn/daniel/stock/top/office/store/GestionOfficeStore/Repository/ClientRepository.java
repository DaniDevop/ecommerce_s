package dn.daniel.stock.top.office.store.GestionOfficeStore.Repository;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {



    Optional<Client> findByNomOrEmailAndPassword(String nom,String email,String password);
    Optional<Client> findByEmail(String email);
}
