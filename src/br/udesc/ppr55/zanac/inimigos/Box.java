package br.udesc.ppr55.zanac.inimigos;

import java.util.ArrayList;
import java.util.Arrays;

public class Box extends Inimigo {
	
	/** {@inheritDoc} */
	@Override
	public int getVidas() {
		return 2;
	}

	private boolean temPC;
	private int hArea;

	public Box(int x, int y, int hArea, boolean temPC) {
		super(x, y, "imgs/pcbox.png");
		this.temPC = temPC;
		this.hArea = hArea;
	}

	@Override
	public void move() {
		move(0, 1);

		if (getY() > hArea) {
			setVisible(true);
		}
	}

	@Override
	public void destruir() {
		setDestruido(true);
		setVisible(false);
		if (temPC) {
			PowerChip pc = new PowerChip(getX(), getY(), 0, hArea);
			notificarDestruidoComPowerChip(getPontos(), new ArrayList<PowerChip>(Arrays.asList(pc)));
		}
	}
	
	@Override
	public int getPontos() {
		return (temPC) ?500 : 300;
	}

}
