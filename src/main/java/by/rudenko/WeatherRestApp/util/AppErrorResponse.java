package by.rudenko.WeatherRestApp.util;

public class AppErrorResponse {

  private String message;
  private long timestamp;

  public AppErrorResponse(String message, long timestamp) {
    this.message = message;
    this.timestamp = timestamp;
  }

  public AppErrorResponse() {
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
}
