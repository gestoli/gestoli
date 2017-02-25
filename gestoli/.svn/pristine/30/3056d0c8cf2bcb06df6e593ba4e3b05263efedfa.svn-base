package es.caib.gestoli.test;

import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class UsuarioToDescomposicionAltas extends TestCase {
	
	
	private Selenium selenium;
	
	
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost",4444,"*firefox","http://gestor:gestor@gestoli.in.at4.net:8080/gestoli/Inici.html?siteLanguage=ca");  
		selenium.start();
	}
	
	public void testDarDeAltaUsuarioOlivicultor() throws InterruptedException {  
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Usuari");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("usuari", "testUsuOli");
		selenium.type("contrasenya", "testUsuOli");
		selenium.select("idiomaId", "label=Català");
		selenium.addSelection("id_grupsArray", "label=Olivicultor");
		selenium.click("moveRight");
		selenium.type("id_observacions", "Este es el usuario asignado a testOli");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("L'usuari s'ha creat amb èxit"));
		
	}
	
	
	public void testDarDeAltaOlivicultorConSuUsuario() throws InterruptedException {  
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Olivicultor");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.select("usuariId", "label=testUsuOli");
		selenium.type("nom", "testOli");
		selenium.type("nif", "12345678");
		selenium.type("codiDO", "X/JK404/N");
		selenium.type("codiDOExperimental", "X/JK404/Exp");
		selenium.type("direccio", "C/ Olivicultor");
		selenium.type("poblacio", "Olivicultorlandia");
		selenium.type("codiPostal", "05673");
		selenium.type("compteBancari", "01234567890123456789");
		selenium.type("email", "oli@oli.es");
		selenium.type("telefon", "971223344");
		selenium.type("fax", "971223345");
		selenium.type("id_observacions", "Ficha de testOlivicultor");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("L'olivicultor s'ha creat amb èxit"));
		
	}	
	
	
	
	public void testDarDeAltaFincasDeOli() throws InterruptedException {  
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Finca");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=X/JK404/N");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("nom", "Finca nº1");
		selenium.type("id_observacions", "Finca nº1 de testOli");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("La finca s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("nom", "Finca nº2");
		selenium.type("id_observacions", "Finca nº2 de testOli");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("La finca s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.type("nom", "Finca nº3");
		selenium.type("id_observacions", "Finca nº3 de testOli");
		selenium.click("guardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("La finca s'ha creat amb èxit"));
		
	}	
	
	
	
	public void testDarDeAltaPlantacionesDescomposicionesDeOli() throws InterruptedException {  
		selenium.open("/gestoli/Inici.html");
		selenium.click("link=Configuració");
		selenium.click("link=Plantació");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=X/JK404/N");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.select("idFinca", "label=Finca nº1");
		selenium.type("municipi", "M1");
		selenium.type("poligon", "P1");
		selenium.type("parcela", "PA1");
		selenium.type("anyPlantacio", "2002");
		selenium.type("id_observacions", "Plantacion 1.1");
		selenium.click("enlaceDescomposicio");
		selenium.waitForPageToLoad("30000");
		selenium.type("variedades[0].numeroOliveres", "5");
		selenium.type("variedades[0].superficie", "5.0");
		selenium.type("variedades[0].maxProduccio", "5.0");
		selenium.type("variedades[1].numeroOliveres", "7");
		selenium.type("variedades[1].superficie", "7.0");
		selenium.type("variedades[1].maxProduccio", "7.0");
		selenium.click("enlaceDescomposicio");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("La plantació s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.select("idFinca", "label=Finca nº1");
		selenium.type("municipi", "M2");
		selenium.type("poligon", "P2");
		selenium.type("parcela", "PA2");
		selenium.type("anyPlantacio", "1940");
		selenium.type("id_observacions", "Plantacion 1.2");
		selenium.click("enlaceDescomposicio");
		selenium.waitForPageToLoad("30000");
		selenium.type("variedades[2].numeroOliveres", "12");
		selenium.type("variedades[2].superficie", "12.0");
		selenium.type("variedades[2].maxProduccio", "12.0");
		selenium.type("variedades[1].numeroOliveres", "23");
		selenium.type("variedades[1].superficie", "23.0");
		selenium.type("variedades[1].maxProduccio", "23.0");
		selenium.click("enlaceDescomposicio");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceGuardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("La plantació s'ha creat amb èxit"));
		selenium.click("enlaceCancelarForm");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.select("idFinca", "label=Finca nº1");
		selenium.type("municipi", "M3");
		selenium.type("poligon", "P3");
		selenium.type("parcela", "PA3");
		selenium.type("anyPlantacio", "2007");
		selenium.type("id_observacions", "Plantacion 1.3");
		selenium.click("enlaceDescomposicio");
		selenium.waitForPageToLoad("30000");
		selenium.type("variedades[2].numeroOliveres", "12");
		selenium.type("variedades[2].superficie", "12.0");
		selenium.type("variedades[2].maxProduccio", "12.0");
		selenium.type("variedades[3].numeroOliveres", "23");
		selenium.type("variedades[3].superficie", "23.0");
		selenium.type("variedades[3].maxProduccio", "23.0");
		selenium.click("enlaceDescomposicio");
		selenium.waitForPageToLoad("30000");
		selenium.click("guardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("La plantació s'ha creat amb èxit"));
		selenium.click("cancelarForm");
		selenium.waitForPageToLoad("30000");
		selenium.click("enlaceCrearForm");
		selenium.waitForPageToLoad("30000");
		selenium.select("idFinca", "label=Finca nº2");
		selenium.type("municipi", "M4");
		selenium.type("poligon", "M4");
		selenium.type("parcela", "PA4");
		selenium.type("anyPlantacio", "1890");
		selenium.type("id_observacions", "Plantacion 2");
		selenium.click("enlaceDescomposicio");
		selenium.waitForPageToLoad("30000");
		selenium.type("variedades[0].numeroOliveres", "34");
		selenium.type("variedades[0].superficie", "34.0");
		selenium.type("variedades[0].maxProduccio", "34.0");
		selenium.click("enlaceDescomposicio");
		selenium.waitForPageToLoad("30000");
		selenium.click("guardarForm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("La plantació s'ha creat amb èxit"));
		selenium.click("cancelarForm");
		selenium.waitForPageToLoad("30000");
		
	}	
	
	
	
	public void tearDown() {  
		selenium.stop();  
	} 	
	
	
	
}
