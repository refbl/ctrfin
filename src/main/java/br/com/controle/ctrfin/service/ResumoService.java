package br.com.controle.ctrfin.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.ctrfin.modelo.Despesa;
import br.com.controle.ctrfin.modelo.Receita;
import br.com.controle.ctrfin.modelo.Resumo;
import br.com.controle.ctrfin.repository.DespesaRepository;
import br.com.controle.ctrfin.repository.ReceitaRepository;
import br.com.controle.ctrfin.resumo.GeradorDeResumo;
import br.com.controle.ctrfin.util.ConverteDataEmPeriodoMes;
import br.com.controle.ctrfin.util.PeriodoMes;

@Service
public class ResumoService {
	
	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	
	public Resumo listar(int ano, int mes) {

		LocalDate dataBase = LocalDate.of(ano, mes, 01);
		
		PeriodoMes periodoMes = new ConverteDataEmPeriodoMes().converter(dataBase);
		
		List<Receita> listaReceitas = this.receitaRepository.buscaPorPeriodo(periodoMes.getDtini(), periodoMes.getDtfim());
		
		List<Despesa> listaDespesas = this.despesaRepository.buscaPorPeriodo(periodoMes.getDtini(), periodoMes.getDtfim());
		
		GeradorDeResumo geradorDeResumo = new GeradorDeResumo(listaReceitas, listaDespesas);
		
		return geradorDeResumo.gerar();
		
	}
	

}
