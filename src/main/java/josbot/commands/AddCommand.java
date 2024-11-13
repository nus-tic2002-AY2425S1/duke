package josbot.commands;

import josbot.JosBotException;
import josbot.parser.DateTimeParser;
import josbot.storage.FileStorage;
import josbot.task.*;
import josbot.ui.UI;

import java.io.IOError;

public class AddCommand extends Command {

    public AddCommand() {

    }

    @Override
    public void execute(TaskList tasks, UI ui, FileStorage file) throws JosBotException
    {
        try{
            Task t = null;
            if(commandType.equals("todo"))
            {
                t = new Todo(description);
            }
            else if(commandType.equals("deadline"))
            {
                String[] deadline_txt = description.split("/by", 2);
                String[] datetime = deadline_txt[1].trim().split(" ");
                DateTimeParser dt = new DateTimeParser();
                if(datetime.length > 1)
                {
                    t = new Deadline(deadline_txt[0].trim(), dt.convertDateTime(deadline_txt[1].trim()), true);
                }
                else
                {
                    t = new Deadline(deadline_txt[0].trim(), dt.convertDateTime(deadline_txt[1].trim()), false);
                }

            }
            else if(commandType.equals("event")){
                String[] event_txt = description.split("/", 3);
                String event_name = event_txt[0].trim();
                String event_from = event_txt[1].replace("from ", "").trim();
                String event_to = event_txt[2].replace("to ", "").trim();
                t = new Event(event_name, event_from, event_to);
            }
            ui.showAddMessage();
            tasks.addTask(t);
            ui.showTaskMessage(t, tasks);
            file.saveToFile(tasks);
        }
        catch(IndexOutOfBoundsException e)
        {
            ui.showDateTimeError();

        }

    }
}
