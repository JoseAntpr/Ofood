
package ea.flickrws;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Usage {

    @SerializedName("candownload")
    @Expose
    private Integer candownload;
    @SerializedName("canblog")
    @Expose
    private Integer canblog;
    @SerializedName("canprint")
    @Expose
    private Integer canprint;
    @SerializedName("canshare")
    @Expose
    private Integer canshare;

    /**
     * 
     * @return
     *     The candownload
     */
    public Integer getCandownload() {
        return candownload;
    }

    /**
     * 
     * @param candownload
     *     The candownload
     */
    public void setCandownload(Integer candownload) {
        this.candownload = candownload;
    }

    /**
     * 
     * @return
     *     The canblog
     */
    public Integer getCanblog() {
        return canblog;
    }

    /**
     * 
     * @param canblog
     *     The canblog
     */
    public void setCanblog(Integer canblog) {
        this.canblog = canblog;
    }

    /**
     * 
     * @return
     *     The canprint
     */
    public Integer getCanprint() {
        return canprint;
    }

    /**
     * 
     * @param canprint
     *     The canprint
     */
    public void setCanprint(Integer canprint) {
        this.canprint = canprint;
    }

    /**
     * 
     * @return
     *     The canshare
     */
    public Integer getCanshare() {
        return canshare;
    }

    /**
     * 
     * @param canshare
     *     The canshare
     */
    public void setCanshare(Integer canshare) {
        this.canshare = canshare;
    }

}
