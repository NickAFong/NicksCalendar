import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * An MVC based model for the Calendar application
 */


public class CalendarModel {
    // private TreeMap<LocalDate, ArrayList<Event>> events;
    //private TreeSet<Event> events;
    private ArrayList<ChangeListener> listeners;
    private LocalDate currentDate;
    private LocalDate highlightedDate;
    private String metric;

    public CalendarModel() {
        //events = new TreeSet<>();
        listeners = new ArrayList<>();
        this.currentDate = LocalDate.now();
        this.highlightedDate = currentDate;
        this.metric = "day";
    }

    // accessor

    /**
     * Constructs a DataModel object
     *
     * @return the event data in an ArrayList
     */

//    public ArrayList<Event> getData() {
//        return new ArrayList<>(events);
//        //return (ArrayList<Event>) (events.clone());
//    }

    /**
     * Formatter for displaying specific events within the model
     *
     * @return toPrint - a formatted String object
     */
//    public String format(ArrayList<Event> events) {
//        String eventsDisplay = events.toString();
//        eventsDisplay = eventsDisplay.replace("[", "");
//        eventsDisplay = eventsDisplay.replace("]", "");
//        String[] splitEventsContents = eventsDisplay.split(", ");
//        String toPrint = splitEventsContents[0];
//        for (int i = 1; i < splitEventsContents.length; i++) {
//            toPrint = toPrint + System.lineSeparator() + splitEventsContents[i];
//        }
//        return toPrint;
//
//    }

    /**
     * Returns today's date
     *
     * @return today - today's date
     */
    public LocalDate getToday() {
        return currentDate;
    }

    /**
     * Returns the highlighted date
     *
     * @return highlightedDate - the highlighted date
     */
    public LocalDate getHighlightedDate() {
        return highlightedDate;
    }

    /**
     * Returns the view metric
     *
     * @return metric - view metric
     */
    public String getMetric() {
        return metric;
    }

    // mutator

    /**
     * Adds the event to the calendar model and notifies all change listeners
     * in the model about this update.
     *
     * @param e - an event
     */
//    public void addEvent(Event e) {
//        //events.put(e.getDate(), add(e));
//        events.add(e);
//        for (ChangeListener l : listeners) {
//            l.stateChanged(new ChangeEvent(this));
//        }
//    }

    /**
     * Updates the highlighted date in the calendar application and notifies all change listeners
     * in the model about this update.
     *
     * @param highlightedDate - the highlighted date in the calendar
     */
    public void setHighlightedDate(LocalDate highlightedDate) {
        this.highlightedDate = highlightedDate;
        for (ChangeListener l : listeners) {
            l.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Updates the view metric in the calendar application and notifies all change listeners
     * in the model about this update.
     *
     * @param metric - the view metric
     */
    public void setMetric(String metric) {
        this.metric = metric.toLowerCase();
        for (ChangeListener l : listeners) {
            l.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Attach a listener to the Model
     *
     * @param c - the listener
     */
    public void attach(ChangeListener c) {
        listeners.add(c);
    }
}
