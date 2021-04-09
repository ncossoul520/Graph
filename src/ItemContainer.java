import java.util.ArrayList;

public class ItemContainer {
    private ArrayList<Item> items = new ArrayList<>();


    public void addItem(String name) {
        addItem( new Item( name ) );
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

    public String displayItems() {
        String out = "";
        for (Item item : items) {
            out += item.getName() + " ";
        }
        return out;
    }

    //-------------------------------------------------------------------
    public class Item {
        private String name, description;

        public Item(String name) {
            this.name = name;
            this.description = "";
        }

        public Item(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
