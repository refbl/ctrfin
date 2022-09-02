package br.com.controle.ctrfin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.controle.ctrfin.controller.dto.ResumoDto;
import br.com.controle.ctrfin.modelo.Resumo;
import br.com.controle.ctrfin.service.ResumoService;

@Controller
@RequestMapping("/resumo")
public class ResumoController {
	
	@Autowired
	private ResumoService resumoSerive;
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<ResumoDto> listarResumo(@PathVariable Integer ano, @PathVariable Integer mes){
		
		Resumo resumo = this.resumoSerive.listar(ano, mes);
		
		ResumoDto resumoDto = new ResumoDto().converter(resumo);
		
		return ResponseEntity.ok(resumoDto);
		
	}

}
