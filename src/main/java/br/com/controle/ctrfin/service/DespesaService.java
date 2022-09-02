package br.com.controle.ctrfin.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.ctrfin.exception.DespesaInvalidaException;
import br.com.controle.ctrfin.modelo.Despesa;
import br.com.controle.ctrfin.repository.DespesaRepository;
import br.com.controle.ctrfin.util.ConverteDataEmPeriodoMes;
import br.com.controle.ctrfin.util.PeriodoMes;

@Service
public class DespesaService {
	
	@Autowired
	private DespesaRepository repository;
	
	public List<Despesa> listar(String descricao){
		if (descricao == null) {
			return this.repository.findAll();
		} else {
			return this.repository.findByDescricao(descricao);
		}
	}

	public Optional<Despesa> consultar(Long id) {
		return this.repository.findById(id);
	}

	public void salvar(Despesa despesa) throws DespesaInvalidaException {
		
		PeriodoMes periodoMes = new ConverteDataEmPeriodoMes().converter(despesa.getData());
		List<Despesa> listaDespesa;
		
		System.out.println("ID NO SAVE : " + despesa.getId());
		
		// ID diferente de NUlo é Alteração
		if (despesa.getId() != null) {
			listaDespesa = this.repository.buscaDespesaPorDescricaoEPeriodoDeDataEId(despesa.getId(), despesa.getDescricao(), periodoMes.getDtini(), periodoMes.getDtfim());
		} else {
			listaDespesa = this.repository.buscaDespesaPorDescricaoEPeriodoDeData(despesa.getDescricao(), periodoMes.getDtini(), periodoMes.getDtfim());
		}
		
		if (listaDespesa == null || listaDespesa.isEmpty()) {
			this.repository.save(despesa);
		} else {
			throw new DespesaInvalidaException("Já existe Despesa com este nome para o mesmo mês/ano");
		}
		
	}

	public void deletar(Long id) {
		this.repository.deleteById(id);
	}

	public List<Despesa> listarPorAnoMes(Integer ano, Integer mes) {
		LocalDate dataBase = LocalDate.of(ano, mes, 01);
		
		PeriodoMes periodoMes = new ConverteDataEmPeriodoMes().converter(dataBase);
		
		return this.repository.buscaPorPeriodo(periodoMes.getDtini(), periodoMes.getDtfim());
	}
}
