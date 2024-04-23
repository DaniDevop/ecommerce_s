package dn.daniel.stock.top.office.store.GestionOfficeStore.Entity;


import jakarta.persistence.*;

@Entity
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String token;
    private String exprired_at;
    private String created_at;


    @ManyToOne
    private Users users;

    public JwtToken( String token, String exprired_at, String created_at, Users users) {

        this.token = token;
        this.exprired_at = exprired_at;
        this.created_at = created_at;

        this.users = users;
    }


    public JwtToken() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExprired_at() {
        return exprired_at;
    }

    public void setExprired_at(String exprired_at) {
        this.exprired_at = exprired_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }



    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
