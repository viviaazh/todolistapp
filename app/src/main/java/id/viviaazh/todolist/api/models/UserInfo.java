package id.viviaazh.todolist.api.models;

public class UserInfo {
    String name;

    public UserInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
