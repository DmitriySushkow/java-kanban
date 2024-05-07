package Tasks;

import java.util.ArrayList;

public class Epic extends Task {
    public ArrayList<Integer> subTasksIdInThisEpic = new ArrayList<>();

    public Epic(String name, String description) {
        this.setName(name);
        this.setDescription(description);
        this.setStatus(TaskStatus.NEW);
    }
}
