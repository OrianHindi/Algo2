import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public  class Bottle_Problem {
    static final int inf  = Integer.MAX_VALUE;

    /**
     * find the right index for the initRibs
     * @param i the value of first bottle.
     * @param j the value of second bottle.
     * @param n the mat length.
     * @return the right index.
     */
    public static int getIndex(int i, int j, int n) {
        return (n + 1) * i + j;
    }

    /**
     * Bottle problem:
     * Algorithem that fill up mat with 1,0
     * 1 means that have connection between two conditions, 0 that there is'nt.
     * @param n capacity of first bottle.
     * @param m capacity of second bottle.
     * @return
     */
    public static boolean[][] initRibs(int n, int m) {
        int dim = (n + 1) * (m + 1);
        boolean mat[][] = new boolean[dim][dim];
        int ind1, ind2;
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                ind1 = getIndex(i, j, m);
                mat[ind1][getIndex(0, j, m)] = true;
                mat[ind1][getIndex(n, j, m)] = true;
                mat[ind1][getIndex(i, 0, m)] = true;
                mat[ind1][getIndex(i, m, m)] = true;
                ind2 = getIndex(Math.min(n, i + j), i + j - Math.min(n, i + j), m);
                mat[ind1][ind2] = true;
                ind2 = getIndex(i + j - Math.min(m, i + j), Math.min(m, i + j), m);
                mat[ind1][ind2] = true;
            }
        }
        for (int v = 0; v < dim; v++) mat[v][v] = true;
        return mat;
    }

    /**
     * algorithem that print the mat with the bottles status.
     * @param mat mat the represent connection between two condictions.
     * @param n   capacity of first bottle.
     * @param m   capacity of second bottle.
     */
    public static void printMat(int mat[][], int n, int m) {
        int stop = 0;
        String arr[] = new String[2 * m * n + 2];
        int go = 0;
        while (stop <= n) {
            for (int j = 0; j <= m; j++) {
                arr[go] = "[" + stop + "," + j + "]\t";
                go++;
                System.out.print("\t [" + stop + "," + j + "]");
            }
            stop++;
            if (stop > n) break;
            for (int i = 0; i <= m; i++) {
                System.out.print("\t[" + stop + "," + i + "]");
                arr[go] = "[" + stop + "," + i + "]\t";
                go++;
            }
            stop++;
        }
        System.out.println();
        for (int i = 0; i < mat.length; i++) {
            System.out.print(arr[i]);
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(" " + mat[i][j] + "\t");
            }
            System.out.println();
        }
    }


    /**
     * FW algorithem to find if is there any path between 2 vertices, True if there is False if there is'nt.
     * @param mat Boolean matrix that represent edegs.
     * @return boolean matrix that tell if two vertices have path. True if there is False if there is'nt.
     */
    public static boolean[][] FW(boolean mat[][]) {
        int n = mat.length;
        boolean ans[][] = new boolean[n][n];
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ans[i][j] = mat[i][k] && mat[k][j] || mat[i][j];
                }
            }
        }
        return ans;
    }

    /**
     * algorithem to find shortest paths between two vertices.
     *Complexity: O(N^3).
     * @param mat matrix that represent weight of edges.
     * @return matrix with shortest path for each two vertices, if there is'nt a path value is infinity.
     */
    public static int[][] shortestPathFW(int mat[][]) {
        int n = mat.length;
        int max = Integer.MAX_VALUE;
        int ans[][] = new int[n][n];
        for (int i = 0; i < n; i++) {         //initialize the math with infinity.
            for (int j = 0; j < n; j++) {
                ans[i][j] = mat[i][j];
            }
        }
        for (int i = 0; i < n; i++) {    // update the weight path from the given matrix.
            for (int j = 0; j < n; j++) {
                if (ans[i][j] == 0 && i != j) ans[i][j] = max;
                if (i == j) ans[i][j] = 0;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (ans[i][k] != max && ans[k][j] != max)
                        ans[i][j] = Math.min(ans[i][k] + ans[k][j], ans[i][j]);  //update the minimum path.
                }
            }
        }
        return ans;
    }


    /**
     * Complexity: O(N^3)
     * @param mat matrix that represent the weight of edges.
     * @return string matrix that represent eq shortest path.
     */
    public static String[][] getShortesFW(int mat[][]) {
        int n = mat.length;
        int max = Integer.MAX_VALUE;
        int FW_mat[][] = shortestPathFW(mat);
        String ans[][] = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (FW_mat[i][j] != max) ans[i][j] = i + " ->" + j;
                else ans[i][j] = "";
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (FW_mat[i][k] != max && FW_mat[k][j] != max) {
                        if (FW_mat[i][j] > FW_mat[i][k] + FW_mat[k][j])
                            ans[i][j] = ans[i][k] + ans[k][j];
                    }
                }
            }
        }
        return ans;
    }

    /**
     * O(n)-->need to check might be higher.
     * find num of components in graph,
     * @param mat FW matrix with true where is a path between 2 vertices.
     * @return array that represent the components.
     */
    public static int[] findNumComponents(boolean[][] mat){
        int n = mat.length;
        int arr[] = new int[n];
        int counter =0;
        boolean flag = true;
        for (int i = 0; i <n ; i++) {
            if(arr[i]==0){
                flag = true;
                for (int j = i+1; j <n ; j++) {
                    if(arr[j]!=0 && mat[i][j]){
                        arr[i]=arr[j];
                        flag = false;
                    }
                }
                if(flag){
                    counter++;
                    arr[i]= counter;
                    for (int j = i+1; j <n ; j++) {
                        if(arr[j]==0 && mat[i][j]) arr[j]=counter;
                    }
                }
            }
        }
        String comp[] = new String[counter];
        for (int i = 0; i <n ; i++) {
            comp[arr[i]-1]+="," + i;
        }
        return arr;
        //if want return num of components return counter;

    }

    /**
     * with a FW given matrix if there is any negative value in the alcson then have negative cycle.
     * @param mat matrix after FW that represent shortest paths.
     * @return true if there is any negative cycle false otherwise.
     */
    public static boolean negativeCycle(int mat[][]){
        boolean flag = false;
        int n = mat.length;
        for (int i = 0; i <n ; i++) {
            if(mat[i][i]<0) flag = true;
        }
        return flag;
    }

    /**
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

        int ans[][] = shortestPathFW(temp);
        for (int i = 0; i <n ; i++) {
            for(int j =0; j<n;j++){
                ans[i][j]= (ans[i][j]+vWeight[i]+vWeight[j])/2;
            }
        }
        return ans;
    }

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
        int ans[][] = shortestPathFW(temp);
        for (int i = 0; i <n ; i++) {
            for(int j =0; j<n;j++){
                ans[i][j]= (ans[i][j]+vWeight[i]+vWeight[j])/2;
            }
        }
        return ans;
    }

    public static int getI(int k, int n){
        return k/(n+1);
    }
    public static int getJ(int k,int n){
        return k%(n+1);
    }



    public static void main(String[] args) {


    }
}
