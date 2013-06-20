package code.droidtools.tools;


import java.io.FilenameFilter;

import org.json.JSONObject;

import android.graphics.Bitmap;

import com.google.inject.assistedinject.Assisted;


/**
 * Factory interface to access local file local files services.
 * For use (@)Inject in you constructor. Implementation mapping is described in {@link DroidToolsModule}
 * 
 */
public interface LocalFilesServiceFactory {

	/**
	 * Use this case when you want to write text to local file system
	 * @param clbc - handler for success/fail cases
	 * @return LocalFileAsyncWriterService
	 * 
	 * use: new getFileWriter(clbc).execut(String fileName,String FileContent);
	 */
	LocalTextFileAsyncWriterService getFileWriter(AsyncHandler <JSONObject> clbc);
	/**
	 * Use this case when you want to read a single text from local file system
	 * @param clbc - handler for success/fail cases
	 * @return LocalFileAsyncWriterService
	 * 
	 * use: new getJsonFileReaderService(clbc).execut(String fileName);
	 */
	LocalJsonFileAsyncReaderService getJsonFileReaderService(AsyncHandler <JSONObject> clbc);
	/**
	 * Use this case when you want to read a few json files from local file system
	 *   <pre>
	 *  {@code final FilenameFilter filter =  new FilenameFilter(){
	 *		@Override
	 *	public boolean accept(File dir, String filename) {
	 *		return filename.endsWith(TARGET_SUFFIX); 
	 *		}
	 *
	 *	};} 
	 *	</pre>
	 * @param clbc - handler for success/fail cases
	 * @param  arrayName - the name of the files array
	 * @param  filter - files filter (example ".json") {@link FilenameFilter}
	 * @param  String [] fields - list of field names to return. If empty return all
	 * @return LocalFileAsyncWriterService
	 * 
	 * use: new getJsonFilesReaderService(clbc).execut(String dirName);
	 */
	LocalJsonFileAsyncReaderService getJsonFilesReaderService(AsyncHandler <JSONObject> clbc,String arrayName,  String [] fields,FilenameFilter filter);
	
	
	/**
	 * Use this case when you want to read a single text from local file system
	 * @param clbc - handler for success/fail cases
	 * @return LocalBinaryFileAsyncReaderService
	 * 
	 * use:  getBinaryFileReaderService(clbc).execut(String fileName);
	 */
	LocalByteArrayFileAsyncReaderService getBinaryFileReaderService(AsyncHandler <byte[]> clbc);
	
	
	
	/**
	 * @param handler
	 * @param height - the target Bitmap required height
	 * @param width - the  target Bitmap required width
	 * @return
	 * Use  getLocalBitmapAsyncReaderService(handler,height,width).execute(filePath);
	 */
	LocalBitmapAsyncReaderService getLocalBitmapAsyncReaderService(AsyncHandler <Bitmap> handler,  @Assisted("height") int height, @Assisted("width") int width);
}
