import java.util.ArrayList;
import java.util.Arrays;

public class DFS {
    private static final int white=0, gray=1,black=2;
    private static int[] color,prev,dist;
    private static ArrayList<Integer>[] graph;



    /**
     * Time Complexity: O(|V|+|E|).
     * @param tree array of arraylist that represent a graph.
     */
    public static void DFS(ArrayList<Integer> tree[]){
        graph=tree;
        int n = tree.length;
        color  = new int[n];
         prev = new int[n];
         dist = new int[n];
        Arrays.fill(prev,-1);
        for (int i = 0; i <n; i++) {
            if(color[i]==white) DFSutil(i);
        }
    }

    private static void DFSutil(int v){
        color[v]=gray;
        for (int u: graph[v]) {
            if(color[u]==white){
                prev[u]=v;
                dist[u]=dist[v]+1;
                DFSutil(u);
            }
        }
        color[v]=black;
    }
}
