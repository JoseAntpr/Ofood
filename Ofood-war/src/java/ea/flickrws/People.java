
package ea.flickrws;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class People {

    @SerializedName("haspeople")
    @Expose
    private Integer haspeople;

    /**
     * 
     * @return
     *     The haspeople
     */
    public Integer getHaspeople() {
        return haspeople;
    }

    /**
     * 
     * @param haspeople
     *     The haspeople
     */
    public void setHaspeople(Integer haspeople) {
        this.haspeople = haspeople;
    }

}
