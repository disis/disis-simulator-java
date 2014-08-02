package core.disis;

import core.simulator.Simulator;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 2. 7. 2014 15:22
 */
public class StaticContext {
    private static Simulator simulator;

    public static void init(Simulator simulator) {
        StaticContext.simulator = simulator;
    }

    public static Simulator getSimulator() {
        return simulator;
    }
}
