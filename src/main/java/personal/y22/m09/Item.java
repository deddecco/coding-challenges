package personal.y22.m09;

class Item {

    public static int id;
    public static String name;
    public static int quantity;
    public static double price;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Item.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Item.name = name;
    }

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        Item.quantity = quantity;
    }

    public static double getPrice() {
        return price;
    }

    public static void setPrice(double price) {
        Item.price = price;
    }

    public String toString() {
        String description = "";
        description += getId() + "\t" + getName() + "\t" + getQuantity() + "\t" + getPrice();
        return description;
    }

    Item(int id, String name, int quantity, double price) {
        Item.id = id;
        Item.name = name;
        Item.quantity = quantity;
        Item.price = price;
    }
}