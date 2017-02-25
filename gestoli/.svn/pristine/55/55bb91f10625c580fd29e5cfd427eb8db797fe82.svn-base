package es.caib.gestoli.test;

import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

public class EstablimentTest extends TestCase {
	
	
	private Selenium selenium;
	
	
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://gestor:gestor@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();
	}
	
	public void testEstablimentAlta() throws InterruptedException {  
		
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Usuari");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("usuari", "UsuEnvTest");
		selenium.type("contrasenya", "usuenvtest");
		selenium.select("idiomaId", "label=Català");
		selenium.addSelection("id_grupsArray", "label=Envasador");
		selenium.click("moveRight");
		selenium.type("id_observacions", "Observacions gestor");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("L'usuari s'ha creat amb èxit"));
		
		selenium.click("link=Configuració");
		selenium.click("link=Establiment");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.select("usuariId", "label=UsuEnvTest");
		selenium.type("nom", "UsuEnv Establecimiento");
		selenium.type("cif", "123456578");
		selenium.type("direccio", "C\\ Est");
		selenium.type("poblacio", "Loc");
		selenium.type("codiPostal", "01234");
		selenium.type("email", "est@est.com");
		selenium.type("telefon", "987653322");
		selenium.type("fax", "988663322");
		selenium.type("numeroTreballadors", "23");
		selenium.type("superficie", "100");
		selenium.click("2");
		selenium.type("codiRC", "321");
		selenium.type("capacitatProduccio", "123");
		selenium.addSelection("id_marcasArray", "label=Caimari");
		selenium.click("moveRight");
		selenium.addSelection("id_marcasArray", "label=Son Parera");
		selenium.click("moveRight");
		selenium.type("nombreSolicitant", "Sol");
		selenium.type("nifSolicitant", "1234");
		selenium.type("telefonSolicitant", "9123333");
		selenium.select("unitatMesura", "label=Litres");
		selenium.type("id_observacions", "Obssss");
		selenium.click("guardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("L'establiment s'ha creat amb èxit"));
		
	}
	
	
	public void testEstablimentAltaYaExiste() throws InterruptedException {
		selenium.open("/gestoli/Inici.html");
		
		selenium.click("link=Configuració");
		selenium.click("link=Establiment");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.select("usuariId", "label=UsuEnvTest");
		selenium.type("nom", "UsuEnv Establecimiento");
		selenium.type("cif", "123456578");
		selenium.type("direccio", "C\\ Est");
		selenium.type("poblacio", "Loc");
		selenium.type("codiPostal", "01234");
		selenium.type("email", "est@est.com");
		selenium.type("telefon", "987653322");
		selenium.type("fax", "988663322");
		selenium.type("numeroTreballadors", "23");
		selenium.type("superficie", "100");
		selenium.click("2");
		selenium.type("codiRC", "321");
		selenium.type("capacitatProduccio", "123");
		selenium.addSelection("id_marcasArray", "label=Caimari");
		selenium.click("moveRight");
		selenium.addSelection("id_marcasArray", "label=Son Parera");
		selenium.click("moveRight");
		selenium.type("nombreSolicitant", "Sol");
		selenium.type("nifSolicitant", "1234");
		selenium.type("telefonSolicitant", "9123333");
		selenium.select("unitatMesura", "label=Litres");
		selenium.type("id_observacions", "Obssss");
		selenium.click("guardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("L'usuari ja ha estat assignat a un altre olivicultor"));
		assertTrue(selenium.isTextPresent("El nom d'establiment ja existeix"));
		
	}	
	
	
	public void testEstablimentBaja() throws InterruptedException {  
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");	
		selenium.click("link=Establiment");
		selenium.waitForPageToLoad("30000");
		
		// Busqueda
		String text=null;
		while (true){
			
			try{
				if (selenium.getBodyText().indexOf("UsuEnv Establecimiento")>=0){
					text="X";
				}
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
		selenium.click("UsuEnv Establecimiento");
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
				if (selenium.getBodyText().indexOf("UsuEnv Establecimiento")>=0){
					text="X";
				}
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
				text=selenium.getText("link=UsuEnvTest");
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
		selenium.click("link=UsuEnvTest");
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
				text=selenium.getText("link=UsuEnvTest");
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