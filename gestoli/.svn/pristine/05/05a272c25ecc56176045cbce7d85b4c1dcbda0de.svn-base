package es.caib.gestoli.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
    
    
	public class VariosTestSuite extends TestSuite {  
	      
	public VariosTestSuite(String name) {  
	  super(name);  
	}  
	    
	public static void main(String[] args) {  
	  TestRunner.run(suite());  
	}  
	    
	public static Test suite() {  

		TestSuite suite = new VariosTestSuite("Pruebas varias Gestoli");  
	   
		suite.addTestSuite(UsuariTest.class);  
		suite.addTestSuite(TipusEnvasTest.class);
		suite.addTestSuite(EstablimentTest.class);
        suite.addTestSuite(OlivicultorTest.class);
		
		return suite;  
	
	}


} 