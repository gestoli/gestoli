package es.caib.gestoli.front.applet.rfid;

                                                                                   
public final class Semaforo {
                                              
    private int s;
                                                                                   
    public Semaforo (int inicial) {
        s = inicial ;
    }
                                                                                
    public synchronized void P() throws InterruptedException {
        while (s==0) { 
        	wait(); 
        }
        s--;
    }
                                                                                   
    public synchronized void V() {
        s++;
        notify();
    }
    
    public int getContador(){
    	return s;
    }
                                                                                   
}
