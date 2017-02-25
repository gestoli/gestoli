package es.caib.gestoli.test;

import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

public class UsuariTest extends TestCase {
	
	
	private Selenium selenium;
	
	
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://gestor:gestor@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();
	}
	
	public void testUsuariAlta() throws InterruptedException {  
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Usuari");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("usuari", "GestorTest");
		selenium.type("contrasenya", "gestor");
		selenium.select("idiomaId", "label=Català");
		selenium.addSelection("id_grupsArray", "label=Consell Regulador - Gestor");
		selenium.click("moveRight");
		selenium.type("id_observacions", "Observacions gestor");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("L'usuari s'ha creat amb èxit"));
		
	}
	
	
	public void testUsuariAltaYaExiste() throws InterruptedException {  
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Usuari");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("usuari", "GestorTest");
		selenium.type("contrasenya", "gestor");
		selenium.select("idiomaId", "label=Català");
		selenium.addSelection("id_grupsArray", "label=Consell Regulador - Gestor");
		selenium.click("moveRight");
		selenium.type("id_observacions", "Observacions gestor");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("El nom d'usuari ja existeix"));
		
	}	
	
	
	public void testUsuariBaja() throws InterruptedException {  
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");	
		selenium.click("link=Usuari");
		selenium.waitForPageToLoad("30000");
		
		// Busqueda
		String text=null;
		while (true){
			
			try{
				text=selenium.getText("link=GestorTest");
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
		selenium.click("link=GestorTest");
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
				text=selenium.getText("link=GestorTest");
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
