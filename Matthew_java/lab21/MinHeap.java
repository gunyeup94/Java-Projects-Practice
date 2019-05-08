import java.util.ArrayList;

/* A MinHeap class of Comparable elements backed by an ArrayList. */
public class MinHeap<E extends Comparable<E>> {

    /* An ArrayList that stores the elements in this MinHeap. */
    private ArrayList<E> contents;
    private int size;

    /* Initializes an empty MinHeap. */
    public MinHeap() {
        contents = new ArrayList<>();
        contents.add(null);
    }

    /* Returns the element at index INDEX, and null if it is out of bounds. */
    private E getElement(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    /* Sets the element at index INDEX to ELEMENT. If the ArrayList is not big
       enough, add elements until it is the right size. */
    private void setElement(int index, E element) {
        while (index >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, element);
    }

    /* Swaps the elements at the two indices. */
    private void swap(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        setElement(index2, element1);
        setElement(index1, element2);
    }

    /* Prints out the underlying heap sideways. Use for debugging. */
    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    /* Recursive helper method for toString. */
    private String toStringHelper(int index, String soFar) {
        if (getElement(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = getRightOf(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getElement(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getElement(index) + "\n";
            int leftChild = getLeftOf(index);
            if (getElement(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }

    /* Returns the index of the left child of the element at index INDEX. */
    private int getLeftOf(int index) {
        return index * 2;
    }

    /* Returns the index of the right child of the element at index INDEX. */
    private int getRightOf(int index) {
        return (index * 2) + 1;
    }

    /* Returns the index of the parent of the element at index INDEX. */
    private int getParentOf(int index) {
        return (index / 2);
    }

    /* Returns the index of the smaller element. At least one index has a
       non-null element. */
    private int min(int index1, int index2) {
        if (getElement(index1) == null) {
            return index2;
        }
        if (getElement(index2) == null) {
            return index1;
        }
        if (getElement(index1).compareTo(getElement(index2)) > 0) {
            return index2;
        } else {
            return index1;
        }
    }

    /* Returns but does not remove the smallest element in the MinHeap. */
    public E peek() {
        if (contents.size() == 1) {
            return null;
        }
        return contents.get(1);
    }

    /* Bubbles up the element currently at index INDEX. */
    private void bubbleUp(int index) {
        if (getParentOf(index) != 0) {
            if (contents.get(index).compareTo(contents.get(getParentOf(index))) < 0) {
                swap(index, getParentOf(index));
                bubbleUp(getParentOf(index));
            }
        }
    }

    /* Bubbles down the element currently at index INDEX. */
    private void bubbleDown(int index) {
        if (getLeftOf(index) >= contents.size()) {
            return;
        } else {
            int min = min(getLeftOf(index), getRightOf(index));
            if (contents.get(index).compareTo(contents.get(min)) > 0) {
                swap(index, min);
                bubbleDown(min);
            }
        }
    }

    /* Inserts element into the MinHeap. */
    public void insert(E element) {
        setElement(contents.size(), element);
        bubbleUp(contents.size() - 1);
        size++;
    }

    /* Returns the number of elements in the MinHeap. */
    public int size() {
        return this.size;
    }

    /* Returns the smallest element. */
    public E removeMin() {
        E removed = getElement(1);
        swap(1, contents.size() - 1);
        contents.remove(contents.size() - 1);
        if (contents.size() > 1) {
            bubbleDown(1);
        }
        size--;
        return removed;
    }

    /* Updates the position of ELEMENT inside the MinHeap, which may have been
       mutated since the inital insert. If a copy of ELEMENT does not exist in
       the MinHeap, do nothing.*/
    public void update(E element) {
        for (E e: contents) {
            if (e.equals(element)) {
                setElement(contents.size(), element);
                int updateIndex = contents.indexOf(element);
                if (getParentOf(updateIndex) != 0 && contents.
                        get(updateIndex).compareTo(contents.get(getParentOf(updateIndex))) < 0) {
                    bubbleUp(updateIndex);
                } else {
                    bubbleDown(updateIndex);
                }
            }
        }
    }
}
