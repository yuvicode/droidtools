package code.demo.droidtools;

import java.io.File;

import org.droid.tools.R;
import org.droid.tools.R.layout;
import org.droid.tools.R.menu;
import org.json.JSONObject;

import com.google.inject.Inject;
import com.google.inject.util.Modules;

import code.droidtools.tools.AsyncHandler;
import code.droidtools.tools.DroidToolsModule;
import code.droidtools.tools.ErrorCodes;
import code.droidtools.tools.LocalFilesServiceFactory;
import code.droidtools.tools.LocalJsonFileAsyncReaderService;
import code.droidtools.tools.LocalTextFileAsyncWriterService;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;





public class DroidtoolsDemoActivity extends RoboActivity {

	@Inject 
	private LocalFilesServiceFactory filesService;

	private LocalJsonFileAsyncReaderService  jsonReader;
	private LocalTextFileAsyncWriterService  jsonWriter ;
	
	private final String CONTENT_FILE_NAME = "droiddemo.json";
	
	private String path;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		path = getApplication().getFilesDir()+File.separator+CONTENT_FILE_NAME;
		
		// enables to add more bindings to the RoBoGuice therefore to use one
		// injector in the project
		RoboGuice.setBaseApplicationInjector(getApplication(),
						RoboGuice.DEFAULT_STAGE, Modules.combine(
								RoboGuice.newDefaultRoboModule(getApplication()),
								new DroidToolsModule()));
		

		  jsonReader = filesService.getJsonFileReaderService(new AsyncHandler<JSONObject>(){

			@Override
			public void onError(ErrorCodes code) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(JSONObject success) {
				
				
			}
			
		});
		
		 jsonWriter = filesService.getFileWriter(new AsyncHandler<JSONObject>(){

			@Override
			public void onError(ErrorCodes code) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(JSONObject success) {
				// TODO Auto-generated method stub
				
			}});
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.droidtools_demo);
		
		
	}



}
