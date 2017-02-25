package es.caib.gestoli.front.applet.rfid;

import java.util.ArrayList;
import java.util.Random;

public class Conversiones{
	
	
	Conversiones(){
		
	}
	
	
	private static String byteToString (byte b){
		
		String res="";
		
		ArrayList partesByte = new ArrayList();
		
		partesByte.add(new Byte((byte) ((b & 0xF0)>>4)));
		partesByte.add(new Byte((byte) (b & 0x0F)));
		
		while (!partesByte.isEmpty()){
		
			int bInt = (int)((Byte)partesByte.get(0)).byteValue();
		
			if (bInt<10){
				
				res+=String.valueOf(bInt);
				
			}else{
				
				switch (bInt){
					case 10:
						res+="A";
						break;
					case 11:
						res+="B";
						break;
					case 12:
						res+="C";
						break;
					case 13:
						res+="D";
						break;
					case 14:
						res+="E";
						break;
					case 15:
						res+="F";
						break;
				}				
				
			}
			
			partesByte.remove(0);
		
		}
		
		return res;
		
	}
	
	
	private static int charToIntByte (char b){
		
		String bStr = ""+b;
		bStr = bStr.toUpperCase();
		
		String comparador = "0123456789ABCDEF";
		
		int pos = comparador.indexOf(bStr);
		
		return pos;
		
	}
	
	
	
    public static String tramaByteToString (byte[] b){
    	
    	String res = "";
    
    	int i;
    	for (i=0;i<b.length;i++){
    		res+=byteToString(b[i]);
    	}
    		
    	return res;
    	
    }
	
    
    
    public static byte[] tramaStringToByte (String b){
    	
    	byte[] bByte = new byte[b.length()/2];
    	
    	if (b.length()%2==0){
    		
    		int i;
    		for(i=0;i<b.length()/2;i++){
    			
    			int bSup=charToIntByte(b.charAt(i*2));
    			int bInf=charToIntByte(b.charAt(i*2+1));
   			
    			bByte[i]=(byte) ((bSup<<4)+bInf);
    		}
    		
    	}
    	
    	return bByte;
   
    	
    }
    
    
    public static String generadorAsciiHexAleatorio(int tamanyo){
    	
    	String res="";
    	Random ran = new Random();
    	
    	int x;
    	for (x=0;x<tamanyo;x++){
    		
    		int ranNum=ran.nextInt(16);
    		
			if (ranNum<10){
				res+=String.valueOf(ranNum);
			}else{
				switch (ranNum){
					case 10:
						res+="A";
						break;
					case 11:
						res+="B";
						break;
					case 12:
						res+="C";
						break;
					case 13:
						res+="D";
						break;
					case 14:
						res+="E";
						break;
					case 15:
						res+="F";
						break;
					default:
						res+="0";
				}				
				
			}
    		
    	}
    	
    	return res;
    	
    }
    
    
	
	
}