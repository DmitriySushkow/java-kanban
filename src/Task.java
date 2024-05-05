import java.util.Objects;

public class Task {

    String name;
    String description;
    private int id;
    private TaskStatus status;

    public Task() {}
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    } // Для создания
    public Task(String name, String description, int id, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.setId(id);
        this.setStatus(status);
    } // Для теста

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        if (Objects.equals(id, task.id)) {
            return true;
        }
        return  Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(status, task.status);
    }
    @Override
    public String toString() {
        return "NAME: '" + name + "' ID: '" + id + "' STATUS: '" + status + "'\nDESCRIPTION: '" + description + "'\n";
    }
}
