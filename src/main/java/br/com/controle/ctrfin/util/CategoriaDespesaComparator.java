package br.com.controle.ctrfin.util;

import java.util.Comparator;

import br.com.controle.ctrfin.modelo.Despesa;

public class CategoriaDespesaComparator implements Comparator<Despesa>{

    @Override
	public int compare(Despesa despesa1, Despesa despesa2) {
		return despesa1.getCategoria().getCodigo() - despesa2.getCategoria().getCodigo();

    }
}


