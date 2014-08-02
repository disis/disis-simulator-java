package core.simulator;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 24. 6. 2014 18:24
 */
public interface EventInvoker {
    void invoke(Simulator simulator, Event event);
}
