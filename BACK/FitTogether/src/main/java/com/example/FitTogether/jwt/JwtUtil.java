package com.example.FitTogether.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private String key = "SSAFY_LASTPJT_FITTOGETHER_EXERCISE_AND_SHARE_WITH_OTHERS";
	private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

	public String createToken(int id, String name) {
		Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24);
		return Jwts.builder().header().add("typ", "JWT").and()
				.claim("id", id)
				.claim("name", name)
				.expiration(exp)
				.signWith(secretKey)
				.compact();
	}

	public Jws<Claims> validate(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
	}
}
