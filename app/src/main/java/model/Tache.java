package model;

public class Tache {
    int id;
    String title;
    int cat;

    public Tache( String title, int cat) {
        this.title = title;
        this.cat = cat;
    }

    public Tache() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }
}
