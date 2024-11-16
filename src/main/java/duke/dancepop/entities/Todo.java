package duke.dancepop.entities;

import duke.dancepop.enums.TaskEnum;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public TaskEnum getType() {
        return TaskEnum.TODO;
    }
}
