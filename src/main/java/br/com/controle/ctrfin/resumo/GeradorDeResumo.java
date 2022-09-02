package br.com.controle.ctrfin.resumo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.controle.ctrfin.modelo.Categoria;
import br.com.controle.ctrfin.modelo.Despesa;
import br.com.controle.ctrfin.modelo.Receita;
import br.com.controle.ctrfin.modelo.Resumo;
import br.com.controle.ctrfin.modelo.ValorTotalCategoria;
import br.com.controle.ctrfin.util.CategoriaDespesaComparator;

public class GeradorDeResumo {
	
	private List<Receita> listaReceitas;
	private List<Despesa> listaDespesas;
	
	public GeradorDeResumo(List<Receita> listaReceitas, List<Despesa> listaDespesas) {
		this.listaReceitas = listaReceitas;
		this.listaDespesas = listaDespesas;
	}

	public Resumo gerar() {
		BigDecimal valorTotalReceita = this.listaReceitas
				.stream()
				.map(Receita::getValor)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		BigDecimal valorTotalDespesa = this.listaDespesas
				.stream()
				.map(Despesa::getValor)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		

		BigDecimal valorSaldoFinalMes = valorTotalReceita.subtract(valorTotalDespesa);
		
		List<ValorTotalCategoria> listaValorPorCategoria = gerarDespesaPorCategoria();
		
		return new Resumo(valorTotalReceita, valorTotalDespesa ,valorSaldoFinalMes ,listaValorPorCategoria);
	}

	private List<ValorTotalCategoria> gerarDespesaPorCategoria() {
		
		if (this.listaDespesas == null || this.listaDespesas.isEmpty()) {
			return null;
		}
		
		//Ordenar a Lista por Categoria
		this.listaDespesas.sort(new CategoriaDespesaComparator());
				
		List<ValorTotalCategoria> listaValorTotalCategoria = new ArrayList<>();
		
		Categoria  ultimaCategoria = null;
		BigDecimal valor = BigDecimal.ZERO;
		
		for (Despesa despesa : this.listaDespesas) {
			
			if (ultimaCategoria == null) {
				ultimaCategoria = despesa.getCategoria();
				valor = despesa.getValor();
				
			} else if (ultimaCategoria.getCodigo().intValue() == despesa.getCategoria().getCodigo().intValue()) {
				valor = valor.add(despesa.getValor());
				
			} else {
				listaValorTotalCategoria.add(new ValorTotalCategoria(ultimaCategoria, valor));
				
				ultimaCategoria = despesa.getCategoria();
				valor = despesa.getValor();
			}
		}
		//Registra o primeiro se só tiver um ou ultimo
		listaValorTotalCategoria.add(new ValorTotalCategoria(ultimaCategoria, valor));
		
		return listaValorTotalCategoria;
	}
}
