package code.droidtools.tools;

import android.os.AsyncTask;


public interface LocalTextFileAsyncWriterService {
	/**
	 * Write text file to the local file system (application "files" root)
	 * 
	 *  @param arg[0] - file name
	 *  @param arg[1] - file content
	 *
	 */
	public AsyncTask<String  , Integer, Void > execute(String ... args);

}
