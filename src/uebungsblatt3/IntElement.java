package uebungsblatt3;

public class IntElement implements Element {
    private int value;

    public IntElement(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Element e) {
        if (e instanceof IntElement)
            return Integer.compare(this.value, ((IntElement) e).value);

        return 0;
    }

    @Override
    public Element clone() {
        return new IntElement(value);
    }

    @Override
    public void print() {
        System.out.println("Int Element with value: " + value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof IntElement))
            return false;

        return ((IntElement) obj).value == value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}

