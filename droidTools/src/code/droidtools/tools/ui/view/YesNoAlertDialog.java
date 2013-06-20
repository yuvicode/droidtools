package code.droidtools.tools.ui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.google.inject.Inject;

public class YesNoAlertDialog implements SimpleAlertDialogService{
	
	protected AlertDialog alertDialog = null;
	protected TwoOptionsAlertHandler handler = null;
	protected String title = null,question= null;
	
	@Inject
	public YesNoAlertDialog()
	{
	
		
				
	}
	
	@Override
	public boolean create(Context context){
		
		if(title==null || question==null)
			return false;
		
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

			// set title
			alertDialogBuilder.setTitle(title);

			// set dialog message
			alertDialogBuilder
				.setMessage(question)
				.setCancelable(false)
				.setPositiveButton("YES",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						if(handler!=null)
							handler.onFirstSelection(null);
						dialog.cancel();
					}
				  })
				.setNegativeButton("NO",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						if(handler!=null)
							handler.onSecondSelection();
						dialog.cancel();
					}
				});
			
			
			alertDialog = alertDialogBuilder.create();
			
			return true;
	}
	@Override
	public void show(){
		if(alertDialog!=null)
			alertDialog.show();
		
	}

	@Override
	public void setHandler(TwoOptionsAlertHandler handler) {
		this.handler = handler;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
		
		if(alertDialog!=null)
			alertDialog.setTitle(title);
	}

	@Override
	public void setQuestion(String question) {
		this.question = question;
		
		if(alertDialog!=null)
			alertDialog.setMessage(question);
		
		
	}

	@Override
	public void setButtonsResource(int positiveBtnTxt, int negativeBtnTxt) {
		if(positiveBtnTxt > 0)
			alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setText(positiveBtnTxt);
		if(negativeBtnTxt >0)
			alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setText(positiveBtnTxt);
	}
	
	
			

}
