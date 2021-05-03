package entities;
import main.*;

public abstract class GenericEntity implements Entity {
    protected String name, description;
    protected Graph.Node currentRoom;

    public Graph.Node getRoom() { return currentRoom; }
    public void setCurrentRoom(Graph.Node currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public abstract void move();

    public Graph.Node randomRoom() {
        String[] rooms = currentRoom.getNeighborNames().split(" ");
        if ( rooms.length > 0 ) {
            int room_num = (int) (Math.random() * rooms.length);
            return currentRoom.getNeighbor( rooms[room_num] );
        }
        return currentRoom;
    }

    public Graph.Node randomRoomOtherThan(String name) {
        String[] rooms = currentRoom.getNeighborNames().split(" ");
        int room_num;
        if ( rooms.length > 0 ) {
            do {
                room_num = (int) (Math.random() * rooms.length);
            } while ( !rooms[room_num].equals( name ) );
            return currentRoom.getNeighbor( rooms[room_num] );
        }
        return currentRoom;
    }

}
