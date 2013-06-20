package code.droidtools.tools;



import code.droidtools.tools.ui.view.ComplexAlertDialogService;
import code.droidtools.tools.ui.view.SimpleAlertDialogService;
import code.droidtools.tools.ui.view.ViewAlertDialog;
import code.droidtools.tools.ui.view.YesNoAlertDialog;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;


/**
 * project Guice module. describes all object-interfaces relations.<br>
 * this should be used in any of the project activities before super.onCreate(savedInstanceState);
 * 
 * <br>USE with RoboGuice:  RoboGuice.setBaseApplicationInjector( getApplication(), RoboGuice.DEFAULT_STAGE, Modules.combine(RoboGuice.newDefaultRoboModule(getApplication()),new DroidToolsModule() )); 
 */
public class DroidToolsModule extends AbstractModule {

	@Override
	protected void configure() {
	
		// ui alert
		bind(SimpleAlertDialogService.class).to(YesNoAlertDialog.class);
				// ui alert
		bind(ComplexAlertDialogService.class).to(ViewAlertDialog.class);
		
		bind(ErrorHandler.class).to(SimpleErrorHandler.class);

		
		install(new FactoryModuleBuilder()
	     .implement(LocalJsonFileAsyncReaderService.class,ReadLocalJsonFile.class)
	     .implement(LocalTextFileAsyncWriterService.class,SaveLocalTextFile.class)
	     .implement(LocalByteArrayFileAsyncReaderService.class,ReadLocalBinaryFile.class)
   	     .implement(LocalBitmapAsyncReaderService.class,LocalBitmapReader.class)
	     .build(LocalFilesServiceFactory.class));
		
	}

}
