package test.gyatsina.axelspringer.models;

/**
 * Created by gyatsina
 */
public class ComplexGameItem {
    private int id;
    private String name;
    private String domain;
    private String image;

    public ComplexGameItem(int id, String name, String domain, String image) {
        this.id = id;
        this.name = name;
        this.domain = domain;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public String getImage() {
        return image;
    }
}

