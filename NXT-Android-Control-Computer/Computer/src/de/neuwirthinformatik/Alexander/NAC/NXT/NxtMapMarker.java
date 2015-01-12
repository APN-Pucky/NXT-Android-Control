package de.neuwirthinformatik.Alexander.NAC.NXT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import de.neuwirthinformatik.Alexander.NAC.GUI.ImageGUI;

public class NxtMapMarker implements MapMarker
{
	double lon;
	double lat;
	double com =0;
	double rot =0;
	double sonar;
	double size = 30;
	ImageGUI ig;
	
	public NxtMapMarker(ImageGUI ig, double lat, double lon)
	{
		this.ig = ig;
		setLon(lon);
		setLat(lat);
	}
	
	public void setSize(double size)
	{
		this.size = size;
	}
	
	public void setLat(double lat)
	{
		this.lat = lat;
	}
	
	public void setLon(double lon)
	{
		this.lon = lon;
	}
	
	public void setCom(double com)
	{
		this.com = (((com%360)+360)%360);
	}
	
	public void setRot(double rot)
	{
		this.rot = (((rot%360)+360)%360);
	}

	public void setSonar(double sonar) 
	{
		this.sonar = sonar;
	}
	
	public double getCom()
	{
		return com;
	}
	
	public double getRot()
	{
		return rot;
	}
	
	@Override
	public double getLat() 
	{
		return lat;
	}

	@Override
	public double getLon() 
	{
		return lon;
	}
	
	public double getSonar()
	{
		return sonar;
	}

	@Override
	public void paint(Graphics g, Point position) 
	{
		double angle = 45 + (ig.getFrontCamera()?0:180);
		double x = position.getX();
		double y = position.getY();
		g.setColor(Color.RED);
		drawLine(g,x,y,x+Math.sin(calc(angle+com))*size,y-Math.cos(calc(angle+com))*size);
		drawLine(g,x,y,x+Math.sin(calc(-angle+com))*size,y-Math.cos(calc(-angle+com))*size);
		drawLine(g,x+Math.sin(calc(angle+com))*size,y-Math.cos(calc(angle+com))*size,x+Math.sin(calc(-angle+com))*size,y-Math.cos(calc(-angle+com))*size);
		g.setColor(Color.BLACK);
		
		drawOval(g, x-size/2, y-size/2, size, size);
		drawLine(g,x,y,x+Math.sin(calc(com+rot))*size/2,y-Math.cos(calc(com+rot))*size/2);
	}
	
	double calc(double angle)
	{
		return Math.toRadians((((angle)%360)+360)%360);
	}
	
	void drawLine(Graphics g, double x1, double y1, double x2, double y2)
	{
		g.drawLine((int)Math.round(x1),(int) Math.round(y1), (int)Math.round(x2),(int) Math.round(y2));
	}
	
	void drawOval(Graphics g, double x1, double y1, double w, double h)
	{
		g.drawOval((int)Math.round(x1),(int) Math.round(y1), (int)Math.round(w),(int) Math.round(h));
	}
}
