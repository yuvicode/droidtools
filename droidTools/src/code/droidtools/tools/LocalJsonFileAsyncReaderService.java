package code.droidtools.tools;

import org.json.JSONObject;

import android.os.AsyncTask;

public interface LocalJsonFileAsyncReaderService {
	/**
	 * @param args - One (root) directory name for a list of files.<br>
	 * Or one single file name
	 * 
	 */
	public AsyncTask<String  , Integer, JSONObject > execute(String ... args);
}
