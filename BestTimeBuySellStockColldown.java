import java.util.Arrays;

public class BestTimeBuySellStockColldown {
    
    public static void main(String[] args) {
        int[] prices  = {1,2,3,0,2};
        int ans = maxProfit(prices);
        System.out.println("Answer: " + ans);
    }

    static int maxProfit(int[] prices){

        //Using Recursion with T.C=O(n^n)(exponential) & S.C=O(n) for recursive stack
        //return solveRecursion(prices, 0, 0);

        //Using Memoization with T.C=O(n*2)(for 2D array) & S.C=O(n*2)+O(n)(for 2D array and recursive stack)
        //int[][] dp = new int[prices.length+1][2];
        //for(int[] row:dp) Arrays.fill(row,-1);
        //return solveMemoization(prices, 0, 0, dp);

        //Using Tabulation with T.C=O(n*2)(for 2D array) & S.C=(n*2)(for external 2D array)
        //return solveTabulation(prices);

        //Using Space Optimization with T.C=O(n*2)(nested loop) & S.C=O(1)
        return solveSpaceOptimization(prices);
    }

    static int solveRecursion(int[] arr, int buy, int index){
        if(arr.length <= index) return 0;
        int profit=0;
        if(buy==0){
            profit = Math.max(-arr[index] + solveRecursion(arr, 1, index+1), 0 + solveRecursion(arr, 0, index+1));
        } else {
            profit = Math.max(arr[index] + solveRecursion(arr, 0, index+2), 0 + solveRecursion(arr, 1, index+1));
        }
        return profit;
    }

    static int solveMemoization(int[] arr, int buy, int index, int[][] dp){
        if(arr.length <= index) return 0;
        int profit=0;
        if(dp[index][buy] != -1)
            return dp[index][buy];
        if(buy==0){
            profit = Math.max(-arr[index] + solveRecursion(arr, 1, index+1), 0 + solveRecursion(arr, 0, index+1));
        } else {
            profit = Math.max(arr[index] + solveRecursion(arr, 0, index+2), 0 + solveRecursion(arr, 1, index+1));
        }
        dp[index][buy] = profit;
        return profit;
    }

    static int solveTabulation(int[] arr){
        int profit=0;
        int[][] dp = new int[arr.length+2][2];
        for(int[] row : dp) Arrays.fill(row, -1);
        for(int index=arr.length-1; index>=0; index--){
            for(int buy=0; buy<2; buy++){
                if(buy==0){
                    profit = Math.max(-arr[index] + dp[index+1][1], 0 + dp[index+1][0]);
                } else {
                    profit = Math.max(arr[index] + dp[index+2][0], 0 + dp[index+1][1]);
                }
                dp[index][buy] = profit;
            }
        }
        return dp[0][1];
    }

    static int solveSpaceOptimization(int[] arr){
        int profit=0;
        int[] current = new int[2];
        int[] ahead = new int[2];
        int[] aheadNext = new int[2];
        for(int index=arr.length-1; index>=0; index--){
            for(int buy=0; buy<2; buy++){
                if(buy==0){
                    profit = Math.max(-arr[index] + ahead[1], 0 + ahead[0]);
                } else {
                    profit = Math.max(arr[index] + aheadNext[0], 0 + ahead[1]);
                }
                current[buy]=profit;
            }
            aheadNext = ahead.clone();
            ahead = current.clone();
        }
        return current[0];
    }
}
