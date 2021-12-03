package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.validator.username.UniqueUserName;

import javax.validation.constraints.*;

public class UserRegisterBindingModel {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    @NotNull(message = "First name is required.")
    @Size(min = 2, max = 15, message = "First name should be between 2 and 15 characters long.")
    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotNull(message = "Last name is required.")
    @Size(min = 2, max = 15, message = "Last name should be between 2 and 15 characters long.")
    public String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @NotNull(message = "Username is required.")
    @Size(min = 2, max = 15, message = "Username should be between 2 and 15 characters long.")
    @UniqueUserName
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotBlank(message = "Email is required.")
    @Email
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotNull(message = "Password is required.")
    @Size(min = 4, message = "Password must be between 4 and 10 characters long.")
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotNull(message = "Password is required.")
    @Size(min = 4, max = 10, message = "Password must be between 4 and 10 characters long.")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
