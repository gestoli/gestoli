package es.caib.gestoli.test;

import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

public class UsuarioToDescomposicionBajas extends TestCase {
	
	
	private Selenium selenium;
	
	
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://gestor:gestor@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();
	}
	
	
	public void testDarDeBajaPlantacionesOlivicultor() throws InterruptedException { 
		
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Plantació");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=X/JK404/N");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=M1");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		selenium.click("link=M2");
		selenium.waitForPageToLoad("30000");
		selenium.click("eliminarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		selenium.click("link=M3");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		selenium.click("link=M4");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");		
		
		assertTrue(selenium.isTextPresent("No s'ha trobat res per mostrar"));
		
	}
	
	
	
	public void testDarDeBajaFincasOlivicultor() throws InterruptedException { 
		
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Finca");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=X/JK404/N");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Finca nº1");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Finca nº2");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Finca nº3");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceBorrarForm");
		assertTrue(selenium.getConfirmation().matches("^Esborrar el registre[\\s\\S] Aquesta acció no es pot desfer\\.$"));
		selenium.waitForPageToLoad("30000");
		
		assertTrue(selenium.isTextPresent("No s'ha trobat res per mostrar"));
		
	}	
	
	

	public void testDarDeBajaFichaOlivicultor() throws InterruptedException { 
		
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");	
		selenium.click("link=Olivicultor");
		selenium.waitForPageToLoad("30000");
		
		// Busqueda
		String text=null;
		while (true){
			
			try{
				text=selenium.getText("link=X/JK404/N");
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
		selenium.click("link=X/JK404/N");
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
				text=selenium.getText("link=X/JK404/N");
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
	
	
	
	public void testDarDeBajaUsuarioOlivicultor() throws InterruptedException { 
		
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");	
		selenium.click("link=Usuari");
		selenium.waitForPageToLoad("30000");
		
		// Busqueda
		String text=null;
		while (true){
			
			try{
				text=selenium.getText("link=testUsuOli");
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
		selenium.click("link=testUsuOli");
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
				text=selenium.getText("link=testUsuOli");
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