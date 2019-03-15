package br.udesc.ppr55.zanac.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.ImageIcon;

public abstract class Sprite {
	
	private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible = true;
    private Image image;
	private int hArea;
	private int wArea;
    
    public Sprite(int x, int y, String imageName) {
    	this.x = x;
    	this.y = y;
        
    	setImage(imageName);
    }
    
    protected void setImage(String imageName) {
    	ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
        
        processImageDimensions();
    }

    protected void processImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }    
    
    public Image getImage() {
        return image;
    }

    public void draw(Graphics g) {
    	g.drawImage(getImage(), getX(), getY(), null);
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
		this.x = x;
	}
    
    public void setY(int y) {
		this.y = y;
	}
    
    public void move() {
    	
    }
    
    public void move(int dx, int dy) {
    	this.y += dy;
    	this.x += dx;
    	/*
    	if (this.x < 0) {
    		this.x = 0;
    	} else {
    		if (this.x > wArea-getWidth()) {
    			this.x = wArea-getWidth();
    		}
    	}
    	
    	if (this.y < 0) {
    		this.y = 0;
    	} else {
    		if (this.y > hArea - getHeight()) {
    			this.y = hArea - getHeight();
    		}
    	}
    	*/
    }
    
    public void moveTo(int x, int y) {
    	this.y = y;
    	this.x = x;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public int getHeight() {
		return height;
	}
    
    public int getWidth() {
		return width;
	}
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setAreaBounds(int width, int height) {
    	this.wArea = width;
    	this.hArea = height;
	}
    
    public int getWidthBoundedArea() {
    	return wArea;
    }
	
    public int getHeightBoundedArea() {
    	return hArea;
    }
    
}
