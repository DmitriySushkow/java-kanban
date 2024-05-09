package tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subTasksIdInThisEpic = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public ArrayList<Integer> getSubTasksIdInThisEpic() {
        return subTasksIdInThisEpic;
    }

    public void clearSubTasksIdList() {
        subTasksIdInThisEpic.clear();
    }

    public void addIdToList(int id) {
        subTasksIdInThisEpic.add(id);
    }

    public void removeIdFromList(Integer id) {
        subTasksIdInThisEpic.remove(id);
    }
}
