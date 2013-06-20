package code.droidtools.tools;

public class SimpleErrorHandler implements ErrorHandler{
 
	private long code;
	private String msg;
	@Override
	public long getErrorCode() {
		return code;
	}
	@Override
	public void setErrorCode(long code) {
		this.code = code;		
	}

	@Override
	public String getErrorMessage() {
		return msg;
	}

	@Override
	public void setErrorMessage(String msg) {
		this.msg  = msg;		
	}

}
