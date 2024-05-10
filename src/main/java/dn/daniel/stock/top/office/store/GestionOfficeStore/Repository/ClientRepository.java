package dn.daniel.stock.top.office.store.GestionOfficeStore.Repository;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {



    Optional<Client> findByNomOrEmail(String nom,String email);

    Optional<Client> findByToken(String token);
    Client findByEmail(String email);


}
