public class Chicken extends GenericEntity {
    public Chicken(String name) {
        this.name = name;
        this.description = "";
    }

    public void move() { // TODO
        Graph.Node nextRoom = null;
        this.currentRoom = nextRoom;
    }
}
