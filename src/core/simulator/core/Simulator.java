package core.simulator.core;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 23. 6. 2014 10:36
 */
public class Simulator {

    private Calendar calendar;
    private TimeStamp localVirtualTime;
    private EventInvoker invoker;

    public Simulator() {
        this(new BaseInvoker());
    }

    public Simulator(EventInvoker invoker) {
        this.invoker = invoker;
    }

    public void simulate(final SimulationModel model) {
        prepareEnvironment(model);

        while (!calendar.isEmpty()) {
            ScheduledEvent scheduledEvent = calendar.getNextEvent();

            localVirtualTime = scheduledEvent.getTimeStamp();
            invoker.invoke(this, scheduledEvent.getEvent());

            calendar.remove(scheduledEvent);
        }
    }

    private void prepareEnvironment(final SimulationModel model) {
        calendar = new Calendar();

        model.prepare();
        for (ScheduledEvent scheduledEvent : model.getInitialEvents()) {
            scheduleAt(scheduledEvent.getTimeStamp(), scheduledEvent.getEvent());
        }
    }

    public TimeStamp getLocalSimulationTime() {
        return localVirtualTime;
    }

    public void scheduleAt(final TimeStamp time, final Event event) {
        calendar.schedule(event, time);
    }
}