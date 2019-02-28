package br.udesc.ppr55.zanac.inimigos;

import br.udesc.ppr55.zanac.core.Sprite;

public abstract class Inimigo extends Sprite{
	
	private boolean destruido;

	public Inimigo(int x, int y, String imageName) {
		super(x, y, imageName);
		// TODO Auto-generated constructor stub
	}
	
	public abstract int getPontos();
	
	
	public void destruir() {
		setImage("imgs/explosao.png");

		this.destruido = true;
	}

	public boolean isDestruido() {
		return destruido;
	}

}
