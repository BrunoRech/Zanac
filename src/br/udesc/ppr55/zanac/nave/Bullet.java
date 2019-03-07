package br.udesc.ppr55.zanac.nave;

import java.awt.Graphics;

import br.udesc.ppr55.zanac.core.Sprite;

public class Bullet extends BalaNave {

	private int yMin;
	private final int BULLET_SPEED = 8;

	public Bullet(int x, int y, int yMin) {
		super(x, y, "imgs/bullet.png");
		this.yMin = yMin;
	}
	
	public void move() {
		move(0, -BULLET_SPEED);
		if (getY() < 0) {
			setVisible(false);
		}
	}

	@Override
	public void draw(Graphics g) {
		
		if (getY() < yMin)
		  super.draw(g);
	}
	
}
