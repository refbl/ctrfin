package br.com.controle.ctrfin.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.controle.ctrfin.modelo.Categoria;
import br.com.controle.ctrfin.modelo.Despesa;

public class DespesaDto {
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	private Categoria categoria;
	
	public DespesaDto(Despesa despesa) {
		this.id = despesa.getId();
		this.descricao = despesa.getDescricao();
		this.valor = despesa.getValor();
		this.data = despesa.getData();
		this.categoria = despesa.getCategoria();
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
	
	public Categoria getCategoria() {
		return categoria;
	}

	public static List<DespesaDto> converter(List<Despesa> listaDespesas) {
		List<DespesaDto> listaDespesaDto = listaDespesas.stream()
				.map(dep -> new DespesaDto(dep)).collect(Collectors.toList());
		
		return listaDespesaDto;
	}
}
