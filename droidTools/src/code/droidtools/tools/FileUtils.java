package code.droidtools.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.webkit.MimeTypeMap;

public class FileUtils {

	public static final String MIME_JPEG = "image/jpeg";
	public static final String MIME_PNG = "image/png";
	public static final String MIME_GIF = "image/gif";

	/**
	 * This is the name of the directory to share files with other applications
	 */

	/**
	 * Got that from the web ...
	 * Create a Bitmap based on file and desired dimensions Copy for Android
	 * documentation
	 * 
	 * @param imagePath
	 *            - path of img file
	 * @param reqWidth
	 *            - the required width (px)
	 * @param reqHeight
	 *            - the required height (px)
	 * @return bitmap
	 */
	public static Bitmap decodeSampledBitmapFromFile(String imagePath,
	        int reqWidth, int reqHeight) {
		if(imagePath == null)
			return null;

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(imagePath, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    
	    Bitmap decodedFile = null;
	    
	    try {
			decodedFile = BitmapFactory.decodeFile(imagePath,  options);
		} catch (OutOfMemoryError  e1) {
			System.gc();
			
			try {
				decodedFile = BitmapFactory.decodeFile(imagePath,  options);
			} catch (OutOfMemoryError  e2) {
				Log.d("FileUtils", e2.getMessage());
			}
		}
	    
	    return decodedFile;
	}

	/**
	 * Calculates if the image need to be resized. Copy for Android
	 * documentation
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}

	/**
	 * copy file
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void copyFile(File source, File target) throws IOException {

		InputStream in = new FileInputStream(source);
		OutputStream out = new FileOutputStream(target);

		byte[] buf = new byte[1024];
		int len;

		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}

		in.close();
		out.close();
	}

	
	public static boolean isImageFile(String uri) {

		String mime = getMimeType(uri).toLowerCase();

		if (mime.startsWith("image/"))
			return true;
		else
			return false;

	}
	
	/**
	 * File mime type
	 * @param file name , path
	 * @return - mime type
	 */
	public static String getMimeType(String url)
	{
	    String type = "";
        MimeTypeMap mime = MimeTypeMap.getSingleton();

	    String extension = MimeTypeMap.getFileExtensionFromUrl(url);
	    if (extension != null && extension.length()>0) {
	        type = mime.getMimeTypeFromExtension(extension);
	       
	    }
	    else{
	    	extension = getLocalFileName(url);
	    	type = mime.getMimeTypeFromExtension(extension); 
	    	
	    }
	    
	    if(type==null)
	    	return "";
    
	    return type;
	}
	
	public static String getLocalFileName(String uri){
		String name = "";
		
		if(uri == null || uri.length()==0)
			return name;
		
		name = uri.substring(uri.lastIndexOf(".")+1);
		
		return name;
	}

}


