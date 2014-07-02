package core.disis;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 30. 6. 2014 15:56
 */

@Path("/")
public class SimulatorRestResource {
    @POST
    @Path("connected")
    public void connected() {
        System.out.println("Yeah!");
    }

    @POST
    @Path("start-simulation")
    public void startSimulation() {
        System.out.println("Yeah!");
    }
}
