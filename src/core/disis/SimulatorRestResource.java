package core.disis;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 30. 6. 2014 15:56
 */

@Path("/")
public class SimulatorRestResource {
    @POST
    @Path("start-simulation")
    public void startSimulation() {
        StaticContext.getController().startSimulation();
    }

    @POST
    @Path("null-message-request")
    @Consumes(MediaType.APPLICATION_JSON)
    public void nullMessageRequest(String rawMessage) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(rawMessage).getAsJsonObject();
        String from = jsonObject.get("from").getAsString();
        double currentLVT = jsonObject.get("current-lvt").getAsDouble();

    }
}
