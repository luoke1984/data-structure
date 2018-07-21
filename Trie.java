import java.util.Map;


public class Trie {
    private TrieNode root;

    static class TrieNode {
        private TrieNode[] children = new TrieNode[26];
        private boolean isEndOfWord;
    }

    public Trie() {
        root = new TrieNode();
    }


    public void insert(String word) {
        //assume the word is lower case
        int index;
        TrieNode temp = root;
        for(Character c : word.toCharArray()) {
            index = c - 'a';
            if (temp.children[index] == null ) {
                temp.children[index] = new TrieNode();
            }
            temp = temp.children[index];
        }
        temp.isEndOfWord = true;
    }


    public boolean search(String word) {
        TrieNode temp = root;
        for (Character c : word.toCharArray()) {
            int index = c - 'a';
            if (index > 26 || temp.children[index] == null) {
                return false;
            }
            temp = temp.children[index];
        }
        return temp.isEndOfWord;
    }




    public static void main(String[] args) {
        Trie tr = new Trie();
        tr.insert("test");
        System.out.println(tr.search("test"));
    }
}



