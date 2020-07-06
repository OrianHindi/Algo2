import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static final int white=0,gray = 1,black=2,inf=Integer.MAX_VALUE;
    /**
     * O(V+E)
     * BFS can tell us if the graph is connect, how much components, who is the components, find the koter of the graph.
     * @param mat mat that represent a graph, true if between 2 vertices have edge false otherwise.
     * @param source source where we start to go on the tree.
     * @return the dist array.
     */
    public static int[] BFS(boolean[][] mat,int source){
        int n = mat.length;
        int dist[]=new int[n];
        int prev[] = new int[n];
        int color[] = new int[n];
        for (int i = 0; i <n ; i++) {
            dist[i]=0;
            color[i]=white;
            prev[i]=-1;
        }
        Queue<Integer> Q = new LinkedList<>();
        color[source]=gray;
        Q.add(source);
        int v;
        while(!Q.isEmpty()){
            v=Q.poll();
            for(int i =0;i<n;i++){
                if(color[i]==white && mat[v][i]){
                    color[i]=gray;
                    prev[i]=v;
                    dist[i]=dist[v]+1;
                    Q.add(i);
                }
            }
            color[v]=black;
        }
        return color;
    }

    public static int[] arrayListBFS(ArrayList<Integer> tree[],int source){
        int n = tree.length;
        int dist[] = new int[n];
        int prev[] = new int[n];
        int colors[] = new int[n];
        for (int i = 0; i <n ; i++) {
                colors[i]=white;
                prev[i]=-1;
                dist[i]=inf;
        }
        dist[source]=0;
        colors[source]=gray;
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        int v=0,u=0;
        while(!q.isEmpty()){
            v=q.poll();
            for (int i = 0; i <tree[v].size() ; i++) {
                u = tree[v].get(i);
                if(colors[u]==white){
                    colors[u]=gray;
                    prev[u] = v;
                    dist[u]= dist[v] +1;
                    q.add(u);
                }
            }
            colors[v]=black;
        }
        return dist;
    }

    /**
     *when graph is given we can check if he is connected using BFS, if we have 2 values at the return array then not connected.
     * if only 1 value is 0 then connected.
     * @param mat matrix that represent a graph  true where there is en edge between two vertices.false otherwise.
     * @return true if connected false otherwise.
     */
    public static boolean isConnected(boolean mat[][]){
        int arr[] = BFS(mat,0);
        int counter =0;
        int n = arr.length;
        for (int i = 0; i <n ; i++) {
            if(arr[i]==0) counter++;
            if(counter>1) return false;
        }
        return true;
    }

    public static boolean isConnectedArrayList(ArrayList<Integer> tree[]){
        int dist[] = arrayListBFS(tree,0);
        for (int i = 0; i <dist.length ; i++) {
            if(dist[i]==inf) return false;
        }
        return true;
    }

    /**
     * O(V+E)*2 = O(V+E).
     * algorithem to find the diameter of a graph(longest path between to vertices)
     * diameter= max length of edges between two vertices.
     * @param mat matrix that represent a graph.
     * @return the value of the diameter.
     */
    public static int findDiameter(boolean mat[][]){
        int diameter=0;
        int arr[] =BFS(mat,0);
        arr = BFS(mat,findMaxInex(arr));
        diameter=arr[findMaxInex(arr)];
        return diameter;
    }

    /**
     * help function that return the index of the max value in the array that given.
     * @param arr array from BFS with dist between the source to ea other vertices.
     * @return the index of max value in the array
     */
    private static int findMaxInex(int arr[]){
        int n = arr.length;
        int idx=-1;
        int max=-1;
        for (int i = 0; i <n ; i++) {
            if(arr[i]>max){
                max=arr[i];
                idx=i;
            }
        }
        return idx;
    }
    public static int[] findComponents(boolean[][] mat){
        int n = mat.length;
        int counter = 1;
        int colors[];
        int arr[] = new int[n+1];
        for (int i = 0; i <n ; i++) {
            if(arr[i]==0){
                colors = BFS(mat,i);
                for (int j = 0; j <colors.length; j++) {
                    if(colors[j]==black){
                        arr[j]=counter;
                    }
                }
                counter++;
            }
        }
        arr[n]= counter-1;
        return arr;
    }

    public static int[] findverBFS(int arr[]){

        int counter = arr[arr.length-1];
        int ans[] = new int[counter];
        for (int i = 0; i <arr.length-1 ; i++) {
            ans[arr[i]-1]++;
        }
        Arrays.sort(ans);
        return ans;

    }
    public static void printPath(boolean[][] mat,int source,int dest){
        int prev[] = BFS(mat,source);
        printRecPath(prev,source,dest);
    }

    public static void printRecPath(int prev[],int source,int dest) {
        if (source == dest) {
            System.out.print(source);
            return;
        }
        else if (prev[dest] == -1) {
            System.out.println("There are no connection bettween the vertices.");
            return;
        }
        else printRecPath(prev, source, prev[dest]);
        System.out.println(dest);
    }



    public static void main(String[] args) {
        boolean[][] graph = {{true,true,false,false,false,false,false,false,false,false},
                {true,true,false,false,false,false,false,false,false,false},
                {false,false,true,false,false,false,false,false,false,false},
                {false,false,false,true,true,true,false,false,false,false},
                {false,false,false,true,true,true,false,false,false,false},
                {false,false,false,true,true,true,false,false,false,false},
                {false,false,false,false,false,false,true,true,false,false},
                {false,false,false,false,false,false,true,true,true,true},
                {false,false,false,false,false,false,false,true,true,false},
                {false,false,false,false,false,false,false,true,false,true},
        };
        int arr[] = findverBFS(findComponents(graph));
        System.out.println(Arrays.toString(arr));
        printPath(graph,0,5);

    }

}
