import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestQuestions {


    public static final int white = 0, gray = 1, black = 2 , inf = Integer.MAX_VALUE;


    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------Test 1-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * check if there is any cycle in the graph time Complexity: O(V+E).
     * Test 1 Question 1.a .
     *
     * @param tree array of arraylist represent a tree.
     * @return true if there is any cycle false otherwise.
     * @throws Exception if the array is in size 0 throw exception.
     */
    public static boolean hasCycle(ArrayList<Integer> tree[]) throws Exception {
        int n = tree.length;
        int v;
        if (n >= 1) v = 0;
        else throw new Exception();
        int colors[] = new int[n];
        int prev[] = new int[n];
        for (int i = 0; i < n; i++) {
            colors[i] = white;
            prev[i] = -1;
        }
        Queue<Integer> qu = new LinkedList<Integer>();
        qu.add(v);
        int u;
        while (!qu.isEmpty()) {
            u = qu.poll();
            for (int i = 0; i < tree[u].size(); i++) {
                v = tree[u].get(i);
                if (colors[v] == white) {
                    colors[v] = gray;
                    prev[v] = u;
                    qu.add(v);
                } else {
                    if (colors[v] == gray) {
                        return true;
                    }
                }
            }
            colors[u] = black;
        }
        return false;
    }

    /**
     * print the cycle(not finished yet)
     * Time Complexity:O(E+V)
     * Test 1 Question 1.b
     *
     * @param tree
     */

    public static void printCycle(ArrayList<Integer> tree[]) {
        int n = tree.length;
        int colors[] = new int[n];
        int prev[] = new int[n];
        for (int i = 0; i < n; i++) {
            colors[i] = white;
            prev[i] = -1;
        }
        colors[0] = gray;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int v = 0, u = 0, v1 = 0, u1 = 0;
        boolean hasCycle = false;
        while (!q.isEmpty() && hasCycle) {
            v = q.poll();
            for (int i = 0; i < tree[v].size(); i++) {
                u = tree[v].get(i);
                if (colors[u] == white) {
                    colors[u] = gray;
                    prev[u] = v;
                    q.add(u);
                } else {
                    if (colors[u] == gray) {
                        v1 = v;
                        u1 = u;
                        hasCycle = true;
                    }
                }
            }
            colors[v] = black;
        }
        String v2s = "", u2s = "", cycle = "";
        int currentVertex = v1;
        while (currentVertex != -1) {
            v2s = v2s + currentVertex;
            currentVertex = prev[currentVertex];
        }
        currentVertex = u1;
        while (currentVertex != -1) {
            u2s = u2s + currentVertex;
            currentVertex = prev[currentVertex];
        }
        cycle = v2s + u2s;
        System.out.println(cycle);

    }
//---------------------------------------------------------------------------------------------------------------------

    /**
     * Test 1 Question 2.
     * Time Complexity: O(V) + O(V+E) + O(V) = O(V+E)
     * find if a leaf belong to the diameter of the tree
     * return true if yes otherwise false;
     */
    public static boolean atDiameter(ArrayList<Integer> tree[], int s) {
        ArrayList<Integer> temp[] = copy(tree);
        int diameter = Fire.fire(temp);
        int dist[] = BFS.arrayListBFS(tree, s);
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == diameter) return true;
        }
        return false;
    }

    protected static ArrayList<Integer>[] copy(ArrayList<Integer> tree[]) {
        ArrayList<Integer> ans[] = new ArrayList[tree.length];
        int n = tree.length;
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < tree[i].size(); j++) {
                temp.add(tree[i].get(j));
            }
            ans[i] = temp;
        }
        return ans;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Question 3 Test 1, Time complexity: O(n+m),
     * This function return the number of the undirected graph edges.(answers is diffrent from the book)
     * @param n the first bottle capacity
     * @param m the second bottle capacity
     * @return the num of undirected edges.
     */
    public static int numOfEdgesBP(int n, int m){
        int dim = (n+1)*(m+1);
        int counter=0;
        int i=0,j=0,index=0;
        for (int k = 0; k <dim ; k++) {
            i = Bottle_Problem.getI(k,n);
            j = Bottle_Problem.getJ(k,n);
            index=Bottle_Problem.getIndex(0,j,n);
            if(k!=index) counter++;
            index= Bottle_Problem.getIndex(i,0,n);
            if(k!=index) counter++;
            index = Bottle_Problem.getIndex(m,j,n);
            if(k!=index) counter++;
            index = Bottle_Problem.getIndex(i,n,n);
            if(index!= k) counter++;
            index = Bottle_Problem.getIndex(i+j-Math.min(i+j,n),Math.min(i+j,n),n);
            if(k!=index) counter++;
            index = Bottle_Problem.getIndex(Math.min(i+j,m),i+j-Math.min(i+j,m),n);
            if(k!=index) counter++;

        }
        return counter;
    }


    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------Test 2-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Question 1 Test2.
     * this function find all the vertices degrees.
     * Time Complexity: O(n+m)
     * @param n the capacity of the first bottle.
     * @param m the capacity of the second bottle.
     * @return the array of the degrees.
     */
    public static int[] degOfVerticesBP(int n, int m){
        int dim = (n+1)*(m+1);
        int deg[] = new int[dim];
        int i=0,j=0,index=0;
        for (int k = 0; k <dim ; k++) {
            i = Bottle_Problem.getI(k,n);
            j = Bottle_Problem.getJ(k,n);
            index=Bottle_Problem.getIndex(0,j,n);
            if(k!=index){
                deg[index]++;
                deg[k]++;
            }
            index= Bottle_Problem.getIndex(i,0,n);
            if(k!=index){
                deg[index]++;
                deg[k]++;
            }
            index = Bottle_Problem.getIndex(m,j,n);
            if(k!=index){
                deg[index]++;
                deg[k]++;
            }
            index = Bottle_Problem.getIndex(i,n,n);
            if(k!=index){
                deg[index]++;
                deg[k]++;
            }
            index = Bottle_Problem.getIndex(i+j-Math.min(i+j,n),Math.min(i+j,n),n);
            if(k!=index){
                deg[index]++;
                deg[k]++;
            }
            index = Bottle_Problem.getIndex(Math.min(i+j,m),i+j-Math.min(i+j,m),n);
            if(k!=index){
                deg[index]++;
                deg[k]++;
            }
        }
        Arrays.sort(deg);
        return deg;
    }

