import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public  class Bottle_Problem {

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
    public static int[][] initRibs(int n, int m) {
        int dim = (n + 1) * (m + 1);
        int mat[][] = new int[dim][dim];
        int ind1, ind2;
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                ind1 = getIndex(i, j, m);
                mat[ind1][getIndex(0, j, m)] = 1;
                mat[ind1][getIndex(n, j, m)] = 1;
                mat[ind1][getIndex(i, 0, m)] = 1;
                mat[ind1][getIndex(i, m, m)] = 1;

                ind2 = getIndex(Math.min(n, i + j), i + j - Math.min(n, i + j), m);
                mat[ind1][ind2] = 1;
                ind2 = getIndex(i + j - Math.min(m, i + j), Math.min(m, i + j), m);
                mat[ind1][ind2] = 1;
            }
        }
        for (int v = 0; v < dim; v++) mat[v][v] = 1;
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

//    public static void isConnected(boolean mat[][]){
//        for (int i = 0; i <mat.length ; i++) {
//            for (int j = 0; j <mat[0].length ; j++) {
//                if(mat[i][j]!=mat[j][i])
//            }
//
//        }
//
//    }

    /**
     * FW algorithem to find if is there any path between 2 vertices, True if there is False if there is'nt.
     *
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
    public static int findNumComponents(boolean mat[][]){
        int n = mat.length;
        int arr[] = new int[n];
        int counter= 0;
        for (int i = 0; i <n ; i++) {
            if(arr[i]==0){
                counter++;
                for (int j = 0; j <mat[0].length ; j++) {
                    if(mat[i][j]) arr[j]=counter;
                }
            }

        }
        return counter;
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
     * Find max value of sub array from a given array.
     * Complexity: O(N^2)
     * @param arr the array that we want to find the max sub array value.
     * @return the max value of the sub array.
     */
    public static int maxSubArr(int arr[]){
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        int mat[][] = new int[n+1][n+1];
        mat[0][0]=0;
        for (int i = 0; i <n ; i++) {
            mat[i+1][i+1]=arr[i];
            mat[0][i+1]=0;
            mat[i+1][0]=0;
        }
        for (int i = 1; i <n+1 ; i++) {
            for (int j = 1; j <n+1 ; j++) {
                mat[i][j]=mat[i][j-1]+arr[j-1];
                if(mat[i][j]>max) max = mat[i][j];
            }
        }
        return max;
    }



    public static void main(String[] args) {
        int arr[] = {3,-2,5,1};

    }
}
