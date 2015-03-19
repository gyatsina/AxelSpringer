package test.gyatsina.wikiatask.models;

public class GamingItemInList {
    private int id;
    private String name;
    private String hub;
    private String language;
    private String topic;
    private String domain;

    public GamingItemInList(int id, String name, String hub, String language, String topic, String domain) {
        this.id = id;
        this.name = name;
        this.hub = hub;
        this.language = language;
        this.topic = topic;
        this.domain = domain;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHub() {
        return hub;
    }

    public String getLanguage() {
        return language;
    }

    public String getTopic() {
        return topic;
    }

    public String getDomain() {
        return domain;
    }
}

