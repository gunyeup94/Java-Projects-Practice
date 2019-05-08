import java.util.LinkedList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BST<T> {

    BSTNode<T> root;

    public BST(LinkedList<T> list) {
        root = sortedIterToTree(list.iterator(), list.size());
    }

    /* Returns the root node of a BST (Binary Search Tree) built from the given
       iterator ITER  of N items. ITER will output the items in sorted order,
       and ITER will contain objects that will be the item of each BSTNode. */
    private BSTNode<T> sortedIterToTree(Iterator<T> iter, int N) {
        if (N == 0) {
            return null;
        }
        BSTNode<T> newTree = new BSTNode<>(null);
        try {
            ArrayList<T> newList = new ArrayList<>();
            int rootIndex = N / 2;
            for (int i = 0; i < rootIndex; i++) {
                newList.add(iter.next());
            }
            Iterator<T> newListLeft = newList.iterator();
            newTree = new BSTNode<>(iter.next());
            newTree.left = sortedIterToTree(newListLeft, rootIndex);
            newTree.right = sortedIterToTree(iter, rootIndex);
            return newTree;
        } catch (NoSuchElementException e) {
            return newTree;
        }
    }

    /* Prints the tree represented by ROOT. */
    private void print() {
        print(root, 0);
    }

    private void print(BSTNode<T> node, int d) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < d; i++) {
            System.out.print("  ");
        }
        System.out.println(node.item);
        print(node.left, d + 1);
        print(node.right, d + 1);
    }

    class BSTNode<T> {
        T item;
        BSTNode<T> left;
        BSTNode<T> right;

        BSTNode(T item) {
            this.item = item;
        }
    }
}
