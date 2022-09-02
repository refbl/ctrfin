package br.com.controle.ctrfin.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.ctrfin.exception.ReceitaInvalidaException;
import br.com.controle.ctrfin.modelo.Receita;
import br.com.controle.ctrfin.repository.ReceitaRepository;
import br.com.controle.ctrfin.util.ConverteDataEmPeriodoMes;
import br.com.controle.ctrfin.util.PeriodoMes;

@Service
public class ReceitaService {
	
	@Autowired
	private ReceitaRepository repository;
	
	public List<Receita> listar(String descricao){
		if (descricao == null) {
			return this.repository.findAll();
		} else {
			return this.repository.findByDescricao(descricao);
		}
	}
	
	public void salvar(Receita receita) throws ReceitaInvalidaException {

		PeriodoMes periodoMes = new ConverteDataEmPeriodoMes().converter(receita.getData());
		
		List<Receita> listaReceita;
		if (receita.getId() != null) {
			listaReceita = this.repository.buscaReceitaPorDescricaoEPeriodoDeDataEId(receita.getId(), receita.getDescricao(), periodoMes.getDtini(), periodoMes.getDtfim());
		} else {
			listaReceita = this.repository.buscaReceitaPorDescricaoEPeriodoDeData(receita.getDescricao(), periodoMes.getDtini(), periodoMes.getDtfim());
		}
		
		if (listaReceita == null || listaReceita.isEmpty()) {
			this.repository.save(receita);
		} else {
			throw new ReceitaInvalidaException("Já existe Receita com este nome para o mesmo mês/ano");
		}
	}

	public Optional<Receita> consultar(Long id) {
		return this.repository.findById(id);
	}

	public void deletar(Long id) {
		this.repository.deleteById(id);
	}

	public List<Receita> listarPorAnoMes(Integer ano, Integer mes) {
		LocalDate dataBase = LocalDate.of(ano, mes, 01);
		
		PeriodoMes periodoMes = new ConverteDataEmPeriodoMes().converter(dataBase);
		
		return this.repository.buscaPorPeriodo(periodoMes.getDtini(), periodoMes.getDtfim());
	}


}
