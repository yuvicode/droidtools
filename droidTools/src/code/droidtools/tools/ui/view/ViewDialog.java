package code.droidtools.tools.ui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;


public class ViewDialog extends YesNoDialog implements ComplexDialogService{

	View view = null;
	boolean isComplete = false;
	
	@Override
	public boolean create(Context context) {
		
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

			// set title
			alertDialogBuilder.setTitle(title);
			alertDialogBuilder.setView(view);

			// set dialog message
			alertDialogBuilder
								.setCancelable(false)
				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						if(handler!=null)
							handler.onFirstSelection(view);
						
						if(isComplete)
							dialog.dismiss();
						
					}
				  })
				.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						if(handler!=null)
							handler.onSecondSelection(view);
						dialog.cancel();
						
					}
				});
			
			
			alertDialog = alertDialogBuilder.create();
			
			return true;
	}



	@Override
	public void setView(View view) {
		this.view = view;
		
	}



	@Override
	public void isComplete(boolean complete) {
		isComplete = complete;
		
	}

}
