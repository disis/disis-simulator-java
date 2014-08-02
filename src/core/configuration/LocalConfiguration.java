package core.configuration;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 30. 6. 2014 15:31
 */

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class LocalConfiguration {

    @SerializedName("simulator-title")
    private final String title;

    @SerializedName("simulator-name")
    private final String name;

    @SerializedName("simulator-description")
    private final String description;

    @SerializedName("simulator-address")
    private final String address;

    @SerializedName("simulator-port")
    private final int port;

    @SerializedName("disis-address")
    private final String disisAddress;

    @SerializedName("disis-port")
    private final int disisPort;

    @SerializedName("disis-path")
    private final String disisPath;

    @SerializedName("surrounding-simulators")
    private final List<String> surroundingSimulators;

    public LocalConfiguration() {
        this("Unknown Simulator", "unknown-simulator", "Unknown Java Simulator", "localhost", 1099, "localhost", 1099, "/disis", Collections.<String>emptyList());
    }

    public LocalConfiguration(String title, String name, String description, String address, int port, String disisAddress, int disisPort, String disisPath, List<String> surroundingSimulators) {
        this.title = title;
        this.name = name;
        this.description = description;
        this.address = address;
        this.port = port;
        this.disisAddress = disisAddress;
        this.disisPort = disisPort;
        this.disisPath = disisPath;
        this.surroundingSimulators = surroundingSimulators;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public String getDisisAddress() {
        return disisAddress;
    }

    public int getDisisPort() {
        return disisPort;
    }

    public String getDisisPath() {
        return disisPath;
    }

    public List<String> getSurroundingSimulators() {
        return surroundingSimulators;
    }

    public String getDisisFullAddress() {
        return String.format("http://%s:%d%s", getDisisAddress(), getDisisPort(), getDisisPath());
    }

    public String getSimulatorFullAddress() {
        return String.format("http://%s:%d", getAddress(), getPort());
    }
}
