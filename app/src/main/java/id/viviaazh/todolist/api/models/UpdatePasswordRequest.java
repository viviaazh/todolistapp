package id.viviaazh.todolist.api.models;

public class UpdatePasswordRequest {
    public String currentPassword;
    public String password;

    public UpdatePasswordRequest(String currentPassword, String password) {
        this.currentPassword = currentPassword;
        this.password = password;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
