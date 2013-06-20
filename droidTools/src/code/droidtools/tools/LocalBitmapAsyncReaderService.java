package code.droidtools.tools;



import android.graphics.Bitmap;
import android.os.AsyncTask;

public interface LocalBitmapAsyncReaderService {
	
	public AsyncTask<String  , Integer, Bitmap > execute(String... filename);

}
