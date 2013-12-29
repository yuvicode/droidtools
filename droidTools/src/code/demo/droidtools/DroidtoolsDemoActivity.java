package code.demo.droidtools;

import java.io.File;
import org.json.JSONObject;

import com.google.inject.Inject;
import com.google.inject.util.Modules;

import code.droidtools.tools.AsyncHandler;
import code.droidtools.tools.DroidToolsModule;
import code.droidtools.tools.ErrorCodes;
import code.droidtools.tools.ErrorService;
import code.droidtools.tools.LocalFilesServiceFactory;
import code.droidtools.tools.LocalJsonFileAsyncReaderService;
import code.droidtools.tools.LocalTextFileAsyncWriterService;
import code.droidtools.tools.RemoteRestService;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;






public class DroidtoolsDemoActivity extends RoboActivity {

	@Inject 
	private LocalFilesServiceFactory filesService;
	
	@Inject 
	private RemoteRestService remoteRestSerive;
	
	@Inject 
	ErrorService errors;
	
	@InjectView(R.id.button1)
	Button add;

	private LocalJsonFileAsyncReaderService  jsonReader;
	private LocalTextFileAsyncWriterService  jsonWriter ;
	
	private final String CONTENT_FILE_NAME = "droiddemo.json";
	
	private final String CONTENT_URL = "http://localhost:8080/jsondemo/";
	
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
		super.onCreate(savedInstanceState);
		

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
		
		setContentView(R.layout.droidtools_demo);
		
		
		LinearLayout addView = (LinearLayout)getLayoutInflater().inflate(R.layout.add, null);
		
		
		
	}

	
	private void getRemote(){
		remoteRestSerive.get(CONTENT_URL, new AsyncHandler<JSONObject>(){

			@Override
			public void onError(ErrorCodes code) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(JSONObject success) {
				// TODO Auto-generated method stub
				
			}});
	}
	
	private void saveRemote(JSONObject obj){
		remoteRestSerive.put(CONTENT_URL, obj, new AsyncHandler<JSONObject>(){

			@Override
			public void onError(ErrorCodes code) {
					errors.showError(code);
			}

			@Override
			public void onSuccess(JSONObject success) {
				getRemote();
				
			}});
	}
	


}
