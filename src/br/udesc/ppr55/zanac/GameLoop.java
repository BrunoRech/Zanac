package br.udesc.ppr55.zanac;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class GameLoop extends JFrame {
	
	private AreaZanac areaZanac;

	private Thread gameLoop;
	
	private int lastKey;
	
	private final List<Integer> pressed = new ArrayList<Integer>();
	
	public GameLoop() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		this.areaZanac = new AreaZanac();
		add(areaZanac);
		
		setCursor( getToolkit().createCustomCursor( new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
	            "null"));

		
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				lastKey = e.getKeyCode();
				if (!pressed.contains(lastKey)) {
					pressed.add(lastKey);
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int key = e.getKeyCode();
					pressed.remove(pressed.indexOf(key));
					if (pressed.size() > 0) {
						lastKey = pressed.get(pressed.size()-1);
					} else {
						lastKey = 0;
					}
				} catch (Exception ex) {
					System.out.println("Erro " + e.getKeyCode());
				}
			}
			
		});
	    
		gameLoop = new Thread() {
			@Override
			public void run() {
				// Game Loop: Nystrom, 2014
				
				long MS_PER_FRAME = 16; //  16 ms/frame = 60 FPS
				long last = System.currentTimeMillis();
				while (true) {
					
					long now = System.currentTimeMillis();
					
					if (now - last > MS_PER_FRAME) {
						last = now;
						
						processInput();
						update();
						render();
						
					}
					
				}
			}
		};
	    gameLoop.start();

	}

	private void render() {
		areaZanac.repaint();
		
		//Toolkit.getDefaultToolkit().sync();
	}

	private void update() {
		areaZanac.updateData();
		
	}

	private void processInput() {
		
		areaZanac.processInput(pressed);
		
	}

}
