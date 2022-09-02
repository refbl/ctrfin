package br.com.controle.ctrfin.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.controle.ctrfin.modelo.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
	
	// Query JPQL - Usar nome da classe e atributos da classe
	@Query("SELECT d FROM Despesa d WHERE d.descricao = :descricao AND d.data >= :dtini AND d.data <= :dtfim")
	List<Despesa> buscaDespesaPorDescricaoEPeriodoDeData(String descricao, LocalDate dtini, LocalDate dtfim);
	
	@Query("SELECT d FROM Despesa d WHERE d.descricao = :descricao AND d.data >= :dtini AND d.data <= :dtfim AND id != :id")
	List<Despesa> buscaDespesaPorDescricaoEPeriodoDeDataEId(Long id, String descricao, LocalDate dtini,
			LocalDate dtfim);
	
	List<Despesa> findByDescricao(String descricao);

	// Query JPQL - Usar nome da classe e atributos da classe
	@Query("SELECT d FROM Despesa d WHERE d.data >= :dtini AND d.data <= :dtfim")
	List<Despesa> buscaPorPeriodo(LocalDate dtini, LocalDate dtfim);

}
