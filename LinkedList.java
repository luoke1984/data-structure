public class LinkedList {

    Node head;

    class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
            next = null;
            prev = null;
        }
    }

    class BTreeNode{
        int data;
        BTreeNode left;
        BTreeNode right;

        public BTreeNode(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private int countNodes(Node n) {
        Node head = n;
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    private void printLinkedList(Node n) {
        while (n != null) {
            System.out.println(n.data);
            n = n.next;
        }
    }

    private void insert(int data) {
        Node newNode = new Node(data);
        newNode.next = head;

        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
    }


    private BTreeNode linkedListToBST(int n) {
        /* Base Case */
        if (n <= 0)
            return null;

        /* Recursively construct the left subtree */
        BTreeNode left = linkedListToBST(n / 2);

        /* head_ref now refers to middle node,
           make middle node as root of BST*/
        BTreeNode root = new BTreeNode(head.data);

        // Set pointer to left subtree
        root.left = left;

        /* Change head pointer of Linked List for parent
           recursive calls */
        head = head.next;

        /* Recursively construct the right subtree and link it
           with root. The number of nodes in right subtree  is
           total nodes - nodes in left subtree - 1 (for root) */
        root.right = linkedListToBST(n - n / 2 - 1);

        return root;


    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
//        list.insert(5);
//        list.insert(4);
        list.insert(3);
        list.insert(2);
        list.insert(1);
        list.printLinkedList(list.head);

        list.linkedListToBST(3);

    }


}
