package es.caib.gestoli.front.applet.rfid;

import gnu.io.CommPortIdentifier;

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*; 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JApplet;

import sun.misc.VM;

public class RfidGrabarUidBd extends JApplet implements Runnable{
	
	static ComRFID comRFID;
	boolean pararHiloTratamiento = false;
	
	String uidTag="";
	String tipoTag="";
	String codigoOli="";
	
	/**
	 * Construye la ruta de redirección una vez obtenida la tarjeta
	 */
	String ruta="";
	
	URL codeBase;
	URL servlet;
	String pagina;
	String appletBase;
	URLConnection connection;
	ObjectOutputStream output;
	ObjectInputStream input;
	
	boolean errorSelect=false;
	boolean luzVerde=false;
	boolean luzRoja=false;
	boolean buscandoCom=true;
	
	Image imgAlerta;
	Image imgOk;
	Image imgTarjeta;
	Image imgStop;
	Image imgBuscandoCom;
	
	boolean verbose=false;
	
	public final int esperandoUID=0;
	public final int esperandoSelect=1;
	public final int esperandoLuzOn=2;

	public void init(){

		try{
			if (getParameter("verbose")!=null){
				if (getParameter("verbose").equals("1")){
					verbose=true;
				}
			}
			
			if (verbose) System.out.println("Proceso de inicializacion.");
			
			if (verbose) System.out.println("Creando rutas de acceso");
			codeBase = this.getCodeBase(); //Devuelve la URL desde donde se descargo el applet
	
			//Construimos la URL del servlet que procesara la peticion
			String protocol = codeBase.getProtocol();
			if (verbose) System.out.println("Protocolo: "+protocol);
			String server = codeBase.getHost();
			if (verbose) System.out.println("Host: "+server);
			int port = codeBase.getPort();
			if (verbose) System.out.println("Puerto: "+port);

			String [] pathArray = codeBase.getPath().split("/");
			String path="";
			int x;
			for (x=0; x<pathArray.length-1;x++){
				if (x>0){
					path+="/";
				}
				path+=pathArray[x];
			}
			
			String imgIdioma = getParameter("idioma");
			if (verbose) System.out.println("Idioma:"+imgIdioma);
			
			codigoOli=getParameter("id");
			String target = path+"/Rfid.html?acc=1&idoliv="+codigoOli;
	
			if ( port != -1 ){
				ruta = server+":"+port+path;
			}else{
				ruta = server+path;
			}
			
			//appletBase=protocol+"://"+server+":"+port+path+"/applet/";
			//pagina=protocol+"://"+server+":"+port+path+"/OlivicultorForm.html?id=";
			appletBase=protocol+"://"+ruta+"/applet/";
			pagina=protocol+"://"+ruta+"/OlivicultorForm.html?id=";
			servlet = new URL(protocol,server, port, target);
			
			if (verbose) System.out.println("Cargando imagenes.");
			if (imgAlerta==null){
				imgAlerta = getToolkit().getImage(new URL(appletBase+"img/Error_"+imgIdioma+".png"));
			}
			if (imgOk==null){
				imgOk = getToolkit().getImage(new URL(appletBase+"img/Correcta_"+imgIdioma+".png"));
			}
			if (imgStop==null){
				imgStop = getToolkit().getImage(new URL(appletBase+"img/Invalida_"+imgIdioma+".png"));
			}
			if (imgTarjeta==null){
				imgTarjeta = getToolkit().getImage(new URL(appletBase+"img/Esperando_"+imgIdioma+".png"));
			}
			if (imgBuscandoCom==null){
				imgBuscandoCom = getToolkit().getImage(new URL(appletBase+"img/Buscando_"+imgIdioma+".png"));
			}
		    
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if (verbose) System.out.println("Creando hilo principal.");
		(new Thread(this,"HiloPrincipal")).start();
		
		if (verbose) System.out.println("Fin proceso inicializacion.");
		
	}
		
	
	public void start(){
		
	}
	
	public void stop(){
		
	}

	public void destroy(){
		
		if (verbose) System.out.println("Inicio destroy.");
		
		if (verbose) System.out.println("Liberando imagenes.");
	    imgAlerta.flush();
	    imgOk.flush();
	    imgStop.flush();
	    imgTarjeta.flush();
	    imgBuscandoCom.flush();
	    
	    if (verbose) System.out.println("Liberando puerto (si no ha sido liberado ya).");
	    pararHiloTratamiento=true;
	    if (comRFID!=null){
	    	ComRFID.pararHiloEscritura();
	    	ComRFID.pararHiloEscritura();
	    	ComRFID.disconnect();
	    	comRFID=null;
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	    
	    if (verbose) System.out.println("Liberando variables generales.");
		codeBase=null;
		servlet=null;
		pagina=null;
		appletBase=null;
		connection=null;
		output=null;
		input=null;
		imgAlerta=null;
		imgOk=null;
		imgStop=null;
		imgTarjeta=null;
		imgBuscandoCom=null;
		uidTag=null;
		tipoTag=null;
		codigoOli=null;
	    
		if (verbose) System.out.println("Saliendo de la maquina virtual.");
	    System.exit(0);
	    
	}
	
	
	public void paint(Graphics g){

		if (errorSelect){
			g.drawImage(imgAlerta, 0, 0, this);
		}else if (luzVerde){
			g.drawImage(imgOk, 0, 0, this);
		}else if (luzRoja){
			g.drawImage(imgStop, 0, 0, this);
		}else if (buscandoCom){
			g.drawImage(imgBuscandoCom, 0, 0, this);			
		}else{
			g.drawImage(imgTarjeta, 0, 0, this);
		}
		
	    try{
	    	if (errorSelect || luzRoja){
	    		if (ComRFID.puertoValido!=null){
		    		Thread.sleep(1500);
		    		errorSelect=false;
		    		luzRoja=false;
		    		g.drawImage(imgTarjeta, 0, 0, this);
	    		}
	    	}
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
	    
	}
	
	
    public void run(){
    	
		try{
			
			comRFID = new ComRFID(null);	
			
			if (verbose) System.out.println("Obteniendo puertos COM disponibles.");
			Iterator itePuertos = ComRFID.getAvailableSerialPorts().iterator();
			
			if (verbose) System.out.println("Inicio prueba de puertos.");
			while (itePuertos.hasNext() && ComRFID.puertoValido==null){
				
				CommPortIdentifier p = (CommPortIdentifier)itePuertos.next();
				if (verbose) System.out.println("Probando puerto: "+p.getName());
				Thread hiloPruebaPuerto = new Thread (comRFID.new CompruebaComunicacion(p),"TestComunicacion");
				hiloPruebaPuerto.start();
				hiloPruebaPuerto.join();
				if (verbose) System.out.println("Fin prueba puerto: "+p.getName());
				hiloPruebaPuerto=null;
				
			}
			if (verbose) System.out.println("Fin prueba de puertos.");
			
			buscandoCom=false;
			repaint();
			
			CommPortIdentifier tmpPort= ComRFID.puertoValido;
			ComRFID.disconnect();
			ComRFID.puertoValido= tmpPort;
			tmpPort=null;
			
			if (ComRFID.puertoValido==null){
				
				if (verbose) System.out.println("Error. No se han encontrado puertos validos.");
				errorSelect=true;
				repaint();
				throw new Exception();
				
			}else{
				
				comRFID = new ComRFID(ComRFID.puertoValido);
				
				if (verbose) System.out.println("Conectando a: "+ComRFID.puertoValido.getName());
				uidTag=comRFID.connect();
	
				if (!uidTag.equals("")){
					if (verbose) System.out.println("Error de conexion: "+uidTag);
					errorSelect=true;
					repaint();
					throw new Exception();
				}else{
					if (verbose) System.out.println("Inicio paso a modo comandos.");
					ComRFID.opUsarModoComandos();
					if (verbose) System.out.println("Fin paso a modo comandos.");
					repaint();
				}
				
				
				int msEsperadosEntreFases=0;
				int fase= esperandoUID;
				
				if (verbose) System.out.println("Entrando en el bucle principal.");
				while(!pararHiloTratamiento){
				
					if (msEsperadosEntreFases>1000){
						fase= esperandoUID;
						msEsperadosEntreFases=0;
					}
					
					while(!ComRFID.existenDatosListosParaTratar()){
						Thread.sleep(10);
						if (fase!=esperandoUID){
							msEsperadosEntreFases+=10;
							if(msEsperadosEntreFases>1000){
								if (verbose) System.out.println("Tiempo de espera agotado, esperando nuevo UID.");
								msEsperadosEntreFases=0;
								fase=esperandoUID;
							}
						}
					}
					
					switch(fase){

						// Fase esperandoUID: A la espera del acercamiento de una tarjeta para obtener el UID
						case esperandoUID: 
							if (ComRFID.discriminarTramaRespuesta(ComRFID.bufferEntrada[1])==ComRFID.AnsGetUID){
							
								errorSelect=false;
								luzVerde=false;
								luzRoja=false;
								repaint();
								
								uidTag=ComRFID.getUIDFromAnsGetUIDTrama(ComRFID.bufferEntrada);
								
								msEsperadosEntreFases=0;
								if (verbose) System.out.println("UID Tarjeta: "+uidTag);
								ComRFID.opSelect();
								
								fase=esperandoSelect;
								
							}
							break;
							
						// Fase esperandoSelect: Esperando el acceso a la tarjeta por comandos
						case esperandoSelect:
							if (ComRFID.discriminarTramaRespuesta(ComRFID.bufferEntrada[1])==ComRFID.Select){
								
								tipoTag=ComRFID.getTipoFromSelectTrama(ComRFID.bufferEntrada);
								
								msEsperadosEntreFases=0;
								if (verbose) System.out.println("Tipo tarjeta: "+tipoTag);
								
								if (tipoTag.equals("MIFARE")){
									
									/*Ahora indicamos en la cabecera de la peticion POST que tipos de datos viajan. Vamos a enviar un objeto al servidor asi que enviamos el nombre de su clase. Para poder enviar objetos, estos tienen que ser serializables, es decir, implementar la interfaz Serializable. Para simplificar el ejemplo usaremos por ejemplo Date que es serializable*/
									Object obj = new String(uidTag);
									
									if (verbose) System.out.println("Abriendo conexion con el servlet.");
									/*Creamos la conexion con el servidor*/
									connection = servlet.openConnection();
									connection.setDoInput(true); //Permitimos datos de respuesta
									connection.setDoOutput(true); //Vamos a enviar datos de salida
									connection.setUseCaches(false); //Desactivamos cache por si realizamos varias comunicaciones
									connection.setRequestProperty("Content-Type", "java-internal/" + obj.getClass().getName());
							
									/*Enviamos la peticion al servidor. Para eso hay que meter el objeto en el flujo de salida de la conexion */
									if (verbose) System.out.println("Enviando datos al servlet.");
									output = new ObjectOutputStream(connection.getOutputStream());
									output.writeObject(obj);
											
									output.flush();
									output.close();
							
									if (verbose) System.out.println("Esperando respuesta del servlet.");
									input = new ObjectInputStream(connection.getInputStream());
									Object response = input.readObject();
									input.close();
									
									String res = (String) response;
									if (verbose) System.out.println("Respuesta del servlet obtenida.");
									
									if (res.equals("ok")){
										if (verbose) System.out.println("UID grabado correctamente en la BBDD.");
										ComRFID.opActivarLed(true, (char)0xC8);
										luzVerde=true;
										repaint();
									}else{
										if (verbose) System.out.println("Error. UID ya existente o error al guardar en la BBDD.");
										ComRFID.opActivarLed(false, (char)0xC8);
										luzRoja=true;
										repaint();
									}
									
									fase=esperandoLuzOn;
									
								}else{
									fase=esperandoUID;
									errorSelect=true;
									repaint();
								}
								
								
							}else{
								msEsperadosEntreFases+=1;
							}
							break;
							
							
						case esperandoLuzOn:
							
							if (ComRFID.discriminarTramaRespuesta(ComRFID.bufferEntrada[1])==ComRFID.ActivarLed){
								
								if (luzVerde){
	
									if (verbose) System.out.println("Luz verde encendida, fianlizando bucle principal");
									pararHiloTratamiento=true;

								}else {
									if (verbose) System.out.println("Luz roja encendida, pasando a espera de nuevo UID.");
									fase=esperandoUID;
								}
								
								
							}else{
								msEsperadosEntreFases+=1;
							}
							break;
		
					}
					
					ComRFID.semLectura.V();
					Thread.sleep(1);
					
				}
			
				if (verbose) System.out.println("Saliendo del bucle principal.");
				
				
			}
				
		}catch (Exception e){
			if (verbose) System.out.println("Ha ocurrido un error.");
			comRFID=null;
			errorSelect=true;
			repaint();
			e.printStackTrace();
		}finally{
			
			if (comRFID!=null && ComRFID.puertoValido!=null){ 
				if (verbose) System.out.println("Eliminando hilos RS232.");
				
				ComRFID.pararHiloEscritura();
				ComRFID.pararHiloLectura();
			
				if (verbose) System.out.println("Desconectando RS232.");
				try{
					ComRFID.disconnect();
					comRFID=null;
				}catch (Exception e) {
					e.printStackTrace();
				}
			    
				if (verbose) System.out.println("Redirigiendo a la pagina del olivicultor.");
			    AppletContext ac = getAppletContext();
				try {
					ac.showDocument(new URL(pagina+codigoOli),"_top");
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
    	
    	
    	
    }
	

}