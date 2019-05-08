import java.util.LinkedList;

public class SimpleNameMap {

    /* Instance variables here? */
    private LinkedList<Entry>[] arrayIndex;


    public SimpleNameMap() {
        arrayIndex = new LinkedList[26];
    }

    /* Returns true if the given KEY is a valid name that starts with A - Z. */
    private static boolean isValidName(String key) {
        return 'A' <= key.charAt(0) && key.charAt(0) <= 'Z';
    }

    /* Returns true if the map contains the KEY. */
    boolean containsKey(String key) {
        if (!isValidName(key)) {
            return false;
        }
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
    String get(String key) {
        if (!isValidName(key)) {
            return null;
        }
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
    void put(String key, String value) {
        if (!isValidName(key)) {
            return;
        }
        int index = Math.floorMod(key.hashCode(), arrayIndex.length);
        double lf = size() / arrayIndex.length;
        if (lf > 0.75) {
            resize();
        }
        if (containsKey(key)) {
            for (int i = 0; i < arrayIndex[index].size(); i++) {
                if (arrayIndex[index].get(i).key.equals(key)) {
                    arrayIndex[index].get(i).value = value;
                }
            }
        } else {
            Entry addingNew = new Entry(key, value);
            arrayIndex[index].add(addingNew);
        }
    }

    int size() {
        int total = 0;
        for (int i = 0; i < arrayIndex.length; i++) {
            total = total + arrayIndex[i].size();
        }
        return total;
    }

    void resize() {
        LinkedList<Entry>[] temp = new LinkedList[arrayIndex.length * 2];
        System.arraycopy(arrayIndex, 0, temp, 0, arrayIndex.length);
        arrayIndex = temp;
    }

    /* Removes a single entry, KEY, from this table and return the VALUE if
       successful or NULL otherwise. */
    String remove(String key) {
        if (!isValidName(key)) {
            return null;
        }
        int index = Math.floorMod(key.hashCode(), arrayIndex.length);
        if (containsKey(key)) {
            for (int i = 0; i < arrayIndex[index].size(); i++) {
                if (arrayIndex[index].get(i).key.equals(key)) {
                    String removeValue = arrayIndex[index].get(i).value;
                    arrayIndex[index].remove(i);
                    return removeValue;
                }
            }
        }
        return null;
    }

    private static class Entry {

        private String key;
        private String value;

        Entry(String key, String value) {
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
