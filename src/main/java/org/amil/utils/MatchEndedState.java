package org.amil.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.amil.entity.Match;
import org.amil.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para implementação do padrão de projeto "estado"
 * 
 * Estado: Final do jogo 
 * 
 * @author Marcos
 *
 */
@Component
public class MatchEndedState implements State{
	
	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	@Autowired
    private MatchService matchService;

	@Override
	public Object readLine(String line) throws ParseException {
		//23/04/2013 15:39:22 - Match 11348965 has ended 
		Match match = null;
		String dateStr = line.substring(0, line.indexOf("-"));		
		Date date = dateFormat.parse(dateStr);
		if (line.indexOf("ended") != -1) {
			Long id = Long.valueOf(line.substring(27, line.indexOf("has ended")).trim());
			match = matchService.get(id);
			match.setEndDate(date);
			matchService.save(match);
		}
		return match;
	}
}
