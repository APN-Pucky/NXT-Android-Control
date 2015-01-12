package de.neuwirthinformatik.Alexander.NAC.NXT;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;

public class NXJCache 
{
	public static void setupNXTCache()
	{
		File root = Environment.getExternalStorageDirectory();

		try {
			String androidCacheFile = "nxj.cache";
			File mLeJOS_dir = new File(root + "/leJOS");
			if (!mLeJOS_dir.exists()) 
			{
				mLeJOS_dir.mkdir();
			}
			File mCacheFile = new File(root + "/leJOS/", androidCacheFile);

			if (root.canWrite() && !mCacheFile.exists()) 
			{
				FileWriter gpxwriter = new FileWriter(mCacheFile);
				BufferedWriter out = new BufferedWriter(gpxwriter);
				out.write("");
				out.flush();
				out.close();
			} 
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.err.println(e);
			System.exit(1);
		}
	}
}
