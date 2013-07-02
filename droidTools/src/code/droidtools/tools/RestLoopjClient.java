package code.droidtools.tools;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;
import android.util.Log;

import com.google.inject.Inject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

/**
 * REST client wrapper for  loopj (http://loopj.com/android-async-http/)
 *
 */
public class RestLoopjClient implements RemoteRestService {

	AsyncHttpClient remoteClient;

	private final String tag = RestLoopjClient.class.getSimpleName();
	private PersistentCookieStore myCookieStore;
	private Context mCtx;

	@Inject
	public RestLoopjClient(Context ctx) {
		mCtx = ctx;
		remoteClient = new AsyncHttpClient();
		remoteClient.setTimeout(30000);
	}
	
	@Override
	public void setSSLSocketFactory(SSLSocketFactory ssLSocketFactory){
	remoteClient.setSSLSocketFactory(ssLSocketFactory);
	}
	
	@Override
	public void useCookieStore(boolean useCookies){
		
		if(useCookies){
			myCookieStore = new PersistentCookieStore(mCtx);
			remoteClient.setCookieStore(myCookieStore);
		}
		
	}
	
	
	@Override
	public void setTimeout(int timeOut){
		remoteClient.setTimeout(timeOut);
	}
	@Override
	public void get(String url, final AsyncHandler<JSONObject> mClbc) {

		remoteClient.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String res) {

				if (res == null) {
					mClbc.onError(ErrorCodes.ERR_UNKNOWN);
					return;
				}

				try {
					JSONObject jres = new JSONObject(res);

					mClbc.onSuccess(jres);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mClbc.onError(ErrorCodes.ERR_WRONG_FORMAT);
				}
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				mClbc.onError(ErrorCodes.ERR_CONNECTION_FAIL);
			}

		});

	}

	@Override
	public void post(String url, Map<String, String> params, Header[] headers,
			final AsyncHandler<JSONObject> mClbc) {

		RequestParams postParams = mapToRequestParams(params);
		remoteClient.post(mCtx, url, headers, postParams,
				"application/x-www-form-urlencoded; charset=UTF-8 ",
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String res) {

						if (res == null) {
							mClbc.onError(ErrorCodes.ERR_UNKNOWN);
							return;
						}

						try {
							JSONObject jres = new JSONObject(res);

							mClbc.onSuccess(jres);

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							Log.d(tag, e.getMessage());
							mClbc.onError(ErrorCodes.ERR_WRONG_FORMAT);
						}
					}

					@Override
					public void onFailure(Throwable e, String arg1) {
						Log.d(tag, e.getMessage());
						mClbc.onError(ErrorCodes.ERR_CONNECTION_FAIL);
					}
					
				});

	}

	@Override
	public void put(String url, JSONObject data,
			final AsyncHandler<JSONObject> mClbc) {

		StringEntity entityToSave = null;
		try {
			entityToSave = new StringEntity(data.toString(), HTTP.UTF_8);
		} catch (UnsupportedEncodingException e) {
			Log.d(tag, e.getMessage());
		}

		if (entityToSave == null){
			mClbc.onError(ErrorCodes.ERR_WRONG_FORMAT);
			return;
		}
			

		remoteClient.put(mCtx, url, entityToSave,
				"application/json; charset=UTF-8",
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String res) {

						if (res == null) {
							mClbc.onError(ErrorCodes.ERR_UNKNOWN);
							return;
						}

						try {
							JSONObject jres = new JSONObject(res);

							mClbc.onSuccess(jres);

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							Log.d(tag, e.getMessage());
							mClbc.onError(ErrorCodes.ERR_WRONG_FORMAT);
						}
					}

					@Override
					public void onFailure(Throwable e, String arg1) {
						Log.d(tag, e.getMessage());
						mClbc.onError(ErrorCodes.ERR_CONNECTION_FAIL);
					}

				});

	}

	
	@Override
	public void delete(String url, Header[] headers,
			final AsyncHandler<JSONObject> mClbc)
	{
		
		remoteClient.delete(mCtx, url, headers, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String res) {

				if (res == null) {
					mClbc.onError(ErrorCodes.ERR_UNKNOWN);
					return;
				}

				try {
					JSONObject jres = new JSONObject(res);

					mClbc.onSuccess(jres);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Log.d(tag, e.getMessage());
					mClbc.onError(ErrorCodes.ERR_WRONG_FORMAT);
				}
			}

			@Override
			public void onFailure(Throwable e, String arg1) {
				Log.d(tag, e.getMessage());
				mClbc.onError(ErrorCodes.ERR_CONNECTION_FAIL);
			}

		});
		
	}
	private RequestParams mapToRequestParams(Map<String, String> orgParams) {
		RequestParams params = new RequestParams();

		if (orgParams == null)
			return params;
		Set<String> names = orgParams.keySet();

		for (String name : names) {
			params.put(name, orgParams.get(name));
		}

		return params;
	}

}
