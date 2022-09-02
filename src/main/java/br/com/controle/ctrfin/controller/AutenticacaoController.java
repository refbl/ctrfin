package br.com.controle.ctrfin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.ctrfin.config.security.TokenService;
import br.com.controle.ctrfin.controller.dto.TokenDto;
import br.com.controle.ctrfin.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authentication = this.authManager.authenticate(dadosLogin);
			
			//Geracao do Token
			String token = tokenService.gerarToken(authentication);
			
			System.out.println("TOKEN : " + token);
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}

}
