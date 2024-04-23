package dn.daniel.stock.top.office.store.GestionOfficeStore.Repository;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository  extends JpaRepository<Users,Integer> {


    Optional<Users> findByNameOrEmailAndPassword(String name, String email, String password);
    Optional<Users> findByEmailAndPassword(String email, String password);
    Optional<Users> findByNameAndPassword(String name, String password);

    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailOrName(String email,String name);
}
