package repository;

public class Answer {
    private final int id;
    private final String name;

    public Answer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
