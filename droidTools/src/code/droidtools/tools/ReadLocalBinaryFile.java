package code.droidtools.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;


/**
 * Utility class to open a text file, read its content out of the UI thread
 * using {@link android.os.AsyncTask}. and parse it as a JSON document use: new
 * LocalBinaryFileAsyncReaderService(handler,ctx).execut(String fileName);
 * 
 * @param {@link AsyncHandler} handler - the output handler
 * @param {@link Context } ctx - the activity context
 * 
 * 
 * 
 */
// TODO: handle the progress bar
public class ReadLocalBinaryFile extends AsyncTask<String, Integer, byte[]>
		implements LocalByteArrayFileAsyncReaderService {

	private final String tag = ReadLocalJsonFile.class.getSimpleName();
	private AsyncHandler <byte[]> handler;
	private Context ctx;
	private ErrorCodes error;
	private boolean hasError = false;;



	@AssistedInject
	public ReadLocalBinaryFile(@Assisted AsyncHandler<byte[]> handler,
			Context ctx) {
		this.handler = handler;
		this.ctx = ctx;

	}


	protected byte[] doInBackground(String... fileName) {
		byte [] result = null;

		if (ctx == null) {
			Log.d(tag, "Context is null");
			hasError = true;
			error = ErrorCodes.ERR_UNKNOWN;
			return null;
		}

		hasError = false;
		result = getBytes(fileName[0]);


		return result;
	}

	
	
	  private byte[] getBytes(String fileName){
		  
	    File file = new File(fileName);
	
	    byte[] result = new byte[(int)file.length()];
	    try {
	      InputStream input = null;
	      try {
	        int totBytesRead = 0;
	        input = new BufferedInputStream(ctx.openFileInput(fileName));
	        while(totBytesRead < result.length){
	          int bytesRemain = result.length - totBytesRead;
	          int bytesRead = input.read(result, totBytesRead, bytesRemain); 
	          if (bytesRead > 0){
	            totBytesRead = totBytesRead + bytesRead;
	          }
	        }
	        
	      }
	      finally {
	      
	        input.close();
	      }
	    }
	    catch (FileNotFoundException ex) {
	    	hasError = true;
			error = ErrorCodes.ERR_FILE_NOT_FOUND;
	    }
	    catch (IOException ex) {
	    	hasError = true;
			error = ErrorCodes.ERR_WRONG_FORMAT;
	    }
	    return result;
	  }
	  
	  
	protected void onProgressUpdate(Integer... progress) {

	}

	protected void onPostExecute(byte [] result) {

		if (hasError && error != null)
			handler.onError(error);
		else if (result == null) 
			handler.onError(ErrorCodes.ERR_UNKNOWN);
		else
			handler.onSuccess(result);
	}
}