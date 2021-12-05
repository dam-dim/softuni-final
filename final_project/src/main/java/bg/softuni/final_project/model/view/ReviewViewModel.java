package bg.softuni.final_project.model.view;

public class ReviewViewModel {
    private String username;
    private String firstName;
    private String lastName;
    private String message;
    private String authorId;
    private String id;
    private String postedOn;
    private boolean isOwner;

    public ReviewViewModel() {

    }

    public String getUsername() {
        return username;
    }

    public ReviewViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ReviewViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ReviewViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ReviewViewModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getAuthorId() {
        return authorId;
    }

    public ReviewViewModel setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getId() {
        return id;
    }

    public ReviewViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getPostedOn() {
        return postedOn;
    }

    public ReviewViewModel setPostedOn(String postedOn) {
        this.postedOn = postedOn;
        return this;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public ReviewViewModel setOwner(boolean owner) {
        isOwner = owner;
        return this;
    }
}
