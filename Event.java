/**
 * @author Nicholas Fong
 * @version 1.0
 * <p>
 * The Event class represents an event
 */

public class Event {
    private String name;
    private TimeInterval timeInterval;
    private boolean type;

    /**
     * Constructs the Event class
     *
     * @param eventName         name of the event
     * @param eventTimeInterval time interval
     * @param eventType         type of event
     */
    public Event(String eventName, TimeInterval eventTimeInterval, boolean eventType) {
        name = eventName;
        timeInterval = eventTimeInterval;
        type = eventType;
    }

    /**
     * Returns the name of the event
     *
     * @return name of the event
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the time interval of event
     *
     * @return time interval of event
     */
    public TimeInterval getTimeInterval() {
        return timeInterval;
    }

    /**
     * Returns a boolean if the event is regular or recurring
     *
     * @return boolean true if event is recurring and false if it is one time
     */
    public boolean getType() {
        return type;
    }
}
