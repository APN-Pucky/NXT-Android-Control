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
	NxtCommander nc1;
	NxtCommander nc2;
	boolean txt = false;
	
	public KeyAndMouseListenerIG(NxtCommander nc1,NxtCommander nc2)
	{
		this.nc1 = nc1;
		this.nc2 = nc2;
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
			case('1'):nc1.speed(1);break;
			case('2'):nc1.speed(2);break;
			case('3'):nc1.speed(3);break;
			case('4'):nc1.speed(4);break;
			case('5'):nc1.speed(5);break;
			case('6'):nc1.speed(6);break;
			case('7'):nc1.speed(7);break;
			case('8'):nc1.speed(8);break;
			case('9'):nc1.speed(9);break;
			case('e'):nc1.forwardRight();break;
			case('q'):nc1.forwardLeft();break;
			case('y'):nc1.backwardLeft();break;
			case('c'):nc1.backwardRight();break;
			case('w'):nc1.forward();break;
			case('s'):nc1.backward();break;
			case('a'):nc1.left();break;
			case('d'):nc1.right();break;
			
			case('b'):nc1.rotateRst();nc2.rotateRst();break;
			case('i'):nc2.rotateR();
			case('k'):nc2.rotateR();
			case('l'):nc1.rotateR();
			case('j'):nc1.rotateL();
				
			case('f'):ig.toggleFlash();break;
			case('g'):ig.toggleCamera();break;
			case('h'):ig.toggleHelper();break;
			case('x'):ig.toggleInfo();break;
			case('v'):ig.toggleMute();break;
			case('t'):ig.changeLang();break;
			case(' '):nc1.stop();break;
			
		}
	}

}
