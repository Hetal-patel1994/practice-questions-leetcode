import java.util.Arrays;
import java.util.HashMap;

public class TwoSumVariant1 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] ans = twoSum(nums, target);
        System.out.println("Answer:" + Arrays.toString(ans));
    }

    static int[] twoSum(int[] nums, int target) {

        /* With all solution and complexities */

        /*
        // Base solution with T.C=O(n^2) & S.C=O(n)
        int[] arr=new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(target == nums[i]+nums[j]){
                    arr[0]=i;
                    arr[1]=j;
                }
            }
        }
        return arr;
        */

        // Optimized solution with T.C=O(n) & S.C=O(n)
        HashMap<Integer,Integer> hmap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int temp = target - nums[i];
            if(hmap.containsKey(temp))
                return new int[]{ hmap.get(temp), i };
            hmap.put(nums[i],i);
        }
        return null;

    }
    
}