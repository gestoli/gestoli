package es.caib.gestoli.test;

import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class UsuarioToDipositAltas extends TestCase {
	
	
	private Selenium selenium;
	
	public void setUp() throws Exception {

	}
	
	public void testDarDeAltaUsuarioEnvasador() throws InterruptedException { 
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://gestor:gestor@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Usuari");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("usuari", "testUsuEnv");
		selenium.type("contrasenya", "testUsuEnv");
		selenium.select("idiomaId", "label=Català");
		selenium.addSelection("id_grupsArray", "label=Envasador");
		selenium.click("moveRight");
		selenium.type("id_observacions", "Este es el usuario asignado a testEnv");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("L'usuari s'ha creat amb èxit"));
		
	}
	
	
	public void testEstablimentAltaEnvasador() throws InterruptedException {  
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://gestor:gestor@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Establiment");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.select("usuariId", "label=testUsuEnv");
		selenium.type("nom", "Envasador Establecimiento");
		selenium.type("cif", "123456789");
		selenium.type("direccio", "C/ Envasador");
		selenium.type("poblacio", "Ciudad Envasador");
		selenium.type("codiPostal", "01237");
		selenium.type("email", "env@env.com");
		selenium.type("telefon", "971223344");
		selenium.type("fax", "971223345");
		selenium.type("numeroTreballadors", "27");
		selenium.type("superficie", "200");
		selenium.click("3");
		selenium.type("temperaturaMaximaPasta", "24");
		selenium.type("capacitatProduccio", "700");
		selenium.addSelection("id_marcasArray", "label=Carbonell");
		selenium.click("moveRight");
		selenium.type("nombreSolicitant", "EnvasadorSol");
		selenium.type("nifSolicitant", "45762245");
		selenium.type("perfilSolicitant", "Perfil Envasador");
		selenium.type("telefonSolicitant", "971223344");
		selenium.select("unitatMesura", "label=Quilos");
		selenium.type("id_observacions", "Observaciones establecimiento envasador");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("L'establiment s'ha creat amb èxit"));
		
	}
	
	
	public void testDarDeAltaZonas() throws InterruptedException {  
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://testUsuEnv:testUsuEnv@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Zona");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("nom", "Planta baja");
		selenium.click("fictici");
		selenium.type("id_observacions", "Obs planta baja");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("La zona s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("nom", "1º Piso");
		selenium.click("fictici");
		selenium.type("id_observacions", "Obs 1º piso");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("La zona s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("nom", "2º Piso");
		selenium.click("fictici");
		selenium.type("id_observacions", "Obs 2º piso");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("La zona s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");
		
		assertTrue(selenium.isTextPresent("Planta baja"));
		assertTrue(selenium.isTextPresent("1º Piso"));
		assertTrue(selenium.isTextPresent("2º Piso"));
	}	
	
	
	
	public void testDarDeAltaDepositos() throws InterruptedException {  
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://testUsuEnv:testUsuEnv@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Dipòsit");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("codiAssignat", "DPB1");
		selenium.type("capacitat", "10");
		selenium.select("idMaterialDiposit", "label=Fibra de vidre");
		selenium.select("idZona", "label=Planta baja");
		selenium.type("id_observacions", "Obs diposit");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("El dipòsit s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");	
		
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("codiAssignat", "DPP1");
		selenium.type("capacitat", "10");
		selenium.select("idMaterialDiposit", "label=Fibra de vidre");
		selenium.select("idZona", "label=1º Piso");
		selenium.type("id_observacions", "Obs diposit");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("El dipòsit s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("codiAssignat", "DPP2");
		selenium.type("capacitat", "10");
		selenium.select("idMaterialDiposit", "label=Fibra de vidre");
		selenium.select("idZona", "label=1º Piso");
		selenium.type("id_observacions", "Obs diposit");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("El dipòsit s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("codiAssignat", "DSP1");
		selenium.type("capacitat", "10");
		selenium.select("idMaterialDiposit", "label=Fibra de vidre");
		selenium.select("idZona", "label=2º Piso");
		selenium.type("id_observacions", "Obs diposit");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("El dipòsit s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("codiAssignat", "DSP2");
		selenium.type("capacitat", "10");
		selenium.select("idMaterialDiposit", "label=Fibra de vidre");
		selenium.select("idZona", "label=2º Piso");
		selenium.type("id_observacions", "Obs diposit");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("El dipòsit s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("codiAssignat", "DSP3");
		selenium.type("capacitat", "10");
		selenium.select("idMaterialDiposit", "label=Fibra de vidre");
		selenium.select("idZona", "label=2º Piso");
		selenium.type("id_observacions", "Obs diposit");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("El dipòsit s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");
		
		assertTrue(selenium.isTextPresent("DPB1"));
		assertTrue(selenium.isTextPresent("DPP1"));
		assertTrue(selenium.isTextPresent("DPP2"));
		assertTrue(selenium.isTextPresent("DSP1"));
		assertTrue(selenium.isTextPresent("DSP2"));
		assertTrue(selenium.isTextPresent("DSP3"));
		
	}	
	
	
	
	public void testCompruebaVistaEstablecimiento() throws InterruptedException {  
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://testUsuEnv:testUsuEnv@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Vista establiment");
		
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("DPB1"));
		
		selenium.click("link=1º Piso");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("DPP1"));
		assertTrue(selenium.isTextPresent("DPP2"));		
		
		selenium.click("link=2º Piso");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("DSP1"));
		assertTrue(selenium.isTextPresent("DSP2"));
		assertTrue(selenium.isTextPresent("DSP3"));
		
	}	
	
	
	
	public void tearDown() {  
		selenium.stop();  
	} 	
	
	
	
}
