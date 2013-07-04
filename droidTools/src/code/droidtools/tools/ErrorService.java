package code.droidtools.tools;

import android.content.Context;

public interface ErrorService {
	
	public void showError( int id);
	public void showError( ErrorCodes errCode);
	
}
