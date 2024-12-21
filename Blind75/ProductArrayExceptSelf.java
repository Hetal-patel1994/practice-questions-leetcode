import java.util.Arrays;

public class ProductArrayExceptSelf {
    
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        int[] ans1 = productExceptSelfNested(arr);
        int[] ans2 = productExceptSelfDPTabulation(arr);
        int[] ans3 = productExceptSelfDPSpaceOptimization(arr);
        System.out.println("This is the answer for variant 1: " + Arrays.toString(ans1) + "-------" + Arrays.toString(ans2) + "-------" + Arrays.toString(ans3) );
    }

    //Base case using Nested Loop with T.C=O(n^2) & S.C=O(1)
    static int[] productExceptSelfNested(int[] arr){
        int[] ans = new int[arr.length];
        for(int i=0;i<arr.length;i++){
            int product=1;
            for(int j=0;j<arr.length;j++){
                if(i==j)
                    continue;
                product = product*arr[j];
            }
            ans[i]=product;
        }
        return ans;
    }

    //Better solution using DP Tabulation with T.C=O(n^2) & S.C=O(1)
    static int[] productExceptSelfDPTabulation(int[] arr){
        int[] ans = new int[arr.length];
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        left[0]=1; right[arr.length-1]=1;
        for(int i=1;i<arr.length;i++){
            left[i]=left[i-1]*arr[i-1];
        }
        for(int i=arr.length-2;i>-1;i--){
            right[i]=right[i+1]*arr[i+1];
        }
        for(int i=0;i<arr.length;i++){
            ans[i]=left[i]*right[i];
        }
        return ans;
    }

    //Optimal solution using DP Space Optimization with T.C=O(n) & S.C=O(1)
    static int[] productExceptSelfDPSpaceOptimization(int[] arr){
        int[] ans = new int[arr.length];
        int right=1;
        ans[0]=1; 
        for(int i=1;i<arr.length;i++){
            ans[i]=ans[i-1]*arr[i-1];
        }
        for(int i=arr.length-1;i>=0;i--){
            ans[i]=ans[i]*right;
            right=arr[i]*right;
        }
        return ans;
    }
}
