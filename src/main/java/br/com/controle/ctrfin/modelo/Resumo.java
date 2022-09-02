package br.com.controle.ctrfin.modelo;

import java.math.BigDecimal;
import java.util.List;

public class Resumo {
	
	private BigDecimal valorTotalReceitas;
	private BigDecimal valorTotalDespesas;
	private BigDecimal valorFinalMes;
	
	private List<ValorTotalCategoria> ListaValorTotalCategoria;
	
	
	public Resumo(BigDecimal valorTotalReceitas, BigDecimal valorTotalDespesas, BigDecimal valorFinalMes,
			List<ValorTotalCategoria> listaValorTotalCategoria) {
		this.valorTotalReceitas = valorTotalReceitas;
		this.valorTotalDespesas = valorTotalDespesas;
		this.valorFinalMes = valorFinalMes;
		ListaValorTotalCategoria = listaValorTotalCategoria;
	}

	public BigDecimal getValorTotalReceitas() {
		return valorTotalReceitas;
	}

	public BigDecimal getValorTotalDespesas() {
		return valorTotalDespesas;
	}

	public BigDecimal getValorFinalMes() {
		return valorFinalMes;
	}

	public List<ValorTotalCategoria> getListaValorTotalCategoria() {
		return this.ListaValorTotalCategoria;
	}

}
