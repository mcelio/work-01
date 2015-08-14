package org.amil.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.transaction.Transactional;

import org.amil.entity.Play;
import org.amil.repository.PlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe representando o serviço para "awards".
 * 
 * @author Marcos
 *
 */
@Service
@Transactional
public class AwardsService {

	@Autowired
	private PlayRepository playRepository;

	/**
	 * Lista os jogadores da regra
	 * 
	 * Jogadores que vencerem uma partida sem morrerem devem ganhar um "award";
	 * 
	 * @return lista de jogadores
	 */
	public Set<String> awardAlive() {
		Map<Long, List<String>> matchWinners = new HashMap<Long, List<String>>();
		Map<Long, List<String>> matchLoosers = new HashMap<Long, List<String>>();
		for(Play play : playRepository.findAll()){					
			Long matchId = play.getMatch().getId();
			List<String> winners = matchWinners.get(matchId);
			if(winners == null){
				winners = new ArrayList<String>();
				winners.add(play.getKiller());
				matchWinners.put(matchId, winners);
			} else {
				winners.add(play.getKiller());
				matchWinners.put(matchId, winners);
			}			
			
			List<String> loosers = matchLoosers.get(matchId);
			if(loosers == null){
				loosers = new ArrayList<String>();
				loosers.add(play.getKilled());
				matchLoosers.put(matchId, loosers);
			} else {
				loosers.add(play.getKilled());
				matchLoosers.put(matchId, loosers);
			}
		}
		
		Map<String, Boolean> awards = new HashMap<String, Boolean>();
		for(Entry<Long, List<String>> entryWinners : matchWinners.entrySet()){			
			for(String winner : entryWinners.getValue()){
				awards.put(winner, true);
				for(String looser : matchLoosers.get(entryWinners.getKey())){
					if(winner.equals(looser)){
						awards.remove(winner);
					} 					
				}				
			}			
		}
		awards.remove("<WORLD>");
		return awards.keySet();
	}
	
	/**
	 * Lista os jogadores da regra
	 * 
	 * Jogadores que matarem 5 vezes em 1 minuto devem ganhar um "award"
	 * 
	 * @return lista de jogadores
	 */
	public Set<String> awardOneMinute() {
		Map<String, List<Date>> killerMap = new HashMap<String, List<Date>>();
		for(Play play : playRepository.findAll()){					
			String killer = play.getKiller();			
			List<Date> playDate = killerMap.get(killer);
			if(playDate == null){
				playDate = new ArrayList<Date>();
				
			}
			playDate.add(play.getPlayDate());
			killerMap.put(killer, playDate);			
		}	
			
		final int KILL_NUMBER_SEQUENCE = 5; ;// 5 ASSASSINATOS SEGUIDOS
		final int KILL_PERIOD = 60000; // 1 MINUTO
		
		// remove <WORLD>
		killerMap.remove("<WORLD>");
		
		Set<String> awards = new HashSet<String>();
		for(Entry<String, List<Date>> entry : killerMap.entrySet()){
			List<Date> playDateList = entry.getValue();
			
			Collections.sort(playDateList, new Comparator<Date>() {
				  public int compare(Date o1, Date o2) {
				      return o1.compareTo(o2);
				  }
				});
			
			if(playDateList.size() > 4){
				long firstDate = playDateList.get(0).getTime();
				long lastDate = playDateList.get(4).getTime();
				
				if(lastDate - firstDate <= 60000){
					awards.add(entry.getKey());
				}
			}			
		}		
		return awards;
	}
	
}
