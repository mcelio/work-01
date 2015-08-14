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
 * Classe representando o serviço para recuperar a arma preferida do jogo.
 * 
 * @author Marcos
 *
 */
@Service
@Transactional
public class GunMostUsedService {

	@Autowired
	private PlayRepository playRepository;

	
	/**
	 * Recupera a arma preferida do jogo.
	 * 
	 * @return
	 */
	public String getGunMostUsed() {
		Map<String, Long> map = new HashMap<String, Long>(); 
		for(Play play : playRepository.findAll()){
			String gun = play.getGun();
			if(map.get(gun) == null){
				map.put(gun, 1L);
			} else {
				Long value = map.get(gun);
				map.put(gun, ++value);
			}
		}
		
		Long gunMostUsedCount = 0L;
		String gunMostUsed = null;
		for(Entry<String, Long> entry : map.entrySet()){
			Long value = entry.getValue();
			if (value > gunMostUsedCount){
				gunMostUsedCount = value;
				gunMostUsed = entry.getKey();
			}
		}
		
		return gunMostUsed;
	}
	
}
