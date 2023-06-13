package cz.spse.check_list.model;

public class Note {
    private int id;
    private String name;
    private String description;
    private int finished;

    public Note(int id, String name, String description, int finished) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.finished = finished;
    }
    public Note(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        if (finished == 1){
            return true;
        } else {
            return false;
        }
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }
}
