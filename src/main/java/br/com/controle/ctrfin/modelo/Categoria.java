package br.com.controle.ctrfin.modelo;

public enum Categoria {
	ALIMENTACAO(1,"Alimentação"), 
	SAUDE(2,"Saúde"), 
	MORADIA(3, "Moradia"), 
	TRANSPORTE(4, "Transporte"), 
	EDUCACAO(5, "Educacao"), 
	LAZER(6, "Lazer"), 
	IMPREVISTOS(7, "Imprevistos"), 
	OUTRAS(8, "Outras");
	
	private Integer codigo;
	private String descricao;
	
	Categoria(Integer codigo, String descricao) {
		this.codigo    = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
