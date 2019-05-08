import java.util.Iterator;
import java.util.LinkedList;

public class HashMap<K, V> implements Map61BL<K, V>  {

    /* Instance variables here? */
    private LinkedList<Entry<K, V>>[] arrayIndex;
    private double loadFactor;
    private int entrySize = 0;

    public HashMap() {
        this.arrayIndex = new LinkedList[16];
        for (int i = 0; i < arrayIndex.length; i++) {
            arrayIndex[i] = new LinkedList<>();
        }
        this.loadFactor = 0.75;
    }

    public HashMap(int initialCapacity) {
        this.arrayIndex = new LinkedList[initialCapacity];
        for (int i = 0; i < arrayIndex.length; i++) {
            arrayIndex[i] = new LinkedList<>();
        }
        this.loadFactor = 0.75;
    }

    public HashMap(int initialCapacity, float loadFactor) {
        this.arrayIndex = new LinkedList[initialCapacity];
        for (int i = 0; i < arrayIndex.length; i++) {
            arrayIndex[i] = new LinkedList<>();
        }
        this.loadFactor = loadFactor;
    }

    public int capacity() {
        return this.arrayIndex.length;
    }

    /* Returns true if the given KEY is a valid name that starts with A - Z. */
    private static boolean isValidName(String key) {
        return 'A' <= key.charAt(0) && key.charAt(0) <= 'Z';
    }

    /* Returns true if the map contains the KEY. */
    public boolean containsKey(K key) {
        int index = Math.floorMod(key.hashCode(), arrayIndex.length);
        for (int i = 0; i < arrayIndex[index].size(); i++) {
            if (arrayIndex[index].get(i).key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the value for the specified KEY. If KEY is not found, return
       null. */
    public V get(K key) {
        int index = Math.floorMod(key.hashCode(), arrayIndex.length);
        if (!containsKey(key)) {
            return null;
        }
        for (int i = 0; i < arrayIndex[index].size(); i++) {
            if (arrayIndex[index].get(i).key.equals(key)) {
                return arrayIndex[index].get(i).value;
            }
        }
        return null;
    }

    /* Puts a (KEY, VALUE) pair into this map. If the KEY already exists in the
       SimpleNameMap, replace the current corresponding value with VALUE. */
    public void put(K key, V value) {
        int index = Math.floorMod(key.hashCode(), arrayIndex.length);
        if (containsKey(key)) {
            for (int i = 0; i < arrayIndex[index].size(); i++) {
                if (arrayIndex[index].get(i).key.equals(key)) {
                    arrayIndex[index].get(i).value = value;
                }
            }
        } else {
            Entry addingNew = new Entry(key, value);
            arrayIndex[index].add(addingNew);
            this.entrySize++;
        }
        double resizeSize = size();
        double arrayLength = arrayIndex.length;
        double lf = resizeSize / arrayLength;
        if (lf > this.loadFactor) {
            resize();
        }
    }

    public int size() {
        return entrySize;
    }

    void resize() {
        LinkedList<Entry<K, V>>[] temp = new LinkedList[arrayIndex.length * 2];
        System.arraycopy(arrayIndex, 0, temp, 0, arrayIndex.length);
        arrayIndex = temp;
    }

    /* Removes a single entry, KEY, from this table and return the VALUE if
       successful or NULL otherwise. */
    public V remove(K key) {
        int index = Math.floorMod(key.hashCode(), arrayIndex.length);
        if (containsKey(key)) {
            for (int i = 0; i < arrayIndex[index].size(); i++) {
                if (arrayIndex[index].get(i).key.equals(key)) {
                    V removeValue = arrayIndex[index].get(i).value;
                    arrayIndex[index].remove(i);
                    entrySize--;
                    return removeValue;
                }
            }
        }
        return null;
    }

    public boolean remove(K key, V value) {
        int index = Math.floorMod(key.hashCode(), arrayIndex.length);
        if (containsKey(key)) {
            for (int i = 0; i < arrayIndex[index].size(); i++) {
                if (arrayIndex[index].get(i).key.equals(key)) {
                    arrayIndex[index].get(i).key = null;
                    arrayIndex[index].get(i).value = null;
                    entrySize--;
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < arrayIndex.length; i++) {
            arrayIndex[i].clear();
        }
        entrySize = 0;
    }

    @Override
    public Iterator<K> iterator() {
        return new EntryIterator();
    }

    private class EntryIterator implements Iterator<K> {
        private int entryIndex = 0;
        private int outsideIndex = 0;
        private int sizeLimit = 0;
        public K next() {
            if (arrayIndex[outsideIndex].size() > entryIndex) {
                K toReturn = arrayIndex[outsideIndex].get(entryIndex).key;
                entryIndex++;
                sizeLimit++;
                return toReturn;
            } else {
                outsideIndex++;
                entryIndex = 0;
                return next();
            }
        }

        public boolean hasNext() {
            return sizeLimit < size();
        }
    }

    private static class Entry<K, V> {

        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /* Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return key.equals(other.key);
        }

        /* Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry
                    && key.equals(((Entry) other).key)
                    && value.equals(((Entry) other).value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}

