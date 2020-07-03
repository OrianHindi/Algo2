public class MaxSumSubMat {

    /**
     * find max sum of submat in mat that given using best.
     * @param mat mat with values
     * @return
     */
    public static int bestSumMat(int mat[][]){
        int arr[] = new int[mat[0].length];  //array the size of cols;
        int maxSum,tempSum;
        maxSum = Integer.MIN_VALUE;
        for (int i =0; i< mat[0].length ; i++) {
            clean(arr);
            for (int j = i; j <mat[0].length ; j++) {
                for (int k = 0; k <mat.length ; k++) {
                    arr[k] += mat[k][j];
                }
                tempSum = Best.best(arr);
                if(maxSum<tempSum) maxSum = tempSum;
            }

        }
        return maxSum;
    }


    /**
     * find the index of the max value in the array.
     * @param arr the array we want search.
     * @return the index of the max value.
     */
    public static int maxIndex(int arr[]){
        int max=Integer.MIN_VALUE;
        int idx=0;
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i]>max) idx= i;
        }
        return idx;
    }

    public static void clean(int arr[]){
        for (int i = 0; i <arr.length ; i++) {
            arr[i]= 0;
        }
    }

    public static void main(String[] args) {
        int mat[][]= { {-1,-2,-3},{-2,5,2},{10,20,12}};
        System.out.println(bestSumMat(mat));
    }

}
