package br.com.controle.ctrfin.controller.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.controle.ctrfin.modelo.Resumo;
import br.com.controle.ctrfin.modelo.ValorTotalCategoria;

public class ResumoDto {
	
	private BigDecimal valorTotalReceitas;
	private BigDecimal valorTotalDespesas;
	private BigDecimal valorFinalMes;


	private List<ValorTotalCategoria> ListaValorTotalCategoria;
	
	public void adicionaCategoria(ValorTotalCategoria valorTotalCategoria) {
		this.ListaValorTotalCategoria.add(valorTotalCategoria);
	}

	public BigDecimal getValorTotalReceitas() {
		return valorTotalReceitas;
	}

	public void setValorTotalReceitas(BigDecimal valorTotalReceitas) {
		this.valorTotalReceitas = valorTotalReceitas;
	}

	public BigDecimal getValorTotalDespesas() {
		return valorTotalDespesas;
	}

	public void setValorTotalDespesas(BigDecimal valorTotalDespesas) {
		this.valorTotalDespesas = valorTotalDespesas;
	}

	public BigDecimal getValorFinalMes() {
		return valorFinalMes;
	}

	public void setValorFinalMes(BigDecimal valorFinalMes) {
		this.valorFinalMes = valorFinalMes;
	}

	public List<ValorTotalCategoria> getListaValorTotalCategoria() {
		return ListaValorTotalCategoria;
	}
	
	
	public void setListaValorTotalCategoria(List<ValorTotalCategoria> listaValorTotalCategoria) {
		ListaValorTotalCategoria = listaValorTotalCategoria;
	}

	public ResumoDto converter(Resumo resumo) {
		
		ResumoDto resumoDto = new ResumoDto();
		resumoDto.setValorTotalReceitas(resumo.getValorTotalReceitas());
		resumoDto.setValorTotalDespesas(resumo.getValorTotalReceitas());
		resumoDto.setValorFinalMes(resumo.getValorFinalMes());
		resumoDto.setListaValorTotalCategoria(resumo.getListaValorTotalCategoria());
		
		return resumoDto;
	}
	
}
