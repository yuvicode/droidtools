package code.droidtools.tools;


import android.os.AsyncTask;

public interface LocalByteArrayFileAsyncReaderService {

	/**
	 * @param args - file name
	 * 
	 */
	public AsyncTask<String  , Integer, byte[] > execute(String ... args);
}
