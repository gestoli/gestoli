/**
 * EstablimentValidator.java
 * 
 */
package es.caib.gestoli.front.spring;


import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.TipusEstabliment;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 * 
 * @author Carlos Pérez(cperez@at4.net)
 */
public class EstablimentValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(EstablimentValidator.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private Integer tipusEstablimentTafona;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;
	private String rolProductor;
	private String rolEnvasador;

	private HibernateTemplate hibernateTemplate;
	


	/**
	 * Indica las clases que soporta este validador.
	 *
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(EstablimentCommand.class);
	}

	/**
	 * Valida el objeto.
	 *
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {

		
//		ValidationUtils.rejectIfEmpty(
//				errors,
//				"usuariId",
//				"error.usuari.buit",
//				"El camp usuari no pot estar buit");
		
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"nom",
				"error.nom.buit",
				"El camp contrasenya no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"cif",
				"error.cif.buit",
				"El camp CIF no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"email",
				"error.email.buit",
				"El camp email no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"tipusEstablimentId",
				"error.tipusEstablimentId.buit",
				"El camp Tipus d'establiment no pot estar buit");

		ValidationUtils.rejectIfEmpty(
				errors,
				"nombreSolicitant",
				"error.nombreSolicitant.buit",
				"El camp Nom sol·licitant no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"unitatMesura",
				"error.unitatMesura.buit",
				"El camp Unitat de mesura sol·licitant no pot estar buit");

		EstablimentCommand establiment = (EstablimentCommand) obj;
		
//		if(establiment.getUsuariId()!= null){
//			Usuari usuario = new Usuari();
//			usuario.setId(establiment.getUsuariId());
//			establiment.setUsuari(usuario);
//		}
		
		if (establiment.getTipusEstablimentId()!=null){
			TipusEstabliment tipoEstablecimiento = new TipusEstabliment();
			tipoEstablecimiento.setId(new Integer(establiment.getTipusEstablimentId().intValue()));
			establiment.setTipusEstabliment(tipoEstablecimiento);
		}
		
		
		boolean esEmail = true;
		if(establiment.getEmail()!= null && !establiment.getEmail().equalsIgnoreCase("")){
			esEmail = Util.isEmail(establiment.getEmail());			
		}
		boolean esTelefono = true;
		if(establiment.getTelefon()!= null && !establiment.getTelefon().equals("")){
			esTelefono = Util.isNumerico(establiment.getTelefon());
		}
		boolean esFax = true;
		if(establiment.getFax()!= null && !establiment.getFax().equals("")){
			esFax = Util.isNumerico(establiment.getFax());
		}
		boolean esCpostal = true;
		if(establiment.getCodiPostal()!= null && !establiment.getCodiPostal().equals("")){
			esCpostal = Util.isNumerico(establiment.getCodiPostal());
		}
		
		boolean esTelefonoSolicitante = true;
		if(establiment.getTelefonSolicitant()!= null && !establiment.getTelefonSolicitant().equals("")){
			esTelefonoSolicitante = Util.isNumerico(establiment.getTelefonSolicitant()); 
		}
		boolean esNif = true;
		if(establiment.getNifSolicitant()!= null && !establiment.getNifSolicitant().equals("") ){
			esNif = Util.isNif(establiment.getNifSolicitant());
		}
// 		// Validamos que si es envasadora o tafona/envasadora tiene una marca asociada
//		boolean envasadoraValidaMarca = true; 
//		if(establiment.getTipusEstabliment()!= null && ( establiment.getTipusEstabliment().getId().intValue() == tipusEstablimentEnvasadora.intValue() || establiment.getTipusEstabliment().getId().intValue() == tipusEstablimentTafonaEnvasadora.intValue()) ){
//			if(establiment.getMarcasArray()== null || establiment.getMarcasArray().length == 0){
//				envasadoraValidaMarca = false;
//			}									
//		}
		// Validamos si RB (codi de tafona) está marcado
		boolean codiRB = true;
		boolean obligatoriCodiRB = false;
		if (establiment.getTipusEstabliment()!= null && (establiment.getTipusEstabliment().getId().intValue() == tipusEstablimentTafona.intValue() || establiment.getTipusEstabliment().getId().intValue() == tipusEstablimentTafonaEnvasadora.intValue()) ) {
			obligatoriCodiRB = true;
			if (establiment.getCodiRB() == null || establiment.getCodiRB().equals("")) {
				codiRB = false;
			}
		}
		// Validamos si RC (codi de envasadora) está marcado
		boolean codiRC = true;
		boolean obligatoriCodiRC = false;
		if (establiment.getTipusEstabliment()!= null && (establiment.getTipusEstabliment().getId().intValue() == tipusEstablimentEnvasadora.intValue() || establiment.getTipusEstabliment().getId().intValue() == tipusEstablimentTafonaEnvasadora.intValue()) ) {
			obligatoriCodiRC = true;
			if (establiment.getCodiRC() == null || establiment.getCodiRC().equals("")) {
				codiRC = false;
			}
		}
		

		try {
			
			if (establiment.getSuperficie()!=null && establiment.getSuperficie().doubleValue()<=0){
				errors.rejectValue("superficie", "error.superficie.negativa" ,"La superficie ha de ser un valor positiu");
			}else{
				ValidationUtils.rejectIfEmpty(
						errors,
						"superficie",
						"error.superficie.buit",
						"El camp Superficie no pot estar buit");	
			}
			
			if (establiment.getCapacitatProduccio()!=null && establiment.getCapacitatProduccio().doubleValue()<=0){
				errors.rejectValue("capacitatProduccio", "error.capacitatProduccio.negativa" ,"La capacitat de produccio ha de ser un valor positiu");
			}else{
				ValidationUtils.rejectIfEmpty(
						errors,
						"capacitatProduccio",
						"error.capacitatProduccio.buit",
						"El camp Capacitat de producció no pot estar buit");	
			}
			
			
//			// Validamos que el tipo de usuario este acorde con el tipo de establecimiento
//			boolean esEnv=false;
//			boolean esPro=false;
//			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//			Usuari usu = oliInfraestructuraEjb.usuariAmbId(establiment.getUsuariId());
//			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//			Iterator iteGrupos = usu.getGrups().iterator();
//			while (iteGrupos.hasNext()){
//				Grup g = (Grup)iteGrupos.next();
//				if (g.getId().equals(rolEnvasador)){
//					esEnv=true;
//				}
//				if (g.getId().equals(rolProductor)){
//					esPro=true;
//				}
//			}
//			
//			if (establiment.getTipusEstabliment().getId().equals(tipusEstablimentEnvasadora) && !esEnv ){
//				errors.rejectValue("tipusEstablimentId", "error.establiment.rol" ,"No es pot asignar un tipus d'establiment incompatible amb l'usuari.");
//			}
//			if (establiment.getTipusEstabliment().getId().equals(tipusEstablimentTafona) && !esPro ){
//				errors.rejectValue("tipusEstablimentId", "error.establiment.rol" ,"No es pot asignar un tipus d'establiment incompatible amb l'usuari.");
//			}
//			if (establiment.getTipusEstabliment().getId().equals(tipusEstablimentTafonaEnvasadora) && !(esEnv && esPro) ){
//				errors.rejectValue("tipusEstablimentId", "error.establiment.rol" ,"No es pot asignar un tipus d'establiment incompatible amb l'usuari.");
//			}	
//			
//			// Validamos que el usuario no  ha sido asignado a ningun otro estrablecimiento
//			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//			if ( establiment.getUsuariId()!= null && oliInfraestructuraEjb.usuarioAsignadoEstablecimiento(establiment.getCampanyaId(), establiment.getUsuariId(),establiment.getId())){
//				errors.rejectValue("usuariId", "error.usuari.repetitEstabliment" ,"L'usuari ja ha estat assignat a un altre establiment");
//			}
			// Validamos que el nombre del establecimiento y su cif  no  existen ya
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (establiment.getCif()!= null && establiment.getNom()!= null && oliInfraestructuraEjb.establimentExisteCifName(establiment.getCampanyaId(), establiment.getCif(), establiment.getNom(), establiment.getId())){
				errors.rejectValue("nom", "error.establimentnom.repetit" ,"El nom del establiment ja existeix");
			}
			// Validamos que el CodiRB del establecimiento no existe ya
			if (!codiRB){
				errors.rejectValue("codiRB", "error.codiRB.buit", "El camp Codi RB no pot estar buit");
			} else {
				if (obligatoriCodiRB) {
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					if (oliInfraestructuraEjb.existeCodiRB(establiment.getCampanyaId(), establiment.getCodiRB(), establiment.getId())) {
						errors.rejectValue("codiRB", "error.codiRB.repetit", "El camp Codi RB ja existeix");
					}
				}
			}
			// Validamos que el CodiRC del establecimiento no existe ya
			if (!codiRC){
				errors.rejectValue("codiRC", "error.codiRC.buit", "El camp Codi RC no pot estar buit");
			} else {
				if (obligatoriCodiRC) {
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					if (oliInfraestructuraEjb.existeCodiRC(establiment.getCampanyaId(), establiment.getCodiRC(), establiment.getId())) {
						errors.rejectValue("codiRC", "error.codiRC.repetit", "El camp Codi RC ja existeix");
					}
				}
			}
//			//Validamos que si es envasadora o tafona/envasadora tiene una marca asociada
//			if (!envasadoraValidaMarca){
//				errors.rejectValue("marcasArray", "error.marcasArray.buit" ,"Ha de seleccionar almenys una marca d'oli");
//			}
			// Validamos formatos
			if (!esEmail){
				errors.rejectValue("email", "error.email.incorrecto" ,"Escrigui el email correctament");
			}
			if (!esTelefono){
				errors.rejectValue("telefon", "error.telefon.incorrecto" ,"Escrigui el telèfon correctament");
			}
			
			if (!esTelefonoSolicitante){
				errors.rejectValue("telefonSolicitant", "error.telefon.incorrecto" ,"Escrigui el telèfon correctament");
			}
			if (!esFax){
				errors.rejectValue("fax", "error.fax.incorrecto" ,"Escrigui el fax correctament");
			}
			if (!esCpostal){
				errors.rejectValue("codiPostal", "error.codiPostal.incorrecto" ,"Escrigui el codi postal correctament");
			}
			if (!esNif){
				errors.rejectValue("nifSolicitant", "error.nifSolicitant.incorrecto" ,"Escrigui el nif correctament");
			}
			
			//Validam poblacio
			ValidationUtils.rejectIfEmpty(
					errors,
					"municipiId",
					"error.poblacio.buit",
					"El camp localitat  no pot estar buit");
			
			//Validam responsable
			ValidationUtils.rejectIfEmpty(
					errors,
					"nomResponsable",
					"error.cif.buit",
					"El camp CIF no pot estar buit");
			
			ValidationUtils.rejectIfEmpty(
					errors,
					"cifResponsable",
					"error.nom.buit",
					"El camp Nom no pot estar buit");
			
			//Si hi ha dni o cif el validam
			if(establiment.getCifResponsable() != null){
				if( !isValidCif(establiment.getCifResponsable()) && 
					!isValidNIF(establiment.getCifResponsable())){
					errors.rejectValue("cifResponsable", "error.cifnif.erroni" ,"Escrigui el nif correctament");
				}
			}
			
			//Validam el diposit fictici
			
			//NO esta buit
			ValidationUtils.rejectIfEmpty(
					errors,
					"nomDipositFictici",
					"error.codigo.buit",
					"El camp Codigo no pot estar buit");
			
			//Mirem si el codi està duplicat
			try {
				
				List dipositsEstabliment = oliInfraestructuraEjb.findDipositsByEstabliment(establiment.getId());
				Diposit dipFictici = oliInfraestructuraEjb.findDipositFicticiByEstabliment(establiment.getId());
				
				if(dipFictici != null){
					for(Object o: dipositsEstabliment){
						Diposit dipEst = (Diposit)o;
	
						if(dipFictici.getCodiAssignat().equalsIgnoreCase(dipEst.getCodiAssignat()) && 
						   (dipFictici.getId() == null || !dipFictici.getId().equals(dipEst.getId())) ){
							errors.rejectValue(
								"nomDipositFictici",
								"error.diposit.duplicat",
								"El codi del dipòsit ja existeix");
						}
						
					}
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		} catch (Exception ex) {
			logger.error("Error validando la establiment. EstablimentValidator", ex);

		}

	}
	
	 /**
     * Injecció de la dependència tipusEstablimentTafona
     *
     * @param tipusEstablimentTafona La classe a injectar.
     */
    public void setTipusEstablimentTafona(Integer tipusEstablimentTafona) {
        this.tipusEstablimentTafona = tipusEstablimentTafona;
    }
    /**
     * Injecció de la dependència tipusEstablimentEnvasadora
     *
     * @param tipusEstablimentEnvasadora La classe a injectar.
     */
    public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
        this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
    }
    
    /**
     * Injecció de la dependència tipusEstablimentTafonaEnvasadora
     *
     * @param tipusEstablimentTafonaEnvasadora La classe a injectar.
     */
    public void setTipusEstablimentTafonaEnvasadora(Integer tipusEstablimentTafonaEnvasadora) {
        this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
    }
    
    /**
     * Injecció de la dependència oliInfraestructuraEjb
     *
     * @param oliInfraestructuraEjb La classe a injectar.
     */
    
    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
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


	public void setRolProductor(String rolProductor) {
		this.rolProductor = rolProductor;
	}

	public void setRolEnvasador(String rolEnvasador) {
		this.rolEnvasador = rolEnvasador;
	}

	public String getRolProductor() {
		return rolProductor;
	}

	public String getRolEnvasador() {
		return rolEnvasador;
	}

	private boolean isValidNIF(String nif){
		int LONGITUD_NIF = 9;
		String ORDRE_LLETRES = "TRWAGMYFPDXBNJZSQVHLCKE";
		   
		boolean isValid = false;
	     
	     if ((nif != null) && (nif.length() == LONGITUD_NIF)) {
	       
	       try
	       {
	         Integer numero = new Integer(nif.substring(0, LONGITUD_NIF - 1));
	         char lletra = nif.charAt(LONGITUD_NIF - 1);
	         isValid = (lletra == ORDRE_LLETRES.charAt(numero.intValue() % ORDRE_LLETRES.length()));
	       } catch (NumberFormatException e) {}
	       
	     }
	 
	     return isValid;
	}
	
	private boolean isValidCif(String cif) {
		String LLETRES_VALIDES = "ABCDEFGHJKLMNPRQSUVW";
		String LLETRES_VALIDES_NUMERO = "ABEH";
		String LLETRES_VALIDES_LLETRA = "KPQS";
		Map<Character, Integer> RELACIO_LLETRES_NUMEROS = new HashMap<Character, Integer>();
		
		RELACIO_LLETRES_NUMEROS.put('J', 0);
		RELACIO_LLETRES_NUMEROS.put('A', 1);
		RELACIO_LLETRES_NUMEROS.put('B', 2);
		RELACIO_LLETRES_NUMEROS.put('C', 3);
		RELACIO_LLETRES_NUMEROS.put('D', 4);
		RELACIO_LLETRES_NUMEROS.put('E', 5);
		RELACIO_LLETRES_NUMEROS.put('F', 6);
		RELACIO_LLETRES_NUMEROS.put('G', 7);
		RELACIO_LLETRES_NUMEROS.put('H', 8);
		RELACIO_LLETRES_NUMEROS.put('I', 9);
		RELACIO_LLETRES_NUMEROS.put('J', 10);
		
		try {
			int par = 0;
			int non = 0;

			int parcial;
			Integer control;
			
			String letraIni = String.valueOf(cif.charAt(0));

			if (cif.length() != 9) {
				return false;
			}
			if (LLETRES_VALIDES.indexOf(letraIni.toUpperCase()) == -1) {
				return false;
			}

			for (int i = 2; i < 8; i += 2) {
				par = par + Integer.parseInt(String.valueOf(cif.charAt(i)));
			}

			for (int i = 1; i < 9; i += 2) {
				int nn = 2 * Integer.parseInt(String.valueOf(cif.charAt(i)));
				if (nn > 9) {
					nn = 1 + (nn - 10);
				}
				non = non + nn;
			}

			parcial = par + non;

			control = (10 - (parcial % 10));

			if (LLETRES_VALIDES_LLETRA.indexOf(letraIni.toUpperCase()) != -1) {
				if (!RELACIO_LLETRES_NUMEROS.get(cif.charAt(8)).equals(control)) {
					return false;
				}
			}
			if (LLETRES_VALIDES_NUMERO.indexOf(letraIni.toUpperCase()) != -1) {

				if (control == 10)
					control = 0;

				if (!control.equals(Integer.parseInt(String.valueOf(cif
						.charAt(8))))) {
					return false;
				}
			}
			if ((LLETRES_VALIDES_LLETRA.indexOf(letraIni.toUpperCase()) == -1)
					&& (LLETRES_VALIDES_NUMERO.indexOf(letraIni.toUpperCase()) == -1)) {
				if (control == 10) {
					control = 0;
				}
				if (!(String.valueOf(RELACIO_LLETRES_NUMEROS.get(control))
						.equals(String.valueOf(cif.charAt(8)).toUpperCase()))
						&& (!control.equals(Integer.parseInt(String.valueOf(cif
								.charAt(8)))))) {
					return false;
				}
			}

			return true;
		} catch (NumberFormatException e) {
			return false;
		} catch (StringIndexOutOfBoundsException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	private boolean isValidNIE(String nie){
		boolean isValid = false;
	     
	     if ((nie != null) && (nie.length() > 0) && (nie.charAt(0) == 'X')) {
	       isValid = isValidNIF(nie.substring(1));
	     }
	     
	     return isValid;

	}
}
