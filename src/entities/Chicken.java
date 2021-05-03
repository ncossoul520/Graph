package entities;

public class Chicken extends GenericEntity {
    public Chicken(String name) {
        this.name = name;
        this.description = "";
    }

    // Move randomly
    public void move() {
        currentRoom.removeCreature( this );
        currentRoom = randomRoom();
        currentRoom.addCreature( this );
    }
}
