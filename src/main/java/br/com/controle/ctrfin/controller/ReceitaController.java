package br.com.controle.ctrfin.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.controle.ctrfin.controller.dto.ReceitaDto;
import br.com.controle.ctrfin.controller.form.ReceitaForm;
import br.com.controle.ctrfin.exception.ReceitaInvalidaException;
import br.com.controle.ctrfin.modelo.Receita;
import br.com.controle.ctrfin.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {
	
	@Autowired
	private ReceitaService receitaService;
	
	@GetMapping
	public List<ReceitaDto> listar(@RequestParam(required = false) String descricao) {			
		List<Receita> ListaReceitas = this.receitaService.listar(descricao);
		
		List<ReceitaDto> receitasDto = ReceitaDto.converter(ListaReceitas);
		
		return receitasDto;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDto> consultar(@PathVariable Long id) {			
		Optional<Receita> receitaOptional = this.receitaService.consultar(id);
		
		if (receitaOptional.isPresent()) {
			return ResponseEntity.ok(new ReceitaDto(receitaOptional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{ano}/{mes}")
	public List<ReceitaDto> listarPorAnoMes(@PathVariable Integer ano, @PathVariable Integer mes) {			
		List<Receita> ListaReceitas = this.receitaService.listarPorAnoMes(ano, mes);
		
		List<ReceitaDto> receitasDto = ReceitaDto.converter(ListaReceitas);
		
		return receitasDto;
	}
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@Valid @RequestBody ReceitaForm receitaForm, UriComponentsBuilder uriBuilder){
		Receita receita = receitaForm.converter();
		
		try {
			this.receitaService.salvar(receita);
			URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
			return ResponseEntity.created(uri).body(new ReceitaDto(receita));
		
		} catch (ReceitaInvalidaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody ReceitaForm receitaForm){
		Optional<Receita> receitaOptional = this.receitaService.consultar(id);
		if (receitaOptional.isPresent()) {
			
			Receita receita = receitaForm.converter();
			receita.setId(receitaOptional.get().getId());
		
			try {
				this.receitaService.salvar(receita);
				return ResponseEntity.ok(new ReceitaDto(receita));
			
			} catch (ReceitaInvalidaException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Receita> optional = this.receitaService.consultar(id);
		if (optional.isPresent()) {
			this.receitaService.deletar(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	

}
