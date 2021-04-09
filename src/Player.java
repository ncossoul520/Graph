import java.util.ArrayList;

public class Player {
    private String name, description;
    private Graph.Node room;
    public ItemContainer items;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ItemContainer();
    }

    public Graph.Node getCurrentRoom() {
        return this.room;
    }

    public void setCurrentRoom(Graph.Node new_room) {
        this.room = new_room;
    }

    public boolean moveToRoom(Graph.Node new_room) {
        if ( this.room.getNeighbor(new_room.getName() ) != null ) {
            setCurrentRoom( new_room );
            return true;
        } else {
            System.out.println("[Warning] Cannot move to room " + new_room + " (not a neighbor)");
        }
        return false;
    }

}
