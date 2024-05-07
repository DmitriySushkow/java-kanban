package TaskManager;

import Tasks.*;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, SubTask> subTasks = new HashMap<>();

    int idCounter = 1;

    public ArrayList<Task> returnTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Task> returnEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Task> returnSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    public void clearTasks() {
        tasks.clear();
        updateIdCounter();
    }

    public void clearEpics() {
        epics.clear();
        updateIdCounter();
    }

    public void clearSubTasks() {
        subTasks.clear();
        for (Epic epic : epics.values()) {
            epic.setStatus(TaskStatus.NEW);
        }
        updateIdCounter();
    }

    public Task getTaskByIdentifier(int id) {
        return tasks.get(id);
    }

    public Epic getEpicByIdentifier(int id) {
        return epics.get(id);
    }

    public SubTask getSubTaskByIdentifier(int id) {
        return subTasks.get(id);
    }

    public void addTask(Task task) {
        task.setId(idCounter);
        tasks.put(idCounter, task);
        idCounter++;
    }

    public void addEpic(Epic epic) {
        epic.setId(idCounter);
        epics.put(idCounter, epic);
        idCounter++;
    }

    public void addSubTask(SubTask subTask) {
        subTask.setId(idCounter);
        Epic thisSubTaskEpic = getEpicByIdentifier(subTask.getIdOfEpic());
        thisSubTaskEpic.subTasksIdInThisEpic.add(idCounter);
        subTasks.put(idCounter, subTask);
        idCounter++;
        updateEpicStatus(thisSubTaskEpic.getId());
    }

    public void updateTask(Task updatedTask) {
        int id = updatedTask.getId();
        if (tasks.containsKey(id)) {
            tasks.put(id, updatedTask);
        }
    }

    public void updateEpic(Epic updatedEpic) {
        int id = updatedEpic.getId();
        if (epics.containsKey(id)) {
            epics.put(id, updatedEpic);
        }
    }

    public void updateSubTask(SubTask updatedSubTask) {
        int id = updatedSubTask.getId();
        if (subTasks.containsKey(id)) {
            subTasks.put(id, updatedSubTask);
            updateEpicStatus(updatedSubTask.getIdOfEpic());
        }
    }

    // !!!МЕТОД НЕ ИСПОЛЬЗУЕТСЯ САМОСТОЯТЕЛЬНО!!! ТОЛЬКО КАК ЧАСТЬ МЕТОДОВ, ОБНОВЛЯЮЩИХ ПОДЗАДАЧИ И ИХ СПИСОК.
    private void updateEpicStatus(int idOfEpic) {
        Epic currentEpic = epics.get(idOfEpic);
        /*
        Если список подзадач эпика пуст (например из него удалили одну единственную подзадачу по её идентификатору),
        метод присваевает этому эпику статус NEW и завершает свою работу.
         */
        if (currentEpic.subTasksIdInThisEpic.isEmpty()) {
            currentEpic.setStatus(TaskStatus.NEW);
            return;
        }
        // Если это не так, проверяется статус каждой подзадачи эпика:
        for (int id : currentEpic.subTasksIdInThisEpic) {
            SubTask currentSubTask = getSubTaskByIdentifier(id);
            if (!(currentSubTask.getStatus().equals(TaskStatus.NEW))) {
                if (currentSubTask.getStatus().equals(TaskStatus.IN_PROGRESS)) {
                    currentEpic.setStatus(TaskStatus.IN_PROGRESS);
                    break;
                } else {
                    currentEpic.setStatus(TaskStatus.DONE);
                }
            }
        }
    }

    public void removeTaskByIdentifier(int id) {
        tasks.remove(id);
        updateIdCounter();
    }

    public void removeEpicByIdentifier(int id) {
        epics.remove(id);
        updateIdCounter();
    }

    public void removeSubTaskByIdentifier(int id) {
        Epic changedEpic = epics.get((subTasks.get(id)).getIdOfEpic());
        subTasks.remove(id);
        updateEpic(changedEpic);
        updateIdCounter();
    }

    // Если все списки станут пустыми, можно сбросить счётчик:
    private void updateIdCounter() {
        if ((tasks.isEmpty()) && (epics.isEmpty()) && (subTasks.isEmpty())) {
            idCounter = 1;
        }
    }

    // Получение списка всех подзадач определённого эпика:
    ArrayList<SubTask> subTasksOfEpic(int idOfEpic) {
        ArrayList<SubTask> subTasksOfEpic = new ArrayList<>();
        Epic currentEpic = epics.get(idOfEpic);
        for (int id : currentEpic.subTasksIdInThisEpic) {
            subTasksOfEpic.add(getSubTaskByIdentifier(id));
        }
        return subTasksOfEpic;
    }
}