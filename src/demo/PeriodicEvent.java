package demo;

import core.simulator.Event;
import core.simulator.Simulator;

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
        double currentTime = simulator.getLocalSimulationTime();
        double nextOccurrenceTime = currentTime + period;
        simulator.scheduleAt(nextOccurrenceTime, this);
    }

    protected abstract void doAction(Simulator simulator);
}
