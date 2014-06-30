package demo;

import core.simulator.core.Event;
import core.simulator.core.EventInvoker;
import core.simulator.core.Simulator;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 29. 6. 2014 16:43
 */
public class SleepInvoker implements EventInvoker {
    private long sleepTime;

    public SleepInvoker(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void invoke(Simulator simulator, Event event) {
        event.execute(simulator);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
