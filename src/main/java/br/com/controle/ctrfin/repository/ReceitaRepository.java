package br.com.controle.ctrfin.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.controle.ctrfin.modelo.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
	
	// Query JPQL - Usar nome da classe e atributos da classe
	@Query("SELECT r FROM Receita r WHERE r.descricao = :descricao AND r.data >= :dtini AND r.data <= :dtfim")
	List<Receita> buscaReceitaPorDescricaoEPeriodoDeData(String descricao, LocalDate dtini, LocalDate dtfim);

	// Query JPQL - Usar nome da classe e atributos da classe
	@Query("SELECT r FROM Receita r WHERE r.descricao = :descricao AND r.data >= :dtini AND r.data <= :dtfim AND id != :id")
	List<Receita> buscaReceitaPorDescricaoEPeriodoDeDataEId(Long id, String descricao, LocalDate dtini, LocalDate dtfim);

	
	List<Receita> findByDescricao(String descricao);

	// Query JPQL - Usar nome da classe e atributos da classe
	@Query("SELECT r FROM Receita r WHERE r.data >= :dtini AND r.data <= :dtfim")
	List<Receita> buscaPorPeriodo(LocalDate dtini, LocalDate dtfim);

}
