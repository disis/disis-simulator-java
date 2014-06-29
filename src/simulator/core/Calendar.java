package simulator.core;

import simulator.core.Event;
import simulator.core.ScheduledEvent;
import simulator.core.TimeStamp;

import java.util.PriorityQueue;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 23. 6. 2014 10:37
 */
public class Calendar {

    private final PriorityQueue<ScheduledEvent> priorityQueue;

    public Calendar() {
        priorityQueue = new PriorityQueue<>();
    }

    public void schedule(Event event, TimeStamp timeStamp) {
        priorityQueue.add(new ScheduledEvent(event, timeStamp));
    }

    public ScheduledEvent getNextEvent() {
        return priorityQueue.peek();
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    public void remove(ScheduledEvent scheduledEvent) {
        priorityQueue.remove(scheduledEvent);
    }
}