package uebungsblatt3;


public interface Element {
    int compareTo(Element e);

    Element clone();

    void print();
}