package core.simulator;

import java.util.Collections;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 24. 6. 2014 18:25
 */
public class SimulationModel {
    public void prepare() {
    }

    public Iterable<ScheduledEvent> getInitialEvents() {
        return Collections.emptyList();
    }
}
