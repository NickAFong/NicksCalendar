/**
 * @author Nicholas Fong
 * @version 1.0
 * <p>
 * The TimeInterval class represents an interval of time and suitable for events
 */

public class TimeInterval {
    private int startTime;
    private int endTime;
    private String startTimeString;
    private String endTimeString;

    /**
     * Constructor for TimeInterval
     *
     * @param start  start time int
     * @param end    end time int
     * @param startT start time string
     * @param endT   end time int
     */
    public TimeInterval(int start, int end, String startT, String endT) {
        startTime = start;
        endTime = end;
        startTimeString = startT;
        endTimeString = endT;
    }

    /**
     * Returns the start time as int
     *
     * @return start time int
     */
    public int getStart() {
        return startTime;
    }

    /**
     * Returns the end time int
     *
     * @return end time int
     */
    public int getEnd() {
        return endTime;
    }

    /**
     * Returns the start time as string
     *
     * @return start time string
     */
    public String startString() {
        return startTimeString;
    }

    /**
     * Returns the end time as string
     *
     * @return end time string
     */
    public String endString() {
        return endTimeString;
    }

    /**
     * Checks if the time interval is valid
     *
     * @param e event
     * @return boolean if there is an interference
     */
    public boolean overlap(Event e) {
        boolean overlap = false;

        if (e.getTimeInterval().getStart() >= getStart() && e.getTimeInterval().getStart() <= getEnd() ||
                e.getTimeInterval().getEnd() >= getStart() && e.getTimeInterval().getEnd() <= getEnd() ||
                e.getTimeInterval().getStart() == getStart() || e.getTimeInterval().getStart() == getEnd() ||
                e.getTimeInterval().getEnd() == getStart() || e.getTimeInterval().getEnd() == getEnd()) {
            overlap = true;
        }

        return overlap;
    }
}
