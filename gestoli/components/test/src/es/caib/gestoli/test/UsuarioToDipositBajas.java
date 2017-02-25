package es.caib.gestoli.test;

import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

public class UsuarioToDipositBajas extends TestCase {
	
	
	private Selenium selenium;
	
	
	public void setUp() throws Exception {

	}
	
	
	public void testDarDeBajaDepositos() throws InterruptedException { 
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://testUsuEnv:testUsuEnv@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Dipòsit");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=DPB1");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		selenium.click("link=DPP1");
		selenium.waitForPageToLoad("30000");
		selenium.click("eliminarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		selenium.click("link=DPP2");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		selenium.click("link=DSP1");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		selenium.click("link=DSP2");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		selenium.click("link=DSP3");
		selenium.waitForPageToLoad("30000");
		selenium.click("eliminarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		
		assertTrue(selenium.isTextPresent("No s'ha trobat res per mostrar"));
		
	}
	
	
	
	public void testDarDeBajaZonas() throws InterruptedException { 
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://testUsuEnv:testUsuEnv@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();		
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Zona");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=1º Piso");
		selenium.waitForPageToLoad("30000");
		selenium.click("eliminarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		selenium.click("link=2º Piso");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Planta baja");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		
		assertTrue(selenium.isTextPresent("No s'ha trobat res per mostrar"));
		
	}	
	
	
	public void testDarDeBajaEstabliment() throws InterruptedException {
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://gestor:gestor@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();		
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");	
		selenium.click("link=Establiment");
		selenium.waitForPageToLoad("30000");
		
		// Busqueda
		String text=null;
		while (true){
			
			try{
				text=selenium.getText("link=Envasador Establecimiento");
				break;
			}catch(SeleniumException e){
				
				try{
					if (selenium.getAttribute("xpath=//div[@id='divSombra_0']/div[8]/span/a[last()]/@href").equals("#")){
						break;
					}
				}catch(SeleniumException e2){
					break;
				}
				selenium.click("//div[@id='divSombra_0']/div[8]/span/a[last()]");
				selenium.waitForPageToLoad("30000");
			}
			
		}
		
		assertTrue(text!=null);
		
		// Borrado
		selenium.click("link=Envasador Establecimiento");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");	
		
		selenium.click("link=Configuració");	
		selenium.click("link=Establiment");
		selenium.waitForPageToLoad("30000");
		
		// Busqueda
		text=null;
		while (true){
			
			try{
				text=selenium.getText("link=Envasador Establecimiento");
				break;
			}catch(SeleniumException e){
				try{
					if (selenium.getAttribute("xpath=//div[@id='divSombra_0']/div[8]/span/a[last()]/@href").equals("#")){
						break;
					}
				}catch(SeleniumException e2){
					break;
				}
				selenium.click("//div[@id='divSombra_0']/div[8]/span/a[last()]");
				selenium.waitForPageToLoad("30000");
			}
			
		}

		assertTrue(text==null);

	}
	
	
	
	public void testDarDeBajaUsuarioEnvasador() throws InterruptedException { 
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://gestor:gestor@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();	
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");	
		selenium.click("link=Usuari");
		selenium.waitForPageToLoad("30000");
		
		// Busqueda
		String text=null;
		while (true){
			
			try{
				text=selenium.getText("link=testUsuEnv");
				break;
			}catch(SeleniumException e){
				
				try{
					if (selenium.getAttribute("xpath=//div[@id='divSombra_0']/div[8]/span/a[last()]/@href").equals("#")){
						break;
					}
				}catch(SeleniumException e2){
					break;
				}
				selenium.click("//div[@id='divSombra_0']/div[8]/span/a[last()]");
				selenium.waitForPageToLoad("30000");
			}
			
		}
		
		assertTrue(text!=null);
		
		// Borrado
		selenium.click("link=testUsuEnv");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");	
		
		selenium.click("link=Configuració");	
		selenium.click("link=Usuari");
		selenium.waitForPageToLoad("30000");
		
		// Busqueda
		text=null;
		while (true){
			
			try{
				text=selenium.getText("link=testUsuEnv");
				break;
			}catch(SeleniumException e){
				try{
					if (selenium.getAttribute("xpath=//div[@id='divSombra_0']/div[8]/span/a[last()]/@href").equals("#")){
						break;
					}
				}catch(SeleniumException e2){
					break;
				}
				selenium.click("//div[@id='divSombra_0']/div[8]/span/a[last()]");
				selenium.waitForPageToLoad("30000");
			}
			
		}

		assertTrue(text==null);

	}	
	
	
	
	
	
	
	public void tearDown() {  
		selenium.stop();  
	} 	
	
	
	
}