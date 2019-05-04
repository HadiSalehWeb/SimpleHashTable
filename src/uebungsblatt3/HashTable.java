package uebungsblatt3;

class HashTable {
    private final int startingCapacity;
    private KeyValuePair[] keyValuePairs;
    private int capacity;
    private int allocatedSpots;
    private Probing probing;

    public HashTable(int startingCapacity, Probing probing) {
        this.startingCapacity = startingCapacity;
        this.probing = probing;
        capacity = startingCapacity;
        keyValuePairs = new KeyValuePair[capacity];
        allocatedSpots = 0;
    }

    public HashTable(int startingCapacity) {
        this(startingCapacity, new LinearProbing());
    }

    public HashTable() {
        this(100);
    }

    private void rehash() {
        HashTable newTable = new HashTable(capacity * 2, probing);

        for (int i = 0; i < capacity; i++)
            if (keyValuePairs[i] != null && !keyValuePairs[i].markedForRemoval)
                newTable.put(keyValuePairs[i].key, keyValuePairs[i].value);

        capacity = newTable.capacity;
        keyValuePairs = newTable.keyValuePairs;
        allocatedSpots = newTable.allocatedSpots;
    }

    private boolean eligibleSpot(Element key, int index) {
        return keyValuePairs[index] == null || keyValuePairs[index].key.compareTo(key) == 0 || keyValuePairs[index].markedForRemoval;
    }

    public Object put(Element key, Object value) {
        ProbingCounter counter = new ProbingCounter(probing, capacity, key.hashCode());

        while (!eligibleSpot(key, counter.index)) counter.increment();

        KeyValuePair oldPair = keyValuePairs[counter.index];

        if (oldPair == null || oldPair.markedForRemoval) allocatedSpots++;

        keyValuePairs[counter.index] = new KeyValuePair(key, value);

        if (allocatedSpots >= capacity * .75) rehash();

        if (oldPair != null && oldPair.key.compareTo(key) == 0 && !oldPair.markedForRemoval)
            return oldPair.value;

        return null;
    }

    public Object get(Element key) {
        ProbingCounter counter = new ProbingCounter(probing, capacity, key.hashCode());

        while (keyValuePairs[counter.index] != null) {
            if (keyValuePairs[counter.index].key.compareTo(key) == 0)
                return keyValuePairs[counter.index].value;

            counter.increment();
        }

        return null;
    }

    public Object get(String key) {
        return get(new StringElement(key));
    }

    public boolean contains(Object value) {
        for (int i = 0; i < capacity; i++)
            if (keyValuePairs[i] != null && !keyValuePairs[i].markedForRemoval && keyValuePairs[i].value.equals(value))
                return true;

        return false;
    }

    public boolean containsKey(String key) {
        return containsKey(new StringElement(key));
    }

    public boolean containsKey(Element key) {
        ProbingCounter counter = new ProbingCounter(probing, capacity, key.hashCode());

        while (keyValuePairs[counter.index] != null) {
            if (keyValuePairs[counter.index].key.compareTo(key) == 0)
                return true;

            counter.increment();
        }

        return false;
    }

    public boolean isEmpty() {
        return allocatedSpots == 0;
    }

    public int size() {
        return allocatedSpots;
    }

    public HashTable clear() {
        capacity = startingCapacity;
        keyValuePairs = new KeyValuePair[capacity];
        allocatedSpots = 0;
        return this;
    }

    public boolean remove(Element key) {
        ProbingCounter counter = new ProbingCounter(probing, capacity, key.hashCode());

        while (keyValuePairs[counter.index] != null) {
            if (keyValuePairs[counter.index].key.compareTo(key) == 0 && !keyValuePairs[counter.index].markedForRemoval) {
                keyValuePairs[counter.index].markedForRemoval = true;
                allocatedSpots--;
                return true;
            }

            counter.increment();
        }

        return false;
    }
}
