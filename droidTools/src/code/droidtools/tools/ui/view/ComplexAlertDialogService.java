package code.droidtools.tools.ui.view;

import android.view.View;

/**
 *Interface for Dialog that present an internal View.
 *
 */
public interface ComplexAlertDialogService extends SimpleAlertDialogService{

	/**
	 * Set the Dialog internal View
	 * @param view
	 */
	public void setView(View view);
	
	
	
	public void isComplete(boolean complete);
	
}
