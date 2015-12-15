
package ea.flickrws;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Comments {

    @SerializedName("_content")
    @Expose
    private String Content;

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
