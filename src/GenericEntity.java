public abstract class GenericEntity implements Entity {
    protected String name, description;
    protected Graph.Node currentRoom;

    public Graph.Node getRoom() { return currentRoom; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public abstract void move();
}
