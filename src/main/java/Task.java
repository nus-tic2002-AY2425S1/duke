public class Task {
    private String description;
    private String status = " ";

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setStatus(String status) {
        if (status.equalsIgnoreCase("mark")) {
            this.status = "X";
        }else if(status.equalsIgnoreCase("unmark")){
            this.status = " ";
        }
    }
    public String getStatus() {
        return status;
    }

    public String printTask() {
        return "[" + status + "] " + description;
    }
}
