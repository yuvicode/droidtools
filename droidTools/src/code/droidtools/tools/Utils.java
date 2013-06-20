package code.droidtools.tools;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

public class Utils {
	
	
	/**
	 * This method convets dp unit to equivalent device specific value in pixels. 
	 * 
	 * source: stackoverflow.com/questions/4605527/converting-pixels-to-dp-in-android
	 * 
	 * @param dp A value in dp(Device independent pixels) unit. Which we need to convert into pixels
	 * @param context Context to get resources and device specific display metrics
	 * @return A float value to represent Pixels equivalent to dp according to device
	 */
	public static float convertDpToPixel(float dp,Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi/160f);
	    return px;
	}
	/**
	 * This method converts device specific pixels to device independent pixels.
	 *
	 *  * source: stackoverflow.com/questions/4605527/converting-pixels-to-dp-in-android
	 *  
	 * @param px A value in px (pixels) unit. Which we need to convert into db
	 * @param context Context to get resources and device specific display metrics
	 * @return A float value to represent db equivalent to px value
	 */
	public static float convertPixelsToDp(float px,Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float dp = px / (metrics.densityDpi / 160f);
	    return dp;

	}

	
	
	public static void sendNotificationString(String msg,Context ctx ){
	    Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();  
	    
	}
	
	
	public static void sendNotificationById(int msgId,Context ctx ){
		
		String msg = ctx.getString(msgId);
		if(msg!=null)
		    Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();

	}
	
	
	/**
	 * Hide/show view by changing View visibility attribute
	 * @param view
	 */
	public static void toggleView(View view){
		if(view.getVisibility() == View.VISIBLE)
			view.setVisibility(View.GONE);
		else
			view.setVisibility(View.VISIBLE);
	}



}
