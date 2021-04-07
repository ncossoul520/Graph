import java.util.ArrayList;

public class Player {
    private String name, description;
    private ArrayList<Item> items;
    private Graph.Node room;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if ( item != null && !items.contains( item ) ) {
            items.add( item );
        }
    }

    public Item removeItem(String name) {
        for (Item item : items) {
            if ( item.getName().equals( name ) ) {
                return removeItem( item );
            }
        }
        return null;
    }


    public Item removeItem(Item item) {
        if ( items.contains( item ) ) {
            items.remove( item );
            return item;
        }
        return null;
    }

    public void displayInventory() {
        System.out.print("Your items:");
        for (Item item : items) {
            System.out.print(" " + item.getName());
        }
        System.out.println();
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
