package es.caib.gestoli.logic.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import es.caib.gestoli.logic.model.Analitica;
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.Traza;

public class EdicioProcessosAux implements java.io.Serializable {

	private Long id;
	private String accio;
	private Date data;
	private Traza traza;
	private Long idDipositLot;
	private int tipusDipositLot;
	private String dipositLot;
	private Establiment establiment;
	private boolean valid;
	private String aux;
	private String observacions; //Camp auxiliar per a posar informació
	private boolean olivaTaula;

	public EdicioProcessosAux() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccio() {
		return accio;
	}

	public void setAccio(String accio) {
		this.accio = accio;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Traza getTraza() {
		return traza;
	}

	public void setTraza(Traza traza) {
		this.traza = traza;
	}

	public Long getIdDipositLot() {
		return idDipositLot;
	}

	public void setIdDipositLot(Long idDipositLot) {
		this.idDipositLot = idDipositLot;
	}

	public int getTipusDipositLot() {
		return tipusDipositLot;
	}

	public void setTipusDipositLot(int tipusDipositLot) {
		this.tipusDipositLot = tipusDipositLot;
	}

	public String getDipositLot() {
		return dipositLot;
	}

	public void setDipositLot(String dipositLot) {
		this.dipositLot = dipositLot;
	}

	public Establiment getEstabliment() {
		return establiment;
	}

	public void setEstabliment(Establiment establiment) {
		this.establiment = establiment;
	}

	public String getAux() {
		return aux;
	}

	public void setAux(String aux) {
		this.aux = aux;
	}
	
	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	/**
	 * Devuelve una fecha formateada
	 */
	public String getDataFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(this.data);
	}


	/**
	 * Devuelve si se puede borrar o no
	 */
	public boolean getValid() {
		
//		// Per a les analítiques hem de comprovar que no s'hagi realitzat una sortida vàlida o bien una analitica posterior per a poder borrar aquesta analítica.
//		if (accio.equals("10")) {
//			Iterator itPares = traza.getTrazasForTtrCodtrapare().iterator();
//			while (itPares.hasNext()) {
//				Traza tra = (Traza) itPares.next();
//				Iterator itFills = tra.getTrazasForTtrCodtrafill().iterator();
//				
//				// Recorrem cada un dels fills per a mirar si és de tipus sortida. o bien analitica
//				while (itFills.hasNext()) {
//					Traza traFill = (Traza) itFills.next();
//					if (traFill.getTipus().equals(new Integer(4))) {
//						SortidaDiposit sdi = (SortidaDiposit) traFill.getSortidesDiposits().iterator().next();
//						// Un pic tenim la sortida localitzada, posam al valor de vàlid de l'analítica, el valor contrari de vàlid de la sortida
//						// D'aquesta manera, si una sortida es válida, no es vàlida l'analítica (no es pot borrar) i viceversa
//						
//						if (sdi.getValid().booleanValue() && traFill.getId().longValue() > traza.getId().longValue() ) {
//							this.valid = !sdi.getValid().booleanValue();
//							break;
//						}						
//					}
//					//Si es analitica tenemos que comprobar que no es ella misma
//					else if(traFill.getTipus().equals(new Integer(5)) && traFill.getId().longValue()!= traza.getId().longValue() ){
//						Analitica ana = (Analitica) traFill.getAnalitiques().iterator().next();							
//							if (ana.getValid().booleanValue() && traFill.getId().longValue() > traza.getId().longValue() ) {
//								this.valid = !ana.getValid().booleanValue();
//								break;
//							}						
//					}
//					
//				}
//			}
//		}
//
//		return this.valid;
		
		
		// -- COMENTAT JOAN GALMES --
//		// Per a les analítiques hem de comprovar que no s'hagi realitzat una sortida vàlida o bien una analitica posterior per a poder borrar aquesta analítica.
//		if (accio.equals("10")) {
//			if(this.valid){
//				Iterator itPares = traza.getTrazasForTtrCodtrapare().iterator();
//				while (itPares.hasNext()) {
//					Traza tra = (Traza) itPares.next();//Tenemos que ver que ningun descendiente de esta traza tiene una traza mayor que la de la analitica(traza)
//					this.valid = noTieneHijosPosteriores(tra);								
//				}
//			}
//		}

		return this.valid;
		
	}
	
