package br.udesc.ppr55.zanac.core;


import java.util.List;

import br.udesc.ppr55.zanac.inimigos.PowerChip;

public interface Observador {
	
	void destruidoComPowerShip(int pontos, List<PowerChip> pcs);
	void destruido(int pontos);
}
