package code.droidtools.tools;

import com.google.inject.Inject;

import android.content.Context;
import android.widget.Toast;

public class SimpleToastError implements ErrorService {

	Context mContext ;
	@Inject
	public SimpleToastError(Context context ){
		mContext = context;
	}
	@Override
	public void showError( int resId) {
		
		Toast.makeText(mContext, resId, Toast.LENGTH_LONG).show();

	}
	@Override
	public void showError(ErrorCodes errCode) {
		Toast.makeText(mContext, errCode.toString(), Toast.LENGTH_LONG).show();
		
	}

}
