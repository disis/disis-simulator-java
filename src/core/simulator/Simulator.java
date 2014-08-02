package core.simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 23. 6. 2014 10:36
 */
public class Simulator {

    private Calendar calendar;
    private double localVirtualTime;
    private EventInvoker invoker;
    private SimulationModel model;
    private boolean isRunning;
    private List<ScheduledEventListener> scheduledEventListeners = new ArrayList<>();

    public Simulator(SimulationModel model) {
        this(model, new BaseInvoker());
    }

    public Simulator(SimulationModel model, EventInvoker invoker) {
        this.model = model;
        this.invoker = invoker;
    }

    public void simulate() {
        prepareEnvironment();
        while (true) {
            while (isRunning && !calendar.isEmpty()) {
                ScheduledEvent scheduledEvent = calendar.getNextEvent();

                localVirtualTime = scheduledEvent.getTimeStamp();
                invoker.invoke(this, scheduledEvent.getEvent());

                calendar.remove(scheduledEvent);
                notifyScheduledEventListeners(scheduledEvent);
            }
        }
    }

    private void prepareEnvironment() {
        calendar = new Calendar();

        model.prepare();
        for (ScheduledEvent scheduledEvent : model.getInitialEvents()) {
            scheduleAt(scheduledEvent.getTimeStamp(), scheduledEvent.getEvent());
        }
    }

    public double getLocalSimulationTime() {
        return localVirtualTime;
    }

    public void scheduleAt(final double time, final Event event) {
        calendar.schedule(event, time);
    }

    public void addEventListener(ScheduledEventListener listener) {
        scheduledEventListeners.add(listener);
    }

    private void notifyScheduledEventListeners(ScheduledEvent scheduledEvent) {
        for (ScheduledEventListener listener : scheduledEventListeners) {
            listener.processed(scheduledEvent);
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}