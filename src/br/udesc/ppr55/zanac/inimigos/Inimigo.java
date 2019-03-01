package br.udesc.ppr55.zanac.inimigos;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ppr55.zanac.core.Observador;
import br.udesc.ppr55.zanac.core.Sprite;

public abstract class Inimigo extends Sprite{
	
	private List<Observador> observadores = new ArrayList<Observador>();
	private boolean destruido;

	public Inimigo(int x, int y, String imageName) {
		super(x, y, imageName);
		// TODO Auto-generated constructor stub
	}
	
	public abstract int getPontos();
	
	
	public void destruir() {
		setImage("imgs/explosao.png");
		this.destruido = true;
		notificarDestruido(getPontos());
	}

	public boolean isDestruido() {
		return destruido;
	}
	
	
	public void anexar(Observador observador) {
		
	}
	
	public void notificarDestruidoComPowerChip(int pontos, List<PowerChip> pcs) {
		for (Observador obs: observadores) {
			obs.destruidoComPowerShip(pontos, pcs);
		}
	}
	
	public void notificarDestruido(int pontos) {
		for (Observador obs: observadores) {
			obs.destruido(pontos);
		}
	}
}
