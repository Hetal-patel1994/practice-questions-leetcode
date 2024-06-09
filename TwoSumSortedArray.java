import java.util.Arrays;
import java.util.HashMap;

public class TwoSumSortedArray {
    
    public static void main(String[] args) {
        int[] arr = {2, 5, 6, 8, 11};
        int target = 7;
        int[] ans1 = twoSumNested(arr, target);
        int[] ans2 = twoSumHashing(arr, target);
        int[] ans3 = twoSumBinarySearch(arr, target);
        int[] ans4 = twoSumTwoPointer(arr, target);
        System.out.println("This is the answer for variant 1: " + Arrays.toString(ans1) + "-------" + Arrays.toString(ans2) + "-------" + Arrays.toString(ans3) + "-------" + Arrays.toString(ans4) );
    }

    //Base case using Nested loop with T.C=O(n^2) & S.C=O(1)
    static int[] twoSumNested(int[] arr, int target) {
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(target==arr[i]+arr[j]){
                    return new int[]{i+1,j+1};
                }
            }
        }
        return new int[0];
    }

    //Better solution using Hashing with T.C=O(n) & S.C=O(n)
    static int[] twoSumHashing(int[] arr, int target) {
        HashMap<Integer,Integer> hmap = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(hmap.containsKey(target-arr[i])){
                return new int[]{hmap.get(target-arr[i]),i+1};
            }
            hmap.put(arr[i], i+1);
        }
        return new int[0];
    }

    //Better solution using Binary Search with T.C=O(nlogn) & S.C=O(1)
    static int[] twoSumBinarySearch(int[] arr, int target) {
        for(int i=0;i<arr.length;i++){
            int low=1,high=arr.length-1;
            while (low<=high) {
                int mid=(low+high)/2;
                if(arr[mid]==target-arr[i])
                    return new int[]{i+1,mid+1};
                else if(arr[mid]<target-arr[i])
                    low=mid+1;
                else
                    high=mid-1;
            }
        }
        return new int[0];
    }

    //Optimal Solution using Two Pointers with T.C=O(n) & S.C=O(1)
    static int[] twoSumTwoPointer(int[] arr, int target) {
        int left=0,right=arr.length-1;
        while (left<=right) {
            if(target<arr[left]+arr[right])
                right--;
            else if(target>arr[left]+arr[right])
                left++;
            else
                return new int[]{left+1,right+1};
        }
        return new int[0];
    }
}
