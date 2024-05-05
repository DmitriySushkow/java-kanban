import java.util.HashMap;

public class TaskManager {

    HashMap<Integer, Task> simpleTasks = new HashMap<>();
    HashMap<Integer, Epic> epicTasks = new HashMap<>();

    int simpleTasksCounter = 1;
    int epicTasksCounter = 1;
    int subTasksCounter = 1;

    // Получение списка всех задач:
    String returnSimpleTasks() {

        StringBuilder allTasksList = new StringBuilder("\nSIMPLE TASKS:\n\n");
        for (Task task : simpleTasks.values()) {
            allTasksList.append(task.toString());
        }
        return allTasksList.toString();
    }
    String returnEpicTasks() {

        StringBuilder allEpicsList = new StringBuilder("EPIC TASKS:\n\n");
        for (Epic epic : epicTasks.values()) {
            allEpicsList.append(epic.toString());
            allEpicsList.append("SUBTASKS IN THIS EPIC:\n");
            for (SubTask subTask : epic.subTasksInThisEpic.values()) {
                allEpicsList.append(subTask.toString());
            }
            allEpicsList.append("\n");
        }
        return allEpicsList.toString();
    }

    // Удаление всех задач:
    void clearSimpleTasks() {
        simpleTasks.clear();
        simpleTasksCounter = 1;
    }
    void clearEpics() {
        epicTasks.clear();
        epicTasksCounter = 1;
        subTasksCounter = 1;
    }

    // Получение по идентификатору:
    Task getTaskByIdentifier(int id) {
        return simpleTasks.get(id);
    }
    Epic getEpicByIdentifier(int id) {
        return epicTasks.get(id);
    }
    SubTask getSubTaskByIdentifier(int id) {
        for (Epic epic : epicTasks.values()) {
            for (SubTask subTask : epic.subTasksInThisEpic.values()) {
                if (subTask.getId() == id) {
                    return subTask;
                }
            }
        }
        return null;
    }

    // Создание. Сам объект должен передаваться в качестве параметра:
    void addSimpleTask(Task task) {
        task.setId(simpleTasksCounter);
        task.setStatus(TaskStatus.NEW);
        simpleTasks.put(simpleTasksCounter, task);
        simpleTasksCounter++;
    }
    void addEpic(Epic epic) {
        epic.setId(epicTasksCounter);
        epic.setStatus(TaskStatus.NEW);
        epicTasks.put(epicTasksCounter, epic);
        epicTasksCounter++;
    }
    void addSubTaskToEpic(SubTask subTask, int epicId) {
        subTask.setId(subTasksCounter);
        subTask.setStatus(TaskStatus.NEW);

        Epic currentEpic = epicTasks.get(epicId);
        currentEpic.subTasksInThisEpic.put(subTasksCounter, subTask);

        subTasksCounter++;
    }

    // Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра:
    void updateTask(Task updatedTask) {
        int id = updatedTask.getId();
        if (simpleTasks.containsKey(id)) {
            simpleTasks.remove(id);
            simpleTasks.put(id, updatedTask);
        }
    }
    void updateEpic(Epic updatedEpic) {

        int id = updatedEpic.getId();

        updatedEpic.setStatus(epicTasks.get(id).getStatus());

        if (epicTasks.containsKey(id)) {
            epicTasks.remove(id);
            epicTasks.put(id, updatedEpic);
        }
    }
    void updateSubTask(SubTask subTask) {
        int id = subTask.getId();
        for (Epic epic : epicTasks.values()) {
            if (epic.subTasksInThisEpic.containsKey(id)) {
                epic.subTasksInThisEpic.remove(id);
                epic.subTasksInThisEpic.put(id, subTask);
                // Если обновлённая подзадача приобретает статус "IN_PROGRESS", её эпик приобретает такой же статус:
                if (subTask.getStatus().equals(TaskStatus.IN_PROGRESS)) {
                    epic.setStatus(TaskStatus.IN_PROGRESS);
                }
                // Если все подзадачи приобретают статус готово, эпик приобретает тот же статус:
                if (isEpicDone(epic)) {
                    epic.setStatus(TaskStatus.DONE);
                }
            }
        }
    }
    // Проверка, имеет ли хоть одна подзадача эпика статус, отличный от "DONE":
    boolean isEpicDone(Epic epic) {
        for (SubTask subTask : epic.subTasksInThisEpic.values()) {
            if (!(subTask.getStatus().equals(TaskStatus.DONE))) {
                return false;
            }
        }
        return true;
    }

    // Удаление по идентификатору:
    void removeTaskByIdentifier(int id) {
        simpleTasks.remove(id);
    }
    void removeEpicByIdentifier(int id) {
        epicTasks.remove(id);
    }
    void removeSubTaskByIdentifier(int id) {
        for (Epic epic : epicTasks.values()) {
            epic.subTasksInThisEpic.remove(id);
        }
    }

    // Получение списка всех подзадач определённого эпика:
    String getAllSubTasksFromEpic(int epicId) {
        return (epicTasks.get(epicId).subTasksInThisEpic).toString();
    }
}