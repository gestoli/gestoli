/**
 * PlantacioCommand.java
 *
 * 
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.util.Collection;
import java.util.Iterator;

import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Plantacio;
import es.caib.gestoli.logic.model.VarietatOliva;



/**
 * Objeto que representa el formulario de mantenimento
 * de usuarios.
 * 
 *
 */
public class PlantacioCommand extends Plantacio {
	
	private static final long serialVersionUID = 4288771702181614740L;
	
	private String idFinca;
	private Long idVariedad;
	private Plantacio plantacio;
	private VarietatOliva varietatOliva;
	private Long idOriginal;
	private Integer numeroOliveres;
	private Double superficie;
	private Double maxProduccio;
	private String observacionsVariedad;
	private DescomposicioPlantacio[] variedades;
	private String fromFinca;
	private String nom;
	private String noDescomposicio;
	private Long municipiId;
	private String nomFinca;
	private Long plantacioBaixaId;
	//private String nomComplet;

	public PlantacioCommand(){ }
	
	public PlantacioCommand(int longitud){
		variedades = new DescomposicioPlantacio[longitud];
		for (int i=0; i<longitud; i++){
			variedades[i]=new DescomposicioPlantacio();
			variedades[i].setVarietatOliva(new VarietatOliva());
		}
	}
	
	/**
     * Rellena los campos de este objeto con la información
     * de la plantacio.
     * @param p Plantacio
     */
    public void fromPlantacio(Plantacio p, Collection variedades) {
    	setActiu(p.getActiu());
    	setAnyPlantacio(p.getAnyPlantacio());
    	setFinca(p.getFinca());
    	setId(p.getId());
    	setMunicipi(p.getMunicipi());
    	setObservacions(p.getObservacions());
    	setParcela(p.getParcela());
    	setPoligon(p.getPoligon());
    	setRegadiu(p.getRegadiu());
    	setDescomposicioPlantacios(p.getDescomposicioPlantacios());
    	setMunicipiId((p.getMunicipi()!=null)?p.getMunicipi().getId():null);
    	setNomFinca((p.getFinca()!=null)?p.getFinca().getNom():"");
    	
    	Collection descompVariedades = p.getDescomposicioPlantacios();
    	DescomposicioPlantacio[] arrayVariedades = new DescomposicioPlantacio[variedades.size()];
    	int i = 0;
    	for (Iterator it = variedades.iterator(); it.hasNext();){
    		VarietatOliva vo = (VarietatOliva)it.next();
    		DescomposicioPlantacio variedad = new DescomposicioPlantacio(p, vo, new Integer(0), new Double(0), new Double(0));
    		arrayVariedades[i] = variedad;
    		++i;
    	}
    	
    	for (Iterator it = descompVariedades.iterator(); it.hasNext();){
    		DescomposicioPlantacio desc = (DescomposicioPlantacio) it.next();
        	boolean encontrado = false;
    		int j = 0;
    		while (!encontrado && j < arrayVariedades.length){
    			DescomposicioPlantacio var = arrayVariedades[j];
    			if ((var.getVarietatOliva().getNom().equals(desc.getVarietatOliva().getNom()) && ((var.getVarietatOliva().getNomVarietat() == null) && (desc.getVarietatOliva().getNomVarietat() == null))) ||
    					((var.getVarietatOliva().getNomVarietat() != null) && (desc.getVarietatOliva().getNomVarietat() != null) && (var.getVarietatOliva().getNomVarietat().equals(desc.getVarietatOliva().getNomVarietat())))
        			) {
        			arrayVariedades[j].setNumeroOliveres(var.getNumeroOliveres().intValue() + desc.getNumeroOliveres().intValue());
    				arrayVariedades[j].setMaxProduccio(var.getMaxProduccio().doubleValue() + desc.getMaxProduccio().doubleValue());
    				arrayVariedades[j].setSuperficie(var.getSuperficie().doubleValue() + desc.getSuperficie().doubleValue());
    				encontrado = true;
    			}
    			j++;
    		}
    	}
    	
    	setVariedades(arrayVariedades);
    }
    
