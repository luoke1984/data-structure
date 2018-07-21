import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class Graph {

    private int v;
    private LinkedList<Integer> adj[];

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i ++) {
            adj[i] = new LinkedList();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public void BFS(int s) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visted = new boolean[v];
        q.add(s);

        visted[s] = true;
        while(!q.isEmpty()) {
            s = q.poll();
            System.out.println(s);

            Iterator<Integer> it = adj[s].iterator();
            while(it.hasNext()) {
                int n = it.next();
                if(!visted[n]) {
                   q.add(n);
                   visted[n] = true;
                }
            }
        }
    }

    //Time Complexity is O(V + E) where V is the vertex(node) and E is the edge.

    public static void main(String[] args) {

       Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.BFS(1);

    }

}
