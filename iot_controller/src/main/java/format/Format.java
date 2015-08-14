
package format;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "Device",
    "Data",
    "required"
})
public class Format {

    @JsonProperty("Device")
    private format.Device Device;
    @JsonProperty("Data")
    private format.Data Data;
    @JsonProperty("required")
    private List<String> required = new ArrayList<String>();

    /**
     * 
     * @return
     *     The Device
     */
    @JsonProperty("Device")
    public format.Device getDevice() {
        return Device;
    }

    /**
     * 
     * @param Device
     *     The Device
     */
    @JsonProperty("Device")
    public void setDevice(format.Device Device) {
        this.Device = Device;
    }

    /**
     * 
     * @return
     *     The Data
     */
    @JsonProperty("Data")
    public format.Data getData() {
        return Data;
    }

    /**
     * 
     * @param Data
     *     The Data
     */
    @JsonProperty("Data")
    public void setData(format.Data Data) {
        this.Data = Data;
    }

    /**
     * 
     * @return
     *     The required
     */
    @JsonProperty("required")
    public List<String> getRequired() {
        return required;
    }

    /**
     * 
     * @param required
     *     The required
     */
    @JsonProperty("required")
    public void setRequired(List<String> required) {
        this.required = required;
    }

}
