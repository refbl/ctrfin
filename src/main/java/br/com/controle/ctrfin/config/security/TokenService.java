package br.com.controle.ctrfin.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.controle.ctrfin.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${ctrfin.jwt.expiration}")
	private String expiration;
	
	@Value("${ctrfin.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		
		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() +   Long.parseLong(this.expiration));
		
		return Jwts.builder()
				.setIssuer("API Controle Financas Pessoais")
				.setSubject(usuarioLogado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
	}

	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	// Retorna o Id do Usuário que foi incluido dentro do Token na geração do Token
	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		
		return Long.parseLong(claims.getSubject());
	}
	
	

}
