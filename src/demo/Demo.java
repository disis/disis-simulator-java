package demo;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import simulator.core.ScheduledEvent;
import simulator.core.SimulationModel;
import simulator.core.Simulator;
import simulator.core.TimeStamp;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 21. 6. 2014 8:07
 */
public class Demo {

    public static void main(String[] args) {
        Simulator simulator = new Simulator(new SleepInvoker(1000));
        simulator.simulate(new SimulationModel(){
            @Override
            public Iterable<ScheduledEvent> getInitialEvents() {
                List<ScheduledEvent> events = new ArrayList<>();
                events.add(new ScheduledEvent(new CounterEvent(100), new TimeStamp(1)));
                return  events;
            }
        });
    }

    public static void restClient() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        WebResource resource = client.resource("http://localhost:8099/disis/test");

        String response = resource.accept(MediaType.TEXT_PLAIN).post(String.class);
        System.out.println(response);
    }
}
