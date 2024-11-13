package starkchatbot.userui;

import com.sun.source.tree.SwitchTree;
import starkchatbot.taskmanager.Task;
import starkchatbot.taskmanager.TaskList;
import starkchatbot.taskmanager.TentativeScheduling;

import java.util.ArrayList;
import java.util.Scanner;

public class TentativeEventHandler {

    public static void tentativeEvent(TaskList taskList) {
        System.out.println("*****   Tentative event planner   ******");
        System.out.println("[1]. Add new event to Tentative Scheduler");
        System.out.println("[2]. Confirm time slot of a Tentative event");
        System.out.println("[3]. Edit time slot of a Tentative event");
        System.out.println("[4]. Exit");
        System.out.print("Please choose the required option: ");

        try {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            switch (userInput.trim()) {
                case "1":
                    addTentativeEvent(taskList);
                    break;
                case "2":
                    confirmTentativeEvent(taskList);
                    break;
                case "3":
                    //edit time slot
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTentativeEvent(TaskList taskList) {
        System.out.print(System.lineSeparator());
        System.out.print("Please enter event description: ");
        Scanner scanner = new Scanner(System.in);
        String details = scanner.nextLine();
        TentativeScheduling tentativeEvent = new TentativeScheduling(details.trim());
        try {

            System.out.print("Please enter total number of available slots: ");
            int totalSlots = scanner.nextInt();
            int counter = 0;
            scanner.nextLine();
            while (totalSlots > 0) {
                counter++;
                System.out.print(counter + ". \t\tfrom: ");
                String start = scanner.nextLine();
                System.out.print(" \t\tto: ");
                String endAt = scanner.nextLine();

                tentativeEvent.addAvailableSlots(start, endAt);
                totalSlots--;
            }
            taskList.addTentativeEvent(tentativeEvent);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void confirmTentativeEvent(TaskList taskList) {

        ArrayList<Task> tentativeEvents = taskList.getTentativeEvent();
        int counter = 0;
        for (Task task : tentativeEvents) {
            if(task.getClass() == TentativeScheduling.class) {
                counter++;
                System.out.print( counter + ". ");
                ((TentativeScheduling) task).printAllAvailableSlots();
                System.out.print(System.lineSeparator());
            }
        }
        counter = 1;
        try{
            System.out.print(System.lineSeparator());
            System.out.print("Please choose the event number: ");
            Scanner eventNumber = new Scanner(System.in);
            int event = eventNumber.nextInt();
            System.out.print("Please choose the slot number to confirm the event: ");
            int slot = eventNumber.nextInt();

            for (Task task : tentativeEvents) {
                if(task.getClass() == TentativeScheduling.class && counter == event) {
                    ((TentativeScheduling) task).setConfirmedSlot(slot);
                    System.out.println("Event confirmed on: " + ((TentativeScheduling) task).getConfirmedSlot());
                    break;
                }
                counter++;
            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
