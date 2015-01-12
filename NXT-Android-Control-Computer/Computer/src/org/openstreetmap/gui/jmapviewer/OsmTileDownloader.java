package org.openstreetmap.gui.jmapviewer;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class OsmTileDownloader 
{

	public static void download(final int zoom,final int tilex,final int tiley,final String path) 
	{
		new Thread(new Runnable(){public void run()
			{
				try
				{
					System.err.println("downloading...");
					
					URL url = new URL("http://c.tile.openstreetmap.org/" +zoom+"/"+tilex+"/"+tiley+".png");
					InputStream in = new BufferedInputStream(url.openStream());
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					int n = 0;
					while (-1!=(n=in.read(buf)))
					{
						out.write(buf, 0, n);
					}
					out.close();
					in.close();
					byte[] response = out.toByteArray();
					File f = new File(path);
					if(f.exists())return;
					f.getParentFile().mkdirs();
					f.createNewFile();
					FileOutputStream fos = new FileOutputStream(f);
					fos.write(response);
					fos.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return;
				}
			}
		}).start();
	}
}
