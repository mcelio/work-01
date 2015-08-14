package org.amil;

import java.util.HashSet;
import java.util.Set;

import org.amil.service.AwardsService;
import org.amil.service.GunMostUsedService;
import org.amil.service.MatchService;
import org.amil.service.PlayService;
import org.amil.service.ReadLogFileService;
import org.amil.service.SerialKillerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AmilTestApplication.class)
public class AmilTestApplicationTests {

	@Autowired
	private ReadLogFileService readLogFile; 
	
	@Autowired
	private ReadLogFileService readLogFileService;
	
	@Autowired
	private GunMostUsedService gunMostUsedService;
	
	@Autowired
	private AwardsService awardsService;
	
	@Autowired
	private SerialKillerService serialKillerService;

	@Autowired	
	private MatchService matchService;
	
	@Autowired	
	private PlayService playService;
	
	@Before
	public void readLogFile(){
		readLogFile.parseFile();
	}
	
	@After
	public void removeLogFile(){
		playService.deleteAll();
		matchService.deleteAll();
	}
	
	@Test
	public void readLogFileTest() {		
		readLogFile.parseFile();
		Assert.assertTrue(matchService.findAll().iterator().hasNext());		
		Assert.assertTrue(playService.findAll().iterator().hasNext());
		
		playService.deleteAll();
		Assert.assertFalse(playService.findAll().iterator().hasNext());
		matchService.deleteAll();
		Assert.assertFalse(matchService.findAll().iterator().hasNext());		
	}
	
	@Test
	public void armaPreferirTest() {
		final String H4 = "H4";		
		String arma = gunMostUsedService.getGunMostUsed();		
		Assert.assertEquals(arma, H4);
	}
	
	@Test
	public void maiorSequenciaTest() {
		final String KILLER = "Joe";		
		String killer = serialKillerService.getSerialKiller();		
		Assert.assertEquals(killer, KILLER);
	}
	
	@Test
	public void maiorVencedorTest() {
		final Set<String> resultado = new HashSet<String>();
		resultado.add("Joe");
		resultado.add("Zeca");
		resultado.add("Jeff");
		Set<String> list = awardsService.awardAlive();
				
		for(String valor : list){			
			Assert.assertTrue(resultado.contains(valor));			
		}		
	}
	
	@Test
	public void umMinutoTest() {
		final Set<String> resultado = new HashSet<String>();
		resultado.add("Zeca");
		Set<String> list = awardsService.awardOneMinute();				
		for(String valor : list){			
			Assert.assertTrue(resultado.contains(valor));			
		}		
	}
}