//----------------------------------------------------------------------------------------------------------------------

    /**
     * Question 3.a Test 2
     * Time Complexity: O(N^2)*2 + O(N^3) = O(N^3).
     * this algorithem find the shortest path in graph when weight is calculate by weight of vertices and not edges.
     * @param mat matrix that represent connection between two vertices.
     * @param vWeight array that represent weight of ea vertices.
     * @return a matrix that represent min weight between 2 vertices.
     */
    public static int[][] weightOnV(boolean mat[][], int vWeight[]){
        int n = mat.length;
        int temp[][] = new int[n][n];
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <n ; j++) {
                if(mat[i][j]) temp[i][j]= vWeight[i]+vWeight[j];
                else temp[i][j] = inf;
            }
            temp[i][i] =0;
        }

        int ans[][] = Bottle_Problem.shortestPathFW(temp);
        for (int i = 0; i <n ; i++) {
            for(int j =0; j<n;j++){
                ans[i][j]= (ans[i][j]+vWeight[i]+vWeight[j])/2;
            }
        }
        return ans;
    }


    /**
     * Question 3.b Test 2.
     * find the shortest path between two vertices when weight is from edges and vertices.
     * Time Complexity: O(n^3).
     * @param mat a matrix that represent the edges weight.
     * @param vWeight array that represent the vertices weight.
     * @return the shortest path between two vertices.
     */
    public static int[][] weightEV(int mat[][],int vWeight[]){
        int n = mat.length;
        int temp[][] = new int[n][n];
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <n ; j++) {
                if(mat[i][j]!=inf) temp[i][j]=2*(mat[i][j])+vWeight[i]+vWeight[j];
                else temp[i][j]=inf;
            }
            temp[i][i]=vWeight[i];
        }
        int ans[][] = Bottle_Problem.shortestPathFW(temp);
        for (int i = 0; i <n ; i++) {
            for(int j =0; j<n;j++){
                ans[i][j]= (ans[i][j]+vWeight[i]+vWeight[j])/2;
            }
        }
        return ans;
    }

    //------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------Test 3-----------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------


    /**
     * Question 1 Test3
     * Time Complexity: O(E+V).
     * find the minimum number of edges that will make the graph eulerian graph.
     * @param tree represent the graph.
     * @return the num of the edges.
     */
//    public static int numOfEdgesToEuler(ArrayList<Integer> tree[]){
//        int counter =0;
//        int len = tree.length;
//        ArrayList<Integer> temp[] = copy(tree);
//        for (int i = 0; i <len ; i++) {
//            if(tree[i].size()%2==1) counter++;
//        }
//        if(BFS.isConnectedArrayList(temp)) return counter/2;
//        int CompEdge =numOfEvenComp(tree);
//        return CompEdge+counter/2;
//    }


//    private static int numOfEvenComp(ArrayList<Integer> tree[]){
//        int counter = 0;
//        int len = tree.length;
//        int color[] = new int[len];
//        int arr[] = new int[len];
//        boolean isEven=true;
//        for (int i = 0; i <len ; i++) {
//            color[i]=white;
//        }
//        for (int i = 0; i <len ; i++) {
//            Arrays.fill(arr,0);
//            isEven=true;
//            if(color[i]==white){
//                counter++;
//                arr=
//            }
//
//
//        }
//    }

    public static void main(String[] args) throws Exception {
        System.out.println(numOfEdgesBP(2,1));
        System.out.println(Arrays.toString(degOfVerticesBP(2,1)));
        ArrayList<Integer> check[] = new ArrayList[4];
        check[0] = new ArrayList<>();
        check[1] = new ArrayList<>();
        check[2] = new ArrayList<>();
        check[3] = new ArrayList<>();
        check[0].add(3);
        check[0].add(1);
        check[1].add(0);


        check[2].add(3);
        check[3].add(0);
        check[3].add(2);
        System.out.println(hasCycle(check));
        printCycle(check);
    }
}
