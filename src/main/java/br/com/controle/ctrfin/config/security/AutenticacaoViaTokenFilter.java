package br.com.controle.ctrfin.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.controle.ctrfin.modelo.Usuario;
import br.com.controle.ctrfin.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{
	
	private TokenService tokenService;
	
	private UsuarioRepository usuarioRepository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		
		if (tokenService.validarToken(token)) {
			autenticarCliente(token);
		} 

		//Segue fluxo da Requisicao
		filterChain.doFilter(request, response);
		
		
	}

	private void autenticarCliente(String token) {
		
		Long idUsuario = this.tokenService.getIdUsuario(token);
		Usuario usuario = this.usuarioRepository.findById(idUsuario).get();
		
		//Cria objeto passando usuario e perfis
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		
		//Classe do Spring q força autenticacao 
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token =  request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		}
		//Retorna token sem o "Bearer"
		return token.substring(7, token.length());
	}

}

