/**
 * A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 */
public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Returns an IntList consisting of the given values. */
    public static IntList of(int... values) {
        if (values.length == 0) {
            return null;
        }
        IntList p = new IntList(values[0], null);
        IntList front = p;
        for (int i = 1; i < values.length; i++) {
            p.rest = new IntList(values[i], null);
            p = p.rest;
        }
        return front;
    }

    /** Returns the size of the list. */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + rest.size();
    }

    /** Returns [position]th value in this list. */
    public int get(int position) {
        // TODO: YOUR CODE HERE
        IntList p = this;
        if (position > p.size() - 1) {
            return 0;
        } else {
            int count = 0;
            while (p != null) {
                count += 1;
                if (count - 1 == position) {
                    return p.first;
                } else {    
                    p = p.rest;
                }
            }
        }
        return 0;
    }

    /** Returns the string representation of the list. */
    public String toString() {
        IntList p = this;
        String s = new String("");
        int listsize = p.size();
        for (int i = 0; i < listsize - 1; i += 1) {
            String tostring = String.valueOf(p.first);
            s = s + tostring + " ";
            p = p.rest;
        }
        String laststring = String.valueOf(p.first);
        s = s + laststring;
        return s;
    }

    /** Returns whether this and the given list or object are equal. */
    public boolean equals(Object o) {
        IntList other = (IntList) o;
        // TODO: YOUR CODE HERE
        IntList p = this;
        if (p.size() != other.size()) {
            return false;
        } else {
            while (p != null) {
                if (p.first != other.first) {
                    return false;
                } else {
                    p = p.rest;
                    other = other.rest;
                }
            }
        }
        return true;
    }
}
