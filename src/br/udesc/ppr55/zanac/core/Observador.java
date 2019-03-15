package br.udesc.ppr55.zanac.core;

import java.util.List;

import br.udesc.ppr55.zanac.inimigos.PowerChip;
import br.udesc.ppr55.zanac.inimigos.ProjetilInimigo;

public interface Observador {
	void destruidoComPowerShip(int pontos, List<PowerChip> pcs);
	void destruido(int pontos);
	void soltouProjetilInimigo(List<ProjetilInimigo> projeteis);
}
