import java.util.Arrays;

public class BestTimeBuySellStock4 {
    public static void main(String[] args) {
        int[] prices  = {3,2,6,5,0,3};
        int k=2;
        int ans = maxProfit(prices, k);
        System.out.println("Answer: " + ans);
    }

    static int maxProfit(int[] prices, int k){

        //Using Recursion with T.C=O(n*n) & S.C=O(n)
        //return solveRecursion(prices, 0, 0, k);

        //Using Memoization with T.C=O(n*2*k)(3D array space) & S.C=O(n*2*k)+O(n)(3D array and recursion stack space)
        //int[][][] dp = new int[prices.length+1][2][k+1];
        //for(int i=0; i<prices.length; i++){
        //    for(int j=0;j<2;j++){
        //        Arrays.fill(dp[i][j], -1);
        //    }
        //}
        //return solveMemoization(prices, 0, 0, dp, k);

        //Using Tabulation with T.C=O(n*2*k)(for 3 nested loops) & S.C=O(n*2*k)(3D array space)
        //return solveTabulation(prices, k);

        //Using Space Optimization with T.C=O(n*2*k)(for 3 nested loops) & S.C=O(1)
        return solveSpaceOptimization(prices, k);

    }

    static int solveRecursion(int[] arr, int buy, int index, int cap){
        int profit = 0;
        if(arr.length == index || cap == 0) return 0;
        if(buy==0){
            profit = Math.max(-arr[index] + solveRecursion(arr, 1, index+1, cap), 0 + solveRecursion(arr, 0, index+1, cap));
        } else {
            profit = Math.max(arr[index] + solveRecursion(arr, 0, index+1, cap-1), 0 + solveRecursion(arr, 1, index+1, cap));
        }
        return profit;
    }

    static int solveMemoization(int[] arr, int buy, int index, int[][][] dp, int cap){
        int profit = 0;
        if(arr.length == index || cap == 0) return 0;
        if(dp[index][buy][cap] != -1)
            return dp[index][buy][cap];
        if(buy==0){
            profit = Math.max(-arr[index] + solveMemoization(arr, 1, index+1, dp, cap), 0 + solveMemoization(arr, 0, index+1, dp, cap));
        } else {
            profit = Math.max(arr[index] + solveMemoization(arr, 0, index+1, dp, cap-1), 0 + solveMemoization(arr, 1, index+1, dp, cap));
        }
        dp[index][buy][cap] = profit;
        return dp[index][buy][cap];
    }

    static int solveTabulation(int[] arr, int k){
        int profit = 0;
        int[][][] dp = new int[arr.length+1][2][k+1];
        for(int index=arr.length-1; index>=0; index--){
            for(int buy=0; buy<2; buy++){
                for(int cap=1; cap<k+1; cap++){
                    if(buy==0){
                        profit = Math.max(-arr[index] + dp[index+1][1][cap], 0 + dp[index+1][0][cap]);
                    } else {
                        profit = Math.max(arr[index] + dp[index+1][0][cap-1], 0 + dp[index+1][1][cap]);
                    }
                    dp[index][buy][cap]=profit;
                }
            }
        }
        return dp[0][0][k];
    }

    static int solveSpaceOptimization(int[] arr, int k){
        int profit = 0;
        int[][] current = new int[2][k+1];
        int[][] ahead = new int[2][k+1];
        for(int index=arr.length-1; index>=0; index--){
            for(int buy=0; buy<2; buy++){
                for(int cap=1; cap<k+1; cap++){
                    if(buy==0){
                        profit = Math.max(-arr[index] + ahead[1][cap], 0 + ahead[0][cap]);
                    } else {
                        profit = Math.max(arr[index] + ahead[0][cap-1], 0 + ahead[1][cap]);
                    }
                    current[buy][cap]=profit;
                }
                ahead = current.clone();
            }
        }
        return ahead[0][k];
    }
}
