package core.disis;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import core.configuration.LocalConfiguration;
import core.simulator.ScheduledEvent;
import core.simulator.ScheduledEventListener;
import core.simulator.Simulator;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 2. 7. 2014 15:40
 */
public class DisisController implements ScheduledEventListener {
    private final LocalConfiguration configuration;
    private final Simulator simulator;
    private final RestSimulatorInfo clientInfo;
    private double latestLocalVirtualTime = -1;
    private Map<String, Integer> counters = new HashMap<>();

    public DisisController(LocalConfiguration configuration, Simulator simulator) {
        this.configuration = configuration;
        this.simulator = simulator;
        clientInfo = new RestSimulatorInfo(
                configuration.getTitle(),
                configuration.getName(),
                configuration.getDescription(),
                configuration.getSimulatorFullAddress(),
                configuration.getSurroundingSimulators());

        for (String simulatorName : configuration.getSurroundingSimulators()) {
            counters.put(simulatorName, 0);
        }
    }

    public void connect() {
        WebResource resource = getResource("connect");
        String s = makeJson(clientInfo);
        resource.type(MediaType.APPLICATION_JSON).post(s);
    }

    public void updateTime(double localVirtualTime) {
        WebResource resource = getResource("update-simulation-timestamp");
        UpdateMessage updateMessage = new UpdateMessage(clientInfo, localVirtualTime);
        resource.post(makeJson(updateMessage));
    }

    @Override
    public void processed(ScheduledEvent scheduledEvent) {
        if(scheduledEvent.getEvent() instanceof DistributedEvent) {
            DistributedEvent distributedEvent = (DistributedEvent)scheduledEvent.getEvent();

            String remoteSimulatorName = distributedEvent.getRemoteSimulatorName();
            int counter = counters.get(remoteSimulatorName) - 1; // decrement counter
            counters.put(remoteSimulatorName, counter);

            if(counter == 0) {
                simulator.setRunning(false);
            }
        }

        if (scheduledEvent.getTimeStamp() > latestLocalVirtualTime) {
            latestLocalVirtualTime = scheduledEvent.getTimeStamp();
            updateTime(latestLocalVirtualTime);
        }
    }

    private WebResource getResource(String method) {
        Client client = Client.create();
        return client.resource(configuration.getDisisFullAddress()).path(method);
    }

    private String makeJson(Object object) {
        return new Gson().toJson(object);
    }
}
