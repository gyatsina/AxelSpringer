package test.gyatsina.axelspringer.models;

/**
 * Created by Admin on 28.08.2015.
 */
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
