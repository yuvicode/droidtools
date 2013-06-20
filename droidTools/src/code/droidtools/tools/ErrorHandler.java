package code.droidtools.tools;


public interface ErrorHandler {
      long getErrorCode();
      void setErrorCode(long code);
      String getErrorMessage();
      void setErrorMessage(String msg);
}
