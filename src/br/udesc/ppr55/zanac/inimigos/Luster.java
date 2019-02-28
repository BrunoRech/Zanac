package br.udesc.ppr55.zanac.inimigos;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ppr55.zanac.core.Sprite;

public class Luster extends Inimigo {

	private int hArea;
	private int wArea;

	private int speed = 3;

	private int contaDestruido;
	private int parou;

	public Luster(int x, int y, int wArea, int hArea) {
		super(x, y, "imgs/luster_blue.png");
		this.hArea = hArea;
		this.wArea = wArea;
	}

	@Override
	public void move() {
		if (isDestruido()) {
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
	
	@Override
	public <T extends Sprite> T deixarMarcas() {
		Lead l = null;
		
		if (parou == 1 || parou == 50) {
			
			l = new Lead(getX(), getY(), wArea, hArea, getX() == 20);
			parou++;
		}
		return (T) l;
	}

	@Override
	public int getPontos() {
		return 500;
	}
}
