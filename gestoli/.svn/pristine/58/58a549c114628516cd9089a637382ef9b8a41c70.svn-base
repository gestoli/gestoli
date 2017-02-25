package es.caib.gestoli.test;

import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

public class OlivicultorTest extends TestCase {
	
	
	private Selenium selenium;
	
	
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://gestor:gestor@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();
	}
	
	public void testOlivicultorAlta() throws InterruptedException {  
		
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Usuari");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("usuari", "OlivicultorTest");
		selenium.type("contrasenya", "olivicultor");
		selenium.select("idiomaId", "label=Català");
		selenium.addSelection("id_grupsArray", "label=Olivicultor");
		selenium.click("moveRight");
		selenium.type("id_observacions", "Observacions olivicultor");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("L'usuari s'ha creat amb èxit"));

		selenium.click("link=Configuració");
		selenium.click("link=Olivicultor");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.select("usuariId", "label=OlivicultorTest");
		selenium.type("nom", "OlivicultorNuevoTest");
		selenium.type("nif", "12345678");
		selenium.type("codiDO", "CodDOOliNuevoTest");
		selenium.type("codiDOExperimental", "CodDOExpOliNuevoTest");
		selenium.type("direccio", "C/ Olivicultor");
		selenium.type("poblacio", "Olivicultorlandia");
		selenium.type("codiPostal", "05673");
		selenium.type("compteBancari", "01234567890123456789");
		selenium.type("email", "oli@oli.es");
		selenium.type("telefon", "971223344");
		selenium.type("fax", "971223345");
		selenium.type("id_observacions", "Observacions olivicultorTest");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("L'olivicultor s'ha creat amb èxit"));
		
	}
	
	
	public void testOlivicultorAltaYaExiste() throws InterruptedException {  
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Olivicultor");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.select("usuariId", "label=OlivicultorTest");
		selenium.type("nom", "OlivicultorNuevoTest");
		selenium.type("nif", "12345678");
		selenium.type("codiDO", "CodDOOliNuevoTest");
		selenium.type("codiDOExperimental", "CodDOExpOliNuevoTest");
		selenium.type("direccio", "C/ Olivicultor");
		selenium.type("poblacio", "Olivicultorlandia");
		selenium.type("codiPostal", "05673");
		selenium.type("compteBancari", "01234567890123456789");
		selenium.type("email", "oli@oli.es");
		selenium.type("telefon", "971223344");
		selenium.type("fax", "971223345");
		selenium.type("id_observacions", "Observacions olivicultorTest");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("L'usuari ja ha estat assignat a un altre olivicultor"));
		assertTrue(selenium.isTextPresent("El camp codi RA ja existeix"));
		assertTrue(selenium.isTextPresent("El camp codi RE ja existeix"));
		assertTrue(selenium.isTextPresent("El NIF de l'olivicultor ja existeix"));		
		
	}	
	
	
	public void testOlivicultorBaja() throws InterruptedException {  
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");	
		selenium.click("link=Olivicultor");
		selenium.waitForPageToLoad("30000");
		
		// Busqueda
		String text=null;
		while (true){
			
			try{
				text=selenium.getText("link=CodDOOliNuevoTest");
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
		selenium.click("link=CodDOOliNuevoTest");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");	
		
		selenium.click("link=Configuració");	
		selenium.click("link=Olivicultor");
		selenium.waitForPageToLoad("30000");
		
		// Busqueda
		text=null;
		while (true){
			
			try{
				text=selenium.getText("link=CodDOOliNuevoTest");
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
		
		selenium.click("link=Configuració");	
		selenium.click("link=Usuari");
		selenium.waitForPageToLoad("30000");
		
		// Busqueda
		text=null;
		while (true){
			
			try{
				text=selenium.getText("link=OlivicultorTest");
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
		selenium.click("link=OlivicultorTest");
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
				text=selenium.getText("link=OlivicultorTest");
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