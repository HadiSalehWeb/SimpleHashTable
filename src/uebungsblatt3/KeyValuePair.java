package uebungsblatt3;

public class KeyValuePair {
    public Element key;
    public Object value;
    public boolean markedForRemoval;

    public KeyValuePair(Element key, Object value) {
        this.key = key;
        this.value = value;
        markedForRemoval = false;
    }
}
