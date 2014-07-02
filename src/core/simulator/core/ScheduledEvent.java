package core.simulator.core;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 23. 6. 2014 10:38
 */
public class ScheduledEvent implements Comparable<ScheduledEvent> {

    private final Event event;
    private final double timeStamp;

    public ScheduledEvent(Event event, double timeStamp) {
        this.event = event;
        this.timeStamp = timeStamp;
    }

    public Event getEvent() {
        return event;
    }

    public double getTimeStamp() {
        return timeStamp;
    }

    @Override
    public int compareTo(ScheduledEvent other) {
        return new Double(getTimeStamp()).compareTo(other.getTimeStamp());
    }
}