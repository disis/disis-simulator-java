package demo;

import simulator.core.Simulator;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 29. 6. 2014 16:39
 */
public class CounterEvent extends PeriodicEvent {
    private int counter = 0;

    protected CounterEvent(int period) {
        super(period);
    }

    @Override
    protected void doAction(Simulator simulator) {
        System.out.println("Time: " + simulator.getLocalSimulationTime());
        System.out.println("Counter: " + counter++);
    }
}