package br.udesc.ppr55.zanac;

import java.util.ArrayList;
import java.util.List;

public class Luster extends Sprite {

	private int hArea;
	private int wArea;

	private int speed = 3;
	private boolean destruido;
	private int contaDestruido;
	private int parou;

	public Luster(int x, int y, int wArea, int hArea) {
		super(x, y, "imgs/luster_blue.png");
		this.hArea = hArea;
		this.wArea = wArea;
	}

	@Override
	public void move() {
		if (destruido) {
			contaDestruido++;
			if (contaDestruido == 10) {
				setVisible(false);
			}
		} else 
			if (getY() < hArea/2) {
				move(0, speed);
			} else {
				if (parou == 0) {
					parou = 1;
				} else {
					if (parou < 100) {
						parou++;
					} else {
						move(0, speed);
					}
				}
			}
	}

	public boolean isDestruido() {
		return destruido;
	}

	public void destruir() {
		setImage("imgs/explosao.png");

		this.destruido = true;	
	}
	
	@Override
	public <T extends Sprite> T deixarMarcas() {
		Lead l = null;
		
		if (parou == 1 || parou == 50) {
			
			l = new Lead(getX(), getY(), wArea, hArea, getX() == 20);
			parou++;
		}
		return (T) l;
	}
}
