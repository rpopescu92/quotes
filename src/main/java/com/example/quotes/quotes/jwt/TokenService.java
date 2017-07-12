package com.example.quotes.quotes.jwt;

import com.example.quotes.quotes.exception.AuthorizationHeaderValueException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@Component
public class TokenService {

    private final Environment environment;
    private final String secret;

    @Autowired
    public TokenService(Environment environment) {
        this.environment = environment;
        this.secret = environment.getProperty("quotes.jwt.secret");
    }

    public String createToken(String user) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(5).toInstant());

        return Jwts.builder()
                .claim("username", user)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    public boolean isValidJWT(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt);
            return true;
        } catch (MissingClaimException e) {
            log.error("Claim not present in JWT");
            throw new AuthorizationHeaderValueException();
        } catch (ExpiredJwtException e) {
            log.error("JWT expired on " + ZonedDateTime.now());
            throw new AuthorizationHeaderValueException();
        } catch (IncorrectClaimException e) {
            log.error("The claim has the wrong value");
            throw new AuthorizationHeaderValueException();
        } catch (SignatureException e) {
            log.error("Invalid signature exception");
            throw new AuthorizationHeaderValueException();
        } catch (MalformedJwtException e) {
            log.error("Malformed JWT exception");
            throw new AuthorizationHeaderValueException();
        }
    }

    public Authentication getAuthentication(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();

        return new UsernamePasswordAuthenticationToken(claims.get("username"), "", new ArrayList<>());
    }


}
