package br.udesc.ppr55.zanac.inimigos;

import br.udesc.ppr55.zanac.core.Sprite;

public class PowerChip extends Sprite {

	private int hArea;
	private long lastTime;

	public PowerChip(int x, int y, int wArea, int hArea) {
		super(x, y, "imgs/powerchip.png");
		
		this.hArea = hArea;
	}

	@Override
	public void move() {
		
		long now = System.currentTimeMillis();
		if (now - lastTime > 500) {
			move(0, 1);
		
			if (getY() >= hArea)
				setVisible(false);
			lastTime = now;
		}
		
	}
}