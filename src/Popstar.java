public class Popstar extends GenericEntity {
    public Popstar(String name) {
        this.name = name;
        this.description = "";
    }

    public void move() { // TODO
        Graph.Node nextRoom = null; /* get next room */
        this.currentRoom = nextRoom;
    }
}
