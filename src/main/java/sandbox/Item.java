package sandbox;

public class Item {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return String.format("Item[value=%s]", value);
    }
}