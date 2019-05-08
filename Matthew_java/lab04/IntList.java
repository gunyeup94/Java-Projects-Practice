/**
 * A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 *
 * This is a dummy implementation to allow IntListTest to compile. Replace this
 * file with your own IntList class.
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
        for (int i = 1; i < values.length; i += 1) {
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
        if (position == 0) {
            return first;
        } else {
            return rest.get(position - 1);
        }
    }

    public boolean equals(Object o) {
        IntList other = (IntList) o;
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


    public void add(int value) {
        IntList p = this;
        if (p.rest != null) {
            p.rest.add(value);
        } else {
            p.rest = new IntList(value, null);
        }
        return;
    }

    public int smallest() {
        IntList p = this;
        int size = p.size();
        if (size == 1) {
            return p.first;
        }
        int smallest = p.first;
        IntList next = p.rest;
        for (int i = 0; i < size; i += 1) {
            if (smallest < next.first) {
                if (next.rest != null) {
                    next = next.rest;
                } else {
                    break;
                }
            } else {
                smallest = next.first;
                if (next.rest != null) {
                    next = next.rest;
                } else {
                    break;
                }
            }
        }
        return smallest;
    }

    public int squaredSum() {
        IntList p = this;
        int sum = 0;
        while (p != null) {
            p.first = p.first * p.first;
            sum = sum + p.first;
            p = p.rest;
        }
        return sum;
    }

    public static void dSquareList(IntList L) {
        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    public static IntList catenate(IntList A, IntList B) {
        if (A == null && B != null) {
            return B;
        } else if (A != null && B == null) {
            return A;
        } else if (A == null && B == null) {
            return null;
        }
        IntList temp = new IntList(A.first, null);
        while (B != null) {
            while (A.rest != null) {
                temp.add(A.rest.first);
                A = A.rest;
            }
            temp.add(B.first);
            B = B.rest;
        }
        return temp;
    }

    public static IntList dcatenate(IntList A, IntList B) {
        if (A == null && B != null) {
            A = B;
            return A;
        } else if (A != null && B == null) {
            return A;
        } else if (A == null && B == null) {
            return null;
        }
        while (B != null) {
            A.add(B.first);
            B = B.rest;
        }
        return A;
    }
}
