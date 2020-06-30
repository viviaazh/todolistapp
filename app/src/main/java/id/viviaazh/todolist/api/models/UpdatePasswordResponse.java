package id.viviaazh.todolist.api.models;

public class UpdatePasswordResponse {
    public String name;

    public UpdatePasswordResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
