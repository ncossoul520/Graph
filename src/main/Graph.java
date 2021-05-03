package main;
import entities.*;
import items.*;
import java.util.ArrayList;
import java.util.List;

public class Graph implements Runnable {
    private static Graph instance; // Singleton

    private static final long TIME_STEP_IN_MS = 5000;
    private List<Node> nodes;
    private ArrayList<GenericEntity> creatures;

    private Graph() {
        nodes = new ArrayList<>();
        creatures = new ArrayList<>();
    }

    public static Graph getInstance() {
        if (instance == null) {
            instance = new Graph();
        }
        return instance;
    }

    public void run() {
        while( true ) {
            updateAllCreatures();
            try {
                Thread.sleep( TIME_STEP_IN_MS );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateAllCreatures() {
        for (GenericEntity c : creatures) {
            c.move();
        }
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

    public void addCreature(GenericEntity entity, String room) {
        creatures.add( entity );
        this.getNode( room ).addCreature( entity );
    }

    //-------------------------------------------------------------------------------
    public class Node {
        private String name;
        private List<Node> neighbors;
        private List<GenericEntity> creatures;
        private boolean player;
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

        public void removeCreature(GenericEntity creature) {
            creatures.remove( creature );
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

        public boolean hasPlayer() {
            return player;
        }

        public void addPlayer() {
            this.player = true;
        }

        public void removePlayer() {
            this.player = false;
        }
    }
}
