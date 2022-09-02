package br.com.controle.ctrfin.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.controle.ctrfin.modelo.Usuario;
import br.com.controle.ctrfin.repository.UsuarioRepository;

/**
 * Busca Usuario no banco atraves do Username. Se existir Usuário o SpringSecurity valida a senha
 * caso contrario retornamos exception
 * @author RENATO
 *
 */
@Service
public class AutenticacaoService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> usuarioOptional = this.repository.findByEmail(username);
		
		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		} else {
			throw new UsernameNotFoundException("Dados Invalidos.");
		}
	}
}
