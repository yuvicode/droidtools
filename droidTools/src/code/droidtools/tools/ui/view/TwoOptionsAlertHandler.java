package code.droidtools.tools.ui.view;

import android.view.View;

/**
 * General purpose interface for 2 options alert dialog box. 
 *  
 *
 */
public interface TwoOptionsAlertHandler {
	
	/**
	 * handle method for the alert dialog first option (OK)
	 */
	public void onFirstSelection(View view);
	
	/**
	 * handle method for the alert dialog second option
	 */
	public void onSecondSelection();

}
