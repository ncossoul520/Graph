public class Popstar extends GenericEntity {
    public Popstar(String name) {
        this.name = name;
        this.description = "";
    }

    // Move to player room if adjacent, else doesn't move
    public void move() {
        if ( currentRoom.getNeighbor( playerRoom.getName() ) != null ) {
            this.currentRoom = playerRoom;
        }
    }
}
