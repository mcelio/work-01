package org.amil.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.amil.entity.Match;
import org.amil.entity.Play;
import org.amil.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para implementação do padrão de projeto "estado"
 * 
 * Estado: Jogadas do jogo 
 * 
 * @author Marcos
 *
 */
@Component
public class PlayState implements State {
	
	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private Match match;
	
	@Autowired
    private PlayService playService;

	@Override
	public Object readLine(String line) throws ParseException {
		String dateStr = line.substring(0, line.indexOf("-"));		
		Date date = dateFormat.parse(dateStr);
		Play play = new Play();
		String killer = line.substring(21, line.indexOf("killed")).trim();
		String killed = null;
		String gun = null;
		if (line.indexOf("<WORLD>") != -1) {
			//23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN
			killer = "<WORLD>";			
			if(line.indexOf(" by ") != -1){
				killed = line.substring(line.indexOf("killed") + 6, line.indexOf("by")).trim();
				gun = line.substring(line.indexOf("by") + 2, line.length()).trim();
			} else {
				killed = line.substring(line.indexOf("killed") + 6, line.indexOf("using")).trim();
				gun = line.substring(line.indexOf("using"), line.length()).trim();
			}			
			
		} else if (line.indexOf("killed") != -1) {
			//23/04/2013 15:36:04 - Roman killed Nick using M16
			killer = line.substring(21, line.indexOf("killed")).trim();
			if(line.indexOf(" by ") != -1){
				killed = line.substring(line.indexOf("killed") + 6, line.indexOf("by")).trim();
				gun = line.substring(line.indexOf("by") + 2, line.length()).trim();
			} else {
				killed = line.substring(line.indexOf("killed") + 6, line.indexOf("using")).trim();
				gun = line.substring(line.indexOf("using") + 5, line.length()).trim();
			}						
		}
		play = new Play();
		play.setPlayDate(date);			
		play.setGun(gun);
		play.setKilled(killed);
		play.setKiller(killer);
		play.setMatch(match);
		playService.save(play);		
		return play;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
}