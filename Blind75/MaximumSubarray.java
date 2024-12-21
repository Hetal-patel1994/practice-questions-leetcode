public class MaximumSubarray {
    
    public static void main(String[] args) {
        int []arr = {-2,1,-3,4,-1,2,1,-5,4};
        int max1 = maxSubArrayNested1(arr);
        int max2 = maxSubArrayNested2(arr);
        int max3 = maxSubArrayKadanesAlgo(arr);
        System.out.println("Maximum product is " + max1 + "-------" + max2 + "-------" + max3);
    }

    //Base case using Nested loop with T.C=O(n^3) & S.C=O(1)
    static int maxSubArrayNested1(int[] arr){
        int max_num=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            for(int j=i;j<arr.length;j++){
                int sum=0;
                for(int k=i;k<=j;k++){
                    sum+=arr[k];
                }
                max_num=Math.max(max_num,sum);
            }
        }
        return max_num;
    }

    //Better solution using Nested loop with T.C=O(n^2) & S.C=O(1)
    static int maxSubArrayNested2(int[] arr){
        int max_num=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            int sum=0;
            for(int j=i;j<arr.length;j++){
                sum+=arr[j];
                max_num=Math.max(max_num,sum);
            }
        }
        return max_num;
    }

    //Optimal solution using Kadane's Algo with T.C=O(n) & S.C=O(1)
    static int maxSubArrayKadanesAlgo(int[] arr){
        int max_num=Integer.MIN_VALUE, sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(sum>max_num)
                max_num=sum;
            if(sum<0)
                sum=0;
        }
        return max_num;
    }
}
