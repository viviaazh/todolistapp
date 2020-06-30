package id.viviaazh.todolist.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Error {
    @SerializedName("username")
    @Expose
    private List<String> username = null;

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    @SerializedName("name")
    @Expose
    private List<String> name = null;

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    @SerializedName("currentPassword")
    @Expose
    private List<String> currentPassword = null;

    public List<String> getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(List<String> currentPassword) {
        this.currentPassword = currentPassword;
    }

    @SerializedName("newPassword")
    @Expose
    private List<String> newPassword = null;

    public List<String> getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(List<String> newPassword) {
        this.newPassword = newPassword;
    }
}
