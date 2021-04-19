public class Wumpus extends GenericEntity {
    public Wumpus(String name) {
        this.name = name;
        this.description = "";
    }

    // If adjacent to player move away, else move randomly
    public void move() {
        if ( currentRoom.getNeighbor( playerRoom.getName() ) != null ) {
            this.currentRoom = randomRoomOtherThan( playerRoom.getName() );
        } else {
            this.currentRoom = randomRoom();
        }
    }
}
