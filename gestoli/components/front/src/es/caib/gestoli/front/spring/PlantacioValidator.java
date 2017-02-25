/**
 * PlantacioValidator.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;


import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Finca;

/**
 * Validador para los campos asociados con el formulario de creación/edición.
 */
public class PlantacioValidator implements Validator {
	private static Logger logger = Logger.getLogger(PlantacioValidator.class);

	private OliInfraestructuraEjb oliInfraestructuraEjb;

	private HibernateTemplate hibernateTemplate;
	
	public PlantacioValidator(){
	}
	
	/**
	 * Indica las clases que soporta este validador.
	 * @see Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return clazz.equals(PlantacioCommand.class);
	}

	
	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		validateOnFinish((PlantacioCommand) obj, errors);
	}
	
	
	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validateFirstPage(PlantacioCommand plantacio, Errors errors) {
		if ((plantacio.getPlantacioBaixaId() == null) || (plantacio.getPlantacioBaixaId().equals(""))) {
			ValidationUtils.rejectIfEmpty(
					errors,
					"anyPlantacio",
					"error.anyPlantacio.buit",
			"El camp Any de plantació no pot estar buit");
			
	
			ValidationUtils.rejectIfEmpty(
					errors,
					"municipiId",
					"error.municipi.buit",
			"El camp Municipi no pot estar buit");
			
	
			ValidationUtils.rejectIfEmpty(
					errors,
					"poligon",
					"error.poligon.buit",
			"El camp Poligon no pot estar buit");
			
	
			ValidationUtils.rejectIfEmpty(
					errors,
					"parcela",
					"error.parcela.buit",
			"El camp Parcel·la no pot estar buit");
		
	
			ValidationUtils.rejectIfEmpty(
					errors,
					"idFinca",
					"error.idFinca.buit",
			"El camp Finca no pot estar buit");
		}
		
		Finca finca = null;
		if (plantacio.getIdFinca() != null && !plantacio.getIdFinca().equals("")){
			finca = new Finca();
			finca.setId(Long.valueOf(plantacio.getIdFinca()));
			plantacio.setFinca(finca);
		}
		

		boolean esFechaCuatroDigitos = true;
		if(plantacio.getAnyPlantacio()!= null) {
			esFechaCuatroDigitos = Util.isFechaCuatroDigitos(plantacio.getAnyPlantacio());			
		}
	
	
		try{
			
			if (!esFechaCuatroDigitos){
				errors.rejectValue("anyPlantacio", "error.anyPlantacio.incorrecto" ,"Escrigui el anyPlantacio correctament");
			}
			
			
		} catch (Exception ex) {
			logger.error("Error validando la plantacio. PlantacioValidator", ex);
		}
		
	}
	
	
	/**
	 * Valida el objeto.
	 * @see Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validateSecondPage(PlantacioCommand plantacio, Errors errors) {
		DescomposicioPlantacio[] descomposiciones = plantacio.getVariedades();
		if ((plantacio.getPlantacioBaixaId() == null) || (plantacio.getPlantacioBaixaId().equals(""))) {
			for (int i = 0; i < descomposiciones.length; i++){
				boolean numOliveresVacio = false;
				boolean superficieVacio = false;
				boolean maxProduccioVacio = false;
				
				DescomposicioPlantacio d = descomposiciones[i];
				
				if (d.getNumeroOliveres() == null || (d.getNumeroOliveres() != null && d.getNumeroOliveres().doubleValue() <= 0.0)) numOliveresVacio = true;
				if (d.getSuperficie() == null || (d.getSuperficie() != null && d.getSuperficie().doubleValue() <= 0.0)) superficieVacio = true;
				if (d.getMaxProduccio() == null || (d.getMaxProduccio() != null && d.getMaxProduccio().doubleValue() <= 0.0)) maxProduccioVacio = true;
				
				if (!((numOliveresVacio && superficieVacio && maxProduccioVacio) || (!numOliveresVacio && !superficieVacio && !maxProduccioVacio))) { // Fila incorrecta. Algún campo incompleto
					
					ValidationUtils.rejectIfEmpty(
							errors,
							"variedades["+i+"].numeroOliveres",
							"error.numeroOliveres.buit",
							"El camp no pot estar buit si hi ha algún camp emplenat");
					
					if (d.getNumeroOliveres() != null && d.getNumeroOliveres().doubleValue() <= 0.0) 
						errors.rejectValue("variedades["+i+"].numeroOliveres", "error.numeroOliveres.buit", "El camp no pot estar buit o ser cero si hi ha algún camp emplenat");
					
					ValidationUtils.rejectIfEmpty(
							errors,
							"variedades["+i+"].superficie",
							"error.superficie.buit",
							"El camp no pot estar buit si hi ha algún camp emplenat");
					
					if (d.getSuperficie() != null && d.getSuperficie().doubleValue() <= 0.0) 
						errors.rejectValue("variedades["+i+"].superficie", "error.superficieDescomposicio.buit", "El camp no pot estar buit o ser cero si hi ha algún camp emplenat");
					
					ValidationUtils.rejectIfEmpty(
							errors,
							"variedades["+i+"].maxProduccio",
							"error.maxProduccio.buit",
							"El camp no pot estar buit si hi ha algún camp emplenat");
					
					if (d.getMaxProduccio() != null && d.getMaxProduccio().doubleValue() <= 0.0) 
						errors.rejectValue("variedades["+i+"].maxProduccio", "error.maxProduccio.buit", "El camp no pot estar buit o ser cero si hi ha algún camp emplenat");
				}
				
				try {
				
					// Comprobamos que no se haya intentado borrar una descomposicion que tenia partidas asociadas
					DescomposicioPlantacio descomposicio = null;
					if (d.getPlantacio()!=null) {
						descomposicio = oliInfraestructuraEjb.descomposicioPlantacioByPlantacioIdVariedadId(d.getPlantacio().getId(), d.getVarietatOliva().getId());
						if (descomposicio != null) {
							Collection partides = oliInfraestructuraEjb.descomposicioPartidaOlivaByDescomposicioPlantacio(descomposicio.getId());
							if (partides != null && partides.size() > 0) { // Comprobamos si existen partidas asociadas
								if ((numOliveresVacio && superficieVacio && maxProduccioVacio) || (d.getNumeroOliveres() != null && d.getSuperficie() != null && d.getMaxProduccio() != null && d.getNumeroOliveres().equals(new Integer(0)) && d.getSuperficie().equals(new Double(0)) && d.getMaxProduccio().equals(new Double(0)))) { // Comprobamos que no se haya borrado la descomposicion asociada
									d.setMaxProduccio(descomposicio.getMaxProduccio());
									d.setNumeroOliveres(descomposicio.getNumeroOliveres());
									d.setSuperficie(descomposicio.getSuperficie());
									errors.rejectValue("variedades["+i+"].numeroOliveres", "descomposicioPlantacio.missatge.borrar.no", "No es pot eliminar la descomposicio perquè està associada a una partida d'oliva");
//									errors.rejectValue("variedades["+i+"].varietatOliva.id", "descomposicioPlantacio.missatge.borrar.no", "No es pot eliminar la descomposicio perquè està associada a una partida d'oliva");
								}
							}
						}
					}
					
				} catch (Exception ex) {
					logger.error("EXCEPTION", ex);
				}
			}
		}
	}
	
	
	public void validateOnFinish(PlantacioCommand plantacio, Errors errors){
		if ((plantacio.getPlantacioBaixaId() == null) || (plantacio.getPlantacioBaixaId().equals(""))) {
			Finca finca = null;
			if (plantacio.getIdFinca() != null && !plantacio.getIdFinca().equals("")){
				finca = new Finca();
				finca.setId(Long.valueOf(plantacio.getIdFinca()));
				plantacio.setFinca(finca);
			}
			
			DescomposicioPlantacio[] descomposiciones = plantacio.getVariedades();
			
			boolean tieneDescomposicion = false;
			
			for (int i = 0; i < descomposiciones.length; i++){
				DescomposicioPlantacio d = descomposiciones[i];
				
				if ((d.getNumeroOliveres() != null && d.getNumeroOliveres().doubleValue() != 0.0) && 
						(d.getSuperficie() != null && d.getSuperficie().doubleValue() != 0.0) && 
						(d.getMaxProduccio() != null && d.getMaxProduccio().doubleValue() != 0.0)) {
					tieneDescomposicion = true;
					break;
				}
			}
			
			if (!tieneDescomposicion) {
				errors.rejectValue("noDescomposicio", "descomposicioPlantacio.missatge.borrar.no", "No es pot eliminar la descomposicio perquè està associada a una partida d'oliva");
			}
		}
	}
	

	/**
	 * @return the hibernateTemplate
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * @param hibernateTemplate the hibernateTemplate to set
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * @param oliInfraestructuraEjb the oliInfraestructuraEjb to set
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	
}
