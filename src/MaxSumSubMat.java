public class MaxSumSubMat {

    public static int bestSumMat(int mat[][]){
        int arr[] = new int[mat[0].length];  //array the size of cols;
        int maxSum,tempSum;
        maxSum = Integer.MIN_VALUE;
        tempSum= 0;
        for (int i =0; i< mat[0].length ; i++) {
                clean(arr);
            for (int j = i; j <mat[0].length ; j++) {
                for (int k = 0; k <mat.length ; k++) {
                    arr[k] += mat[k][j];
                }
                tempSum = best(arr);
                if(maxSum<tempSum) maxSum = tempSum;
            }

        }
        return maxSum;
    }
    public static int best(int arr[]){
        int n = arr.length;
        int max,sum;
        int i =0;
        while(i<n && arr[i]<=0) i++;
        if(i==n){
            int index = maxIndex(arr);
            max = arr[index];
            return max;
        }
        max = arr[i];
        sum =0;
        while(i<n){
            sum = sum+arr[i];
            if(sum<=0) sum=0;
            else if(sum>max)max = sum;
            i++;
        }
        return max;
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
