import java.util.ArrayList;

public class Chicken extends GenericEntity {
    public Chicken(String name) {
        this.name = name;
        this.description = "";
    }

    // Move randomly
    public void move() {
        this.currentRoom = randomRoom();
    }
}
