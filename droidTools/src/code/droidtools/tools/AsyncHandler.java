package code.droidtools.tools;


/**
 * general interface for async calls handler
 *
 * @param <T> - type of success object
 */
public interface AsyncHandler <T>{
		public void onError(ErrorCodes code);
		public void onSuccess(T success);
}
