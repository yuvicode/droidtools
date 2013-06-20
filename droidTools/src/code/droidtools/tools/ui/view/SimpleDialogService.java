package code.droidtools.tools.ui.view;

import android.content.Context;

/**
 * Basic interface for Dialog with "Title", "Message(question)" and 2 user options: "Yes"/"No" 
 *
 */
public interface SimpleDialogService {
	
	/**
	 * This method actually created the dialog. should be used ONLY AFTER user set ALL parameters
	 * @param context
	 * @return
	 */
	boolean create(Context context);
	/**
	 * Set the Dialog Title
	 * @param title
	 */
	public void setTitle(String title);
	/**
	 * Set the question the user is answering
	 * @param question
	 */
	public void setQuestion(String question);
	/**
	 * Set the internal Dialog handler, for user selection
	 * @param handler
	 */
	public void setHandler(TwoOptionsDialogHandler handler) ;
	
	/**
	 * change the Buttons labels from default "ok" and "no" to some thing ...
	 * @param positiveBtnTxt
	 * @param negativeBtnTxt
	 */	
	public void setButtonsResource(int positiveBtnTxt, int negativeBtnTxt);
	
	/**
	 * Present the Dialog on screen
	 */
	public void show();
}
