package demo;

import simulator.core.Event;
import simulator.core.Simulator;
import simulator.core.TimeStamp;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 29. 6. 2014 16:24
 */
public abstract class PeriodicEvent implements Event {
    private int period;

    protected PeriodicEvent(int period) {
        this.period = period;
    }

    @Override
    public void execute(Simulator simulator) {
        doAction(simulator);
        TimeStamp currentTime = simulator.getLocalSimulationTime();
        TimeStamp nextOccurrenceTime = currentTime.add(period);
        simulator.scheduleAt(nextOccurrenceTime, this);
    }

    protected abstract void doAction(Simulator simulator);
}
