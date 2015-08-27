package test.gyatsina.axelspringer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gyatsina
 */
public class ImagesResponse {
    private int page;
    @SerializedName("per_page")
    private int perPage;
    @SerializedName("total_count")
    private int totalCount;
    private List<ShutterImage> data;

    public ImagesResponse(int page, int perPage, int totalCount, List<ShutterImage> data) {
        this.page = page;
        this.perPage = perPage;
        this.totalCount = totalCount;
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<ShutterImage> getData() {
        return data;
    }
}
