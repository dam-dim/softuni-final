package bg.softuni.final_project.model.view;

public class CourseViewModel {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private int clicks;

    public CourseViewModel() {
    }

    public String getId() {
        return id;
    }

    public CourseViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CourseViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getClicks() {
        return clicks;
    }

    public CourseViewModel setClicks(int clicks) {
        this.clicks = clicks;
        return this;
    }
}
