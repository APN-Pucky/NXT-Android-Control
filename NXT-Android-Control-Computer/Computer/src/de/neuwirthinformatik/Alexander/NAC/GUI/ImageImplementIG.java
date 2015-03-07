package de.neuwirthinformatik.Alexander.NAC.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImageImplementIG extends JPanel 
{
	ImageGUI ig;
	Image img;
	int width;
	int height;
	boolean helper = false;
	boolean info = true;
	

    public ImageImplementIG(ImageGUI ig, int widht, int height) 
    {
    	this.ig = ig;
    	this.width = widht;
    	this.height = height;
    	helper = true;
    	info = true;
    }
    
    public void updateSize()
    {
    	Dimension size = new Dimension(width, height);
    	setPreferredSize(size);
    	setMinimumSize(size);
    	setMaximumSize(size);
    	setSize(size);
    	//setLayout(null);
    }
    
    public void setImage(BufferedImage img)
    {
    	this.img = img.getScaledInstance(width, height, Image.SCALE_FAST);
    }
    
    public Image getImage()
    {
    	return img;
    }
    
    public void toggleInfo()
    {
    	info = !info;
    }
    
    public void toggleHelper()
    {
    	helper = !helper;
    }

    public void paintComponent(Graphics g) 
    {
    	if(img != null)drawImage(g);
    	//System.out.println("COM: " + ig.getNMM().getCom() + " || ROT: " + ig.getNMM().getRot());
    	if(helper)drawHelper(g);
    	if(info)drawInfo(g);
    }
    
    private void drawInfo(Graphics g) 
    {
    	g.setColor(Color.GREEN);
    	Font f = g.getFont();
    	Font gf = new Font(f.getName(), 20, 20);
    	g.setFont(gf);
		String s = "Distance: " +ig.getNMM().getSonar() + "\n";
		g.drawString(s, 30, 30);
		s = "Camera: " + (ig.getFrontCamera()?"FRONT":"BACK") + "\n";
		g.drawString(s, 30, 50);
		s = "Mute: " + (ig.getMute()?"ON":"OFF") + "\n";
		g.drawString(s, 30, 70);
		s = "Flash: " + (ig.getFlash()?"ON":"OFF") + "\n";
		g.drawString(s, 30, 90);
		s = "Language: " + (ig.getLang())+ "\n";
		g.drawString(s, 30, 110);
	}

	private void drawImage(Graphics g)
    {
    	g.drawImage(img, 0, 0, null);
    }
    
    private void drawHelper(Graphics g) 
    {
    	g.setColor(Color.GREEN);
		if(ig.getNMM().getRot() == 0)
		{
			if(ig.getFrontCamera())
			{
				drawLine(g,(39/(double)100)*width, (100/(double)100)*height, (49/(double)100)*width, (66/(double)100)*height);
				drawLine(g,(98/(double)100)*width, (100/(double)100)*height, (68/(double)100)*width, (66/(double)100)*height);
			}
			else
			{

				drawLine(g,(30/(double)100)*width, (100/(double)100)*height, (45/(double)100)*width, (66/(double)100)*height);
				drawLine(g,(60/(double)100)*width, (100/(double)100)*height, (55/(double)100)*width, (66/(double)100)*height);
			}
		}
		if(ig.getNMM().getRot() == 45)
		{
			if(!ig.getFrontCamera())
			{
				drawLine(g,(30/(double)100)*width, (100/(double)100)*height, (0/(double)100)*width, (66/(double)100)*height);
			}
		}
		if(ig.getNMM().getRot() == 90)
		{
			
		}
		if(ig.getNMM().getRot() == 135)
		{
			if(ig.getFrontCamera())
			{
				drawLine(g,(25/(double)100)*width, (100/(double)100)*height, (-30/(double)100)*width, (66/(double)100)*height);
			}
		}
		if(ig.getNMM().getRot() == 180)
		{
			if(ig.getFrontCamera())
			{
				drawLine(g,(39/(double)100)*width, (100/(double)100)*height, (49/(double)100)*width, (66/(double)100)*height);
				drawLine(g,(98/(double)100)*width, (100/(double)100)*height, (68/(double)100)*width, (66/(double)100)*height);
			}
			else
			{
				drawLine(g,(30/(double)100)*width, (100/(double)100)*height, (45/(double)100)*width, (66/(double)100)*height);
				drawLine(g,(60/(double)100)*width, (100/(double)100)*height, (55/(double)100)*width, (66/(double)100)*height);
			}
		}
		if(ig.getNMM().getRot() == 225)
		{
			if(!ig.getFrontCamera())
			{
				drawLine(g,(30/(double)100)*width, (100/(double)100)*height, (0/(double)100)*width, (66/(double)100)*height);
			}
		}
		if(ig.getNMM().getRot() == 270)
		{
			
		}
		if(ig.getNMM().getRot() == 315)
		{
			if(ig.getFrontCamera())
			{
				drawLine(g,(25/(double)100)*width, (100/(double)100)*height, (-30/(double)100)*width, (66/(double)100)*height);
			}
		}
	}

	private void drawLine(Graphics g, double x1, double y1, double x2, double y2)
    {
    	g.drawLine((int)Math.round(x1), (int)Math.round(y1), (int)Math.round(x2), (int)Math.round(y2));
    }
}
