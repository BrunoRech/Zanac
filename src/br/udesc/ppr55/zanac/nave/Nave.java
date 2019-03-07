package br.udesc.ppr55.zanac.nave;

import br.udesc.ppr55.zanac.core.Sprite;

public class Nave extends Sprite {

	public Nave() {
		super(-1, 0, "imgs/ship.png");
	}

	@Override
	public void move(int dx, int dy) {
		int x = getX();
		int y = getY();
		
		if (dx + x > 0 && dx + x + getWidth() < getWidthBoundedArea() &&
				dy + y > 0 && dy + y + getHeight() < getHeightBoundedArea()) {
			super.move(dx, dy);
		}
		
	}
	
}