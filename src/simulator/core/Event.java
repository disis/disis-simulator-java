package simulator.core;

import simulator.core.Simulator;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 23. 6. 2014 10:39
 */
public interface Event {
    void execute(Simulator simulator);
}
