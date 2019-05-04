package uebungsblatt3;

public class StringElement implements Element {
    private String value;

    public StringElement(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(Element e) {
        if (e instanceof StringElement)
            return value.compareTo(((StringElement) e).value);

        return 0;
    }

    @Override
    public Element clone() {
        return new StringElement(value);
    }

    @Override
    public void print() {
        System.out.println("String Element with value: " + value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof StringElement))
            return false;

        return ((StringElement) obj).value == value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

