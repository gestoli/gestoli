package es.caib.gestoli.front.spring;

import java.util.Collection;

import es.caib.gestoli.logic.model.RendimentVarietat;
import es.caib.gestoli.logic.model.VarietatOliva;

public class RendimentVarietatCommand extends RendimentVarietat {
	
	private static final long serialVersionUID = 1L;

	private RendimentVarietat[] rendiments;
	private String tipusRendimentCampanya;
	
	public RendimentVarietatCommand() {}
	
	public RendimentVarietatCommand(Collection varietats, boolean esRendiment){
		rendiments = new RendimentVarietat[varietats.size()];
		if (esRendiment) {
			Object[] rendimentsVarietat = varietats.toArray();
			for (int i = 0; i < varietats.size(); i++) {
				rendiments[i] = (RendimentVarietat)rendimentsVarietat[i];
			}
		} else {
			Object[] varietatsOliva = varietats.toArray();
			for (int i = 0; i < varietats.size(); i++) {
				rendiments[i] = new RendimentVarietat();
				rendiments[i].setVarietatOliva((VarietatOliva)varietatsOliva[i]);
				rendiments[i].setIdVarietatOliva(((VarietatOliva)varietatsOliva[i]).getId().longValue());
			}
		}
	}

	public RendimentVarietat[] getRendiments() {
		return rendiments;
	}
	public void setRendiments(RendimentVarietat[] rendiments) {
		this.rendiments = rendiments;
	}

	public String getTipusRendimentCampanya() {
		return tipusRendimentCampanya;
	}
	public void setTipusRendimentCampanya(String tipusRendimentCampanya) {
		this.tipusRendimentCampanya = tipusRendimentCampanya;
	}
}
