package code.droidtools.tools;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 *  Utility class to Save a text file out of the UI thread using {@link android.os.AsyncTask}.<br>
 *  use: new SaveLocalTextFile(handler,ctx).execut(String fileName,String FileContent);
 *  
 *  @param {@link AsyncHandler <JSONObject>} handler - the output handler
 *  @param {@link Context } ctx - the activity context
 *
 */
public class SaveLocalTextFile extends AsyncTask<String  , Integer, Void > implements LocalTextFileAsyncWriterService{
	
	private final String LTAG = SaveLocalTextFile.class.getSimpleName();
	protected AsyncHandler <JSONObject> handler;
	private Context ctx;
	protected ErrorCodes error;
	protected boolean hasError = false; ;
	@AssistedInject
	public SaveLocalTextFile(@Assisted AsyncHandler <JSONObject> handler,Context ctx){
		this.handler = handler;
		this.ctx = ctx;
		
	}
    protected  Void doInBackground(String... file) {

    	if (ctx == null){
    		Log.d(LTAG,"Context is null");
			hasError = true;
			error = ErrorCodes.ERR_UNKNOWN;
			return null;
    	}
    	FileOutputStream fos;
		try {
			//TODO: warning if file exists ?
			 fos = ctx.openFileOutput(file[0], Context.MODE_PRIVATE);
			fos.write(file[1].getBytes());
			fos.close();
			
		} catch (FileNotFoundException e) {
		
			Log.d(LTAG, e.getMessage());
			hasError = true;
			error = ErrorCodes.ERR_FILE_NOT_FOUND;
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		finally{
			
		}
		return null;
		   	
     }

    protected void onProgressUpdate(Integer... progress) {
       
    }

    protected void onPostExecute(Void c) {
    	if(handler!=null){
        	if (hasError){
        		if(error!=null)
        			handler.onError(error);
        		else
        			handler.onError(ErrorCodes.ERR_UNKNOWN);
        	}
        	else
        		handler.onSuccess(null);
    	}

    }
}