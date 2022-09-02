package br.com.controle.ctrfin.util;

import java.time.LocalDate;

public class ConverteDataEmPeriodoMes {
	
	public PeriodoMes converter(LocalDate data) {
		//Seta o primeiro dia do mes
		LocalDate dtini = LocalDate.of(data.getYear(), data.getMonth(), 1);
		
		//Calcula o primeiro dia do mes seguinte e subtrai um dia.
		PeriodoMes periodoMes = new PeriodoMes(dtini ,dtini.plusMonths(1).minusDays(1));

		return periodoMes;
	}

}
