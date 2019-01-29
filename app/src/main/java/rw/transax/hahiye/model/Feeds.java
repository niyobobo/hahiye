package rw.transax.hahiye.model;

public class Feeds {

    private int type;
    private String description;
    private String date;

    public Feeds() {

    }

    public Feeds(int type, String description, String date) {
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
