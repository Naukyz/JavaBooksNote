package ThinkInJava.c14.c8.NullPosition;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/29
 */
public class Position {
    private String title;
    private Person person;

    public Position(String title) {
        this.title = title;
        person = Person.NULL;
    }

    public Position(String title, Person person) {
        this.title = title;
        this.person = person;
        if (person == null) {
            person = Person.NULL;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        if (person == null) {
            this.person = Person.NULL;
        }
        this.person = person;

    }

    @Override
    public String toString() {
        return "Position : " + title + "\n" + person;
    }
}
