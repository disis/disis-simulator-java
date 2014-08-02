package core.disis;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 2. 7. 2014 15:22
 */
public class StaticContext {
    private static DisisController controller;

    public static void init(DisisController controller) {
        StaticContext.controller = controller;
    }

    public static DisisController getController() {
        return controller;
    }
}
