package es.caib.gestoli.front.spring;

import java.util.Collection;

import es.caib.gestoli.logic.model.Pais;

public class PaisCommand extends Pais {

	private static final long serialVersionUID = 1L;

	private Pais[] paisos;
	
	public PaisCommand() {}
	
	public PaisCommand(Collection paises) {
		if (paises != null) {
			Integer i = 0;
			paisos = new Pais[paises.size()];
			for (Object obj : paises) {
				Pais p = (Pais)obj;
				paisos[i] = p;
				i++;
			}
		}
	}

	public Pais[] getPaisos() {
		return paisos;
	}
	public void setPaisos(Pais[] paisos) {
		this.paisos = paisos;
	}
}
