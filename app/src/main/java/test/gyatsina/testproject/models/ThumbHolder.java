package test.gyatsina.testproject.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gyatsina
 */

// Class for parsed Thumb model received from backend
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
