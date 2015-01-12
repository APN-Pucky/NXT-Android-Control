package de.neuwirthinformatik.Alexander.NAC.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketFlash;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketSpeakString;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketSwapCamera;
import de.neuwirthinformatik.Alexander.NAC.NXT.NxtCommander;

public class KeyAndMouseListenerIG implements KeyListener,MouseListener
{
	ImageGUI ig;
	NxtCommander nc;
	boolean txt = false;
	
	public KeyAndMouseListenerIG(NxtCommander nc)
	{
		this.nc = nc;
	}
	
	public void setIG(ImageGUI ig)
	{
		this.ig = ig;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent k) 
	{
		
	}

	@Override
	public void keyReleased(KeyEvent k) 
	{
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		e.consume();
		if(txt)
		{
			if(e.getKeyChar() == '\n')
			{
				txt = !txt;
				COM.sendAndro(new PacketSpeakString(ig.txt.getText()));
				ig.txt.setText("");
				return;
			}
			if(e.getKeyChar() == '\b')
			{
				if(ig.txt.getText().length() >0)ig.txt.setText(ig.txt.getText().substring(0, ig.txt.getText().length()-1));
				return;
			}
			if(e.getKeyChar() == '\t')
			{
				ig.txt.setText(ig.txt.getText() + " ");
				return;
			}
			ig.txt.setText(ig.txt.getText() + e.getKeyChar());
			return;
		}
		switch(e.getKeyChar())
		{
			case('\n'):txt = !txt;break;
			case('1'):nc.speed(1);break;
			case('2'):nc.speed(2);break;
			case('3'):nc.speed(3);break;
			case('4'):nc.speed(4);break;
			case('5'):nc.speed(5);break;
			case('6'):nc.speed(6);break;
			case('7'):nc.speed(7);break;
			case('8'):nc.speed(8);break;
			case('9'):nc.speed(9);break;
			case('e'):nc.rotateR();break;
			case('q'):nc.rotateL();break;
			case('y'):nc.backwardLeft();break;
			case('c'):nc.backwardRight();break;
			case('w'):nc.forward();break;
			case('s'):nc.backward();break;
			case('a'):nc.left();break;
			case('d'):nc.right();break;
			case('b'):nc.rotateRst();break;
			case('f'):ig.toggleFlash();break;
			case('g'):ig.toggleCamera();break;
			case('h'):ig.toggleHelper();break;
			case('x'):ig.toggleInfo();break;
			case('v'):ig.toggleMute();break;
			case('t'):ig.changeLang();break;
			case(' '):nc.stop();break;
			
		}
	}

}
