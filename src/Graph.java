import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public List<Node> getNodes() {
        return nodes;
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

    //-------------------------------------------------------------------------------
    public class Node {
        private String name;
        private List<Node> neighbors;
        private List<GenericEntity> creatures;
        public ItemContainer items;

        private Node(String name) {
            this.name = name;
            neighbors = new ArrayList<>();
            creatures = new ArrayList<>();
            items = new ItemContainer();
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

        public void addCreature(GenericEntity creature) {
            creatures.add( creature );
            creature.setCurrentRoom( this );
        }

        public String displayCreatures() {
            String out = "";
            for (GenericEntity creature : creatures) {
                out += creature.getName() + " ";
            }
            return out;
        }

        public List<GenericEntity> getCreatures() {
            return creatures;
        }
    }
}
