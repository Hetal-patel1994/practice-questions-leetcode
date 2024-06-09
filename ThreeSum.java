import java.util.*;

public class ThreeSum {
    
    public static void main(String[] args) {
        int[] arr = {-1,0,1,2,-1,-4};
        List<List<Integer>> ans1 = twoSumNested(arr);
        List<List<Integer>> ans2 = twoSumHashing(arr);
        List<List<Integer>> ans3 = twoSumTwoPointer(arr);
        System.out.println("This is the answer: " + ans1 + "-------" + ans2 + "-------" + ans3 );
    }

    //Base case using Nested Loop with T.C=O(n^3*logn) & S.C=O(2N)
    static List<List<Integer>> twoSumNested(int[] arr) {
        Set<List<Integer>> hset = new HashSet<>();
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                for(int k=j+1;k<arr.length;k++){
                    if(arr[i]+arr[j]+arr[k]==0){
                        List<Integer> temp = Arrays.asList(arr[i],arr[j],arr[k]);
                        temp.sort(null);
                        hset.add(temp);
                    }
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>(hset);
        return ans;
    }

    //Better solution using Hashing with T.C=O(n^2*logn) & S.C=O(2N)+O(N)
    static List<List<Integer>> twoSumHashing(int[] arr) {
        Set<List<Integer>> hset = new HashSet<>();
        for(int i=0;i<arr.length;i++){
            Set<Integer> st = new HashSet<>();
            for(int j=i+1;j<arr.length;j++){
                int third = -(arr[i]+arr[j]);
                if(st.contains(third)){
                    List<Integer> temp = Arrays.asList(arr[i],arr[j],third);
                    temp.sort(null);
                    hset.add(temp);
                }
                st.add(arr[j]);
            }
        }
        List<List<Integer>> ans = new ArrayList<>(hset);
        return ans;
    }

    //Optimal solution using Two Pointer with T.C=O(n^2) & S.C=O(1)
    static List<List<Integer>> twoSumTwoPointer(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr);
        for(int i=0;i<arr.length;i++){
            if(i>0 && arr[i]==arr[i-1]) continue;
            int j=i+1;
            int k=arr.length-1;
            while (j<k) {
                int sum=arr[i]+arr[j]+arr[k];
                if(sum<0) j++;
                else if(sum>0) k--;
                else {
                    List<Integer> temp = Arrays.asList(arr[i],arr[j],arr[k]);
                    ans.add(temp);
                    j++;
                    k--;
                    while (j<k && arr[j]==arr[j-1]) j++;
                    while (j<k && arr[k]==arr[k+1]) k--;
                }
            }
        }
        return ans;
    }
    
}
