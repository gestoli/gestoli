/**
 * ProcesElaboracioOliValidator.java
 *
 * Creada el 19 de juny de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.PartidaOliva;

/**
 * Validador para los campos asociados con el formulario de creaciÃ³n/ediciÃ³n.
 */
public class ProcesElaboracioOliValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(ProcesElaboracioOliValidator.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
//	private String desqualificat;
//    private String pendentQualificar;
    
	private HibernateTemplate hibernateTemplate;
	
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(ProcesElaboracioOliCommand.class);
	}
	

	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		ProcesElaboracioOliCommand elaboracioOliCommand = (ProcesElaboracioOliCommand) obj;
		
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"data",
				"error.dataElaboracio.buit",
				"El camp Data elaboracio no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"responsable",
				"error.responsable.buit",
				"El camp nom responsable no pot estar buit");
		
		if (elaboracioOliCommand.getDiposits() != null && elaboracioOliCommand.getDiposits().length > 0){
			ValidationUtils.rejectIfEmpty(
					errors,
					"idPartidaOli",
					"error.nom.partida.buit",
					"El camp nom de la partida no pot estar buit");
					
			ValidationUtils.rejectIfEmpty(
					errors,
					"idCategoriaOli",
					"error.varietat.buit",
					"El camp categoria no pot estar buit");
		}
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"acidesa",
				"error.acidesa.buit",
				"El camp acidesa no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"temperatura",
				"error.temperatura.buit",
				"El camp temperatura no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"talcQuantitat",
				"error.talcQuantitat.buit",
				"El camp talc quantitat no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"talcMarcaComercial",
				"error.talcMarcaComercial.buit",
				"El camp talc marca no pot estar buit");
		
		ValidationUtils.rejectIfEmpty(
				errors,
				"talcLot",
				"error.talcLot.buit",
				"El camp talc lot no pot estar buit");
		
		if (elaboracioOliCommand.getEstabliment().getUnitatMesura().equals("l")) {
			ValidationUtils.rejectIfEmpty(
					errors,
					"totalLitrosOli",
					"error.totalLitros.buit",
					"El camp del total de litros no pot estar buit");
		} else {
			ValidationUtils.rejectIfEmpty(
					errors,
					"totalKilosOli",
					"error.totalKilos.buit",
					"El camp del total de kilos no pot estar buit");
		}
		
		try {
			Diposit diposit = null;
			Double total = 0.0;
			if (elaboracioOliCommand.getDiposits().length > 0) { // S'ha ficat oli dins els diposits
				for (int i = 0; i < elaboracioOliCommand.getDiposits().length; i++) {
					if (elaboracioOliCommand.getEstabliment().getUnitatMesura().equals("l")) {
						if (elaboracioOliCommand.getLitros()[i] != null && elaboracioOliCommand.getLitros()[i].doubleValue()<0){
							errors.rejectValue("litros["+i+"]", "error.litros.negativo","El numero de litres introduits ha de ser major que 0");
						}else{
							ValidationUtils.rejectIfEmpty(
									errors,
									"litros["+i+"]",
									"error.litros.buit",
									"S'ha d'introduir la quantitat introduïda dins el dipòsit");
							if (elaboracioOliCommand.getLitros()[i] != null){
								total += elaboracioOliCommand.getLitros()[i].doubleValue();
							}
						}
					}else{
						if (elaboracioOliCommand.getKilos()[i] != null && elaboracioOliCommand.getKilos()[i].doubleValue()<0){
							errors.rejectValue("kilos["+i+"]", "error.kilos.negativo","El numero de kilos introduits ha de ser major que 0");
						}else{
							ValidationUtils.rejectIfEmpty(
									errors,
									"kilos["+i+"]",
									"error.litros.buit",
									"S'ha d'introduir la quantitat introduïda dins el dipòsit");
							if (elaboracioOliCommand.getKilos()[i] != null){
								total += elaboracioOliCommand.getKilos()[i].doubleValue();
							}
						}
						
					}

					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					diposit = oliInfraestructuraEjb.dipositAmbId(elaboracioOliCommand.getDiposits()[i].getId());
					if (diposit.getCapacitat() != null) {
						double capacitatActual = diposit.getCapacitat().doubleValue() - (diposit.getVolumActual() != null?diposit.getVolumActual().doubleValue():new Double(0).doubleValue());
						
						if ((elaboracioOliCommand.getLitros()[i]!=null?elaboracioOliCommand.getLitros()[i].doubleValue():new Double(0).doubleValue()) > capacitatActual) {
							Object[] valor = new Object[1];
							if (elaboracioOliCommand.getEstabliment().getUnitatMesura().equals("l")) {
								valor[0] =  String.valueOf(capacitatActual);
								errors.rejectValue("litros["+i+"]", "error.litros.incorrecto", valor,"Els litres que es volen introduÃ¯r superen els que hi caben actualment: {0}");
							}else{
								valor[0] =  String.valueOf(capacitatActual * 0.916);
								errors.rejectValue("kilos["+i+"]", "error.kilos.incorrecto", valor,"Els quilos que es volen introduÃ¯r superen els que hi caben actualment: {0}");
							}
						}
						
					}
				}
			
			} else { // L'olivicultor es queda tot l'oli
				if(elaboracioOliCommand.getObservacions()== null || (elaboracioOliCommand.getObservacions()!= null && elaboracioOliCommand.getObservacions().equals(""))){
					errors.rejectValue("observacions", "error.observacions.buit" ,"Si l'olivicultor es queda tot l'oli ha d'indicar el codi del propietari");
				}
			}
			
			// Hem de comprovar tot l'oli retirat pels olivicultors
			for(int i = 0; i < elaboracioOliCommand.getIdOlivicultors().length; i++){
				if (elaboracioOliCommand.getEstabliment().getUnitatMesura().equals("l")) {
					ValidationUtils.rejectIfEmpty(
							errors,
							"litrosRetirats[" + i + "]",
							"error.oliRetirat.buit",
							"Ha d'indicar la quantitat d'oli que es queda el propietari");
					if (elaboracioOliCommand.getLitrosRetirats()[i] != null){
						total += elaboracioOliCommand.getLitrosRetirats()[i].doubleValue();
					}
				} else if (elaboracioOliCommand.getEstabliment().getUnitatMesura().equals("k")) {
					ValidationUtils.rejectIfEmpty(
							errors,
							"kilosRetirats[" + i + "]",
							"error.oliRetirat.buit",
							"Ha d'indicar la quantitat d'oli que es queda el propietari");
					if (elaboracioOliCommand.getKilosRetirats()[i] != null){
						total += elaboracioOliCommand.getKilosRetirats()[i].doubleValue();
					}
				}
			}
			
			// Ja tenim el total. Hem de comprovar que sigui igual al que han entrat a totalLitrosOli
			if (elaboracioOliCommand.getEstabliment().getUnitatMesura().equals("l")) {
				if (!elaboracioOliCommand.getTotalLitrosOli().equals(total)){
					errors.rejectValue("totalLitrosOli", "error.total.incorrecte" ,"La suma de la quantitat dels dipòsits més la dels olivicultors ha de ser igual a la total indicada");
				}
			} else {
				if (!elaboracioOliCommand.getTotalKilosOli().equals(total)){
					errors.rejectValue("totalKilosOli", "error.total.incorrecte" ,"La suma de la quantitat dels dipòsits més la dels olivicultors ha de ser igual a la total indicada");
				}
			}
			
			// Comprovam que les partides d'oliva es corresponguin amb la varietat escollida
			if (elaboracioOliCommand.getIdCategoriaOli()!= null && elaboracioOliCommand.getIdCategoriaOli()!= 1){
				boolean qualificada = true;
				for (PartidaOliva pao : elaboracioOliCommand.getPartides()){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					PartidaOliva partida = oliInfraestructuraEjb.partidaAmbId(pao.getId());
					if (!partida.getQualificada()){
						qualificada = false;
						break;
					}
				}
				if (qualificada){
					for (Diposit dip : elaboracioOliCommand.getDiposits()){
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						Diposit d = oliInfraestructuraEjb.dipositAmbId(dip.getId());
						if (d.getVolumActual() != null && d.getVolumActual() > 0 && !d.getPartidaOli().getEsQualificada()){
								qualificada = false;
								break;
						}
					}
				}
				if (!qualificada){
				errors.rejectValue(
						"idCategoriaOli", 
						"error.varietat.descomposicio.no.qualificada" ,
						"La varietat només pot ser No DO, ja que el contingut dels contenidors conté varietats no autoritzades");
				}
			}
			
			//COMPROVEM DATA EXECUCIO
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			
			Diposit[] diposits = elaboracioOliCommand.getDiposits();
			PartidaOliva[] partides = elaboracioOliCommand.getPartides();
			
			boolean correcte = true;
			
			//Comprovacio dipòsits
			if(diposits != null && diposits.length > 0){
				for(int i=0; i<diposits.length; i++) {
					Diposit d = diposits[i];
					if(!oliInfraestructuraEjb.esDataDipositCorrecte(elaboracioOliCommand.getData(), d.getId())){
						correcte = false;
					}
				}
			}
			
			//Comprovació partida oliva
			if(partides != null && diposits.length>0){
				for(int i=0; i<partides.length; i++){
					PartidaOliva po = partides[i];
					if(!oliInfraestructuraEjb.esDataPartidaOlivaCorrecte(elaboracioOliCommand.getData(), po.getId())){
						correcte = false;
					}
				}
			}
			
			//Comprovació campanya
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
			if(!oliInfraestructuraEjb.esDataCampanyaCorrecte(elaboracioOliCommand.getData())){
				correcte = false;
			}
			
			if(!correcte){
				errors.rejectValue(
						"data",
						"error.dataExecucio.posterior",
						"Hi ha processos posteriors a la data introduida");
			}
		} catch (Exception ex) {
			logger.error("Exception", ex);
		}
		
	}

//	public void setDesqualificat(String desqualificat) {
//		this.desqualificat = desqualificat;
//	}
//
//	public void setPendentQualificar(String pendentQualificar) {
//		this.pendentQualificar = pendentQualificar;
//	}

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

	
}