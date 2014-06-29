package simulator.core;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 23. 6. 2014 10:38
 */
public class ScheduledEvent implements Comparable<ScheduledEvent> {

    private final Event event;
    private final TimeStamp timeStamp;

    public ScheduledEvent(Event event, TimeStamp timeStamp) {
        this.event = event;
        this.timeStamp = timeStamp;
    }

    public Event getEvent() {
        return event;
    }

    public TimeStamp getTimeStamp() {
        return timeStamp;
    }

    @Override
    public int compareTo(ScheduledEvent other) {
        return getTimeStamp().compareTo(other.getTimeStamp());
    }
}