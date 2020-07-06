public class Best {
    public static int findSum(int arr[]){
        int sum=0;
        for (int i = 0; i <arr.length ; i++) {
            sum+=arr[i];
        }
        return sum;
    }

    /**
     * Complexity: O(n).
     * find the max sum of sub array
     * @param arr the array we want serach.
     * @return the max sum.
     */
    public static int best(int arr[]){
        int n = arr.length;
        int ans[] = new int[3];
        int max,sum,start,end;
        start = end = -1;
        int i =0;
        while(i<n && arr[i]<=0) i++;
        if(i==n){
            int index = maxIndex(arr);
            max = arr[index];
            ans[0]=ans[1]=index;
            ans[2]= max;
            //if we want to return the index of the subarray return the arr;
            return max;
        }
        max = arr[i];
        sum =0;
        start=i;
        while(i<n){
            sum = sum+arr[i];
            if(sum<=0) {
                start = i+1;
                sum=0;
            }
            else if(sum>max){
                max = sum;
                end =i;
            }
            i++;
        }
        ans[0]=start;
        ans[1] = end;
        ans[2] = max;
        return max;
        //if we want return the index of the sub
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

    /**
     * found max sum of sub array in circle Array
     * @param arr the arr we want found the max sum of sub array.
     * @return the max sum.
     */
    public static int roundBest(int arr[]){
        int arraySum= findSum(arr);
        int BestSum = best(arr);
        int negArr[] = new int[arr.length];
        for (int i = 0; i <arr.length ; i++) {
            negArr[i]= -arr[i];
        }
        int sumNeg = best(negArr);
        int totalSum;
        totalSum = Math.max(BestSum,arraySum+sumNeg);
        return totalSum;
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


    //hello myname is orian.
    public static void main(String[] args) {
        int arr[] = {1,3,-10,5};
        System.out.println(roundBest(arr));
    }







}
