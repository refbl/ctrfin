package br.com.controle.ctrfin.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.controle.ctrfin.modelo.Receita;

public class ReceitaDto {
	
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	
	public ReceitaDto(Receita receita) {
		this.id = receita.getId();
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
	}
	
	public Long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public LocalDate getData() {
		return data;
	}

	public static List<ReceitaDto> converter(List<Receita> listaReceitas) {
		List<ReceitaDto> listaReceitaDto = listaReceitas.stream().map(rec -> new ReceitaDto(rec)).collect(Collectors.toList());
		
		return listaReceitaDto;
	}
	
	
	

}
