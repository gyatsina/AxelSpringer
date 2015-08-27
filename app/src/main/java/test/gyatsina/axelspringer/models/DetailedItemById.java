package test.gyatsina.axelspringer.models;

/**
 * Created by gyatsina
 */
public class DetailedItemById {
    private int id;
    private String wordmark;
    private String title;
    private String url;
    private String lang;
    private String domain;
    private String desc;
    private String image;
    private String wam_score;
    private Stats stats;
    private OriginalDimensions original_dimensions;

    public DetailedItemById(int id, String wordmark, String title, String url, String lang, String domain, String desc, String image, String wam_score, Stats stats, OriginalDimensions original_dimensions) {
        this.id = id;
        this.wordmark = wordmark;
        this.title = title;
        this.url = url;
        this.lang = lang;
        this.domain = domain;
        this.desc = desc;
        this.image = image;
        this.wam_score = wam_score;
        this.stats = stats;
        this.original_dimensions = original_dimensions;
    }

    public int getId() {
        return id;
    }

    public String getWordmark() {
        return wordmark;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getLang() {
        return lang;
    }

    public String getDomain() {
        return domain;
    }

    public String getDesc() {
        return desc;
    }

    public String getImage() {
        return image;
    }

    public String getWam_score() {
        return wam_score;
    }

    public Stats getStats() {
        return stats;
    }

    public OriginalDimensions getOriginal_dimensions() {
        return original_dimensions;
    }

    public class Stats {
        private int edits;
        private int articles;
        private int pages;
        private int users;
        private int activeUsers;
        private int images;
        private int videos;
        private int admins;

        public Stats(int edits, int articles, int pages, int users, int activeUsers, int images, int videos, int admins) {
            this.edits = edits;
            this.articles = articles;
            this.pages = pages;
            this.users = users;
            this.activeUsers = activeUsers;
            this.images = images;
            this.videos = videos;
            this.admins = admins;
        }

        public int getEdits() {
            return edits;
        }

        public int getArticles() {
            return articles;
        }

        public int getPages() {
            return pages;
        }

        public int getUsers() {
            return users;
        }

        public int getActiveUsers() {
            return activeUsers;
        }

        public int getImages() {
            return images;
        }

        public int getVideos() {
            return videos;
        }

        public int getAdmins() {
            return admins;
        }
    }

    public class OriginalDimensions{
        private String width;
        private String height;

        public OriginalDimensions(String width, String height) {
            this.width = width;
            this.height = height;
        }

        public String getWidth() {
            return width;
        }

        public String getHeight() {
            return height;
        }
    }
}
