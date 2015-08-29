package test.gyatsina.axelspringer.event;

/**
 * Created by gyatsina
 */

// Event for Failure cases, f.i. failure response from backend
public class ErrorEvent {

  private final int errorMessage;

  public ErrorEvent(int errorMessage) {
    this.errorMessage = errorMessage;
  }

  public int getErrorMessage() {
    return errorMessage;
  }
}
