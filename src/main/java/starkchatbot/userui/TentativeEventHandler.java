package starkchatbot.userui;

import starkchatbot.taskmanager.Task;
import starkchatbot.taskmanager.TaskList;
import starkchatbot.taskmanager.TentativeTask;

import java.util.ArrayList;
import java.util.Scanner;

public class TentativeEventHandler {

    /**
     * Displays a menu for managing tentative events and processes user input
     *
     * @param taskList the task list to manage tentative events.
     */
    public static void tentativeEvent(TaskList taskList) {
        boolean isactive = true;

        while (isactive) {
            System.out.print(System.lineSeparator());
            System.out.println("------------  Tentative event planner  ------------ ");
            System.out.println("\t\t[1]. Add new event to Tentative Scheduler");
            System.out.println("\t\t[2]. Confirm time slot of a Tentative event");
            System.out.println("\t\t[3]. Add time slots of a Tentative event");
            System.out.println("\t\t[4]. Change confirmed time slot of a Tentative event");
            System.out.println("\t\t[5]. Exit");
            System.out.print(System.lineSeparator());
            System.out.print("\tPlease choose the required option: ");

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
                        addTimeSlots(taskList);
                        break;
                    case "4":
                        editConfirmSlot(taskList);
                        break;
                    case "5":
                        isactive = false;
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Adds a new tentative event with time slots to the task list.
     *
     * @param taskList the task list to add the tentative event to.
     */
    private static void addTentativeEvent(TaskList taskList) {
        System.out.print(System.lineSeparator());
        System.out.print("\tPlease enter event description: ");
        Scanner scanner = new Scanner(System.in);
        String details = scanner.nextLine();
        TentativeTask tentativeEvent = new TentativeTask(details.trim());
        try {

            System.out.print("\tPlease enter total number of available slots: ");
            int totalSlots = scanner.nextInt();
            int counter = 0;
            scanner.nextLine();
            while (totalSlots > 0) {
                counter++;
                System.out.print("\t" + counter + ". \t\tfrom: ");
                String startAt = scanner.nextLine();
                System.out.print(" \t\tto: ");
                String endAt = scanner.nextLine();

                tentativeEvent.addAvailableSlots(startAt, endAt);
                totalSlots--;
            }
            taskList.addTentativeEvent(tentativeEvent);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Prints all tentative events in the task list.
     *
     * @param tentativeEvents the list of tentative tasks to print.
     */
    private static void printAllTentativeEvents(ArrayList<Task> tentativeEvents) {

        int counter = 0;
        for (Task task : tentativeEvents) {
            if (task.getClass() == TentativeTask.class) {
                counter++;
                System.out.print("\t" + counter + ". ");
                ((TentativeTask) task).printAllAvailableSlots();
                System.out.print(System.lineSeparator());
            }
        }
    }

    private static void confirmTentativeEvent(TaskList taskList) {
        ArrayList<Task> tentativeEvents = taskList.getTentativeEvent();
        printAllTentativeEvents(tentativeEvents);
        int counter = 1;
        try {

            System.out.print("\tPlease choose the event number: ");
            Scanner eventNumber = new Scanner(System.in);
            int event = eventNumber.nextInt();
            System.out.print("\tPlease choose the slot number to confirm the event: ");
            int slot = eventNumber.nextInt();

            for (Task task : tentativeEvents) {
                if (task.getClass() == TentativeTask.class && counter == event) {
                    ((TentativeTask) task).setConfirmedSlot(slot);
                    System.out.println("Event confirmed on: " + ((TentativeTask) task).getConfirmedSlot());
                    break;
                }
                counter++;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void editConfirmSlot(TaskList taskList) {
        ArrayList<Task> tentativeEvents = taskList.getTentativeEvent();
        printAllTentativeEvents(tentativeEvents);
        int counter = 1;
        try {

            System.out.print("\tPlease choose the event number to edit confirmed slot: ");
            Scanner eventNumber = new Scanner(System.in);
            int event = eventNumber.nextInt();
            System.out.print("\tPlease choose the new slot number to confirm the event: ");
            int slot = eventNumber.nextInt();

            for (Task task : tentativeEvents) {
                if (task.getClass() == TentativeTask.class && counter == event) {
                    ((TentativeTask) task).editConfirmedSlot();
                    ((TentativeTask) task).setConfirmedSlot(slot);
                    System.out.println("Event changed to: " + ((TentativeTask) task).getConfirmedSlot());
                    break;
                }
                counter++;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void addTimeSlots(TaskList taskList) {
        ArrayList<Task> tentativeEvents = taskList.getTentativeEvent();
        printAllTentativeEvents(tentativeEvents);
        int counter = 1;
        try {

            System.out.print("\tPlease choose the event number to add slot: ");
            Scanner input = new Scanner(System.in);
            int event = input.nextInt();
            System.out.println("\tPlease enter the new slot from: ");
            System.out.print(" \t\tfrom: ");
            String start = readInput();
            System.out.print(" \t\tto: ");
            String endAt = readInput();

            for (Task task : tentativeEvents) {
                if (task.getClass() == TentativeTask.class && counter == event) {
                    ((TentativeTask) task).addAvailableSlots(start, endAt);
                    break;
                }
                counter++;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String readInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

}
