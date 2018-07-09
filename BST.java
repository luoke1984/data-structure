import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;

class Node {
    int key;
    Node left;
    Node right;

    public Node(int item) {
        key = item;
        right = null;
        left = null;
    }
}

public class BST {
    Node root;

    public BST() {
        root = null;
    }
    //DFS
    public void preOrder(Node node) {

        if (node == null) {
            return;
        }
        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }


    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);

    }


    public void postOrder(Node node) {

        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);

    }

    //DFS
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) {
                continue;
            }
            System.out.println(node.key);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    //Height
    public int height(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    //Insert
    public void insert(int item) {
        root = addRecursive(root, item);
    }

    private Node addRecursive(Node node, int item) {
        if (node == null) {
            return new Node(item);
        }
        if (item < node.key) {
            node.left = addRecursive(node.left, item);
        } else if (item < node.key) {
            node.right = addRecursive(node.right, item);
        }
        return node;
    }

    //Search
    public boolean search(int item) {
        return searchRecursive(root, item);
    }

    private boolean searchRecursive(Node node, int item) {
        if (node == null) {
            return false;
        }
        if (node.key == item) {
            return true;
        }
        return item < node.key ? searchRecursive(node.left, item) : searchRecursive(node.right, item);
    }

    //Delete
    public void delete(int item) {
        root = deleteRecursive(root, item);
    }

    private Node deleteRecursive(Node node, int item) {
        if (node == null) {
            return node;
        }
        if (item < node.key) {
            node.left = deleteRecursive(node.left, item);

        } else if (item > node.key) {
            node.right = deleteRecursive(node.right, item);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.key = findMin(node.right);
            node.right = deleteRecursive(node.right, node.key);
        }
        return node;
    }

    private int findMin(Node node) {
        return node.left == null ? node.key : findMin(node.left);
    }


    //Check whether a tree is balanced
    private int isBalanced(Node node) {
        if (node == null) {
            return 0;
        }

        int lH = isBalanced(node.left);
        if (lH == -1) {
            return -1;
        }

        int rH = isBalanced(node.right);
        if (rH == -1) {
            return -1;
        }

        if (Math.abs(lH - rH) > 1) {
            return -1;
        }
        return Math.max(lH, rH) + 1;
    }


    public boolean balance(Node node) {
        int result = isBalanced(node);
        if (result != -1) {
            return true;
        }
        return false;
    }



    public static void main(String[] args) {

        BST bst = new BST();
        bst.root = new Node(12);
        bst.root.left = new Node(13);
        bst.root.right = new Node(14);
        bst.root.left.left = new Node(3);
        bst.root.left.left.left = new Node(4);
        bst.root.right.right = new Node(9);
        System.out.println(bst.balance(bst.root));



    }

}
