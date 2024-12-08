package josbot.task;


/**
 * Represent Todo Task
 */
public class Todo extends Task {


    /**
     * Creates a todo task with its description
     *
     * @param description contains task description
     */
    public Todo(String description) {
        super(description);
    }


    /**
     * Return String of Todo type
     *
     * @return Todo type
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Return String of the todo details
     *
     * @return
     */

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
