package test.gyatsina.wikiatask.models;

public class DetailedItemById {
    private int id;
    private String wordmark;
    private String title;
    private String url;
    private String lang;
    private String domain;


//    stats: {
//        edits: 2203091,
//                articles: 7085,
//                pages: 532199,
//                users: 24136943,
//                activeUsers: 220,
//                images: 37676,
//                videos: 1932,
//                admins: 54
//    },
//    topUsers: [
//            4454520,
//            1142365,
//            1970687,
//            1187559,
//            1905698,
//            3326813,
//            356904,
//            1916935,
//            4767172,
//            1888721
//            ],
//
//    flags: [ ],
//    desc: "The Call of Duty Wiki is the number one place for Call of Duty information on the web. We host detailed information on everything Call of Duty; whether it be the campaign levels of Call of Duty 2 or the weapons from Call of Duty: Black Ops. We're currently working on over 5,500 pages, and we maintain around 29,000 images used to adorn our articles and gallery pages. We're always striving to improve our information, so feel free to stop down and join in! Or maybe you're just stuck on a level, or want to know more about your favorite weapon. We'll be able to help.",
//    image: "http://vignette3.wikia.nocookie.net/wikiaglobal/images/d/da/Wikia-Visualization-Main%2CCallofduty.png/revision/latest?cb=20120828154217",
//    wam_score: "98.5521",
//    original_dimensions: {
//        width: "320",
//                height: "320"
//    }

    public DetailedItemById(int id, String name, String hub, String language, String topic, String domain) {
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

