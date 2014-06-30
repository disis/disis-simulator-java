package demo;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import core.configuration.ConfigurationLoader;
import core.configuration.LocalConfiguration;
import core.simulator.core.ScheduledEvent;
import core.simulator.core.SimulationModel;
import core.simulator.core.Simulator;
import core.simulator.core.TimeStamp;
import core.simulator.disis.RestClientInfo;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 21. 6. 2014 8:07
 */
public class Demo {

    public static void main(String[] args) {
        String configurationPath = new File("src/demo/configuration/configuration-sample.json").getAbsolutePath();
        LocalConfiguration configuration = ConfigurationLoader.load(configurationPath, LocalConfiguration.class);

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource resource = client.resource(configuration.getDisisFullAddress());
        RestClientInfo clientInfo = new RestClientInfo(
                configuration.getTitle(),
                configuration.getName(),
                configuration.getDescription(),
                configuration.getSimulatorFullAddress());
        Gson gson = new Gson();
        resource.type(MediaType.APPLICATION_JSON).post(gson.toJson(clientInfo));
    }

    private static void simulatorDemo() {
        Simulator simulator = new Simulator(new SleepInvoker(1000));
        simulator.simulate(new SimulationModel(){
            @Override
            public Iterable<ScheduledEvent> getInitialEvents() {
                List<ScheduledEvent> events = new ArrayList<>();
                events.add(new ScheduledEvent(new CounterEvent(100), new TimeStamp(0)));
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
