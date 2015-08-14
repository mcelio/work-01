package org.amil.service;

import javax.transaction.Transactional;

import org.amil.entity.Match;
import org.amil.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe representando o serviço para operações do banco de dados da entidade "Match".
 * 
 * @author Marcos
 *
 */
@Service
@Transactional
public class MatchService {

	@Autowired
	MatchRepository matchRepository;

	@Transactional
	public void save(Match match) {
		matchRepository.save(match);
	}
	
	@Transactional
	public void deleteAll() {
		matchRepository.deleteAll();
	}
	
	public Iterable<Match> findAll() {
		return matchRepository.findAll();
	}
	
	public Match get(Long id) {
		return matchRepository.findOne(id);
	}
}
