package es.caib.gestoli.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
    
    
	public class UsuarioToDescomposicionTestSuite extends TestSuite {  
	      
	public UsuarioToDescomposicionTestSuite(String name) {  
	  super(name);  
	}  
	    
	public static void main(String[] args) {  
	  TestRunner.run(suite());  
	}  
	    
	public static Test suite() {  

		TestSuite suite = new UsuarioToDescomposicionTestSuite("Desde la creacion de usuario hasta las descomposiciones Gestoli");  
	   
		suite.addTestSuite(UsuarioToDescomposicionAltas.class);  
		suite.addTestSuite(UsuarioToDescomposicionBajas.class);
		
		return suite;  
	
	}


} 