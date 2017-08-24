package ThinkInJava.c14.c8.NullPosition;

import java.util.ArrayList;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/29
 */
public class Staff extends ArrayList<Position> {
    public void add(String title, Person person) {
        add(new Position(title, person));
    }

    public void add(String... titles) {
        for (String title : titles) {
            add(new Position(title));
        }
    }

    public Staff(String... titles) {
        add(titles);
    }

    public boolean positionAvailable(String title) {
        for (Position position : this) {
            if (position.getTitle().equals(title) && position.getPerson() == Person.NULL) {
                return true;
            }
        }
        return false;
    }

    public void fillPosition(String title, Person person) {
        for (Position position : this) {
            if (position.getTitle().equals(title) && position.getPerson() == Person.NULL) {
                position.setPerson(person);
                return;
            }
        }
        throw new RuntimeException("title not available");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Position position : this) {
            stringBuilder.append(position).append("\n");
            stringBuilder.append("---\n");
        }
        return stringBuilder.toString();
    }
}
