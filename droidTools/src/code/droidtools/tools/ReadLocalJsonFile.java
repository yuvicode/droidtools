package code.droidtools.tools;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Utility class to open a text file, read its content out of the UI thread
 * using {@link android.os.AsyncTask}. and parse it as a JSON document use: new
 * ReadLocalJsonFile(handler,ctx).execut(String fileName);
 * 
 * @param {@link AsyncHandler <JSONObject> } handler - the output handler
 * @param {@link Context } ctx - the activity context
 * 
 * 
 * 
 */
// TODO: handle the progress bar
public class ReadLocalJsonFile extends AsyncTask<String, Integer, JSONObject>
		implements LocalJsonFileAsyncReaderService {

	private final String tag = ReadLocalJsonFile.class.getSimpleName();
	private AsyncHandler <JSONObject> handler;
	private Context ctx;
	private ErrorCodes error;
	private boolean hasError = false;;
	private String arrayName;
	private FilenameFilter filter;
	private String[] fields;

	private final int SIZE = 8000; 
	@AssistedInject
	public ReadLocalJsonFile(@Assisted AsyncHandler <JSONObject> handler,
			Context ctx) {
		this.handler = handler;
		this.ctx = ctx;

	}

	@AssistedInject
	public ReadLocalJsonFile(@Assisted AsyncHandler <JSONObject> handler,
			@Assisted String arrayName, @Assisted FilenameFilter filter,
			@Assisted String[] fields, Context ctx) {
		this.handler = handler;
		this.ctx = ctx;
		this.arrayName = arrayName;
		this.filter = filter;
		this.fields = fields;

	}

	protected JSONObject doInBackground(String... fileName) {
		JSONObject result = null;

		if (ctx == null) {
			Log.d(tag, "Context is null");
			hasError = true;
			error = ErrorCodes.ERR_UNKNOWN;
			return null;
		}

		if (filter == null) // one file
			result = getJsonFile(fileName[0]);
		else {
			result = new JSONObject();
			JSONArray jsonArr = new JSONArray();
			try {
				result.put(arrayName, jsonArr);
			} catch (JSONException e) {
				hasError = true;
				Log.d(tag, e.getMessage());
				error = ErrorCodes.ERR_WRONG_FORMAT;
			}

			File dir;

			if (fileName != null && fileName.length > 0)
				dir = new File(fileName[0]);
			else
				dir = ctx.getFilesDir();

			String[] locaFiles = dir.list(filter);

			if (!hasError && locaFiles != null && locaFiles.length > 0) {
				for (String localFile : locaFiles) {

					jsonArr.put(getJsonFile(localFile));

				}
			}

		}

		return result;
	}

	protected JSONObject getJsonFile(String fileName) {
		JSONObject result = null;

		StringBuffer strContent = new StringBuffer();
		FileInputStream fis = null;
		
		try {

			FileInputStream f  = ctx.openFileInput(fileName);
			byte[] barray = new byte[SIZE];
			
			while ( (f.read( barray,0, SIZE )) != -1 ){
					strContent.append(new String(barray)); 
				
			}

		} catch (FileNotFoundException e) {

			error = ErrorCodes.ERR_FILE_NOT_FOUND;
			hasError = true;
			Log.d(tag, e.getMessage());
		} catch (IOException e) {
			hasError = true;
			error = ErrorCodes.ERR_CONNECTION_FAIL;
			Log.d(tag, e.getMessage());
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				hasError = true;
				error = ErrorCodes.ERR_CONNECTION_FAIL;
				Log.d(tag, ex.getMessage());
			}
		}

		if (!hasError) {
			try {
				
					
				result = new JSONObject(strContent.toString());
	

			} catch (JSONException e) {
				Log.d(tag, e.getMessage());
				hasError = true;
				error = ErrorCodes.ERR_WRONG_FORMAT;
			}
		}

		// select fields

		if (result != null && fields != null && fields.length > 0) {
			JSONObject tmp = new JSONObject();

			for (String name : fields) {
				boolean done = false;
				try { //1st attempt string
					tmp.put(name, result.getString(name));
					done = true;
				} catch (JSONException e) {

				}

				if (!done) {//2nd attempt int
					try {
						tmp.put(name, result.getInt(name));
						done = true;
					} catch (JSONException e) {

					}
				}
				if (!done) {
					try {// 3rd attempt object
						tmp.put(name, result.getJSONObject(name));
						done = true;
					} catch (JSONException e) {

					}
				}
			}
			result = tmp;
		}

		return result;
	}

	protected void onProgressUpdate(Integer... progress) {

	}

	protected void onPostExecute(JSONObject result) {

		if (error != null)
			handler.onError(error);
		else if (result == null) 
			handler.onError(ErrorCodes.ERR_UNKNOWN);
		else
			handler.onSuccess(result);
	}
}