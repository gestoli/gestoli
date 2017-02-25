/**
 * SeguretatInterceptor.java
 *
 */
package es.caib.gestoli.front.spring.interceptor;


import java.text.StringCharacterIterator;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import es.caib.gestoli.logic.interfaces.OliUsuariEjb;
import es.caib.gestoli.logic.model.Usuari;


/**
 * Interceptor que avalua els paràmetres passats i n'elimina els caràcters invàlids
 * perquè no es pugui fer Cross Site Scripting ni HTTP request spliting
 * 
 * @author Joan Galmés <joanga@limit.es>
 *
 */
public class SeguretatInterceptor extends HandlerInterceptorAdapter {

	private HibernateTemplate hibernateTemplate;

	private String rolAdministracio;
	private String rolDoGestor;
	private String rolDoControl;
	private String rolOlivicultor;
	private String rolProductor;
	private String rolEnvasador;
	private String rolEstAdministrador;
	private String rolEstEncarregat;
	private String rolEstTreballador;
	private String rolEstConsulta;
	
	private OliUsuariEjb oliUsuariEjb;


	/**
	 * Mètode callback que es crida cada vegada que arriba una
	 * petició.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler)
	throws Exception {
		Enumeration paramNames = request.getParameterNames();
		
	    while(paramNames.hasMoreElements()) {
	      String paramName = (String)paramNames.nextElement();
	      String[] paramValues = request.getParameterValues(paramName);
	      
	      for(int i = 0; i<paramValues.length; i++){
	    	  if(isInvalidText(paramValues[i])){
	    		  paramValues[i] = encodeHTML(paramValues[i]);
	    	  }
	      }
	    }
	    
	    //Seguretat tafones i envasadores
	    if(request.isUserInRole(rolEnvasador) || request.isUserInRole(rolProductor)){
	    	Long idEstabliment = null;
	    	if(request.getParameter("idEstabliment") != null){
	    		idEstabliment = Long.parseLong(request.getParameter("idEstabliment"));
	    	} else if(request.getParameter("establimentId") != null){
	    		idEstabliment = Long.parseLong(request.getParameter("establimentId"));
	    	}
	    	
	    	oliUsuariEjb.setHibernateTemplate(getHibernateTemplate());
	    	Usuari u = oliUsuariEjb.verificaLogin();
	    	
	    	if(idEstabliment != null){
	    		if(!oliUsuariEjb.verificaEstabliment(idEstabliment, u.getId())){
	    			throw new Exception("ACCES DENIED");
	    		}
	    	}
	    }
	    
	    //Seguretat Olivicultors
	    if(request.isUserInRole(rolOlivicultor)){
	    	oliUsuariEjb.setHibernateTemplate(getHibernateTemplate());
	    	Usuari u = oliUsuariEjb.verificaLogin();
	    	
	    	if(request.getServletPath().equals("/OlivicultorForm.html")){
	    		Long idOlivicultor = Long.parseLong(request.getParameter("id"));
	    		
	    		if(!oliUsuariEjb.verificaOlivicultor(idOlivicultor, u.getId())){
	    			throw new Exception("ACCES DENIED");
	    		}
	    	}
	    }

		return true;
	}
	
	


	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Enumeration paramNames = request.getParameterNames();
		
	    while(paramNames.hasMoreElements()) {
	      String paramName = (String)paramNames.nextElement();
	      String[] paramValues = request.getParameterValues(paramName);
	      
	      for(int i = 0; i<paramValues.length; i++){
	    	  paramValues[i] = decodeHTML(paramValues[i]);
	      }
	    }
	}




	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}

	
	/**
	 * get the hibernate template.
	 * @return the hibernate spring template.
	 */
	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}


	/**
	 * Funció que substitueix els caràcters especials per la seva codificació HTML
	 * 
	 * @param s
	 * @return
	 */
	private static String encodeHTML(String s) {
	    final StringBuffer result = new StringBuffer();
	    final StringCharacterIterator iterator = new StringCharacterIterator(s);
	    char character = iterator.current();
	    while(character != StringCharacterIterator.DONE){
	    	if(character == '<'){
	    		result.append("&lt;");
	    	} else if(character == '>'){
	    		result.append("&gt;");
	    	} else if(character == '\"'){
	    		result.append("&quot;");
	    	} else if(character == '\''){
	    		result.append("&#039;");
	    	} else if(character == '\\'){
	    		result.append("&#092;");
	    	} else if(character == '&'){
	    		result.append("&amp;");
	    	} else {
	    		//the char is not a special one
	    		//add it to the result as is
	    		result.append(character); 
	    	}
	    	character = iterator.next();
	    }
	    
	    return result.toString();
	}
	
	private static String decodeHTML(String s){
		String resultat = s;
		resultat = resultat.replaceAll("$lt;", "<");
		resultat = resultat.replaceAll("&gt;", ">");
		resultat = resultat.replaceAll("&quot;", "\"");
		resultat = resultat.replaceAll("&amp;", "&");
		resultat = resultat.replaceAll("&#039;", "\'");
		resultat = resultat.replaceAll("&#092;", "\\");
		return resultat;
	}

	
	/**
	 * Funció que ens retorna si un texte és vàlid o no.
	 * Serà vàlid si no hi ha caràcters especials.
	 * 
	 * @param s
	 * @return
	 */
	private static Boolean isInvalidText(String s){
	    for(int i=0; i<s.length(); i++){
	        char c = s.charAt(i);
	        if(c > 127 || c=='"' || c=='<' || c=='>') {
	           return true;
	        }
	    }
	    return false;
	}


	public String getRolAdministracio() {
		return rolAdministracio;
	}


	public void setRolAdministracio(String rolAdministracio) {
		this.rolAdministracio = rolAdministracio;
	}


	public String getRolDoGestor() {
		return rolDoGestor;
	}


	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}


	public String getRolDoControl() {
		return rolDoControl;
	}


	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
	}


	public String getRolOlivicultor() {
		return rolOlivicultor;
	}


	public void setRolOlivicultor(String rolOlivicultor) {
		this.rolOlivicultor = rolOlivicultor;
	}


	public String getRolProductor() {
		return rolProductor;
	}


	public void setRolProductor(String rolProductor) {
		this.rolProductor = rolProductor;
	}


	public String getRolEnvasador() {
		return rolEnvasador;
	}


	public void setRolEnvasador(String rolEnvasador) {
		this.rolEnvasador = rolEnvasador;
	}

	
	public String getEstAdministrador() {
		return rolEstAdministrador;
	}


	public void setRolEstAdministrador(String rolEstAdministrador) {
		this.rolEstAdministrador = rolEstAdministrador;
	}
	
	
	
	public String getRolEstEncarregat() {
		return rolEstEncarregat;
	}




	public void setRolEstEncarregat(String rolEstEncarregat) {
		this.rolEstEncarregat = rolEstEncarregat;
	}




	public String getRolEstAdministrador() {
		return rolEstAdministrador;
	}




	public String getRolEstTreballador() {
		return rolEstTreballador;
	}


	public void setRolEstTreballador(String rolEstTreballador) {
		this.rolEstTreballador = rolEstTreballador;
	}
	
	
	public String getRolEstConsulta() {
		return rolEstConsulta;
	}


	public void setRolEstConsulta(String rolEstConsulta) {
		this.rolEstConsulta = rolEstConsulta;
	}
	

	public OliUsuariEjb getOliUsuariEjb() {
		return oliUsuariEjb;
	}


	public void setOliUsuariEjb(OliUsuariEjb oliUsuariEjb) {
		this.oliUsuariEjb = oliUsuariEjb;
	}

	
	
}