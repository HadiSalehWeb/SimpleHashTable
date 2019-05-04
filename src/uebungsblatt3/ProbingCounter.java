package uebungsblatt3;

public class ProbingCounter {
    Probing probing;
    int capacity;
    int hash;

    public int index;

    public ProbingCounter(Probing probing, int capacity, int hash) {
        this.probing = probing;
        this.capacity = capacity;
        this.hash = hash;
        index = hash % capacity;
        while (index < 0) index += capacity;
        probing.startProbing();
    }

    public void increment() {
        index = (hash + probing.nextNum()) % capacity;

        //Make sure it's not negative
        while (index < 0) index += capacity;
    }
}
