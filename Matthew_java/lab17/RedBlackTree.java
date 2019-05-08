public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    /* Creates an empty RedBlackTree. */
    public RedBlackTree() {
        root = null;
    }

    /* Creates a RedBlackTree from a given BTree (2-3-4) TREE. */
    public RedBlackTree(BTree<T> tree) {
        Node<T> btreeRoot = tree.root;
        root = buildRedBlackTree(btreeRoot);
    }

    /* Builds a RedBlackTree that has isometry with given 2-3-4 tree rooted at
       given node R, and returns the root node. */
    RBTreeNode<T> buildRedBlackTree(Node<T> r) {

        if (r == null) {
            return null;
        }
        if (r.getItemCount() == 2) {
            return new RBTreeNode<T>(true, r.getItemAt(0), buildRedBlackTree(r.getChildAt(0)),
                    new RBTreeNode<T>(false,
                            r.getItemAt(1),
                            buildRedBlackTree(r.getChildAt(1)),
                            buildRedBlackTree(r.getChildAt(2))));
        }
        if (r.getItemCount() == 3) {
            return new RBTreeNode<T>(true, r.getItemAt(1),
                    new RBTreeNode<T>(false,
                            r.getItemAt(0),
                            buildRedBlackTree(r.getChildAt(0)),
                            buildRedBlackTree(r.getChildAt(1))),
                    new RBTreeNode<T>(false,
                            r.getItemAt(2),
                            buildRedBlackTree(r.getChildAt(2)),
                            buildRedBlackTree(r.getChildAt(3))));
        } else {
            if (r.getChildrenCount() == 0) {
                return new RBTreeNode<T>(true, r.getItemAt(0));
            } else {
                return new RBTreeNode<T>(true,
                        r.getItemAt(0),
                        buildRedBlackTree(r.getChildAt(0)),
                        buildRedBlackTree(r.getChildAt(1)));
            }
        }
    }

    /* Flips the color of NODE and its children. Assume that NODE has both left
       and right children. */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /* Rotates the given node NODE to the right. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        RBTreeNode<T> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        newRoot.isBlack = node.isBlack;
        node.isBlack = false;
        return newRoot;
    }

    /* Rotates the given node NODE to the left. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        RBTreeNode<T> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        newRoot.isBlack = node.isBlack;
        node.isBlack = false;
        return newRoot;
    }

    /* Insert ITEM into the red black tree, rotating
       it accordingly afterwards. */
    void insert(T item) {
        root = insertHelper(root, item);
        root.isBlack = true;
    }

    RBTreeNode<T> insertHelper(RBTreeNode<T> node, T item) {
        if (node == null) {
            return new RBTreeNode<T>(false, item);
        }
        if (node.item.compareTo(item) > 0) {
            node.left = insertHelper(node.left, item);
        } else if (node.item.compareTo(item) < 0) {
            node.right = insertHelper(node.right, item);
        }
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }



    /* Returns whether the given node NODE is red. Null nodes (children of leaf
       nodes are automatically considered black. */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }


    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /* Creates a RBTreeNode with item ITEM and color depending on ISBLACK
           value. */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /* Creates a RBTreeNode with item ITEM, color depending on ISBLACK
           value, left child LEFT, and right child RIGHT. */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

}
