package es.caib.gestoli.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
    
    
	public class UsuarioToDipositTestSuite extends TestSuite {  
	      
	public UsuarioToDipositTestSuite(String name) {  
	  super(name);  
	}  
	    
	public static void main(String[] args) {  
	  TestRunner.run(suite());  
	}  
	    
	public static Test suite() {  

		TestSuite suite = new UsuarioToDipositTestSuite("Desde la creacion de usuario hasta los depositos Gestoli");  
	   
		suite.addTestSuite(UsuarioToDipositAltas.class);  
		suite.addTestSuite(UsuarioToDipositBajas.class);
		
		return suite;  
	
	}


} 