package entities;
import main.*;

public interface Entity {
    public String getName();
    public String getDescription();
    public void move();
    public Graph.Node getRoom();
}
