package core.simulator.core;

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

    public Simulator(SimulationModel model) {
        this(model, new BaseInvoker());
    }

    public Simulator(SimulationModel model, EventInvoker invoker) {
        this.model = model;
        this.invoker = invoker;
    }

    public void simulate() {
        prepareEnvironment();

        while (!calendar.isEmpty()) {
            ScheduledEvent scheduledEvent = calendar.getNextEvent();

            localVirtualTime = scheduledEvent.getTimeStamp();
            invoker.invoke(this, scheduledEvent.getEvent());

            calendar.remove(scheduledEvent);
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
}