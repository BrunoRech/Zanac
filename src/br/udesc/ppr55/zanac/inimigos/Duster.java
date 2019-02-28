package br.udesc.ppr55.zanac.inimigos;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import br.udesc.ppr55.zanac.core.Sprite;

public class Duster extends Inimigo {

	private final int DUSTER_SPEED = 2;

	private List<Point2D> points;
	private Shape pathShape;
	private int hArea;

	public Duster(int x, int y, int wArea, int hArea) {
		super(x, y, "imgs/duster_yellow.png");

		this.hArea = hArea;

		int x1 = x;
		Path2D path = new Path2D.Double();
		path.moveTo(x1, 0);
		if (x1 < wArea / 2) {
			path.curveTo(x1, 200, x1 + 400, 200, x1 + 400, hArea);
		} else {
			path.curveTo(x1, 200, x1 - 400, 200, x1 - 400, hArea);
		}

		pathShape = path;

		points = new ArrayList<>();
		PathIterator pi = pathShape.getPathIterator(null, 0.00001); // nivel de
																	// detalhamento
																	// dos
																	// pontos
		while (!pi.isDone()) {
			double[] coords = new double[6];
			switch (pi.currentSegment(coords)) {
			case PathIterator.SEG_MOVETO:
			case PathIterator.SEG_LINETO:
				points.add(new Point2D.Double(coords[0], coords[1]));
				break;
			}
			pi.next();
		}

	}

	private Point2D pos;
	private int index;

	private int contaDestruido;

	@Override
	public void move() {
		if (!isDestruido()) {

			if (index < points.size()) {
				pos = points.get(index);

				index = index + DUSTER_SPEED;

				move((int) (pos.getX() - getX()), (int) (pos.getY() - getY()));

				if (getY() >= hArea)
					setVisible(false);
			}
		} else {
			contaDestruido++;
			if (contaDestruido == 10) {
				setVisible(false);
			}
		}

	}

	protected double angleTo(Point2D from, Point2D to) {
		double angle = Math.atan2(to.getY() - from.getY(), to.getX() - from.getX());
		return angle;
	}

	@Override
	public int getPontos() {
		return 60;
	}

}
