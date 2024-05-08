package Tasks;

public class SubTask extends Task {
    private int idOfEpic;

    public SubTask(String name, String description, int idOfEpic) {
        super(name, description);
        this.setIdOfEpic(idOfEpic);
        this.setStatus(TaskStatus.NEW);
    }

    public int getIdOfEpic() {
        return idOfEpic;
    }

    public void setIdOfEpic(int idOfEpic) {
        this.idOfEpic = idOfEpic;
    }
}
