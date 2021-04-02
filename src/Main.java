import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNode("hall");
        g.addNode("closet");
        g.addNode("dungeon");

        g.addDirectedEdge("hall", "closet");
        g.addUnidirectedEdge("hall", "dungeon");

        Graph.Node current = g.getNode("hall");

        String response = "";
        Scanner s = new Scanner(System.in);

        displayHelp();

        do {
            System.out.println("You are in the " + current.getName() );
            System.out.println("What do you want to do? >");
            response = s.nextLine();

            if ( response.length() >= 4 && response.substring(0,3).equals("go ") ) {
                String room_name = response.substring(3);
                if ( current.getNeighbor( room_name ) != null ) {
                    current = current.getNeighbor( room_name );
                } else {
                    System.out.println("Can't go to the " + room_name);
                }

            }
            else if ( response.equals("look") ) {
                System.out.println("Next rooms: " + current.getNeighborNames() );
            }
            else if ( response.length() >= 9 && response.substring(0,9).equals("add room ") ) {
                String room_name = response.substring(9);
                if ( current.getNeighbor( room_name ) == null ) {
                    g.addNode( room_name );
                    g.addDirectedEdge(current.getName(), room_name);
                    System.out.println("New room created: " + room_name);
                }
            }
            else if ( response.equals("help") ) {
                displayHelp();
            }
        } while (!response.equals("quit"));

    }

    private static void displayHelp() {
        System.out.println("List of commands available:");
        System.out.println("   go <name>       : goes into that room if it exists");
        System.out.println("   look            : display the list of neighbors");
        System.out.println("   add room <name> : create a new room");
        System.out.println("   help            : displays this message");
        System.out.println("   quit            : quit the game");
    }
}
