import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> nodes;

    public Graph() {
        nodes = new ArrayList<Node>();
    }

    public void addNode(String name) {
        if ( getNode(name) == null ) {
            nodes.add(new Node(name));
        }
    }

    public void addDirectedEdge(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    public void addUnidirectedEdge(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        n1.addNeighbor(n2);
    }

    public Node getNode(String name) {
        for (Node n : nodes) {
            if ( n.getName().equals(name) ) {
                return n;
            }
        }
        return null;
    }


    public class Node {
        private String name;
        private List<Node> neighbors;
        private List<Item> items;


        private Node(String name) {
            this.name = name;
            neighbors = new ArrayList<Node>();
            items = new ArrayList<>();
        }

        private void addNeighbor(Node n) {
            neighbors.add(n);
        }

        public Node getNeighbor(String name) {
            for (Node n : neighbors) {
                if ( n.getName().equals( name ) ) {
                    return n;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public String getNeighborNames() {
            String names = "";
            for (Node n : neighbors) {
                names += n.getName() + " ";
            }
            return names;
        }

        public String displayItems() {
            String out = "";
            for (Item item : items) {
                out += " " + item.getName();
            }
            return out;
        }

        public void addItem(String name) {
            addItem(name, "");
        }

        public void addItem(String name, String description) {
            for (Item item : items) {
                if ( item.getName().equals( name ) ) {
                    return;
                }
            }
            addItem( new Item(name, description) );
        }

        public void addItem(Item item) {
            if ( item != null && !items.contains( item ) ) {
                items.add( item );
            }
        }

        public Item removeItem(String name) {
            for (Item item : items) {
                if ( item.getName().equals( name ) ) {
                    items.remove( item );
                    return item;
                }
            }
            return null;
        }


    }

}
