package de.neuwirthinformatik.Alexander.NAC.GUI;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketStop;

public class WindowListenerIG implements WindowListener
{
	ImageGUI ig;
	
	public void setIG(ImageGUI ig)
	{
		this.ig = ig;
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) 
	{
		ig.active = true;
	}

	@Override
	public void windowClosed(WindowEvent arg0) 
	{
		ig.active = false;
	}

	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		ig.active = false;
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) 
	{
		ig.active = true;
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) 
	{
		ig.active = true;
	}

	@Override
	public void windowIconified(WindowEvent arg0) 
	{
		ig.active = true;
	}

	@Override
	public void windowOpened(WindowEvent arg0) 
	{
		ig.active = true;
	}

}
