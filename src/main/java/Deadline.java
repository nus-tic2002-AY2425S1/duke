public class Deadline extends Task{
    private String deadline;
    public Deadline(String description, char symbol,String deadline) {
        super(description,symbol);
        this.deadline ="(by:"+deadline+")";
    }
    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline ="(by:"+deadline+")";
    }
    @Override
    public String getDescription() {
        return super.getDescription()+deadline;
    }
}
