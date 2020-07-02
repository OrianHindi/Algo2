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

    /**
     * found max sum of sub array in circle Array
     * @param arr the arr we want found the max sum of sub array.
     * @return the max sum.
     */
    public static int roundBest(int arr[]){
        int sum1= findSum(arr);
        int sum2 = best(arr);
        int negArr[] = new int[arr.length];
        for (int i = 0; i <arr.length ; i++) {
            negArr[i]= -arr[i];
        }
        int sumNeg = best(negArr);
        int totalSum;
        totalSum = Math.max(sum2,sum1+sumNeg);
        return totalSum;
    }


    public static void main(String[] args) {
        int arr[] = {1,3,-10,5};
        System.out.println(roundBest(arr));
    }







}
