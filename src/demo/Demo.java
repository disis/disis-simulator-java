package demo;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import core.configuration.ConfigurationLoader;
import core.configuration.LocalConfiguration;
import core.disis.DisisController;
import core.disis.RestSimulatorInfo;
import core.disis.SimulatorRestResource;
import core.disis.StaticContext;
import core.simulator.ScheduledEvent;
import core.simulator.SimulationModel;
import core.simulator.Simulator;
import demo.model.ticker.CounterEvent;
import demo.model.ticker.TickerModel;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 21. 6. 2014 8:07
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        Map<String, Object> conf1 = new HashMap<>();
        conf1.put("path", "src/demo/configuration/demo2/configuration-sample-1.json");
        conf1.put("start-time", 0.0);
        conf1.put("delay", 100l);

        Map<String, Object> conf2 = new HashMap<>();
        conf2.put("path", "src/demo/configuration/demo2/configuration-sample-2.json");
        conf2.put("start-time", 1.0);
        conf2.put("delay", 100l);

        runSimulator(conf2);
        System.in.read();
    }

    private static void runSimulator(Map<String, Object> conf) throws IOException {
        String configurationPath = new File((String) conf.get("path")).getAbsolutePath();
        LocalConfiguration configuration = ConfigurationLoader.load(configurationPath, LocalConfiguration.class);

        HttpServer httpServer = GrizzlyServerFactory.createHttpServer(configuration.getSimulatorFullAddress(), new ClassNamesResourceConfig(SimulatorRestResource.class));
        httpServer.start();

        Simulator simulator = new Simulator(new TickerModel(2, (Double) conf.get("start-time")), new SleepInvoker((Long) conf.get("delay")));
        DisisController controller = new DisisController(configuration, simulator);
        StaticContext.init(controller);

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource resource = client.resource(configuration.getDisisFullAddress()).path("connect");
        RestSimulatorInfo clientInfo = new RestSimulatorInfo(
                configuration.getTitle(),
                configuration.getName(),
                configuration.getDescription(),
                configuration.getSimulatorFullAddress(),
                configuration.getSurroundingSimulators());
        Gson gson = new Gson();
        resource.type(MediaType.APPLICATION_JSON).post(gson.toJson(clientInfo));
    }

    private static Client connectSimulator(LocalConfiguration configuration) throws IOException {
        HttpServer httpServer = GrizzlyServerFactory.createHttpServer(configuration.getSimulatorFullAddress(), new ClassNamesResourceConfig(SimulatorRestResource.class));
        httpServer.start();

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource resource = client.resource(configuration.getDisisFullAddress()).path("connect");
        RestSimulatorInfo clientInfo = new RestSimulatorInfo(
                configuration.getTitle(),
                configuration.getName(),
                configuration.getDescription(),
                configuration.getSimulatorFullAddress(),
                configuration.getSurroundingSimulators());
        Gson gson = new Gson();
        resource.type(MediaType.APPLICATION_JSON).post(gson.toJson(clientInfo));
        return client;
    }

    public static void demo1() throws IOException {
        String configurationPath = new File("src/demo/configuration/demo1/configuration-sample.json").getAbsolutePath();
        LocalConfiguration configuration = ConfigurationLoader.load(configurationPath, LocalConfiguration.class);

        HttpServer httpServer = GrizzlyServerFactory.createHttpServer(configuration.getSimulatorFullAddress(), new ClassNamesResourceConfig(SimulatorRestResource.class));
        httpServer.start();

        Simulator simulator = new Simulator(new TickerModel(1, 0.0), new SleepInvoker(100));
        DisisController controller = new DisisController(configuration, simulator);
        StaticContext.init(controller);

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource resource = client.resource(configuration.getDisisFullAddress()).path("connect");
        RestSimulatorInfo clientInfo = new RestSimulatorInfo(
                configuration.getTitle(),
                configuration.getName(),
                configuration.getDescription(),
                configuration.getSimulatorFullAddress(),
                configuration.getSurroundingSimulators());
        Gson gson = new Gson();
        resource.type(MediaType.APPLICATION_JSON).post(gson.toJson(clientInfo));
        System.in.read();
    }

    private static void simulatorDemo() {
        Simulator simulator = new Simulator(new SimulationModel() {
            @Override
            public void prepare() {

            }

            @Override
            public Iterable<ScheduledEvent> getInitialEvents() {
                List<ScheduledEvent> events = new ArrayList<>();
                events.add(new ScheduledEvent(new CounterEvent(100), 0));
                return events;
            }
        }, new SleepInvoker(1000));
        simulator.simulate();
    }

    public static void restClient() {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        WebResource resource = client.resource("http://localhost:8099/disis/test");

        String response = resource.accept(MediaType.TEXT_PLAIN).post(String.class);
        System.out.println(response);
    }
}
