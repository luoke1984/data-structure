import java.util.ArrayList;

    class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public class HashMap<K, V> {
        private ArrayList<HashNode<K, V>> bucketArray;
        private int numBuckets;
        private int size;

        public HashMap() {
            bucketArray = new ArrayList<>();
            numBuckets = 10;
            size = 0;

            for (int i = 0; i < numBuckets; i++) {
                bucketArray.add(null);
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private int getBucketIndex(K key) {
            int hash = key.hashCode();
            return hash % numBuckets;
        }

        public V get(K key) {
            HashNode<K, V> node = bucketArray.get(getBucketIndex(key));

            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                }
                node = node.next;
            }
            return null;
        }

        public V remove(K key) {
            int index = getBucketIndex(key);
            HashNode<K, V> node = bucketArray.get(index);

            HashNode<K, V> prev = null;

            while (node != null) {
                if (node.key.equals(key)) {
                    break;
                }
                prev = node;
                node = node.next;
            }

            if (node == null) {
                return null;
            }

            if (prev != null) {
                prev.next = node.next;
            } else {
                bucketArray.set(index, node.next);
            }

            size--;
            return node.value;
        }

        public void add(K key, V value) {
            int index = getBucketIndex(key);
            HashNode<K, V> head = bucketArray.get(index);

            while (head != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }

            HashNode<K, V> node = new HashNode<>(key, value);
            head = bucketArray.get(index);
            node.next = head;
            bucketArray.set(index, node);
            size++;

            if ((1.0 * size) / numBuckets > 0.7) {
                ArrayList<HashNode<K, V>> temp = bucketArray;
                bucketArray = new ArrayList<>();
                numBuckets = 2 * numBuckets;
                size = 0;

                for (int i = 0; i < numBuckets; i++) {
                    bucketArray.add(null);
                }
                for (HashNode<K, V> n : temp) {
                    while (n != null) {
                        add(n.key, n.value);
                        n = n.next;
                    }
                }
            }
        }

        public static void main(String[] args) {
            HashMap<String, Integer> m = new HashMap<>();
            m.add("a", 1);
            m.add("b", 2);
            m.add("c", 3);

            System.out.println(m.get("b"));

            System.out.println(m.size);

        }
    }

