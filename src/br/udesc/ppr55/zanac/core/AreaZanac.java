package br.udesc.ppr55.zanac.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import br.udesc.ppr55.zanac.inimigos.Box;
import br.udesc.ppr55.zanac.inimigos.Duster;
import br.udesc.ppr55.zanac.inimigos.Inimigo;
import br.udesc.ppr55.zanac.inimigos.Lead;
import br.udesc.ppr55.zanac.inimigos.Luster;
import br.udesc.ppr55.zanac.inimigos.PowerChip;
import br.udesc.ppr55.zanac.inimigos.ProjetilInimigo;
import br.udesc.ppr55.zanac.nave.Bullet;
import br.udesc.ppr55.zanac.nave.Nave;
import br.udesc.ppr55.zanac.nave.ProjetilNave;

public class AreaZanac extends JPanel implements Observador{
	
	private static AreaZanac instance;
	private Nave nave;
	private final int MOVENAVE = 7;
	
	private Random sorteio;
	
	private List<PowerChip> chips = new ArrayList<>(); 
	/**
	 private List<Duster> inimigos = new ArrayList<>();
	private List<Luster> inimigos2 = new ArrayList<>();
	private List<Box> boxes = new ArrayList<>(); 
	 */
	
	
	private List<Inimigo> in = new ArrayList<Inimigo>();
	
	private List<ProjetilInimigo> MunicaoInimigo = new ArrayList<>();
	private List<ProjetilNave> projetil = new ArrayList<>();
	
	private int vidas = 4;
	
	
	//testes
	//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//int width = ((int) screenSize.getWidth()/2);
	//int height = ((int) screenSize.getHeight()/2);
	

	
	private int pontos = 0;
	private Image image1, image2;
	private int dx = 0, dy = 0;
	
	private long lastTimeFired = 0;
	private long lastTimeSpawned;
	private int contaPC;
	
	private int enemiesDestroyed;
	private int lastEnemyDestroyed;
	
	//synchronized evita q o método seja chamado simultaneamente
	
	public static synchronized AreaZanac getInstance() {
		if(instance == null) {
			instance = new AreaZanac();
		}
		return instance;
	}
	
	private AreaZanac() {
		
		nave = new Nave();
		sorteio = new Random();
		
		morreuNave();
		ImageIcon ii = new ImageIcon("imgs/fundo.png");
        image1 = ii.getImage();		

        ImageIcon ic = new ImageIcon("imgs/navecontador.png");
        image2 = ic.getImage();	
        
	}
	/*
	List<Point2D> points;
	private void createBezierCurve() {
		if (points != null) 
			return;
		
		Path2D path = new Path2D.Double();
		int x1 = 400;
        path.moveTo(x1, 0);
        path.curveTo(x1-100, 200, x1+500, 200, x1+00, getHeight());
        
        points = new ArrayList<>();
        PathIterator pi = path.getPathIterator(null, 0.02); // 0.02 determina a velocidade
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

	private void drawBezierCurve(Graphics g) {
		for (int i = 0; i < points.size(); i++) {
	        Point2D pos = points.get(i);
	        
	        g.drawRect((int) (pos.getX() - getX()), (int) (pos.getY() - getY()), 1, 1);
	        
		}
		
	}
*/	
	protected void paintComponent(Graphics g) {
	//	createBezierCurve();
		
		super.paintComponent(g);
		
		if (nave.getX() == -1) { 
			nave.setX(getWidth()  / 2);
			nave.setY(getHeight() / 2);
			
			nave.setAreaBounds(getWidth(), getHeight());
		}
		
		int wtotal = getWidth();
		int htotal = getHeight();
		
		int w = image1.getWidth(null);
		int h = image1.getHeight(null);
		for (int i = 0; i*w < wtotal; i++)
			for (int j = 0; j*h < htotal; j++)
				g.drawImage(image1, i*w, j*h, null);
		
		//drawBezierCurve(g);
		
		Font font = new Font("Consolas", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.WHITE);
		FontMetrics metrics = g.getFontMetrics(font);

		if (vidas == 0) {
			String chave = "Game over"; 
			g.drawString(chave, (getWidth() / 2) - (metrics.stringWidth(chave)/2), 
					getHeight() / 2);			
		} else {
			String chave = "" + vidas;
			int vy = getHeight() - metrics.getHeight();
			int vx = getWidth() - metrics.stringWidth(chave);
			g.drawString(chave, vx - 50, vy);
			g.drawImage(image2, vx - 30, getHeight() - image2.getHeight(null) - 20, null);
		}
		
		String chave = "" + enemiesDestroyed;
		g.drawString(chave, 50, getHeight() - metrics.getHeight());
		
		if (nave.isVisible())
			g.drawImage(nave.getImage(), nave.getX(), nave.getY(), null);
		
		for (ProjetilNave b:projetil) {
			b.draw(g);
		}
		
		for (PowerChip pc:chips)
			pc.draw(g);
		
		for (Inimigo i: in)
			i.draw(g);
			
		
		for (ProjetilInimigo lead:MunicaoInimigo)
			lead.draw(g);

	}

