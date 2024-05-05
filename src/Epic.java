import java.util.HashMap;

public class Epic extends Task {

   HashMap<Integer, SubTask> subTasksInThisEpic = new HashMap<>();

   public Epic(String name, String description) {
      this.name = name;
      this.description = description;
   }
}
