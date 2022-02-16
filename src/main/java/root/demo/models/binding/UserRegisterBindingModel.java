package root.demo.models.binding;

import root.demo.customValidations.isEmailFree;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRegisterBindingModel {

    @NotEmpty(message = "Email name cannot be empty.")
    @isEmailFree(message = "This email is already taken.")
    private String email;
    @NotEmpty(message = "Password  cannot be empty.")
    private String password;
    private String confirmPassword;
    @NotEmpty(message = "First name cannot be empty.")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty.")
    private String lastName;
    @NotEmpty(message = "Address cannot be empty.")
    private String address;
    @NotNull(message = "Telephone cannot be empty.")
    private int telephone;

    public UserRegisterBindingModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
}
