public class MyTreeSet<E> {
    private class Node {
        public E element;
        public Node left;
        public Node right;
        public Node(E element) {
            this.element = element;
            left = null;
            right = null;
        }
    }
    private int size = 0;
    private Node root = null;
    private Node findNode(Object element) {
        Node curNode = root;
        Comparable<E> cKey = (Comparable<E>) element;
        while (curNode != null) {
            int cmp = cKey.compareTo(curNode.element);
            if (cmp < 0) {
                curNode = curNode.right;
            }
            if (cmp == 0) {
                return curNode;
            }
        }
        return null;
    }
    private Node findParent(Object element) {
        Comparable<E> cKey = (Comparable<E>) element;
        Node child = root;
        Node parent = root;
        while (child != null) {
            int cmp = cKey.compareTo(child.element);
            if (cmp < 0) {
                parent = child;
                child = child.left;
            }
            if (cmp > 0) {
                parent = child;
                child = child.right;
            }
            if (cmp == 0) {
                return parent;
            }
        }
        return null;
    }
    public boolean contains(Object element) {
        Node curNode = findNode(element);
        if (curNode == null) return false;
        return true;
    }
    public void add(E element) {
        if (root == null) {
            root = new Node(element);
            size++;
            return;
        }
        addHelper(element, root);
    }
    private void addHelper(E element, Node  node) {
        Comparable<E> cKey = (Comparable<E>) element;
        int cmp = cKey.compareTo(node.element);
        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node(element);
                size++;
                return;
            }
            addHelper(element, node.left);
        }
        if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node(element);
                size++;
                return;
            }
            addHelper(element, node.right);
        }
        if (cmp == 0){
            System.out.println("Element already exist!");
        }
    }
    public E remove (Object element) {
        Node child = findNode(element);
        if (child == null) return null;
        if (size == 1) {
            root = null;
            size--;
            return child.element;
        }
        Node parent = findParent(element);
        if (child.left != null && child.right != null) {
            Node preemnik = findPreemnik(child.right);
            Node preParent = findParent(preemnik.element);
            removeHelper(preemnik, preParent);
            child.element = preemnik.element;
            E oldValue = child.element;
            return oldValue;
        } else if (child == root) {
            if (child.left != null) root = child.left;
            if (child.right != null) root = child.right;
            size--;
            return child.element;
        } else {
            return removeHelper(child,parent);
        }
    }
    private E removeHelper(Node child, Node parent) {
        if (child.left == null && child.right == null) {
            if (parent.left == child) parent.left = null;
            if (parent.right == child) parent.right = null;
            size--;
            return child.element;
        }
        if (child.left == null) {
            if (parent.left == child) parent.left = child.right;
            if (parent.right == child) parent.right = child.right;
            size--;
            return child.element;
        }
        if (child.right == null) {
            if (parent.left == child) parent.left = child.left;
            if (parent.right == child) parent.right = child.left;
            size--;
            return child.element;
        }
        return null;
    }
    private Node findPreemnik(Node node) {
        if (root == null) {
            System.out.println("Tree is empty!");
            return node;
        }
        System.out.println("Root is " + root.element);
        LER(root);
        return node;
    }
    private void LER(Node node) {
        if (node.left   != null) LER(node.left);
        System.out.println(node.element);
        if (node.right != null) LER(node.right);
    }
}
