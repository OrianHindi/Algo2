import java.util.Arrays;

public class FuelStation {
    public static int findStation(int a[],int b[]){
        int sum1,sum2;
        sum1=sum2=0;
        for(int i =0;i<a.length;i++){
            sum1+=a[i];
            sum2+=b[i];
        }
        if(sum2>sum1) return -1;
        int c[] = new int[a.length];
        for (int i = 0; i <c.length ; i++) {
            c[i]=a[i]-b[i];
        }
        int startIdx = bestIdx(c);
        return startIdx;
    }

    public static int bestIdx(int arr[]){
        int idx=-1;
        int i =0;
        int max = Integer.MIN_VALUE;
        while(i<arr.length && arr[i]<0) i++;
        if(i==arr.length){
            for (int j = 0; j <arr.length ; j++) {
                if(arr[j]>max) idx = j;
            }
            return idx;
        }
        int sum  =0;
        idx = i;
        while(i<arr.length){
            sum+=arr[i];
            if(sum<=0){
                idx = i+1;
                sum =0;
            }
            if(sum>max) max = sum;
            i++;
        }
        return idx;
    }

    public static int[]  best(int arr[]){
        int max = arr[0];
        int tempMax,start,end,tempStart;
        tempMax=start=end=tempStart=0;
        for(int i = 0 ; i <arr.length;i++){
            tempMax+=arr[i];
            if(max<tempMax){
                max = tempMax;
                start=tempStart;
                end =i;
            }
            if(tempMax<0){
                tempMax=0;
                tempStart=i+1;
            }
        }
        int ans[] = {max,start,end};
        return ans;
    }

    public static void main(String[] args) {
        int check[] = {1,-2,1,29};
        int arr[] =best(check);
        System.out.println(Arrays.toString(arr));
    }
}
