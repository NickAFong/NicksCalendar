import javax.swing.event.ChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Nick Fong, Monica Orme, Ben Gonzalez
 */

/**
 * An MVC based model for the Calendar application
 */


public class CalendarModel {
    public static LocalDate currentDate;
    //private TreeMap<LocalDate, ArrayList<Event>> events;
    //private TreeSet<Event> events;
    private ArrayList<ChangeListener> listeners;


    public CalendarModel() {
        //       events = new TreeSet<>();
        currentDate = LocalDate.now(); // capture today
        listeners = new ArrayList<>();
    }


    /**
     Constructs a DataModel object
     @return the data in an ArrayList
     */
    /*
	 public ArrayList<Event> getData() {
	    return (ArrayList<Event>) (event.clone());
	 }
	 */

    /**
     * Attach a listener to the Model
     *
     * @param c - the listener
     */
    public void attach(ChangeListener c) {
        listeners.add(c);
    }

    /**
     Change the data in the model at a particular location
     @param location the index of the field to change
     @param value the new value
     */

	 /*
	 public void update(int location, double value) {
	    data.set(location, new Double(value));
	    for (ChangeListener l : listeners) {
	       l.stateChanged(new ChangeEvent(this));
	    }
	 }
	 */
}
