package org.amil.service;

import javax.transaction.Transactional;

import org.amil.entity.Play;
import org.amil.repository.PlayRepository;
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
public class PlayService {

	@Autowired
	private PlayRepository playRepository;

	@Transactional
	public void save(Play play) {
		playRepository.save(play);
	}
	
	@Transactional
	public void deleteAll() {
		playRepository.deleteAll();
	}
	
	public Iterable<Play> findAll() {
		return playRepository.findAll();
	}
}
