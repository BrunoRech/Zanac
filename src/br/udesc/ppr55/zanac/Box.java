package br.udesc.ppr55.zanac;

public class Box extends Sprite {

	private boolean temPC;
	private int hArea;
	private int destruido;

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

	public PowerChip destruir() {
		this.destruido++;
		setVisible(destruido == 2);
		if (destruido == 2 && temPC) {
			return new PowerChip(getX(), getY(), 0, hArea);
		} else {
			return null;
		}
	}

	public boolean isDestruido() {
		return destruido == 2;
	}

}
