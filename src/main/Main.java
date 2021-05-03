package main;
import entities.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<GenericEntity> creatures;

    public static void main(String[] args) {
        Graph level = Graph.getInstance();

        // Create rooms
        level.addNode("hall");
        level.addNode("closet");
        level.addDirectedEdge("hall", "closet");
        level.addNode("dungeon");
        level.addDirectedEdge("hall", "dungeon");
        level.addNode("gym");
        level.addDirectedEdge("dungeon", "gym");

        // Create items
        level.getNode("hall").items.addItem("axe");
        level.getNode("closet").items.addItem("shirt");
        level.getNode("dungeon").items.addItem("diamond");

        // Create creatures
        level.addCreature( new Chicken("chicken1"), "hall" );
        level.addCreature( new Chicken("chicken2"), "hall" );
        level.addCreature( new Wumpus("wumpus1"), "hall" );
        level.addCreature( new Popstar("popstar1"), "hall" );

        // Create player
        Player p1 = new Player("Chad", "the Dude");
        p1.setCurrentRoom(level.getNode("hall"));

        // Start the thread
        (new Thread(level)).start();

        String response = "";
        Scanner s = new Scanner(System.in);
        displayHelp();

        do {
            System.out.println("\nYou are in the " + p1.getCurrentRoom().getName());
            System.out.println("What do you want to do? >");
            response = s.nextLine();

            // Help
            if (response.equals("help")) {
                displayHelp();
            }

            // Go
            else if (response.length() >= 4 && response.substring(0, 3).equals("go ")) {
                String room_name = response.substring(3);
                if (level.getNode(room_name) != null) {
                    p1.moveToRoom(level.getNode(room_name));
                }
            }

            // Look
            else if (response.equals("look")) {
                System.out.println("Creatures: " + p1.getCurrentRoom().displayCreatures());
                System.out.println("Your items: " + p1.items.displayItems());
                System.out.println("Items in the room: " + p1.getCurrentRoom().items.displayItems());
                System.out.println("Next rooms: " + p1.getCurrentRoom().getNeighborNames());

            }

            // Add room
            else if (response.length() >= 9 && response.substring(0, 9).equals("add room ")) {
                String room_name = response.substring(9);
                if (p1.getCurrentRoom().getNeighbor(room_name) == null) {
                    level.addNode(room_name);
                    level.addDirectedEdge(p1.getCurrentRoom().getName(), room_name);
                    System.out.println("New room created: " + room_name);
                }
            }

            // Take
            else if (response.length() >= 5 && response.substring(0, 5).equals("take ")) {
                String item_name = response.substring(5);
                p1.items.addItem(p1.getCurrentRoom().items.removeItem(item_name));
                System.out.println("Your items: " + p1.items.displayItems());
            }

            // Drop
            else if (response.length() >= 5 && response.substring(0, 5).equals("drop ")) {
                String item_name = response.substring(5);
                p1.getCurrentRoom().items.addItem(p1.items.removeItem(item_name));
                System.out.println("Your items: " + p1.items.displayItems());
            }
       } while(!response.equals("quit"));
    }

    private static void displayHelp() {
        System.out.println("List of commands available:");
        System.out.println("   go <name>       : goes into that room if it exists");
        System.out.println("   look            : display the list of neighbors");
        System.out.println("   add room <name> : create a new room");
        System.out.println("   take <item>     : take an item in the room");
        System.out.println("   drop <item>     : drop an item in the room");
        System.out.println("   help            : displays this message");
        System.out.println("   quit            : quit the game");
    }
}