	public void processInput(List<Integer> presseds) {

		for (Integer pressed: presseds) {
			switch (pressed) {
			  case KeyEvent.VK_LEFT: 
				  		dx = -MOVENAVE;
				  		break;
				  
			  case KeyEvent.VK_RIGHT:
				  		dx = MOVENAVE;
				  		break;
						  
			  case KeyEvent.VK_UP:
				  		dy = -MOVENAVE;
				  		break;
						  
			  case KeyEvent.VK_DOWN:
				  		dy = MOVENAVE;
				  		break;
				  		
			  case KeyEvent.VK_SPACE:
			        	long now = System.currentTimeMillis();
			        	if (now - lastTimeFired > 256 && projetil.size() == 0) {
			        		
			        		switch (contaPC) {
				        		case 0:
				        		case 1: 
					        		if (contaPC  == 1) {
						        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)-4, nave.getY()+120, nave.getY()));
					        		}
					        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)-4, nave.getY()+60, nave.getY()));
					        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)-4, nave.getY(), nave.getY()));
					        		break;
					        		
				        		case 2:
				        		case 3:
					        		if (contaPC == 3) {
						        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)-10, nave.getY()+120, nave.getY()));
						        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)+2, nave.getY()+120, nave.getY()));
					        		}
					        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)-10, nave.getY()+60, nave.getY()));
					        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)-10, nave.getY(), nave.getY()));
					        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)+2, nave.getY()+60, nave.getY()));
					        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)+2, nave.getY(), nave.getY()));
					        		break;
					        		
				        		default:
					        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)-10, nave.getY()+60, nave.getY()));
					        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)+2, nave.getY()+60, nave.getY()));				        			
					        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)-4, nave.getY()+50, nave.getY()));

					        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)-10, nave.getY(), nave.getY()));
					        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)+2, nave.getY(), nave.getY()));				        			
					        		projetil.add(new Bullet((nave.getX() + nave.getWidth()/2)-4, nave.getY()-10, nave.getY()));
			        		}
			        		
			        		lastTimeFired = now;
			        	}
				  		break;
			}
		}		
	}

	public void updateData() {
		
		if (vidas == 0) {
			return;
		}
		
		spawnInimigos();
		spawnPowerChips();
		
		nave.move(dx, dy);
		
		checkCollisions();
		
		int i = chips.size() - 1;
		while (i >= 0) {
			PowerChip pc = chips.get(i);
			if (pc.isVisible()) {
				pc.move();
			} else {
				chips.remove(i);
			}
			i--;
		}

		i = in.size() - 1;
		while (i >= 0) {
			Inimigo d = in.get(i);
			if (d.isVisible()) {
				d.move();
			} else {
				in.remove(i);
			}
			i--;
		}
/*
 * 	TODO
		i = inimigos2.size() - 1;
		while (i >= 0) {
			Luster r = inimigos2.get(i);
			if (r.isVisible()) {
				r.move();
				Lead l = r.deixarMarcas();
				if (l != null) {
					MunicaoInimigo.add(l);
				}
			} else {
				inimigos2.remove(i);
			}
			i--;
		}
*/
		i = projetil.size() - 1; 
		while (i >= 0) {
			ProjetilNave b = projetil.get(i);
			if (b.isVisible()) {
				b.move();
			} else {
				projetil.remove(i);
			}
			i--;
		}
		
		i = MunicaoInimigo.size() - 1;
		while (i >= 0) {
			ProjetilInimigo r = MunicaoInimigo.get(i);
			if (r.isVisible()) {
				r.move();
			} else {
				MunicaoInimigo.remove(i);
			}
			i--;
		}

		dx = 0;
		dy = 0;
		
	}
	
	private void spawnPowerChips() {
		
		if (lastEnemyDestroyed != enemiesDestroyed && (enemiesDestroyed == 2 || (enemiesDestroyed-2) % 16 == 0)) {
			int x = sorteio.nextInt(getWidth()-175) +  175;
			
			int pc = sorteio.nextInt(3);
			
			in.add(new Box(x, 55, getHeight(), pc == 0));
			in.add(new Box(x+55, 0, getHeight(), pc == 1));
			in.add(new Box(x+110, 55, getHeight(), pc == 2));
			
			lastEnemyDestroyed = enemiesDestroyed;
		}
		
	}

	private void spawnInimigos() {
		
		long now = System.currentTimeMillis();

		if (now - lastTimeSpawned > 2000 && getWidth() > 0) { // spawn 1 inimigo a cada 2 segundos getWidth() > 0 para garantir que j� est� pronto para desenhar
			if (sorteio.nextInt()%2==0) {
				
				int x;
				if (sorteio.nextInt()%2==0) {
					x = 20;	
				} else {
					x = getWidth()-48;
				}
				 
				in.add(new Luster(x, 0, getWidth(), getHeight()));
				
			} else {
				int x = sorteio.nextInt(getWidth()) +  20;
				in.add(new Duster(x, 0, getWidth(), getHeight()));
			}
			lastTimeSpawned = now;
		}
	}

	private void checkCollisions() {

        Rectangle r3 = nave.getBounds();

        for (PowerChip pc:chips) {
            
            Rectangle r2 = pc.getBounds();

            if (r3.intersects(r2)) {
            	pc.setVisible(false);
                pontos += 170;
                this.contaPC++;
            }
        }

        for (Inimigo ini:in) {
            
            Rectangle r2 = ini.getBounds();

            if (r3.intersects(r2)) {
            	ini.setVisible(false);
            	morreuNave();
            }
        }

        for (ProjetilInimigo ini:MunicaoInimigo) {
            
            Rectangle r2 = ini.getBounds();

            if (r3.intersects(r2)) {
            	ini.setVisible(false);
            	morreuNave();
            }
        }
        

        for (ProjetilNave b : projetil) {

            Rectangle r1 = b.getBounds();

            if (b.isVisible()) {
	            for (Inimigo d :in) {
	
	                Rectangle r2 = d.getBounds();
	
	                if (!d.isDestruido() && r1.intersects(r2)) {
	                    
	                    b.setVisible(false);
	                    d.destruir();

	                }
	            }
            }
        }
    }

	private void morreuNave() {
		vidas--;
		if (vidas == 0)
			nave.setVisible(false);
		else
			nave.moveTo(-1,0);
		
		this.contaPC = 0;
		this.enemiesDestroyed = 0;
		this.lastEnemyDestroyed = 0;
	}

	@Override
	public void destruidoComPowerShip(int pontos, List<PowerChip> pcs) {
		chips.addAll(pcs);
		enemiesDestroyed++;
		this.pontos += pontos;
	}

	@Override
	public void destruido(int pontos) {
		enemiesDestroyed++;
        this.pontos += pontos;
	}
}
