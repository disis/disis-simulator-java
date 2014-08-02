package core.simulator;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 24. 6. 2014 18:24
 */
public class BaseInvoker implements EventInvoker {
    @Override
    public void invoke(Simulator simulator, Event event) {
        event.execute(simulator);
    }
}
