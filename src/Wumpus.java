public class Wumpus extends GenericEntity {
    public Wumpus(String name) {
        this.name = name;
        this.description = "";
    }

    public void move() { // TODO
        Graph.Node nextRoom = null;/* get next room */
        this.currentRoom = nextRoom;
    }
}
