package com.example.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
	// token kullanım süresi
	// 1Gün ==> saat * dakika * saniye * mili saniye
	// 1Gün
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60 * 1000;

	// token şifreleme ve çözme için kullanılan key (Yani JWT nin 3. partini )
	public static final String SIGNING_KEY = "hcelal";

	// token dan username ı aldığımız method
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// token nın biteceği tarihi öğrenme method
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
	}

	// token nın süresi doldumu
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// User dan token generate işlemi
	public String generateToken(User user) {
		return doGenerateToken(user.getUsername());
	}

	// User name dan token generate işlemi
	private String doGenerateToken(String subject) {

		Claims claims = Jwts.claims().setSubject(subject);
		claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("USER")));

		return Jwts.builder().setClaims(claims)
				// imzalama yeri sunucu, domain gibi ..
				.setIssuer("http://hcelal.com")
				// imzalanma zamanı (saati)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				// tokenın biteceği tarih
				// (şuanki zaman + belirlenen süre 24 saat mili saniye olarak)
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS))
				// şifreleme algoritmaso ve imzası ile (hcelal :D )
				.signWith(SignatureAlgorithm.HS256, SIGNING_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
