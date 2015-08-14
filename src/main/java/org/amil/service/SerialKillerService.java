package org.amil.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.amil.entity.Play;
import org.amil.repository.PlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe representando o serviço para recuperar o jogadore da maior sequencia de assassinatos.
 * 
 * @author Marcos
 *
 */
@Service
@Transactional
public class SerialKillerService {

	@Autowired
	private PlayRepository playRepository;

	/**
	 * Recupera o jogador da maior sequencoa de assassinatos.
	 * 
	 * @return jogador
	 */
	public String getSerialKiller() {
		Map<String, Long> map = new HashMap<String, Long>();
		String serialKiller = null;
		for(Play play : playRepository.findAll()){
			String killer = play.getKiller();
			if(map.get(killer) == null){
				map.put(killer, 1L);
				serialKiller = killer;
			} else {
				if(killer.equals(serialKiller)){
					Long value = map.get(killer);
					map.put(killer, ++value);	
				} else {
					serialKiller = killer;
				}				
			}
		}
		
		Long killerCount = 0L;
		String killer = null;
		for(Entry<String, Long> entry : map.entrySet()){
			Long value = entry.getValue();
			if (value > killerCount && !entry.getKey().equals("<WORLD>")){
				killerCount = value;
				killer = entry.getKey();
			}
		}		
		return killer;
	}
	
}
