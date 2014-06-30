package simulator.disis;

import com.google.gson.annotations.SerializedName;

/**
 * This is DISIS
 * Authors: Jirka Penzes & Jan Voracek
 * Date: 30. 6. 2014 11:08
 */
public class RestClientInfo {

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

    public RestClientInfo(String title, String remoteName, String description, String endPointAddress) {
        this.title = title;
        this.remoteName = remoteName;
        this.description = description;
        this.endPointAddress = endPointAddress;
    }
}
