import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();

        // Create rooms
        g.addNode("hall");
        g.addNode("closet");
        g.addNode("dungeon");
        g.addDirectedEdge("hall", "closet");
        g.addUnidirectedEdge("hall", "dungeon");

        // Create items
        g.getNode("hall").addItem( "axe" );
        g.getNode("closet").addItem( "shirt" );
        g.getNode("dungeon").addItem( "diamond" );

        // Create player
        Player p1 = new Player("Chad", "the Dude");
        p1.setCurrentRoom( g.getNode("hall") );

        String response = "";
        Scanner s = new Scanner(System.in);

        displayHelp();

        do {
            System.out.println("\nYou are in the " + p1.getCurrentRoom().getName() );
            System.out.println("What do you want to do? >");
            response = s.nextLine();

            // Help
            if ( response.equals("help") ) {
                displayHelp();
            }

            // Go
            else if ( response.length() >= 4 && response.substring(0,3).equals("go ") ) {
                String room_name = response.substring(3);
                if ( g.getNode( room_name ) != null ) {
                    p1.moveToRoom(g.getNode(room_name));
                }
            }

            // Look
            else if ( response.equals("look") ) {
                p1.displayInventory();
                System.out.println("Items in the room:" + p1.getCurrentRoom().displayItems() );
                System.out.println("Next rooms: " + p1.getCurrentRoom().getNeighborNames() );

            }

            // Add room
            else if ( response.length() >= 9 && response.substring(0,9).equals("add room ") ) {
                String room_name = response.substring(9);
                if ( p1.getCurrentRoom().getNeighbor( room_name ) == null ) {
                    g.addNode( room_name );
                    g.addDirectedEdge( p1.getCurrentRoom().getName(), room_name);
                    System.out.println("New room created: " + room_name);
                }
            }

            // Take
            else if ( response.length() >= 5 && response.substring(0,5).equals("take ") ) {
                String item_name = response.substring(5);
                p1.addItem( p1.getCurrentRoom().removeItem( item_name ) );
                p1.displayInventory();
            }

            // Drop
            else if ( response.length() >= 5 && response.substring(0,5).equals("drop ") ) {
                String item_name = response.substring(5);
                p1.getCurrentRoom().addItem( p1.removeItem( item_name ) );
                p1.displayInventory();
            }


        } while (!response.equals("quit"));

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
