package test.gyatsina.axelspringer.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 28.08.2015.
 */
public class ThumbHolder {
    @SerializedName("large_thumb")
    private LargeThumb largeThumb;

    public ThumbHolder(LargeThumb largeThumb) {
        this.largeThumb = largeThumb;
    }

    public LargeThumb getLargeThumb() {
        return largeThumb;
    }

    public class LargeThumb {
        private String url;

        public LargeThumb(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
