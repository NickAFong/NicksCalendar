import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author Nicholas Fong
 * @version 1.0
 * <p>
 * The MyCalendarTester is a class with a main method
 */

public class MyCalendarTester {
    public static void main(String[] args) throws IOException {
        LocalDate today = LocalDate.now();
        MyCalendar myCalendar = new MyCalendar(today);

        myCalendar.printCalendar(myCalendar.currentDay());

        Scanner input = new Scanner(System.in);

        System.out.print("Select one of the following options: \n" + "[V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit \n");

        String firstChoice = input.next();
        String secondChoice = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");

        while (!firstChoice.equalsIgnoreCase("Q")) {
            switch (firstChoice.toUpperCase()) {
                case "V":
                    System.out.println("[D]ay view or [M]onth view?");
                    secondChoice = input.next();

                    while (!secondChoice.equalsIgnoreCase("G")) {
                        switch (secondChoice.toUpperCase()) {
                            case "D":
                                System.out.println(formatter.format(myCalendar.currentDay()));
                                System.out.println(myCalendar.getEvents(myCalendar.currentDay()));
                                System.out.println("[P]revious or [N]ext or [G]o back to main menu?");
                                secondChoice = input.next();

                                while (!secondChoice.equalsIgnoreCase("G")) {
                                    switch (secondChoice.toUpperCase()) {
                                        case "P":
                                            myCalendar.previousDay();
                                            System.out.println(formatter.format(myCalendar.currentDay()));
                                            System.out.println(myCalendar.getEvents(myCalendar.currentDay()));
                                            break;
                                        case "N":
                                            myCalendar.nextDay();
                                            System.out.println(formatter.format(myCalendar.currentDay()));
                                            System.out.println(myCalendar.getEvents(myCalendar.currentDay()));
                                            break;
                                    }
                                    System.out.println("[P]revious or [N]ext or [G]o back to main menu?");
                                    secondChoice = input.next();
                                }
                                break;
                            case "M":
                                myCalendar.printView(myCalendar.currentDay());
                                System.out.println("[P]revious or [N]ext or [G]o back to main menu?");
                                secondChoice = input.next();

                                while (!secondChoice.equalsIgnoreCase("G")) {
                                    switch (secondChoice.toUpperCase()) {
                                        case "P":
                                            myCalendar.previousMonth();
                                            myCalendar.printView(myCalendar.currentDay());
                                            break;
                                        case "N":
                                            myCalendar.nextMonth();
                                            myCalendar.printView(myCalendar.currentDay());
                                            break;
                                    }
                                    System.out.println("[P]revious or [N]ext or [G]o back to main menu?");
                                    secondChoice = input.next();
                                }
                                break;
                        }
                    }
                    break;

                case "C":
                    System.out.print("Name: ");
                    input.nextLine();
                    String name = input.nextLine();

                    System.out.print("Date [mm/dd/yyyy]: ");
                    String date = input.next();
                    String[] day = date.split("/");
                    if (day[2].length() == 2) {
                        day[2] = "20" + day[2];
                    }

                    System.out.print("Start [24hr]: ");
                    String start = input.next();
                    String startT = start.replaceAll(":", "");
                    int startTime = Integer.parseInt(startT);

                    System.out.print("End [24hr]: ");
                    String end = input.next();
                    String endT = end.replaceAll(":", "");
                    int endTime = Integer.parseInt(endT);

                    TimeInterval timeInterval = new TimeInterval(startTime, endTime, start, end);
                    Event event = new Event(name, timeInterval, false);
                    LocalDate newDate = LocalDate.of(Integer.parseInt(day[2]), Integer.parseInt(day[0]), Integer.parseInt(day[1]));

                    if (!myCalendar.timeOverlapCheck(newDate, event)) {
                        myCalendar.addEvent(newDate, event);
                        System.out.println("Event Created " + "\n" + myCalendar.getEvents(newDate));
                    }
                    else {
                        System.out.println("Event not created because of time conflict. \n");
                    }
                    break;

                case "G":
                    System.out.print("Enter the date to go to [mm/dd/yyyy]: ");
                    date = input.next();
                    day = date.split("/");
                    if (day[2].length() == 2) {
                        day[2] = "20" + day[2];
                    }

                    newDate = LocalDate.of(Integer.parseInt(day[2]), Integer.parseInt(day[0]), Integer.parseInt(day[1]));

                    System.out.println(formatter.format(newDate));
                    System.out.println(myCalendar.getEvents(newDate));
                    break;

                case "E":
                    System.out.println(myCalendar.getAllEvents());
                    break;

                case "D":
                    System.out.print("[S]elected or [A]ll ");
                    secondChoice = input.next();

                    switch (secondChoice.toUpperCase()) {
                        case "S":
                            System.out.print("Enter the date to delete one event [mm/dd/yyyy]: ");
                            date = input.next();
                            day = date.split("/");
                            if (day[2].length() == 2) {
                                day[2] = "20" + day[2];
                            }
                            newDate = LocalDate.of(Integer.parseInt(day[2]), Integer.parseInt(day[0]), Integer.parseInt(day[1]));

                            System.out.println(formatter.format(newDate));
                            System.out.println(myCalendar.getEvents(newDate));

                            if (!myCalendar.getEvents(newDate).equals("No events listed")) {
                                System.out.print("Enter the name of the event to delete: ");
                                input.nextLine();
                                String name2 = input.nextLine();

                                Event e = myCalendar.findEvent(newDate, name2);

                                boolean removed = myCalendar.removeOneTimeEventSpecific(newDate, e);

                                if (removed == true) {
                                    System.out.println("The event is deleted.\nCurrent Schedule for " + formatter.format(newDate) + ":");
                                    System.out.println(myCalendar.getEvents(newDate));
                                }
                                else
                                    System.out.println("The event was not deleted.");
                            }
                            break;

                        case "A":
                            System.out.print("Enter the date to delete all events on [mm/dd/yyyy]: ");
                            date = input.next();
                            day = date.split("/");
                            if (day[2].length() == 2) {
                                day[2] = "20" + day[2];
                            }
                            newDate = LocalDate.of(Integer.parseInt(day[2]), Integer.parseInt(day[0]), Integer.parseInt(day[1]));

                            boolean removed2 = myCalendar.removeOneTimeEventAll(newDate);
                            if (removed2 == true) {
                                System.out.println("The recurring events are deleted: ");
                                System.out.println(formatter.format(newDate));
                                System.out.println(myCalendar.getEvents(newDate));
                            }
                            else
                                System.out.println("The events were not deleted.");
                            break;
                    }

                    break;

                default:
                    if (!firstChoice.equalsIgnoreCase("Q")) {
                        System.out.println("Invalid, try again");
                    }
                    break;
            }

            System.out.print("Select one of the following options: \n" + "[V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit \n");
            firstChoice = input.next();
        }


        System.out.println("Good Bye");
        input.close();

        PrintWriter out = new PrintWriter(new File(System.getProperty("user.home"), "Desktop/output.txt"), "UTF-8");
        myCalendar.printOutput(out);
        out.close();

    }
}
