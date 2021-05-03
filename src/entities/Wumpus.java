package entities;

public class Wumpus extends GenericEntity {
    public Wumpus(String name) {
        this.name = name;
        this.description = "";
    }

    // If adjacent to player move away, else move randomly
    public void move() {
        String[] rooms = currentRoom.getNeighborNames().split(" ");
        for (String room : rooms) {
            if ( currentRoom.getNeighbor( room ).hasPlayer() ) {
                currentRoom.removeCreature( this );
                currentRoom = randomRoomOtherThan( room );
                currentRoom.addCreature( this );
                return;
            }
        }
        currentRoom = randomRoom();
    }
}
