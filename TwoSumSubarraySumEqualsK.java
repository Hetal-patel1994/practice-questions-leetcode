import java.util.HashMap;
import java.util.Map;

public class TwoSumSubarraySumEqualsK {
    public static void main(String[] args) {
        int[] arr = {1,1,1};
        int target = 2;
        int ans1 = subarraySumNested1(arr, target);
        int ans2 = subarraySumNested2(arr, target);
        int ans3 = subarraySumPreSumHashing(arr, target);
        System.out.println("This is the answer for variant 1: " + (ans1) + "-------" + (ans2) + "-------" + (ans3)  );
    }

    //Base case using Nested loop with T.C=O(n^3) & S.C=O(1)
    static int subarraySumNested1(int[] arr, int target) {
        int count=0;
        for(int i=0;i<arr.length;i++){
            for(int j=i;j<arr.length;j++){
                int sum=0;
                for(int k=i;k<=j;k++){
                    sum+=arr[k];
                }
                if(sum==target)
                    count++;
            }
        }
        return count;
    }

    //Better solution using Nested loop with T.C=O(n^2) & S.C=O(1)
    static int subarraySumNested2(int[] arr, int target) {
        int count=0;
        for(int i=0;i<arr.length;i++){
            int sum=0;
            for(int j=i;j<arr.length;j++){
                sum+=arr[j];
                if(sum==target)
                    count++;
            }
        }
        return count;
    }

    //Optimal solution using Prefix Sum Hashing with T.C=O(n) & S.C=O(n)
    static int subarraySumPreSumHashing(int[] arr, int target) {
        int presum=0, count=0;
        Map<Integer,Integer> hmap = new HashMap<>();
        hmap.put(0,1);
        for(int i=0;i<arr.length;i++){
            presum+=arr[i];
            int remove=presum-target;
            count+=hmap.getOrDefault(remove,0);
            hmap.put(presum,hmap.getOrDefault(presum,0)+1);
        }
        return count;
    }
}
