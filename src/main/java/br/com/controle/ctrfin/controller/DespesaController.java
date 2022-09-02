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

import br.com.controle.ctrfin.controller.dto.DespesaDto;
import br.com.controle.ctrfin.controller.form.DespesaForm;
import br.com.controle.ctrfin.exception.DespesaInvalidaException;
import br.com.controle.ctrfin.modelo.Despesa;
import br.com.controle.ctrfin.service.DespesaService;

@RestController
@RequestMapping("/despesas")
public class DespesaController {
	
	@Autowired
	private DespesaService despesaService;
	
	@GetMapping
	public List<DespesaDto> listar(@RequestParam(required = false) String descricao) {

		List<Despesa> ListaDespesas = this.despesaService.listar(descricao);
	
		List<DespesaDto> despesasDto = DespesaDto.converter(ListaDespesas);
		
		return despesasDto;
	}
	
	@GetMapping("/{ano}/{mes}")
	public List<DespesaDto> listarPorAnoMes(@PathVariable Integer ano, @PathVariable Integer mes) {			
		List<Despesa> ListaDespesas = this.despesaService.listarPorAnoMes(ano, mes);
		
		List<DespesaDto> despesasDto = DespesaDto.converter(ListaDespesas);
		
		return despesasDto;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DespesaDto> consultar(@PathVariable Long id) {			
		Optional<Despesa> despesaOptional = this.despesaService.consultar(id);
		
		if (despesaOptional.isPresent()) {
			return ResponseEntity.ok(new DespesaDto(despesaOptional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@Valid @RequestBody DespesaForm despesaForm, UriComponentsBuilder uriBuilder){
		Despesa despesa = despesaForm.converter();
		
		try {
			this.despesaService.salvar(despesa);
			URI uri = uriBuilder.path("/despesas/{id}").buildAndExpand(despesa.getId()).toUri();
			return ResponseEntity.created(uri).body(new DespesaDto(despesa));
		
		} catch (DespesaInvalidaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody DespesaForm despesaForm){
		Optional<Despesa> despesaOptional = this.despesaService.consultar(id);
		if (despesaOptional.isPresent()) {
			
			Despesa despesa = despesaForm.converter();
			despesa.setId(despesaOptional.get().getId());
		
			try {
				this.despesaService.salvar(despesa);
				return ResponseEntity.ok(new DespesaDto(despesa));
			
			} catch (DespesaInvalidaException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Despesa> optional = this.despesaService.consultar(id);
		if (optional.isPresent()) {
			this.despesaService.deletar(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
