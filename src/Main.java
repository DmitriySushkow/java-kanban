/*
Привет! Внёс правки по новым комментариям =)
A____A
|・ㅅ・| Meow
|っ　ｃ|
|　　　|
|　　　|
|　　　|
|　　　|
|　　　|
|　　　|
|　　　|
|　　　| Хорошей проверки и праздников! :3
U￣ ￣U
 */

import manager.TaskManager;
import tasks.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        /*
        Создайте две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей.
        Распечатайте списки эпиков, задач и подзадач через System.out.println(..).
        Измените статусы созданных объектов, распечатайте их. Проверьте, что статус задачи и подзадачи сохранился,
        а статус эпика рассчитался по статусам подзадач.
        И, наконец, попробуйте удалить одну из задач и один из эпиков.
         */
        Task task1 = new Task("Прочитать комментарии Сергея", "Проанализировать, что сделано не верно");
        Task task2 = new Task("Исправить код", "Обработать его в целом и по конкретеым комментариям");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        //System.out.println(taskManager.returnTasks());
        Epic epic1 = new Epic("Войти в айти", "Получать 300к/наносек.");
        SubTask subTask1 = new SubTask("Учить джаву", "Ещё более усердно",3);
        SubTask subTask2 = new SubTask("Найти работу мечты", "Чтоб бесплатные печеньки и всё такое", 3);
        taskManager.addEpic(epic1);
        taskManager.addSubTask(subTask1);
        taskManager.addSubTask(subTask2);
        Epic epic2 = new Epic("Успеть всё сделать к жёсткому дедлайну", "Который надвигается");
        SubTask subTask3 = new SubTask("Вгрызться в учёбу!", "И сделать всё правильно", 6);
        taskManager.addEpic(epic2);
        taskManager.addSubTask(subTask3);
        //System.out.println(taskManager.returnEpics());
        //System.out.println(taskManager.returnSubTasks());

        task1.setStatus(TaskStatus.DONE);
        task2.setStatus(TaskStatus.IN_PROGRESS);
        //System.out.println(taskManager.returnTasks());
        subTask1.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateSubTask(subTask1);
        //System.out.println(taskManager.returnEpics());
        subTask3.setStatus(TaskStatus.DONE);
        taskManager.updateSubTask(subTask3);
        //System.out.println(taskManager.returnEpics());

        taskManager.removeTaskByIdentifier(1);
        //System.out.println(taskManager.returnTasks());
        taskManager.clearTasks();
        //System.out.println(taskManager.returnTasks());
    }
}