    /**
     * Rellena los campos de este objeto con la información
     * de la plantacio.
     * @param p Plantacio
     */
    public void fromPlantacio(Plantacio p, Collection variedades, Collection experimentals, Collection olivaTaula) {
    	setActiu(p.getActiu());
    	setAnyPlantacio(p.getAnyPlantacio());
    	setFinca(p.getFinca());
    	setId(p.getId());
    	setMunicipi(p.getMunicipi());
    	setObservacions(p.getObservacions());
    	setParcela(p.getParcela());
    	setPoligon(p.getPoligon());
    	setRegadiu(p.getRegadiu());
    	setDescomposicioPlantacios(p.getDescomposicioPlantacios());
    	setMunicipiId((p.getMunicipi()!=null)?p.getMunicipi().getId():null);
    	setNomFinca((p.getFinca()!=null)?p.getFinca().getNom():"");
    	
    	Collection descompVariedades = p.getDescomposicioPlantacios();
    	DescomposicioPlantacio[] arrayVariedades = new DescomposicioPlantacio[variedades.size() + experimentals.size() + olivaTaula.size()];
    	int i = 0;
    	for (Iterator it = variedades.iterator(); it.hasNext();){
    		VarietatOliva vo = (VarietatOliva)it.next();
    		DescomposicioPlantacio variedad = new DescomposicioPlantacio(p, vo, new Integer(0), new Double(0), new Double(0));
    		arrayVariedades[i] = variedad;
    		++i;
    	}
    	for (Iterator it = experimentals.iterator(); it.hasNext();){
    		VarietatOliva vo = (VarietatOliva)it.next();
    		DescomposicioPlantacio variedad = new DescomposicioPlantacio(p, vo, new Integer(0), new Double(0), new Double(0));
    		arrayVariedades[i] = variedad;
    		++i;
    	}
    	for (Iterator it = olivaTaula.iterator(); it.hasNext();){
    		VarietatOliva vo = (VarietatOliva)it.next();
    		DescomposicioPlantacio variedad = new DescomposicioPlantacio(p, vo, new Integer(0), new Double(0), new Double(0));
    		arrayVariedades[i] = variedad;
    		++i;
    	}
    	
    	for (Iterator it = descompVariedades.iterator(); it.hasNext();){
    		DescomposicioPlantacio desc = (DescomposicioPlantacio) it.next();
        	boolean encontrado = false;
    		int j = 0;
    		while (!encontrado && j < arrayVariedades.length){
    			DescomposicioPlantacio var = arrayVariedades[j];
    			if ((var.getVarietatOliva().getNom().equals(desc.getVarietatOliva().getNom()) && ((var.getVarietatOliva().getNomVarietat() == null) && (desc.getVarietatOliva().getNomVarietat() == null))) ||
    					((var.getVarietatOliva().getNomVarietat() != null) && (desc.getVarietatOliva().getNomVarietat() != null) && (var.getVarietatOliva().getNomVarietat().equals(desc.getVarietatOliva().getNomVarietat())))
        			) {
        			arrayVariedades[j].setNumeroOliveres(var.getNumeroOliveres().intValue() + desc.getNumeroOliveres().intValue());
    				arrayVariedades[j].setMaxProduccio(var.getMaxProduccio().doubleValue() + desc.getMaxProduccio().doubleValue());
    				arrayVariedades[j].setSuperficie(var.getSuperficie().doubleValue() + desc.getSuperficie().doubleValue());
    				encontrado = true;
    			}
    			j++;
    		}
    	}
    	
    	setVariedades(arrayVariedades);
    }

	public String getIdFinca() {
		return idFinca;
	}
	public void setIdFinca(String idFinca) {
		this.idFinca = idFinca;
	}

	public Long getIdVariedad() {
		return idVariedad;
	}
	public void setIdVariedad(Long idVariedad) {
		this.idVariedad = idVariedad;
	}

	public Long getIdOriginal() {
		return idOriginal;
	}
	public void setIdOriginal(Long idOriginal) {
		this.idOriginal = idOriginal;
	}

	public Double getMaxProduccio() {
		return maxProduccio;
	}
	public void setMaxProduccio(Double maxProduccio) {
		this.maxProduccio = maxProduccio;
	}

	public Integer getNumeroOliveres() {
		return numeroOliveres;
	}
	public void setNumeroOliveres(Integer numeroOliveres) {
		this.numeroOliveres = numeroOliveres;
	}

	public String getObservacionsVariedad() {
		return observacionsVariedad;
	}
	public void setObservacionsVariedad(String observacionsVariedad) {
		this.observacionsVariedad = observacionsVariedad;
	}

	public Plantacio getPlantacio() {
		return plantacio;
	}
	public void setPlantacio(Plantacio plantacio) {
		this.plantacio = plantacio;
	}

	public Double getSuperficie() {
		return superficie;
	}
	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}

	public VarietatOliva getVarietatOliva() {
		return varietatOliva;
	}
	public void setVarietatOliva(VarietatOliva varietatOliva) {
		this.varietatOliva = varietatOliva;
	}
	
	public DescomposicioPlantacio[] getVariedades() {
		return variedades;
	}
	public void setVariedades(DescomposicioPlantacio[] variedades) {
		this.variedades = variedades;
	}

	public String getFromFinca() {
		return fromFinca;
	}
	public void setFromFinca(String fromFinca) {
		this.fromFinca = fromFinca;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNoDescomposicio() {
		return noDescomposicio;
	}
	public void setNoDescomposicio(String noDescomposicio) {
		this.noDescomposicio = noDescomposicio;
	}

	public String getNomComplejo() {
		return this.getMunicipi()+" - "+this.getPoligon()+" - "+this.getParcela();
	}
	
	public Long getMunicipiId() {
		return municipiId;
	}
	public void setMunicipiId(Long municipiId) {
		this.municipiId = municipiId;
	}
	
	public String getNomFinca() {
		return nomFinca;
	}
	public void setNomFinca(String nomFinca) {
		this.nomFinca = nomFinca;
	}

	public Long getPlantacioBaixaId() {
		return plantacioBaixaId;
	}
	public void setPlantacioBaixaId(Long plantacioBaixaId) {
		this.plantacioBaixaId = plantacioBaixaId;
	}

	public String getNomComplet() {
		if (this.getMunicipi() != null){
			return this.getMunicipi().getNom() + " (" + this.getPoligon() + " - " + this.getParcela() + ")";
		} else {
			return this.getNom();
		}
	}
	/*public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}*/

}
