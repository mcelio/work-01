package org.amil;

import org.amil.service.MatchService;
import org.amil.service.ReadLogFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * 
 * Aplicação com objetivo ler recursos de uma arquivo de log e disponibiizá-los através de serviços REST
 * O recursos são baseado nos logs de um jogo com as entidades MATCH e PLAY
 * 
 * @author Marcos
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.amil")
public class AmilTestApplication /**implements CommandLineRunner */{

	/**
	 * Serviço para ler arquivo de log e gravar no banco de dados. 
	 */			
	@Autowired
	private ReadLogFileService readLogFileService;

	/**
	 * Serviço operaçoes da entidade 'Match' no banco de dados. 
	 */
	@Autowired
	private MatchService matchService;
	
	/**
	 * Método principal de inicio de execução do programa 
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AmilTestApplication.class, args);		
	}
}
