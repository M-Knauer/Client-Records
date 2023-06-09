package com.marcelo.main.security.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.marcelo.main.entities.User;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User entity) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		   return JWT.create()
		        .withIssuer("ClientRecord")
		        .withSubject(entity.getLogin())
		        .withClaim("id", entity.getId())
		        .withExpiresAt(getValidity())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("Erro ao gerar token jwt: "+exception);
		}
	}

	private Instant getValidity() {
		return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
	}
	
	public String getSubject(String tokenJwt) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
		    return JWT.require(algorithm)
		        .withIssuer("ClientRecord")
		        .build()
		        .verify(tokenJwt)
		        .getSubject();
		     
		} catch (JWTVerificationException exception){
		    throw new RuntimeException("Token JWT inválido ou expirado");
		}
	}
}
