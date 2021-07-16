import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;


/**
 * @author Nicholas Fong
 * @version 1.0
 * <p>
 * The MyCalendar class defines an underlying data structure to hold events
 */

public class MyCalendar {
    private HashMap<LocalDate, ArrayList<Event>> myCalendar;
    private LocalDate currentDate;
    private String fileName = "";

    /**
     * Constructor for MyCalendar
     *
     * @param cd calendar start date
     */
    public MyCalendar(LocalDate cd) {
        myCalendar = new HashMap<LocalDate, ArrayList<Event>>();
        currentDate = cd;
        fileName = "events.txt";

        try {
            this.fileReader();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Add an Event
     *
     * @param d LocalDate key
     * @param e Event value
     */
    public void addEvent(LocalDate d, Event e) {
        if (myCalendar.containsKey(d)) {
            ArrayList<Event> events = myCalendar.get(d);
            events.add(e);
            myCalendar.put(d, events);
        }
        else {
            ArrayList<Event> events = new ArrayList<Event>();
            events.add(e);
            myCalendar.put(d, events);
        }
    }

    /**
     * Removes a single one time event
     *
     * @param d date to remove
     * @param e event to remove
     * @return boolean if the event was removed
     */
    public boolean removeOneTimeEventSpecific(LocalDate d, Event e) {
        boolean removed = false;
        if (myCalendar.containsKey(d)) {
            ArrayList<Event> events = myCalendar.get(d);
            Event ev = findEvent(d, e.getName());
            if (!ev.getType()) {
                events.remove(e);
            }
            myCalendar.put(d, events);

            if (events.isEmpty()) {
                myCalendar.remove(d);
            }
            removed = true;
        }
        return removed;
    }

    /**
     * Removes all one time events on specific date
     *
     * @param d date to remove
     * @return boolean if the event was removed
     */
    public boolean removeOneTimeEventAll(LocalDate d) {
        boolean removed = false;
        if (myCalendar.containsKey(d)) {
            ArrayList<Event> events = myCalendar.get(d);

            Iterator<Event> itr = events.iterator();
            while (itr.hasNext()) {
                Event e = itr.next();
                if (!e.getType()) {
                    itr.remove();
                }
            }

            myCalendar.put(d, events);

            if (events.isEmpty()) {
                myCalendar.remove(d);
            }
            removed = true;
        }

        return removed;
    }

    /**
     * reads the event.txt file
     *
     * @throws FileNotFoundException
     */
    public void fileReader() throws FileNotFoundException {
        File events = new File(fileName);
        Scanner input = new Scanner(events);
        TimeInterval timeInterval;
        Event event;

        while (input.hasNextLine()) {
            String eventName = input.nextLine();
            String marker = input.next().toUpperCase();
            if (Character.isLetter(marker.charAt(0))) {
                String[] days = marker.split("");

                String startTS = input.next();
                String startT = startTS.replaceAll(":", "");
                int startTime = Integer.parseInt(startT);

                String endTS = input.next();
                String endT = endTS.replaceAll(":", "");
                int endTime = Integer.parseInt(endT);

                timeInterval = new TimeInterval(startTime, endTime, startTS, endTS);

                String[] startD = input.next().split("/");
                if (startD[2].length() == 2) {
                    startD[2] = "20" + startD[2];
                }
                String[] endDate = input.next().split("/");
                if (endDate[2].length() == 2) {
                    endDate[2] = "20" + endDate[2];
                }
                event = new Event(eventName, timeInterval, true);

                LocalDate newDateS = LocalDate.of(Integer.parseInt(startD[2]), Integer.parseInt(startD[0]), Integer.parseInt(startD[1]));
                LocalDate newDateE = LocalDate.of(Integer.parseInt(endDate[2]), Integer.parseInt(endDate[0]), Integer.parseInt(endDate[1]));

                int dayOfWeek = 0;

                while (!newDateS.isEqual(newDateE)) {
                    for (int x = 0; x < days.length; x++) {
                        switch (days[x]) {
                            case "M":
                                dayOfWeek = 1;
                                break;
                            case "T":
                                dayOfWeek = 2;
                                break;
                            case "W":
                                dayOfWeek = 3;
                                break;
                            case "R":
                                dayOfWeek = 4;
                                break;
                            case "F":
                                dayOfWeek = 5;
                                break;
                            case "A":
                                dayOfWeek = 6;
                                break;
                            case "S":
                                dayOfWeek = 7;
                                break;
                        }

                        if (newDateS.getDayOfWeek().getValue() == dayOfWeek) {
                            this.addEvent(newDateS, event);
                        }
                    }
                    newDateS = newDateS.plusDays(1);
                }

                if (input.hasNextLine()) {
                    input.nextLine();
                }
            }

            else {
                String[] day = marker.split("/");
                if (day[2].length() == 2) {
                    day[2] = "20" + day[2];
                }

                String startTS = input.next();
                String startT = startTS.replaceAll(":", "");
                int startTime = Integer.parseInt(startT);

                String endTS = input.next();
                String endT = endTS.replaceAll(":", "");
                int endTime = Integer.parseInt(endT);

                timeInterval = new TimeInterval(startTime, endTime, startTS, endTS);

                event = new Event(eventName, timeInterval, false);

                LocalDate newDate = LocalDate.of(Integer.parseInt(day[2]), Integer.parseInt(day[0]), Integer.parseInt(day[1]));

                this.addEvent(newDate, event);

                if (input.hasNextLine()) {
                    input.nextLine();
                }
            }
        }

        input.close();
    }

    /**
     * Return the current day
     *
     * @return the currentDay
     */
    public LocalDate currentDay() {
        return currentDate;
    }

    /**
     * Move to the previous day
     *
     * @return the previous day
     */
    public LocalDate previousDay() {
        return currentDate = currentDate.minusDays(1);
    }

    /**
     * Move to the next day
     *
     * @return the next day
     */
    public LocalDate nextDay() {
        return currentDate = currentDate.plusDays(1);
    }

    /**
     * Move to the previous month
     *
     * @return the previous month
     */
    public LocalDate previousMonth() {
        return currentDate = currentDate.minusMonths(1);
    }

    /**
     * Move to the next month
     *
     * @return the next month
     */
    public LocalDate nextMonth() {
        return currentDate = currentDate.plusMonths(1);
    }

    /**
     * Finds an event on a specific date
     *
     * @param d    date event is on
     * @param name name of the event
     * @return the event if found
     */
    public Event findEvent(LocalDate d, String name) {
        Event event = null;

        if (!myCalendar.containsKey(d)) {
            return event;
        }

        ArrayList<Event> events = myCalendar.get(d);
        for (Event ev : events) {
            if (name.equals(ev.getName())) {
                event = ev;
            }
        }

        return event;
    }

    /**
     * Checks if there is a time overlap
     *
     * @param d date to check
     * @param e event to check
     * @return boolean if there is an overlap
     */
    public boolean timeOverlapCheck(LocalDate d, Event e) {
        boolean overlap = false;
        if (myCalendar.containsKey(d)) {
            ArrayList<Event> events = myCalendar.get(d);
            for (Event ev : events) {
                if (ev.getTimeInterval().overlap(e)) {
                    overlap = true;
                }
            }
        }
        return overlap;
    }

    /**
     * Returns all events
     *
     * @param currentDate current starting date to return events from
     * @return all events
     */
    public String getEvents(LocalDate currentDate) {
        String eventOutput = "";

        if (!myCalendar.containsKey(currentDate)) {
            eventOutput = "No events today";
        }
        else {
            sortEventsTime(myCalendar.get(currentDate));
            ArrayList<Event> events = myCalendar.get(currentDate);

            for (Event e : events) {
                eventOutput = eventOutput + "  " + e.getName() + " | " + e.getTimeInterval().startString() + " - " + e.getTimeInterval().endString() + "\n";
            }
        }

        return eventOutput;
    }

    /**
     * Returns events that are not recurring
     *
     * @param currentDate date to get events from
     * @return all non-recurring events
     */
    public String getNonRecurringEvents(LocalDate currentDate) {
        String eventOutput = "";

        if (!myCalendar.containsKey(currentDate))
            eventOutput = "No Events";
        else {
            sortEventsTime(myCalendar.get(currentDate));
            ArrayList<Event> events = myCalendar.get(currentDate);

            for (Event e : events) {
                if (e.getType()) {
                    eventOutput = eventOutput + "  " + e.getName() + " | " + e.getTimeInterval().startString() + " - " + e.getTimeInterval().endString() + "\n";
                }
            }
        }

        return eventOutput;
    }

    /**
     * Returns all events
     *
     * @return all events
     */
    public String getAllEvents() {
        String eventOutput = new String();
        TreeMap<LocalDate, ArrayList<Event>> sortedCalendar = new TreeMap<>();
        sortedCalendar.putAll(myCalendar);

        for (LocalDate day : sortedCalendar.keySet()) {
            ArrayList<Event> events = myCalendar.get(day);

            if (!eventOutput.contains(Integer.toString(day.getYear()))) {
                eventOutput = eventOutput + day.getYear() + "\n";
            }
            eventOutput = eventOutput + "  " + day.getMonth() + "\n" + "    " + day.getDayOfMonth() + " " + day.getDayOfWeek() + "\n";

            sortEventsTime(events);
            for (Event e : events) {
                eventOutput = eventOutput + "      " + e.getName() + " | " + e.getTimeInterval().startString() + " - " + e.getTimeInterval().endString() + "\n";
            }
        }

        return eventOutput;
    }

    /**
     * Sorts the ArrayList of events in descending order
     * Contains class SortByTime implementing Comparator<Event>
     *
     * @param events ArrayList of events
     */
    public void sortEventsTime(ArrayList<Event> events) {

        class SortByTime implements Comparator<Event> {

            public int compare(Event e1, Event e2) {
                int event1 = e1.getTimeInterval().getStart();
                int event2 = e2.getTimeInterval().getStart();
                return Integer.compare(event1, event2);
            }

        }

        Collections.sort(events, new SortByTime());
    }

    /**
     * Prints the output
     *
     * @param out PrintWriter object to print to
     */
    public void printOutput(PrintWriter out) {
        String eventOutput = new String();
        TreeMap<LocalDate, ArrayList<Event>> sortedCalendar = new TreeMap<>();
        sortedCalendar.putAll(myCalendar);

        for (LocalDate day : sortedCalendar.keySet()) {
            ArrayList<Event> events = myCalendar.get(day);

            if (!eventOutput.contains(Integer.toString(day.getYear()))) {
                eventOutput = eventOutput + day.getYear() + "\n";
                out.println(day.getYear());
            }

            eventOutput = eventOutput + "  " + day.getMonth() + "\n";
            out.println("  " + day.getMonth());

            eventOutput = eventOutput + "    " + day.getDayOfMonth() + " " + day.getDayOfWeek() + "\n";
            out.println("    " + day.getDayOfMonth() + " " + day.getDayOfWeek());

            sortEventsTime(events);
            for (Event e : events) {
                eventOutput = eventOutput + "      " + e.getName() + " | " + e.getTimeInterval().startString() + " - " + e.getTimeInterval().endString() + "\n";
                out.println("      " + e.getName() + " | " + e.getTimeInterval().startString() + " - " + e.getTimeInterval().endString());
            }
        }
    }

    /**
     * Prints a calendar in month view
     *
     * @param currentDate date to print from
     */
    public void printCalendar(LocalDate currentDate) {
        LocalDate beginning = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
        String firstDayString = beginning.getDayOfWeek().toString();

        int firstDayInt = 0;
        switch (firstDayString) {
            case "SUNDAY":
                firstDayInt = 1;
                break;
            case "MONDAY":
                firstDayInt = 2;
                break;
            case "TUESDAY":
                firstDayInt = 3;
                break;
            case "WEDNESDAY":
                firstDayInt = 4;
                break;
            case "THURSDAY":
                firstDayInt = 5;
                break;
            case "FRIDAY":
                firstDayInt = 6;
                break;
            case "SATURDAY":
                firstDayInt = 7;
                break;
        }

        System.out.println("             " + currentDate.getMonth() + " " + currentDate.getYear());
        System.out.println(" Su    Mo    Tu    We    Th    Fr    Sa");

        int weekday = 0;
        for (int firstDaySpaces = 1; firstDaySpaces < firstDayInt; firstDaySpaces++) {
            System.out.print("      ");
            weekday++;
        }

        LocalDate today = LocalDate.now();


        for (int day = 1; day <= currentDate.lengthOfMonth(); day++) {
            if (day == today.getDayOfMonth() && currentDate.getMonthValue() == today.getMonthValue() && currentDate.getYear() == today.getYear()) {
                if (day < 10) {
                    System.out.print(" [" + day + "]");
                }
                else {
                    System.out.print("[" + day + "]");
                }
                weekday++;
                if (weekday % 7 == 0) {
                    System.out.println();
                }
                else {
                    System.out.print("  ");
                }
            }
            else {
                if (day < 10) {
                    System.out.print("  " + day);
                }
                else {
                    System.out.print(" " + day);
                }

                weekday++;

                if (weekday % 7 == 0) {
                    System.out.println();
                }
                else {
                    System.out.print("   ");
                }
            }

            beginning = beginning.plusDays(1);
        }

        System.out.println("\n");
    }

    /**
     * Prints a calendar view
     *
     * @param currentDate date to print from
     */
    public void printView(LocalDate currentDate) {
        LocalDate beginning = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
        String firstDayString = beginning.getDayOfWeek().toString();

        int firstDayInt = 0;
        switch (firstDayString) {
            case "SUNDAY":
                firstDayInt = 1;
                break;
            case "MONDAY":
                firstDayInt = 2;
                break;
            case "TUESDAY":
                firstDayInt = 3;
                break;
            case "WEDNESDAY":
                firstDayInt = 4;
                break;
            case "THURSDAY":
                firstDayInt = 5;
                break;
            case "FRIDAY":
                firstDayInt = 6;
                break;
            case "SATURDAY":
                firstDayInt = 7;
                break;
        }

        System.out.println("             " + currentDate.getMonth() + " " + currentDate.getYear());
        System.out.println(" Su    Mo    Tu    We    Th    Fr    Sa");

        int weekday = 0;
        for (int firstDaySpaces = 1; firstDaySpaces < firstDayInt; firstDaySpaces++) {
            System.out.print("      ");
            weekday++;
        }

        LocalDate today = LocalDate.now();


        for (int day = 1; day <= currentDate.lengthOfMonth(); day++) {
            if (myCalendar.containsKey(beginning)) {
                if (day < 10) {
                    System.out.print(" {" + day + "}");
                }
                else {
                    System.out.print("{" + day + "}");
                }
                weekday++;
                if (weekday % 7 == 0) {
                    System.out.println();
                }
                else {
                    System.out.print("  ");
                }
            }
            else {
                if (day < 10) {
                    System.out.print("  " + day);
                }
                else {
                    System.out.print(" " + day);
                }

                weekday++;

                if (weekday % 7 == 0) {
                    System.out.println();
                }
                else {
                    System.out.print("   ");
                }
            }

            beginning = beginning.plusDays(1);
        }

        System.out.println("\n");
    }
}
