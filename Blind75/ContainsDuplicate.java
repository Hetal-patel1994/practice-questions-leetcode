import java.util.*;

public class ContainsDuplicate {
    
    public static void main(String[] args) {
        int arr[] = {1,1,1,3,3,4,3,2,4,2};
        boolean maxPro1 = containsDuplicateNested(arr);
        boolean maxPro2 = containsDuplicateSorting(arr);
        boolean maxPro3 = containsDuplicateSet(arr);
        boolean maxPro4 = containsDuplicateMap(arr);
        System.out.println("Max profit is: " + maxPro1 + "-------" + maxPro2 + "-------" + maxPro3 + "-------" + maxPro4);
    }

    //Base case using Nested loop with T.C=O(n^2) & S.C=O(1)
    static boolean containsDuplicateNested(int[] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]==arr[j])
                    return true;
            }
        }
        return false;
    }

    //Better Solution using Sorting with T.C=O(nlogn) & S.C=O(1)
    static boolean containsDuplicateSorting(int[] arr){
        Arrays.sort(arr);
        for(int i=1;i<arr.length;i++){
            if(arr[i]==arr[i-1])
                return true;
        }
        return false;
    }

    //Optimal Solution 1 using Set with T.C=O(n) & S.C=O(n)
    static boolean containsDuplicateSet(int[] arr){
        Set<Integer> hset = new HashSet<>();
        for(int i=0;i<arr.length;i++){
            if(hset.contains(arr[i]))
                return true;
            hset.add(arr[i]);
        }
        return false;
    }

    //Optimal Solution 2 using HashMap with T.C=O(n) & S.C=O(n)
    static boolean containsDuplicateMap(int[] arr){
        Map<Integer,Integer> hmap = new HashMap<>();
        for(int value:arr){
            if(hmap.containsKey(value) && hmap.get(value)>=1)
                return true;
            hmap.put(value, hmap.getOrDefault(value, 0)+1);
        }
        return false;
    }
}
