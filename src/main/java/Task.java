public class Task {
  protected String _name;
  protected boolean _status;

  public Task() {}
  public Task(String name) {
    _name = name;
    _status = false;
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + _name;
  }

  public String getStatusIcon() {
    return ( _status ? "X" : " ");
  }


  public boolean getStatus() { return _status; }
  public String getName() { return _name; }
  public void markTask() {
    _status = true;
  }
  public void unmarkTask() {
    _status = false;
  }
}
