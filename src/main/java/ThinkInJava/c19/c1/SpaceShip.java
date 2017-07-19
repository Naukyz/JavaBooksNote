package ThinkInJava.c19.c1;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/19
 */
public enum SpaceShip {
    SCOUT, CARGO, TRANSPORT, CRUISER, BATTLESHIP, MOTHERSHIP;

    public String toString() {
        String id = name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower;
    }

    public static void main(String[] args) {
        for (SpaceShip s : values()) {
            System.out.println(s + " " + s.name());
        }
    }
}
