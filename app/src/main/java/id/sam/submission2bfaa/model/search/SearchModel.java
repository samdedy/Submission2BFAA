
package id.sam.submission2bfaa.model.search;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchModel implements Serializable, Parcelable
{

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    public final static Creator<SearchModel> CREATOR = new Creator<SearchModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SearchModel createFromParcel(Parcel in) {
            return new SearchModel(in);
        }

        public SearchModel[] newArray(int size) {
            return (new SearchModel[size]);
        }

    }
    ;
    private final static long serialVersionUID = -5204867283311993926L;

    protected SearchModel(Parcel in) {
        this.totalCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.incompleteResults = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.items, (Item.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public SearchModel() {
    }

    /**
     * 
     * @param incompleteResults
     * @param totalCount
     * @param items
     */
    public SearchModel(Integer totalCount, Boolean incompleteResults, List<Item> items) {
        super();
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totalCount);
        dest.writeValue(incompleteResults);
        dest.writeList(items);
    }

    public int describeContents() {
        return  0;
    }

}
