package bg.softuni.final_project.model.binding;

import javax.validation.constraints.NotBlank;

public class ReviewAddBindingModel {
    private String message;

    public ReviewAddBindingModel() {
    }

    @NotBlank(message = "You need to enter a message.")
    public String getMessage() {
        return message;
    }

    public ReviewAddBindingModel setMessage(String message) {
        this.message = message;
        return this;
    }
}
