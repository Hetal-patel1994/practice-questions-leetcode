import java.util.*;

public class TwoSumVariant2 {
    
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        String ans = twoSum(nums, target);
        System.out.println("Answer:" + ans);
    }

    static String twoSum(int[] nums, int target) {
        
        /* With all solution and complexities */

        /*
        // Base solution with T.C=O(n^2) & S.C=O(1)
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(target == nums[i]+nums[j]){
                    return "YES";
                }
            }
        }
        return "NO";
        */

        /*
        // Better solution with T.C=O(n) & S.C=O(n)
        HashMap<Integer,Integer> hmap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int temp = target - nums[i];
            if(hmap.containsKey(temp))
                return "YES";
            hmap.put(nums[i],i);
        }
        return "NO";
        */

        
        // Optimized solution with T.C=O(n) & S.C=O(1)
        Arrays.sort(nums);
        int left=0, right=nums.length-1;
        while(left<right){
            int sum = nums[left]+nums[right];
            if(target == sum)
                return "YES";
            else if(target < sum)
                right--;
            else left++;
        }
        return "NO";
        
    }
}
