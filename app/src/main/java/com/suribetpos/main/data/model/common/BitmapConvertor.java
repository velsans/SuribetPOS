package com.suribetpos.main.data.model.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
 
 
import android.os.AsyncTask;
import android.os.Environment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.Toast;

import com.suribetpos.main.data.fcm.CrashAnalytics;


public class BitmapConvertor{ 
 
 
	private int mDataWidth;
	private byte mRawBitmapData[];
	private byte[] mDataArray;
	private static final String TAG = "BitmapConvertor";
	private ProgressDialog mPd;
	private Context mContext;
	private int mWidth, mHeight;
	private String mStatus;
	private String mFileName;
	 
	public BitmapConvertor() 
	{
		// TODO Auto-generated constructor stub 
		
	} 
	 
	public BitmapConvertor(Context context) {
		// TODO Auto-generated constructor stub 
		mContext = context;
	} 
 
 
/** 
 * Converts the input image to 1bpp-monochrome bitmap 
 * @param inputBitmap : Bitmpa to be converted 
 * @param fileName : Save-As filename 
 * @return :  Returns a String. Success when the file is saved on memory card or error. 
 */ 
    public String convertBitmap(Bitmap inputBitmap, String fileName){
		 
    	mWidth = 124; //inputBitmap.getWidth();
    	mHeight = 124; //inputBitmap.getHeight();
    	mFileName = fileName;
    	mDataWidth=((mWidth+31)/32)*4*8;
    	mDataArray = new byte[(mDataWidth * mHeight)];
    	mRawBitmapData = new byte[(mDataWidth * mHeight) / 8];
    	ConvertInBackground convert = new ConvertInBackground();
    	//convert.execute(inputBitmap);
    	
    	
    	convertArgbToGrayscale(inputBitmap, mWidth, mHeight);
    	//createBlackAndWhite(inputBitmap);
    	createRawMonochromeData(); 
    	mStatus = saveImage(mFileName, mWidth, mHeight);
    	
    	
    	
    	return mStatus;
    	  
    	 
    } 
    
    public void createBlackAndWhite(Bitmap src) 
    {
        int width = src.getWidth();
        int height = src.getHeight();
     
        Bitmap bmOut = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
     
        final float factor = 255f;
        final float redBri = 0.2126f;
        final float greenBri = 0.2126f;
        final float blueBri = 0.0722f;
     
        int length = width * height;
        int[] inpixels = new int[length];
        int[] oupixels = new int[length];
     
        src.getPixels(inpixels, 0, width, 0, 0, width, height);
     
        int point = 0;
        for(int pix: inpixels){
            int R = (pix >> 16) & 0xFF;
            int G = (pix >> 8) & 0xFF;
            int B = pix & 0xFF;
     
            float lum = (redBri * R / factor) + (greenBri * G / factor) + (blueBri * B / factor);
     
            if (lum > 0.4) {
            	mDataArray[point] = 0xFFFFFFFF;
            }else{ 
            	mDataArray[point] = (byte) 0xFF000000;
            } 
            point++;
        } 
    
    } 
 
	private void convertArgbToGrayscale(Bitmap bmpOriginal, int width, int height)
	{
    	int pixel;
    	int k = 0;
    	int A = 0, B=0,G=0,R=0;
    	int Gray = 0;
    	try
    	{ 
    	for(int x = 0; x < height; x++) 
    	{
            for(int y = 0; y < width; y++, k++) 
            {
                // get one pixel color 
                pixel = bmpOriginal.getPixel(y, x);
                 
                // retrieve color of all channels 
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                // take conversion up to one single value by calculating pixel intensity. 
               Gray = (int)(0.299  * R + 0.55 * G + 0.1 * B);
               // Gray = (int)(0.2126 * R + 0.7152 * G + 0.0722 * B); 
                // set new pixel color to output bitmap 
                if (Gray < 128) 
                {
					mDataArray[k] = 0;
				} 
                else 
				{ 
					mDataArray[k] = 1;
				} 
            } 
            if(mDataWidth>width)
            {
				for(int p=width;p<mDataWidth;p++,k++)
				{
					mDataArray[k]=1;
				} 
			} 
        } 
    	}
    	catch (Exception ex) {
			CrashAnalytics.CrashReport(ex);
		}
    } 
     
    private void createRawMonochromeData()
    { 
    	int length = 0;
    	for (int i = 0; i < mDataArray.length; i = i + 8) {
			byte first = mDataArray[i];
			for (int j = 0; j < 7; j++) {
				byte second = (byte) ((first << 1) | mDataArray[i + j]);
				first = second;
			} 
			mRawBitmapData[length] = first;
			length++;
		} 
    } 
    
     
    private String saveImage(String fileName, int width, int height) 
    {
    	FileOutputStream fileOutputStream;
    	BMPFile bmpFile = new BMPFile();
		File file = new File(Environment.getExternalStorageDirectory(), fileName + ".bmp");
		try { 
			file.createNewFile();
			fileOutputStream = new FileOutputStream(file);
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			return "Memory Access Denied"; 
		} 
		
		bmpFile.saveBitmap(fileOutputStream, mRawBitmapData, width, height);
		return "Success"; 
    } 
 
 
    class ConvertInBackground extends AsyncTask<Bitmap, String, Void>
    {
 
 
		@Override 
		protected Void doInBackground(Bitmap... params) {
			// TODO Auto-generated method stub 
			convertArgbToGrayscale(params[0], mWidth, mHeight);
	    	createRawMonochromeData(); 
	    	mStatus = saveImage(mFileName, mWidth, mHeight);
			return null; 
		} 
 
 
		 
		@Override 
		protected void onPostExecute(Void result) 
		{
			// TODO Auto-generated method stub 
			mPd.dismiss();
            Toast.makeText(mContext, "Image created successfully. Please check in sdcard", Toast.LENGTH_LONG).show();
		} 
 
 
 
 
		@Override 
		protected void onPreExecute() 
		{ 
			// TODO Auto-generated method stub 
			mPd= ProgressDialog.show(mContext, "Converting Image", "Please Wait", true, false, null);
		} 
 
 
		 
    	 
    }
}
