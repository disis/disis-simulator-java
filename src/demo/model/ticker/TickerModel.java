package demo.model.ticker;

import core.simulator.ScheduledEvent;
import core.simulator.SimulationModel;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 02/08/14 11:48
 */
public class TickerModel extends SimulationModel {

    private int tick = 1;
    private double firstTickTime;

    public TickerModel(int tick, double firstTickTime) {
        this.tick = tick;
        this.firstTickTime = firstTickTime;
    }

    @Override
    public void prepare() {
        ScheduledEvent scheduledEvent = new ScheduledEvent(new CounterEvent(tick), firstTickTime);
        initialEvents.add(0, scheduledEvent);
    }
}
