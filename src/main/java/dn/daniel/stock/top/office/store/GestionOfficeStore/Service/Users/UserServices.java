package dn.daniel.stock.top.office.store.GestionOfficeStore.Service.Users;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.JwtToken;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.Users;

import java.util.List;

public interface UserServices {


    Users newUser(Users users);

    Users findByNameOrEmailAndPassword(String name,String email,String password);
    Users findByEmail(String email);
    Users findByEmailForgotPassword(String email,String password,String password_confirm);

    List<Users> getAllClients();
    JwtToken findByToken(String token);
}
