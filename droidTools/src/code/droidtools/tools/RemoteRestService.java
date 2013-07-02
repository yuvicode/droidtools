package code.droidtools.tools;

import java.util.Map;

import org.apache.http.Header;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.json.JSONObject;


/**
 * Simple interface for asynchronous REST calls  
 *
 */
public interface RemoteRestService {
	
	
	/**
	 * @param url - url to call 
	 * @param mClbc - handler for results
	 */
	public void get(String url,AsyncHandler <JSONObject> mClbc);

	/**
	 * @param url - url to call 
	 * @param params - map of parameters to send
	 * @param headers - additional headers
	 * @param mClbc - handler for results 
	 */
	public void post(String url,  Map <String,String> params, Header[] headers,AsyncHandler<JSONObject> mClbc);

	/**
	 * @param url - url to call
	 * @param data - JSON object to send 
	 * @param mClbc - handler for results
	 */
	public void put(String url, JSONObject data, AsyncHandler<JSONObject> mClbc);

	/**
	 * @param useCookiesc - save and use Cookies
	 */
	void useCookieStore(boolean useCookies);

	/**
	 * @param timeOut - set timeout in milliseconds. default is 30000 (30 seconds)
	 */
	void setTimeout(int timeOut);

	void setSSLSocketFactory(SSLSocketFactory ssLSocketFactory);

	
	void delete(String url, Header[] headers,AsyncHandler<JSONObject> mClbc);
	
}
