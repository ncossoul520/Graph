package entities;
import main.*;

public class Popstar extends GenericEntity {
    public Popstar(String name) {
        this.name = name;
        this.description = "";
    }

    // Move to player room if adjacent, else doesn't move
    public void move() {
        String[] rooms = currentRoom.getNeighborNames().split(" ");
        for (String room : rooms) {
            if ( currentRoom.getNeighbor( room ).hasPlayer() ) {
                currentRoom.removeCreature( this );
                currentRoom = currentRoom.getNeighbor( room );
                currentRoom.addCreature( this );
                return;
            }
        }
    }
}
