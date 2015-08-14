package org.amil.controllers;

import java.util.Set;

import org.amil.service.AwardsService;
import org.amil.service.GunMostUsedService;
import org.amil.service.ReadLogFileService;
import org.amil.service.SerialKillerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe controle responsável por mapear as url e disponibilizar no formato JSON
 * os acessos aos recursos
 * 
 * @author Marcos
 *
 */
@RestController
public class ReadLogFileController {

	/**
	 * Serviço ReadLogFileService
	 */
	@Autowired
	private ReadLogFileService readLogFileService;
	
	/**
	 * Serviço GunMostUsedService
	 */
	@Autowired
	private GunMostUsedService gunMostUsedService;
	
	/**
	 * Serviço AwardsService
	 */
	@Autowired
	private AwardsService awardsService;
	
	/**
	 * Serviço SerialKillerService
	 */
	@Autowired
	private SerialKillerService serialKillerService;

	/**
	 * Mapeamento do serviço REST para ler e gravar no banco de dados o arquivo de log. 
	 * 
	 * @return status - formato JSON
	 * 
	 * {"status":"ok"}
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/lerarquivolog", produces = "application/json")
	public @ResponseBody String lerArquivoLog() {
		try {
			readLogFileService.parseFile();
			JSONObject json = new JSONObject();
			json.put("status", "ok");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Mapeamento do serviço REST para obter a arma mais usada pelo vencedor. 
	 * 
	 * @return String - formato JSON
	 * 
	 * {"bonus":"Arma preferida (a que mais matou) do vencedor","arma":"M16"}
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/armapreferida", produces = "application/json")
	public @ResponseBody String armapreferida() {
		try {
			JSONObject json = new JSONObject();
			String arma = gunMostUsedService.getGunMostUsed();
			json.put("arma", arma);
			json.put("bonus", "Arma preferida (a que mais matou) do vencedor");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Mapeamento do serviço REST para obter o vencedor com a maior sequencia de assassinatos.
	 * 
	 * @return jogadores - formato JSON
	 * 
	 * {"bonus":"Maior sequência de assassinatos efetuadas por um jogador (streak) sem morrer, dentro da partida","jogador":"Roman"}
	 * 
	 */	
	@RequestMapping(method = RequestMethod.GET, value = "/maiorsequencia", produces = "application/json")
	public @ResponseBody String maiorSequencia() {
		try {
			JSONObject json = new JSONObject();
			String jogador = serialKillerService.getSerialKiller();
			json.put("jogador", jogador);
			json.put("bonus", "Maior sequência de assassinatos efetuadas por um jogador (streak) sem morrer, dentro da partida");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Mapeamento do serviço REST para obter o vencedor que não tenha morrido no jogo.
	 * 
	 * @return jogadores - formato JSON
	 * 
	 * {"jogadores":["Roman"],"bonus":"Jogadores que venceram uma partida sem morrerem"}
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/vencedorsemmorrer", produces = "application/json")
	public @ResponseBody String awardAlive() {
		try {
			JSONObject json = new JSONObject();
			Set<String> awards = awardsService.awardAlive();
			json.put("jogadores", awards);
			json.put("bonus", "Jogadores que venceram uma partida sem morrerem");			
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Mapeamento do serviço REST para obter o vencedor que conseguiu matar mais de 5 vezes em 1 minuto.
	 * 
	 * @return jogadores - formato JSON
	 * 
	 * {"jogadores":["Roman"],"bonus":"Jogadores que mataram 5 vezes em 1 minuto"}
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/matadoremumminuto", produces = "application/json")
	public @ResponseBody String awardOneMinute() {
		try {
			JSONObject json = new JSONObject();
			Set<String> awards = awardsService.awardOneMinute();
			json.put("jogadores", awards);
			json.put("bonus", "Jogadores que mataram 5 vezes em 1 minuto");			
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
