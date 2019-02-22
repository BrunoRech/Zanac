package br.udesc.ppr55.zanac;

public class Lead extends Sprite {

	private int speed;
	private int hArea;

	public Lead(int x, int y, int wArea, int hArea, boolean toRight) {
		super(x, y, "imgs/lead.png");
		
		if (toRight) {
			speed = 2;
		} else {
			speed = -2;
		}
		
		this.hArea = hArea;
	}
	
	@Override
	public void move() {
		move(speed, 1);
		
		if (getY() > hArea) {
			setVisible(true);
		}
	}

}