	public boolean noTieneHijosPosteriores(Traza tra) {
				boolean resultado =  true;
				Iterator itFills = tra.getTrazasForTtrCodtrafill().iterator();
				// Recorrem cada un dels fills per a mirar si és de tipus sortida. o bien analitica	
				System.out.println("********** noTieneHijosPosteriores");
				System.out.println("***************************"+ tra.getId());
				
				if(tra.getTrazasForTtrCodtrafill().size()== 0){
						System.out.println("SIN HIJOS traza:"+tra.getId()+"  Tipo: "+tra.getTipus());
						if (tra.getTipus().equals(new Integer(4)) || tra.getTipus().equals(new Integer(9))) {
							SortidaDiposit sdi = (SortidaDiposit) tra.getSortidesDiposits().iterator().next();
							// Un pic tenim la sortida localitzada, posam al valor de vàlid de l'analítica, el valor contrari de vàlid de la sortida
							// D'aquesta manera, si una sortida es válida, no es vàlida l'analítica (no es pot borrar) i viceversa
							
							if (sdi.getValid().booleanValue() && tra.getId().longValue() > traza.getId().longValue() ) {
								resultado = !sdi.getValid().booleanValue();								
							}						
						}else if (tra.getTipus().equals(new Integer(6))) {
							EntradaLot elot = (EntradaLot) tra.getEntradesLots().iterator().next();
							// Un pic tenim la sortida localitzada, posam al valor de vàlid de l'analítica, el valor contrari de vàlid de la sortida
							// D'aquesta manera, si una sortida es válida, no es vàlida l'analítica (no es pot borrar) i viceversa
							
							if (elot.getValid().booleanValue() && tra.getId().longValue() > traza.getId().longValue() ) {
								resultado = !elot.getValid().booleanValue();
							}						
						}
						//Si es analitica tenemos que comprobar que no es ella misma
						else if(tra.getTipus().equals(new Integer(5)) && tra.getId().longValue()!= traza.getId().longValue() ){
							Analitica ana = (Analitica) tra.getAnalitiques().iterator().next();							
								if (ana.getAnalisiFisicoQuimicValid().booleanValue() && tra.getId().longValue() > traza.getId().longValue() ) {
									resultado = !ana.getAnalisiFisicoQuimicValid().booleanValue();
								}						
						}
				}else{
						while (itFills.hasNext()) {					
							Traza traFill = (Traza) itFills.next();
							System.out.println("HIJO traza:"+traFill.getId()+"  Tipo: "+traFill.getTipus());
							if (traFill.getTipus().equals(new Integer(4)) || tra.getTipus().equals(new Integer(9))) {
								SortidaDiposit sdi = (SortidaDiposit) traFill.getSortidesDiposits().iterator().next();
								// Un pic tenim la sortida localitzada, posam al valor de vàlid de l'analítica, el valor contrari de vàlid de la sortida
								// D'aquesta manera, si una sortida es válida, no es vàlida l'analítica (no es pot borrar) i viceversa
								
								if (sdi.getValid().booleanValue() && traFill.getId().longValue() > traza.getId().longValue() ) {
									resultado = !sdi.getValid().booleanValue();
									break;
								}						
							}else if (traFill.getTipus().equals(new Integer(6))) {
								EntradaLot elot = (EntradaLot) traFill.getEntradesLots().iterator().next();
								if (elot.getValid().booleanValue() && traFill.getId().longValue() > traza.getId().longValue() ) {
									resultado = !elot.getValid().booleanValue();
									break;
								}						
							}else if (traFill.getTipus().equals(new Integer(7))) {
								SortidaLot slot = (SortidaLot) traFill.getSortidesLots().iterator().next();
								if (slot.getValid().booleanValue() && traFill.getId().longValue() > traza.getId().longValue() ) {
									resultado = !slot.getValid().booleanValue();
									break;
								}						
							}
							
							//Si es analitica tenemos que comprobar que no es ella misma
							else if(traFill.getTipus().equals(new Integer(5)) && traFill.getId().longValue()!= traza.getId().longValue() ){
								Analitica ana = (Analitica) traFill.getAnalitiques().iterator().next();							
									if (ana.getAnalisiFisicoQuimicValid().booleanValue() && traFill.getId().longValue() > traza.getId().longValue() ) {
										resultado = !ana.getAnalisiFisicoQuimicValid().booleanValue();
										break;
									}						
							}
							
							if(resultado){
								resultado = noTieneHijosPosteriores(traFill);
								if(!resultado){
									break;
								}
							}
							
						}
				}				
				
				
				System.out.println("********** Resultado "+resultado+"------"+ tra.getId());
				
		return resultado;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isOlivaTaula() {
		return olivaTaula;
	}

	public void setOlivaTaula(boolean olivaTaula) {
		this.olivaTaula = olivaTaula;
	}

	/**
	 * Devuelve el proceso padre
	 */
	public String getProcesPosterior() {
		String resultado = "";
		Iterator it = traza.getTrazasForTtrCodtrafill().iterator();
		while (it.hasNext()) {
			Traza tra = (Traza) it.next();
			if (resultado != null && !"".equals(resultado)) resultado += ", ";
			resultado += tra.getId();
		}
		return resultado;
	}
}
