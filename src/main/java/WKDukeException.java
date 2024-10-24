public class WKDukeException extends Exception {
    protected String detail = null;
    protected String help = null;

    public WKDukeException(String message) {
        super(message);
    }

    public WKDukeException(String message, String detail) {
        this(message);
        this.detail = detail;
    }

    public WKDukeException(String message, String detail, String help) {
        this(message, detail);
        this.help = help;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }
}
