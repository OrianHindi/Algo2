import java.util.ArrayList;
import java.util.Arrays;

public class Trees {


    /**
     * Time complexity: O(v) + O(n*logn)[sort the deg array].
     *  if can built a tree from given degrees array. return the tree else throw exception.
     * @param deg array of vertices degrees.
     * @return the tree that have been built from the array
     */
    public static int[][] buildTree(int deg[]) throws Exception{
        int n = deg.length;
        int sum =0;
        int tree[][] = new int[n][n];
        for (int i = 0; i <n; i++) {
            sum+=deg[i];
        }
        if(sum/2 +1 !=n){
            System.out.println("Cant build tree");
            throw new Exception("Not a tree");
        }
        Arrays.sort(deg);
        int j =findIndex(deg);
        for (int i = 0; i <n-2 ; i++) {
            if(deg[i]==1) {
                tree[j][i] = 1;
                deg[i]--;
                deg[j]--;
            }
            if (deg[j] == 1) j++;
        }
        tree[n-1][n-2]=1;
        return tree;
    }

    public static int findIndex(int deg[]){

        for (int i = 0; i <deg.length ; i++) {
            if(deg[i]>1) return i;
        }
        return -1;
    }

    static int vertex =0;


    /**
     *
     * @param deg complexcity o(vertex) + o(n*log(n))
     * @return
     * @throws Exception
     */
    public static int [][] build_tree_from_degree(int [] deg) throws Exception {
        if (!check_degree(deg)) {
            throw new Exception("NOT INIT TREE");
        }
        // System.out.println(vertex);
        int [][] Graph =new int [vertex][vertex];
        //ArrayList<ArrayList<Integer>> Graph = new ArrayList<ArrayList<Integer>>(vertex);
        Arrays.sort(deg); // n*log(n)
        int pointer_j = 0;
        for (int i = 0; i < deg.length; i++) {
            if (deg[i] > 1) {
                pointer_j = i;
                break;
            }
        }
        for (int i = 0; i < deg.length-2; i++) {
            if (deg[i] == 1) {
                Graph[pointer_j][i]=1;
                deg[i]=deg[i]-1;
                deg[pointer_j]=deg[pointer_j]-1;
            }
            if(deg[pointer_j]==1){
                pointer_j++;
            }
        }
        int N = deg.length-1;
        int N_1 = deg.length-2;
        Graph[N][N_1]=1;
        ///////print graph
        for (int i = 0; i <Graph.length ; i++) {
            for (int j = 0; j <Graph.length ; j++) {
                System.out.print(Graph[i][j]+" ");
            }
            System.out.println();
        }
        return Graph;
    }

    public static boolean check_degree(int [] deg){ // o(n)
        int sum =0;
        for (int i = 0; i <deg.length ; i++) {
            sum+=deg[i];
        }
        if(sum%2==1){
            return false;
        }
        vertex = sum/2+1;
        if (sum == 2*(deg.length-1)){
            if(vertex==deg.length){
                return true;
            }
        }
        return false;
    }

    /**
     * check if graph is given is a tree or no.
     * Time complexity: O(V+E) +E
     * Use BFS check if the graph is connected and have |v|-1 edges,if yes return true else false.
     * @param tree matrix that represent a tree.
     * @return true if the graph represent a tree false otherwise.
     */
    public static boolean isTree(ArrayList<Integer> tree[]){
        int n = tree.length;
        int counter=0;
      //  BFS(tree); //need to implement BFS that get Arraylist[].
        ArrayList<Integer> temp;
        for (int i = 0; i <n ; i++) {
            temp=tree[i];
            for (int j = 0; j <temp.size() ; j++) {
                counter++;
                if(counter>n-1) return false;
            }
        }
        return true;
    }


    public static void main(String[] args) throws Exception {
        int deg[] = {1,1,1,1,2,2,3,3};
        int tree[][]=null;
        try {
            tree=buildTree(deg);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    for (int i=0; i<deg.length;i++){
        System.out.println(Arrays.toString(tree[i]));
    }
        System.out.println("-------------------------");


    }
}
