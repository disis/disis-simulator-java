package core.simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 24. 6. 2014 18:25
 */
public abstract class SimulationModel {

    protected List<ScheduledEvent> initialEvents;

    protected SimulationModel() {
        initialEvents = new ArrayList<>();
    }

    public abstract void prepare();

    public Iterable<ScheduledEvent> getInitialEvents() {
        return initialEvents;
    }
}
