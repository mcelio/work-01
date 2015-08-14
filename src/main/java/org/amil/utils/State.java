package org.amil.utils;

import java.text.ParseException;

/**
 * Interface para implementação do padrão de projeto "estado"
 * 
 * @author Marcos
 *
 */
public interface State {
	
	/**
	 * Métod lê uma linha e identifica em qual fase o jogo se encontra delegando 
	 * para sua devida implementação.
	 * 
	 * @param line - linha String
	 * @return - objecto persistido na base de dados
	 * @throws ParseException exceção a ser lançada.
	 */
	Object readLine(String line) throws ParseException;
	
}
