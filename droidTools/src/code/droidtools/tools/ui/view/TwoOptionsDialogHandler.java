package code.droidtools.tools.ui.view;

import android.view.View;

/**
 * General purpose interface for 2 options  dialog box. 
 *  
 *
 */
public interface TwoOptionsDialogHandler {
	
	/**
	 * handle method for the  dialog first option (OK)
	 */
	public void onFirstSelection(View view);
	
	/**
	 * handle method for the dialog second option
	 */
	public void onSecondSelection(View view);

}
