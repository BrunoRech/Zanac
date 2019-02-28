package br.udesc.ppr55.zanac.inimigos;


public class Box extends Inimigo {

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
	
	@Override
	public void destruir() {
		this.destruido++;
		setVisible(destruido == 2);
		if (destruido == 2 && temPC) {
			PowerChip pc = new PowerChip(getX(), getY(), 0, hArea);
			//add PowerShip na area zanac
		}
	}
	
/*	public PowerChip destruir() {
	} * */

	public boolean isDestruido() {
		return destruido == 2;
	}

	@Override
	public int getPontos() {

		return (temPC?500:300);
	}

}