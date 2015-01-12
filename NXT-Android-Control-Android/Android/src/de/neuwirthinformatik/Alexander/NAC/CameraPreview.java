package de.neuwirthinformatik.Alexander.NAC;

import java.io.IOException;

import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import de.neuwirthinformatik.Alexander.NAC.COM.COM;
import de.neuwirthinformatik.Alexander.NAC.GLOBAL.Packets.PacketImage;
import de.neuwirthinformatik.Alexander.NAC.PORT.PacketAndroImage;

public class CameraPreview  implements SurfaceHolder.Callback, Camera.PreviewCallback
{
	public Camera mCamera;
	SurfaceHolder mSurfHolder;
	short cam;
	
	public CameraPreview()
	{
		cam =0;
	}
	
	@Override
	public void onPreviewFrame(byte[] data, Camera camera)
	{
		Camera.Parameters parameters = camera.getParameters();
		Size size = parameters.getPreviewSize();
		YuvImage image = new YuvImage(data, parameters.getPreviewFormat(), size.width, size.height, null);
		PacketImage pi = new PacketAndroImage(cam,image);
		COM.sendPC(pi);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3)
	{
		mSurfHolder = arg0;
    
		paramCamera();
    
		mCamera.startPreview();
	}
  
	@Override
	public void surfaceCreated(SurfaceHolder arg0)
	{
		mSurfHolder = arg0;
		
		mCamera = Camera.open(cam);
		
		paramCamera();
		
		
		try
		{
			mCamera.setPreviewDisplay(mSurfHolder);
			mCamera.setPreviewCallback(this);
		}
		catch (IOException e)
		{
			mCamera.release();
			mCamera = null;
		}
	}
  
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0)
	{
		mCamera.setPreviewCallback(null);
		mCamera.stopPreview();
		mCamera.release();
		mCamera = null;
	}
	
	private void paramCamera()
	{
		Parameters parameters;
		parameters = mCamera.getParameters();
		parameters.setPreviewFpsRange(20, 30);
		String NowFocusMode = parameters.getFocusMode();
		if ( NowFocusMode != null )
		{
			parameters.setFocusMode("auto");
		}
		parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
		mCamera.setParameters(parameters);
	}
	
	public void swapCamera()
	{
		cam = (short) ((cam==0)?1:0);
		this.surfaceDestroyed(mSurfHolder);
		this.surfaceCreated(mSurfHolder);
		mCamera.startPreview();
	}

	public void toggleFlash() 
	{
		Camera.Parameters cp = mCamera.getParameters();
		cp.setFlashMode(cp.getFlashMode().equals(Camera.Parameters.FLASH_MODE_TORCH)?Camera.Parameters.FLASH_MODE_OFF:Camera.Parameters.FLASH_MODE_TORCH);
		mCamera.setParameters(cp);
	}
}
