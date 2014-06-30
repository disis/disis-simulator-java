package core.configuration;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 30. 6. 2014 15:31
 */

import com.google.gson.annotations.SerializedName;

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

    @SerializedName("simulator-path")
    private final String path;

    @SerializedName("disis-address")
    private final String disisAddress;

    @SerializedName("disis-port")
    private final int disisPort;

    @SerializedName("disis-path")
    private final String disisPath;

    public LocalConfiguration() {
        this("Unknown Simulator", "unknown-simulator", "Unknown Java Simulator", "localhost", 1099, "/", "localhost", 1099, "/disis");
    }

    public LocalConfiguration(String title, String name, String description, String address, int port, String path, String disisAddress, int disisPort, String disisPath) {
        this.title = title;
        this.name = name;
        this.description = description;
        this.address = address;
        this.port = port;
        this.path = path;
        this.disisAddress = disisAddress;
        this.disisPort = disisPort;
        this.disisPath = disisPath;
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

    public String getPath() {
        return path;
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

    public String getDisisFullAddress() {
        return String.format("http://%s:%d%s", getDisisAddress(), getDisisPort(), getDisisPath());
    }

    public String getSimulatorFullAddress() {
        return String.format("http://%s:%d%s", getAddress(), getPort(), getPath());
    }
}