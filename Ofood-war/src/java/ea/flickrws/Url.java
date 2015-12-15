
package ea.flickrws;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Url {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("_content")
    @Expose
    private String Content;

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The Content
     */
    public String getContent() {
        return Content;
    }

    /**
     * 
     * @param Content
     *     The _content
     */
    public void setContent(String Content) {
        this.Content = Content;
    }

}
