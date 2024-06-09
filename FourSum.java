import java.util.*;

public class FourSum {
 
    public static void main(String[] args) {
        
        int[] arr = {1,0,-1,0,-2,2};
        int target = 0;
        List<List<Integer>> ans1 = twoSumNested(arr, target);
        List<List<Integer>> ans2 = twoSumHashing(arr, target);
        List<List<Integer>> ans3 = twoSumTwoPointer(arr, target);
        System.out.println("This is the answer for variant 1: " + ans1 + "-------" + ans2 + "-------" + ans3 );
    }

    //Brute Force using Nested loop with T.C=O(n^4*logn) & S.C=O(2N)
    static List<List<Integer>> twoSumNested(int[] arr, int target) {
        Set<List<Integer>> hset = new HashSet<>();
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                for(int k=j+1;k<arr.length;k++){
                    for(int l=k+1;l<arr.length;l++){
                        if(arr[i]+arr[j]+arr[k]+arr[l]==target){
                            List<Integer> temp = Arrays.asList(arr[i],arr[j],arr[k],arr[l]);
                            temp.sort(null);
                            hset.add(temp);
                        }
                    } 
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>(hset);
        return ans;
    }

    //Better Solution using Hashing with T.C=O(n^3*logn) & S.C=O(2N)+O(N)
    static List<List<Integer>> twoSumHashing(int[] arr, int target) {
        Set<List<Integer>> hset = new HashSet<>();
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                Set<Integer> st = new HashSet<>();
                for(int k=j+1;k<arr.length;k++){
                    int fourth= target-(arr[i]+arr[j]+arr[k]);
                    if(st.contains(fourth)){
                        List<Integer> temp = Arrays.asList(arr[i],arr[j],arr[k],fourth);
                        temp.sort(null);
                        hset.add(temp);
                    }
                    st.add(arr[k]);
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>(hset);
        return ans;
    }

    //Optimal solution using Two Pointers with T.C=O(n^3) & S.C=O(1)
    static List<List<Integer>> twoSumTwoPointer(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(arr);
        for(int i=0;i<arr.length;i++){
            if(i>0 && arr[i]==arr[i-1]) continue;
            for(int j=i+1;j<arr.length;j++){
                if(j>i+1 && arr[j]==arr[j-1]) continue;
                int k=j+1;
                int l=arr.length-1;
                while(k<l){
                    long sum=arr[i];
                    sum += arr[j];
                    sum += arr[k];
                    sum += arr[l];
                    if(sum==target) {
                        List<Integer> temp = Arrays.asList(arr[i],arr[j],arr[k],arr[l]);
                        ans.add(temp);
                        k++;
                        l--;
                        while (k<l && arr[k]==arr[k-1]) k++;
                        while (k<l && arr[l]==arr[l+1]) l--;
                    }
                    else if(sum<target) k++;
                    else l--;
                }
            }
        }
        return ans;
    }
    

}
