package core.disis;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 30. 6. 2014 11:08
 */
public class RestSimulatorInfo {

    // Fast Food Left
    @SerializedName("title")
    public String title;

    // fast-food-II
    @SerializedName("remote-name")
    public String remoteName;

    // Implemented in Java platform
    @SerializedName("description")
    public String description;

    // http://localhost:1000/fast-food-ii/push/
    @SerializedName("end-point-address")
    public String endPointAddress;

    // ["fast-food-I", "highway-I"]
    @SerializedName("surrounding-simulators")
    public List<String> surroundingSimulators;

    public RestSimulatorInfo(String title, String remoteName, String description, String endPointAddress, List<String> surroundingSimulators) {
        this.title = title;
        this.remoteName = remoteName;
        this.description = description;
        this.endPointAddress = endPointAddress;
        this.surroundingSimulators = surroundingSimulators;
    }
}
