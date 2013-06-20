package code.droidtools.tools;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;


public class LocalBitmapReader extends AsyncTask<String, Integer, Bitmap> implements LocalBitmapAsyncReaderService {

	
	private AsyncHandler<Bitmap> handler;
	private ErrorHandler error;
	private boolean hasError = false;
	int height, width;

	@AssistedInject
	public LocalBitmapReader(@Assisted AsyncHandler<Bitmap> handler, @Assisted ErrorHandler error,  @Assisted("height") int height, @Assisted("width") int width) {
		this.handler = handler;
		this.height = height;
		this.width = width;
		this.error = error;

	}

	protected Bitmap doInBackground(String ...fileName) {


		Bitmap myBitmap = null;
		try {
			myBitmap = FileUtils.decodeSampledBitmapFromFile(fileName[0], width, height);
		} catch (Error e) {
			// memory error
			hasError  =true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myBitmap;
				
	}

	protected void onProgressUpdate(Integer... progress) {

	}

	protected void onPostExecute(Bitmap result) {

		if (hasError ){
			
			handler.onError(ErrorCodes.ERR_NO_MEMORY);
		}
		else
			handler.onSuccess(result);
	}

	
}