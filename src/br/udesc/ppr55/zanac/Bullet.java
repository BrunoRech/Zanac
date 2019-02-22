package br.udesc.ppr55.zanac;

import java.awt.Graphics;

public class Bullet extends Sprite {

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
