
package format;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "payload",
    "timestamp",
    "count",
    "unit"
})
public class Properties_ {

    @JsonProperty("payload")
    private Payload payload;
    @JsonProperty("timestamp")
    private Timestamp timestamp;
    @JsonProperty("count")
    private Count count;
    @JsonProperty("unit")
    private Unit unit;

    /**
     * 
     * @return
     *     The payload
     */
    @JsonProperty("payload")
    public Payload getPayload() {
        return payload;
    }

    /**
     * 
     * @param payload
     *     The payload
     */
    @JsonProperty("payload")
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    /**
     * 
     * @return
     *     The timestamp
     */
    @JsonProperty("timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param timestamp
     *     The timestamp
     */
    @JsonProperty("timestamp")
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 
     * @return
     *     The count
     */
    @JsonProperty("count")
    public Count getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    @JsonProperty("count")
    public void setCount(Count count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The unit
     */
    @JsonProperty("unit")
    public Unit getUnit() {
        return unit;
    }

    /**
     * 
     * @param unit
     *     The unit
     */
    @JsonProperty("unit")
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

}
