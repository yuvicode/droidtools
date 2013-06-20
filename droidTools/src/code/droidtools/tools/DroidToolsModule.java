package code.droidtools.tools;



import code.droidtools.tools.ui.view.ComplexDialogService;
import code.droidtools.tools.ui.view.SimpleDialogService;
import code.droidtools.tools.ui.view.ViewDialog;
import code.droidtools.tools.ui.view.YesNoDialog;

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
		bind(SimpleDialogService.class).to(YesNoDialog.class);
				// ui alert
		bind(ComplexDialogService.class).to(ViewDialog.class);
		
		bind(ErrorHandler.class).to(SimpleErrorHandler.class);

		
		install(new FactoryModuleBuilder()
	     .implement(LocalJsonFileAsyncReaderService.class,ReadLocalJsonFile.class)
	     .implement(LocalTextFileAsyncWriterService.class,SaveLocalTextFile.class)
	     .implement(LocalByteArrayFileAsyncReaderService.class,ReadLocalBinaryFile.class)
   	     .implement(LocalBitmapAsyncReaderService.class,LocalBitmapReader.class)
	     .build(LocalFilesServiceFactory.class));
		
	}

}
