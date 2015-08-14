package org.amil.repository;

import org.amil.entity.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio para entidade Match
 * 
 * @author Marcos
 *
 */
public interface MatchRepository extends CrudRepository<Match, Long> {

	@Query("select p from Play p")
	String[] mostUsedGunByWinner();
 
}
