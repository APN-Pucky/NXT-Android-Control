package de.neuwirthinformatik.Alexander.NAC.GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.tilesources.OfflineOsmTileSource;

import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketFlash;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketLang;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketMute;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketSwapCamera;
import de.neuwirthinformatik.Alexander.NAC.NXT.NxtCommander;
import de.neuwirthinformatik.Alexander.NAC.NXT.NxtMapMarker;

public class ImageGUI extends JFrame
{
	
	ImageImplementIG imagepanel;
	JMapViewer jmv;
	JTextArea txt;
	NxtMapMarker nmm;
	boolean active;
	int millirepaint;
	boolean changed;
	int width;
	int height;
	boolean camera_f = true;
	boolean mute = false;
	boolean flash = false;
	String lang = "de_DE";
	
	public ImageGUI(int width, int height, int parm_millirepaint,NxtCommander nc)
	{
		setTitle("NxtAndroControl");
		KeyAndMouseListenerIG kamlig = new KeyAndMouseListenerIG(nc);
		WindowListenerIG wlig = new WindowListenerIG();
		//setLayout(null);
			jmv = new JMapViewer();
	    	jmv.setTileSource(new OfflineOsmTileSource("file:///E:/de.neuwirthinformatik.Alexander/Developing/OSM/Tiles/",0,18));
	    	jmv.setPreferredSize(new Dimension(300, 300));
	    	jmv.setZoom(14);
	    	jmv.setZoomContolsVisible(false);
	    	jmv.setDisplayPositionByLatLon(51.901332, 7.64007, jmv.getZoom());
	    	jmv.setLocation(width-jmv.getWidth(), 0);
	    	
	    	txt = new JTextArea(1,20);
	    	txt.addKeyListener(kamlig);
	    	txt.setSize(new Dimension(width, 100));
	    	txt.setLocation(0, height-txt.getHeight());
	    	txt.setEditable(false);
	    	
			imagepanel = new ImageImplementIG(this,width,height);
			imagepanel.setPreferredSize(new Dimension(width, height));
			imagepanel.setLocation(0,0);
			imagepanel.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.gridx = 2;
				c.gridy = 2;
				c.anchor = GridBagConstraints.FIRST_LINE_END;
				c.weightx = 1;
				c.weighty = 1;
			imagepanel.add(jmv,c);
				c.gridx = 2;
				c.gridy = 2;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.anchor = GridBagConstraints.LAST_LINE_START;
				c.weightx = 1;
				c.weighty = 1;
			imagepanel.add(txt,c);
	    add(imagepanel);
	    	
	    setVisible(true);
	    nmm = new NxtMapMarker(this,51.901332, 7.64007);
	    jmv.addMapMarker(nmm);
	    wlig.setIG(this);
	    kamlig.setIG(this);
	    addWindowListener(wlig);
	    //addKeyListener(kamlig);
	    //addMouseListener(kamlig);
	    this.millirepaint = parm_millirepaint;
	    this.active = true;
	    this.width =width;
	    this.height = height;
	    setSize(width,height);
	    pack();
	    new UpdateThreadIG(this).start();
	}
	
	public void updatePosition(double lat, double lon, double compass, double rot,double sonar)
	{
		nmm.setLat(lat);
		nmm.setLon(lon);
		nmm.setCom(compass);
		nmm.setRot(rot);
		nmm.setSonar(sonar);
		jmv.setDisplayPositionByLatLon(lat, lon, jmv.getZoom());
	}
	
	public NxtMapMarker getNMM()
	{
		return nmm;
	}
	
	public void toggleInfo() 
	{
		imagepanel.toggleInfo();
	}

	public void toggleHelper() 
	{
		imagepanel.toggleHelper();
	}
	
	public void toggleMute()
	{
		mute = !mute;
		COM.sendAndro(new PacketMute());
	}

	public void toggleFlash() 
	{
		flash = !flash;
		COM.sendAndro(new PacketFlash());
	}

	public void toggleCamera() 
	{
		camera_f = !camera_f;
		COM.sendAndro(new PacketSwapCamera());
	}
	
	public void changeLang()
	{
		switch(lang)
		{
			case("de_DE"):lang = "en_GB";COM.sendAndro(new PacketLang(lang));break;
			case("en_GB"):lang = "es_ES";COM.sendAndro(new PacketLang(lang));break;
			case("es_ES"):lang = "de_DE";COM.sendAndro(new PacketLang(lang));break;
		}
	}
	
	public boolean getFrontCamera()
	
	{
		return camera_f;
	}
	
	public boolean getFlash()
	{
		return flash;
	}
	
	public boolean getMute()
	{
		return mute;
	}
	
	public String getLang()
	{
		return lang;
	}
	
	public void updateImage(BufferedImage img)
	{
		changed = true;
		imagepanel.setImage(img);
	}
	
	public void start()
	{
		this.active = true;
	}
	
	public void stop()
	{
		this.active = false;
	}
}