package es.caib.gestoli.front.applet.rfid;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;

public class ComRFID {
	
	private static CommPortIdentifier port;
	private static SerialPort serialPort;
	public static Thread threadLectura, threadEscritura;
	
	private static boolean eliminarHiloLectura =false;
	private static boolean eliminarHiloEscritura =false;
	
	public static byte[] bufferEntrada;
	public static byte[] bufferSalida;
	
	public static Semaforo semLectura;
	public static Semaforo semEscritura;
	private static boolean existenDatosListosParaTratar;
	
	public static final int ActivarLed=0;
	public static final int AnsGetUID=1;
	public static final int Select=2;
	public static final int WriteBlock=3;
	public static final int ReadBlock=4;
	public static final int WriteKeyE2P=5;
	public static final int Login=6;
	public static final int TramaNoTratada=7;
	public static final int WriteConfigBlock=8;
	public static final int FirmwareVersion=9;

	private static int esperaSem = 5;
	
	public static CommPortIdentifier puertoValido=null; 
	
	
    public ComRFID(CommPortIdentifier port) throws Exception
    {
        super();
        
        this.port=port;
        semLectura=new Semaforo(0);
        semEscritura=new Semaforo(0);
        existenDatosListosParaTratar=false;
    }
    
    // Conexion con el puerto RS232 y ejecucion de los hilos de lectura/escritura
    String connect () throws Exception
    {
        
        if ( port.isCurrentlyOwned() )
        {
        	System.out.println("Puerto en uso");
            return "Error: El puerto RS232 esta ocupado.";
        }
        else
        {
            CommPort commPort = port.open(this.getClass().getName(),2000);
            
            if ( commPort instanceof SerialPort )
            {
                serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();

                threadLectura=new Thread(new SerialReader(in),"LecturaRfid");
                threadLectura.start();
                threadEscritura=new Thread(new SerialWriter(out),"EscrituraRfid");
                threadEscritura.start();

            }
            else
            {
                return "Error: No se reconoce el puerto RS232.";
            }
        }     
        return "";
    }
    
    
    public static void disconnect(){
    	threadEscritura=null;
    	threadLectura=null;
    	if (serialPort!=null){
    		serialPort.close();
    	}
    	serialPort=null;
    	semLectura=null;
    	semEscritura=null;
    	port=null;
    	bufferEntrada=null;
    	bufferSalida=null;
    	puertoValido=null;
    	eliminarHiloLectura =false;
    	eliminarHiloEscritura =false;
    	existenDatosListosParaTratar=false;
    	
    }
    
    
    // Reconocimiento de puertos RS232 disponibles
    public static HashSet getAvailableSerialPorts() {
        HashSet h = new HashSet();
        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
            switch (com.getPortType()) {
            case CommPortIdentifier.PORT_SERIAL:
                try {
                	System.out.println("Abriendo puerto, "  + com.getName());
                    CommPort thePort = com.open("CommUtil", 50);
                    System.out.println("Cerrando puerto, "  + com.getName());
                    thePort.close();
                    System.out.println("Poniendo puerto en la lista de disponibles, "  + com.getName());
                    h.add(com);
                } catch (PortInUseException e) {
                    System.out.println("Port, "  + com.getName() + ", is in use.");
                } catch (Exception e) {
                    System.err.println("Failed to open port " +  com.getName());
                    e.printStackTrace();
                }
            }
        }
        return h;
    }

    
    // Detiene el hilo de lectura
    public static void pararHiloLectura(){
    	eliminarHiloLectura=true;
    	if (semLectura!=null){
    		semLectura.V();
    	}
    	try{
    		Thread.sleep(10);
    	}catch (Exception e) {
		}
    }
    
    
    // Detiene el hilo de escritura
    public static void pararHiloEscritura(){
    	eliminarHiloEscritura=true;
    	if (semEscritura!=null){
    		semEscritura.V();
    	}
    	try{
    		Thread.sleep(10);
    	}catch (Exception e) {
		}
    }    
    
    
    // Comando que configura el lector para ejecutarse en modo comandos 0x00
    public static void opUsarModoComandos(){
    	try{
	    	while (semEscritura.getContador()>0){ Thread.sleep(esperaSem); }
	    	bufferSalida = Conversiones.tramaStringToByte("023B0207004403");
	    	semEscritura.V();
	    	while (semEscritura.getContador()>0){ Thread.sleep(esperaSem); }
	    	bufferSalida = Conversiones.tramaStringToByte("0239003903");
	    	semEscritura.V();
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    // Discriminacion de la respuesta dependiendo del OpCode
    public static int discriminarTramaRespuesta (byte op){
    	
    	char opChar = (char) (op&0x00FF);
    	
    	switch (opChar){
    		case 0xBC:
    			return ActivarLed;
    		case 0x90:
    			return AnsGetUID;
    		case 0x91:
    			return Select;
    		case 0xA2:
    			return ReadBlock;
    		case 0xA3:
    			return WriteBlock;
    		case 0xA0:
    			return WriteKeyE2P;
    		case 0xA1:
    			return Login;
    		case 0xBB:
    			return WriteConfigBlock;
    		case 0x82:
    			return FirmwareVersion;
    		default:
    			return TramaNoTratada;
    	
    	}
    	
    }
    
    
    // Obtiene el UID de una trama de respuesta UID
    // Programado solo para el modo de operacion con comandos 0x00
    public static String getUIDFromAnsGetUIDTrama (byte[] trama){
    	
    	int i,j;
    	
    	switch ((char)trama[2]){
	    	case 0x05:
	    		i=4;
	    		j=i+3;
	    		break;
	    	case 0x08:
	    		i=4;
	    		j=i+7;	    		
	    		break;
	    	case 0x0B:
	    		i=4;
	    		j=i+9;	    		
	    		break;
	    	default:
	    		return "Error. No se usa el modo de operacion 0x00.";
    	}
    	
    	byte[] byteUID = new byte[(j-i)+1];
    	
    	int z;
    	for (z=0; z<byteUID.length;z++){
    		byteUID[z]=trama[z+i];
    	}
    	
    	return Conversiones.tramaByteToString(byteUID);
    	
    }
    
    
    // Envio del comando Select, para acceder a la tarjeta una vez detectado su UID
    public static void opSelect(){
    	try{
	    	while (semEscritura.getContador()>0){ Thread.sleep(esperaSem); }
	    	bufferSalida = Conversiones.tramaStringToByte("0211001103");
	    	semEscritura.V();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    
    // Identificacion del tipo de tarjeta en una trama de respuesta Select
    public static String getTipoFromSelectTrama(byte[] trama){
    	
    	switch((char)trama[3]){
	    	case 0x00:
	    		return "DESFIRE"; 
	    	case 0x01:
	    		return "MIFARE";
	    	default:
	    		return "ERROR";
    	}
    	
    }
    
    
    // Comando que ejecuta la lectura de un bloque de memoria de la tarjeta.
    // Si el bloque esta vacio devuelve null
    public static void opReadBlock(char bloque){
    	byte [] temp= Conversiones.tramaStringToByte("022201000003");
    	temp[3]=(byte)bloque;
    	temp[4]=(byte)(0x22+0x01+bloque);
    	try{
	    	while (semEscritura.getContador()>0){ Thread.sleep(esperaSem); }
	    	bufferSalida = temp; 
	    	semEscritura.V();
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    // Obtiene los 16bytes del bloque leido en una trama de respuesta ReadBlock
    public static String getBlockFromReadBlockTrama (byte[] trama){
    	
    	byte[] temp=new byte[16];
    	
    	if((char)trama[2]==0x10){
    		int i;
    		for (i=0;i<16;i++){
    			temp[i]=trama[i+3];
    		}
    		
    		return Conversiones.tramaByteToString(temp);
    		
    	}else{
    		return null;
    	}
    	
    }
    
       
    // Ejecuta el comando WriteBlock que escribe 16bytes en una celda de memoria de la tarjeta
    public static void opWriteBlock(char bloque, String info){
    	
    	if (info.length()!=32){
    		info="E";
    	}
    	
    	byte [] datos = Conversiones.tramaStringToByte(info);
    	
    	byte [] temp=new byte[22];
    	temp[0]=(byte)0x02;
    	temp[1]=(byte)0x23;
    	temp[2]=(byte)0x11;
    	temp[3]=(byte)bloque;
    	
    	int cheksum= 0x23+0x11+bloque;
    	
    	int z;
    	for (z=0;z<16;z++){
    		temp[4+z]=datos[z];
    		cheksum+=(datos[z]&0x00FF);
    	}
    	
    	temp[20]=(byte)(cheksum&0x00FF);
    	temp[21]=(byte)0x03;
    	
    	try{
	    	while (semEscritura.getContador()>0){ Thread.sleep(esperaSem); }
	    	bufferSalida = temp; 
	    	semEscritura.V();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	
    }
    
    // Metodo que obtiene la confirmacion de escritura correcta en tramas de
    // respuesta WriteBlock
    public static boolean getConfirmFromWriteBlockTrama (byte[] trama){
    	
    	if((char)trama[3]==0x00){
    		return true;
    	}else{
    		return false;
    	}
    	
    }
    
    
    // Comando que activa los leds durante un periodo de tiempo especificado en "ms5"
    public static void opActivarLed(boolean elVerde,char ms5){

    	byte [] temp;
    	int checksum;

		temp= Conversiones.tramaStringToByte("023C0200000003");
    	checksum = 0x3C+0x02;
    	
    	if (!elVerde){
    		checksum+=0x01;
    		temp[3]=(byte)0x01;
    	}
    	
    	checksum+=ms5;
    	temp[4]=(byte)ms5;
    	temp[5]=(byte)checksum;
    	
    	try{
	    	while (semEscritura.getContador()>0){ Thread.sleep(esperaSem); }
	    	bufferSalida = temp; 
	    	semEscritura.V();
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    
    // Escribe una clave en un bloque
    public static void opWriteKeyE2P(char indice, String clave){
    	
    	byte [] temp= new byte[12];
    	byte [] datos = Conversiones.tramaStringToByte(clave);
    	
    	int checksum = 0x20+0x07+indice;
    	
    	temp[0]=(byte)0x02;
    	temp[1]=(byte)0x20;
    	temp[2]=(byte)0x07;
    	temp[3]=(byte)indice;
    	
    	int z;
    	for (z=0;z<6;z++){
    		temp[4+z]=datos[z];
    		checksum+=(datos[z]&0x00FF);
    	}
    	
    	temp[10]=(byte)checksum;
    	temp[11]=(byte)0x03;
    	     
    	try{
	    	while (semEscritura.getContador()>0){ Thread.sleep(esperaSem); }
	    	bufferSalida = temp; 
	    	semEscritura.V();
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    
    // Metodo que obtiene la confirmacion de escritura correcta en tramas de
    // respuesta WriteKeyE2P
    public static boolean getConfirmFromWriteKeyE2PTrama (byte[] trama){
    	
    	if((char)trama[3]==0x00){
    		return true;
    	}else{
    		return false;
    	}
    	
    }

    
    // Login de tipo 0x08 (0x03 no implementado)
    public static void opLogin(char bloque, char tipoClave, String clave){
    	
    	byte [] temp= new byte[13];
    	byte [] datos = Conversiones.tramaStringToByte(clave);
    	
    	int checksum = 0x21+0x08+bloque+tipoClave;
    	
    	temp[0]=(byte)0x02;
    	temp[1]=(byte)0x21;
    	temp[2]=(byte)0x08;
    	temp[3]=(byte)bloque;
    	temp[4]=(byte)tipoClave;
    	
    	int z;
    	for (z=0;z<6;z++){
    		temp[5+z]=datos[z];
    		checksum+=(datos[z]&0x00FF);
    	}

    	temp[11]=(byte)(checksum & 0x00FF);
    	temp[12]=(byte)0x03;
    	     
    	try{
	    	while (semEscritura.getContador()>0){ Thread.sleep(esperaSem); }
	    	bufferSalida = temp; 
	    	semEscritura.V();
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    
    // Metodo que obtiene la confirmacion de login correcto
    public static boolean getConfirmFromLoginTrama (byte[] trama){
    	
    	if((char)trama[3]==0x00){
    		return true;
    	}else{
    		return false;
    	}
    	
    }

    
    
    // Comando que devuelve la version del lector
    public static void opGetFirmwareMinorVersion(){

    	byte [] temp= Conversiones.tramaStringToByte("0202000203");
    	     
    	try{
	    	while (semEscritura.getContador()>0){ Thread.sleep(esperaSem); }
	    	bufferSalida = temp; 
	    	semEscritura.V();
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    
    // Obtiene la version menor de la trama de respuesta del firmware
    public static byte getMinorFirmwareFromFirmwareTrama (byte[] trama){
    	return trama[4];
    }
    
    
    
    // Hilo de lectura del puerto RS232
    public static class SerialReader implements Runnable 
    {
        InputStream in;
        
        public SerialReader ( InputStream in )
        {
            this.in = in;
        }
        
        public void run ()
        {
            char[] buffer = new char[1024];
            
            try
            {
            	while (true){
            		
            		while (this.in.available()<3){
            			Thread.sleep(10);
            		}
            		
            		buffer[0]=(char) this.in.read();
            		buffer[1]=(char) this.in.read();
            		buffer[2]=(char) this.in.read();
            		
            		while (this.in.available()<buffer[2]+2){
            			Thread.sleep(200);
            		}
            		
            		int indiceBuf=3;
            		
            		while (indiceBuf<buffer[2]+5){
            			buffer[indiceBuf]=(char) this.in.read();
            			indiceBuf++;
            		}
            		
            		bufferEntrada=new byte[indiceBuf];
            		int i;
            		for (i=0;i<bufferEntrada.length;i++){
            			bufferEntrada[i]=(byte)buffer[i];
            		}
            		
            		existenDatosListosParaTratar=true;
            		semLectura.P();
            		if (eliminarHiloLectura){
            			break;
            		}
            		existenDatosListosParaTratar=false;
            		
            	}

            }
            catch (Exception e)
            {
                ComRFID.pararHiloEscritura();
                ComRFID.disconnect();
            }finally{
            	try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
    }


    // Hilo de escritura del puerto RS232
    public static class SerialWriter implements Runnable 
    {
        OutputStream out;
        
        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }
        
        public void run ()
        {
            try
            {             
            	while (true){            		
            		semEscritura.P();
            		if (eliminarHiloEscritura){
            			break;
            		}
            		this.out.write(bufferSalida);
            		bufferSalida=null;
            	}

            }
            catch ( Exception e ){
            	ComRFID.pararHiloLectura();
            	ComRFID.disconnect();
			}finally{
            	try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
        }
    }


	public static boolean existenDatosListosParaTratar() {
		return existenDatosListosParaTratar;
	}
	
	public static void setExistenDatosListosParaTratar(boolean b) {
		existenDatosListosParaTratar=b;
	}
	
	public class CompruebaComunicacion implements Runnable{
		
		CompruebaComunicacion(CommPortIdentifier port){
			this.port=port;
		}
		
		CommPortIdentifier port;
		InputStream in;
        OutputStream out;
        
        public void run (){
        	
            try{             
            	puertoValido=null;
            	
            	System.setSecurityManager(null);
                
                if (!port.isCurrentlyOwned() ){
                	
                	System.out.println("Conectando a puerto de prueba.");
                    CommPort commPort = port.open(this.getClass().getName(),2000);
                    
                    if (commPort instanceof SerialPort){
                    	
                    	System.out.println("Configurando RS232.");
                        SerialPort serialPort = (SerialPort) commPort;
                        serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                        
                        in = serialPort.getInputStream();
                        out = serialPort.getOutputStream();
                        
                        System.out.println("Enviando trama de prueba de conexion.");
                        byte [] testCom = Conversiones.tramaStringToByte("0200000003");
                        
                        this.out.write(testCom);
                        
                        System.out.println("Recibiendo datos.");
                        int timeout=0;
                		while (this.in.available()<5 && timeout<100){
                			timeout+=10;
                			Thread.sleep(10);
                		}
                		
                		if (!(this.in.available()<5)){
                			byte [] resTestCom = new byte[5]; 
                			this.in.read(resTestCom,0,5);
                			
                			String strResTestCom = Conversiones.tramaByteToString(resTestCom);
                			
                			if (strResTestCom.equals("0280008003")){
                				System.out.println("Trama de prueba de conexion correcta.");
                				puertoValido=port;
                			}else{
                				System.out.println("Trama de prueba de conexion INCORRECTA.");
                			}

                		}else{
                			System.out.println("Timeout esperando la trama de respuesta.");
                		}
                		
                		System.out.println("Cerrando puerto.");
                		in.close();
                		out.close();
                		serialPort.close();
                        
                    }else{
                    	System.out.println("El puerto no es RS232.");
                    }
                    
                    commPort.close();
                     
                }  
                

            }catch ( Exception e ){
            	puertoValido=null;
            	System.out.println("Error puerto invalido.");
            	e.printStackTrace();
			}            
        }

		
	}
	

}
