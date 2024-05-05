public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        createTasks(taskManager);
        //printAllTasks(taskManager);

    // 1) Меняются ли статусы и поля при обновлении задачи:
        Task updatedTask1 = new Task(taskManager.getTaskByIdentifier(1).name, taskManager.getTaskByIdentifier(1).description,
        1, TaskStatus.DONE);
        Task updatedTask2 = new Task(taskManager.getTaskByIdentifier(2).name, "Сегодня борщик",
        2, TaskStatus.IN_PROGRESS);
        taskManager.updateTask(updatedTask1);
        taskManager.updateTask(updatedTask2);
        //printAllTasks(taskManager);
        // Статусы и поля меняются!

    // 2) Поведение эпиков и подзадач при обновлении:
        Epic updatedEpic1 = new Epic(taskManager.getEpicByIdentifier(1).name, "И стать частью этого нового мира");
            updatedEpic1.setId(1);
            // Попробуем вручную сменить статус эпика, в котором есть незавершённые подзадачи и обновить его:
            updatedEpic1.setStatus(TaskStatus.DONE);
            taskManager.updateEpic(updatedEpic1);
        // printAllTasks(taskManager);
        // Поля меняются, а статус не меняется. Супер!

        // Обновим подзадачи эпика и посмотрим на его статус:
        SubTask updatedSubTask3 = new SubTask(taskManager.getSubTaskByIdentifier(3).name,
        taskManager.getSubTaskByIdentifier(3).description);
            updatedSubTask3.setId(3);
            updatedSubTask3.setStatus(TaskStatus.IN_PROGRESS);
            taskManager.updateSubTask(updatedSubTask3);
            //printAllTasks(taskManager);
            // Статус эпика меняется вместе со статусом подзадачи!

        // Установим у всех подзадач одного эпика статус DONE:
        SubTask updatedSubTask4 = new SubTask(taskManager.getSubTaskByIdentifier(4).name,
        taskManager.getSubTaskByIdentifier(4).description);
            updatedSubTask4.setId(4);
            updatedSubTask4.setStatus(TaskStatus.DONE);
            taskManager.updateSubTask(updatedSubTask4);
        updatedSubTask3.setStatus(TaskStatus.DONE);
        taskManager.updateSubTask(updatedSubTask3);
        //printAllTasks(taskManager);
        // Статус эпика меняется на DONE!

    // 3. Удаление задач, эпиков и подзадач:
        taskManager.removeTaskByIdentifier(1);
        taskManager.removeTaskByIdentifier(2);
        taskManager.removeTaskByIdentifier(3);
        taskManager.removeEpicByIdentifier(1);
        taskManager.removeSubTaskByIdentifier(3);
        //printAllTasks(taskManager);
    }

    // Метод создаёт 4 простые задачи, 2 эпика и по 2 подзадачи для каждого из них:
    static void createTasks(TaskManager taskManager) {

        Task task1 = new Task("Сходить на работу.", "Заработать денег на оплату Яндекс Практикума.");
        Task task2 = new Task("Приготовить ужин.", "Сегодня картошечка.");
        Task task3 = new Task("Прибраться в квартире.", "Тщательно и основательно!");
        Task task4 = new Task("Отдохнуть вечерком.", "Вышел сериал по \"Fallout\"!");
        taskManager.addSimpleTask(task1);
        taskManager.addSimpleTask(task2);
        taskManager.addSimpleTask(task3);
        taskManager.addSimpleTask(task4);

        Epic epic1 = new Epic("Войти в айти.", "И получать 300к/наносек!");
        SubTask subTask1e1 = new SubTask("Учить джаву.", "И не сдаваться!");
        SubTask subTask2e1 = new SubTask("Найти работу мечты.", "Удалёночка, все дела.");
        taskManager.addEpic(epic1);
        taskManager.addSubTaskToEpic(subTask1e1, epic1.getId());
        taskManager.addSubTaskToEpic(subTask2e1, epic1.getId());

        Epic epic2 = new Epic("Закончить 4 спринт", "Он был странным и разочаровывающим =(");
        SubTask subTask1e2 = new SubTask("Пройти теорию, тренажёры и сдать ТЗ",
                "Обалдеть от кучи ошибок, явных недоработок и непонятного и косноязычного описания ТЗ");
        SubTask subTask2e2 = new SubTask("Разбираться в кривых формулировках неделю и написать код за день =)",
                "Не стать, при этом, токсичным =)");
        taskManager.addEpic(epic2);
        taskManager.addSubTaskToEpic(subTask1e2, epic2.getId());
        taskManager.addSubTaskToEpic(subTask2e2, epic2.getId());
    }
    // Метод выводит в консоль все созданные задачи:
    static void printAllTasks(TaskManager taskManager) {

        System.out.println(taskManager.returnSimpleTasks());
        System.out.println(taskManager.returnEpicTasks());
    }
}