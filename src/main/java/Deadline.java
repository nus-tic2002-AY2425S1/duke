public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, char symbol, String deadline) {
        super(description, symbol);
        this.deadline = "(by:" + deadline + ")";
        this.isDone = false;
    }

    public Deadline(String description, char symbol) {
        super(description, symbol);
        String timeline;
        timeline = description.substring(description.indexOf("(by:")+4, description.indexOf(")"));
        this.deadline = "(by:" + timeline + ")";
        setDescription(description.substring(0, description.indexOf("(")));
        this.isDone = false;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = "(by:" + deadline + ")";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + deadline;
    }
}
