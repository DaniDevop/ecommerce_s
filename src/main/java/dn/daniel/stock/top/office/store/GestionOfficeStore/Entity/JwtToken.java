package dn.daniel.stock.top.office.store.GestionOfficeStore.Entity;


import jakarta.persistence.*;

@Entity
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String token;
    private String date_expired;


    @ManyToOne
    private Client client;



    public JwtToken( String token, String date_expired,Client client) {

        this.token = token;
     this.date_expired=date_expired;

        this.client = client;
    }


    public JwtToken() {
    }
    public JwtToken(Client client) {
        this.client=client;
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


    public String getDate_expired() {
        return date_expired;
    }

    public void setDate_expired(String date_expired) {
        this.date_expired = date_expired;
    }

    public Client getclient() {
        return client;
    }

    public void setclient(Client client) {
        this.client = client;
    }
}
