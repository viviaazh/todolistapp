package id.viviaazh.todolist.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Error {
    @SerializedName("username")
    @Expose
    private List<String> username = null;

    @SerializedName("name")
    @Expose
    private List<String> name = null;

    @SerializedName("password")
    @Expose
    private List<String> password = null;

    public Error(List<String> username, List<String> name, List<String> password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }
}
