package dn.daniel.stock.top.office.store.GestionOfficeStore.configuration.Filter;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.el.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
public class JwtClientService {





    public static String generateToken(String username)  {
        String encodedString = Base64.getUrlEncoder().encodeToString(username.getBytes());
        return encodedString;
    }

    public static String extractUsername(String token) {
        try {
            byte[] decodedBytes = Base64.getUrlDecoder().decode(token);
            String decodedString = new String(decodedBytes);
            return decodedString;
        } catch (IllegalArgumentException e) {
            // Gérer l'erreur de décodage ici
            e.printStackTrace(); // Afficher l'erreur pour le débogage
            return null; // Ou renvoyer une valeur par défaut, selon le cas
        }
    }



}
