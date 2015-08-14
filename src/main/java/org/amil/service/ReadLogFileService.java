package org.amil.service;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.util.Scanner;

import org.amil.entity.Match;
import org.amil.utils.MatchEndedState;
import org.amil.utils.MatchStartedState;
import org.amil.utils.PlayState;
import org.amil.utils.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe representando o serviço para operações de leitura de arquivo de log e banco de dados banco de dados.
 * 
 * @author Marcos
 *
 */
@Service
public class ReadLogFileService implements State {

	private State state;

	@Autowired
	private MatchStartedState matchStartedState;

	@Autowired
	private MatchEndedState matchEndedState;
	
	@Autowired	
	private MatchService matchService;
	
	@Autowired	
	private PlayService playService;

	@Autowired
	private PlayState playState;

	private File readFile() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		//return classLoader.getResource("input/log.log").getFile();
		//return classLoader.getResourceAsStream("input/log.log");
		return new File("input" + File.separator + "log.log");
	}

	public void parseFile() {
		try {
			// remove todos regostros antes de ler o arquivo
			playService.deleteAll();
			matchService.deleteAll();

			Scanner scanner = new Scanner(readFile());
			Match match = null;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line != null && !line.trim().equals("")) {					
					if (line.indexOf("New match") != -1) {
						setState(matchStartedState);
					} else if (line.indexOf("ended") != -1) {
						setState(matchEndedState);
					} else if (line.indexOf("killed") != -1) {
						playState.setMatch(match);
						setState(playState);
					}
					Object o = readLine(line);
					if (o instanceof Match) {
						match = (Match) o;
					}
				}
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object readLine(String line) throws ParseException {
		return this.state.readLine(line);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
