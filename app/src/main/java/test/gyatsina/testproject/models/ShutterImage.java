package test.gyatsina.testproject.models;

/**
 * Created by gyatsina
 */

// Class for parsed Image model received from backend
public class ShutterImage {
    private int id;
    private ThumbHolder assets;
    private String description;

    public ShutterImage(int id, ThumbHolder assets, String description) {
        this.id = id;
        this.assets = assets;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public ThumbHolder getAssets() {
        return assets;
    }

    public String getDescription() {
        return description;
    }
}
