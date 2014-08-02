package core.disis;

import core.simulator.Event;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 2. 7. 2014 16:45
 */
public abstract class DistributedEvent implements Event {
    private String remoteSimulator;

    protected DistributedEvent(String remoteSimulator) {
        this.remoteSimulator = remoteSimulator;
    }

    public String getRemoteSimulatorName() {
        return remoteSimulator;
    }
}
