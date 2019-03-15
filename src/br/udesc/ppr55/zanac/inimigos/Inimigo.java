package br.udesc.ppr55.zanac.inimigos;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ppr55.zanac.core.Observador;
import br.udesc.ppr55.zanac.core.Sprite;

public abstract class Inimigo extends Sprite{
	
	private List<Observador> observadores = new ArrayList<Observador>();
	private boolean destruido;
	
	/** Contador que indica quantos tiros o inimigo recebeu. Inicia em 0 (zero) */
	private int tirosRecebidos;
	
	public Inimigo(int x, int y, String imageName) {
		super(x, y, imageName);
		destruido = false;
		tirosRecebidos = 0;
	}

	public abstract int getPontos();
		
	/** @return Quantidade de vidas do inimigo. Ou seja, a quantidade de balas que a nave precisa acertar para destru�-lo */
	public int getVidas() {
		return 1;
	}
	
	/** M�todo auxiliar para incrementar em 1 (um) o n�mero de tiros recebidos pelo inimigo */
	public void receberTiro() {
		receberTiros(1);
	}
	
	/**
	 * M�todo auxiliar para incrementar em <i>tiros</i> vezes o n�mero de tiros recebidos pelo inimigo.
	 * Ap�s incrementar o n�mero de tiros recebidos, � verificado se o inimigo deve ser destru�do
	 * @param tiros
	 */
	public void receberTiros(int tiros) {
		tirosRecebidos += tiros;
		verificarVidas();
	}

	/** Verifica se o inimigo n�o possui mais vidas. Nesse caso, ele deve ser destru�do */
	public void verificarVidas() {
		if(!possuiVidas()) {
			this.destruir();
		}
	}
	
	/**
	 * Verifica se o inimigo ainda possui vidas, de acordo com o n�mero total de vidas, definidos em "this.getVidas()",
	 * e de acordo com o n�meto total de tiros recebidos, definido em "this.tirosRecebidos".
	 * @return Verdadeiro se o inimigo possui vidas, e Falso caso contr�rio.
	 */
	public boolean possuiVidas() {
		return getVidas() > tirosRecebidos;
	}

	public void destruir() {
		setImage("imgs/explosao.png");
		setDestruido(true);
		notificarDestruido(getPontos());
	}
	
	protected void setDestruido(boolean destruido) {
		this.destruido = destruido;
	}

	public boolean isDestruido() {
		return destruido;
	}
	
	public void anexar(Observador observador) {
		observadores.add(observador);
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
	
	public void notificarSoltouProjetilInimigo(List<ProjetilInimigo> projeteis) {
		
		for (Observador obs: observadores) {
			obs.soltouProjetilInimigo(projeteis);
		}
	}
	
}
