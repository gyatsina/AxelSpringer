package test.gyatsina.axelspringer.event;

/**
 * Created by gyatsina
 */
public class ErrorEvent {

  private final int errorMessage;

  public ErrorEvent(int errorMessage) {
    this.errorMessage = errorMessage;
  }

  public int getErrorMessage() {
    return errorMessage;
  }
}
