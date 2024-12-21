import java.util.Arrays;

public class BestTimeBuySellStock2 {
    
    public static void main(String[] args) {
        int[] prices  = {7,1,5,3,6,4};
        int ans = maxProfit(prices);
        System.out.println("Answer: " + ans);
    }

    static int maxProfit(int[] prices){
        //Optimal Solution 1 using Single Pass Greedy 
        //return singlePass(prices);

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

    //Using Recursion with T.C=O(n^n)(exponential) & S.C=O(n) for recursive stack
    static int solveRecursion(int[] arr, int buy, int index){
        if(arr.length == index) return 0;
        int profit = 0;
        if(buy==0){
            profit = Math.max(-arr[index] + solveRecursion(arr, 1, index+1), 0 + solveRecursion(arr, 0, index+1));
        }
        if(buy==1){
            profit = Math.max(arr[index] + solveRecursion(arr, 0, index+1), 0 + solveRecursion(arr, 1, index+1));
        }
        return profit;
    }

    //Using DP Tabulation with T.C=O(n*2)(for 2D array) & S.C=O(n*2)+O(n)(for 2D array and recursive stack)
    static int solveMemoization(int[] arr, int buy, int index, int[][] dp){
        if(arr.length == index) return 0;
        int profit = 0;
        if(dp[index][buy] != -1)
            return dp[index][buy];
        if(buy==0){
            profit = Math.max(-arr[index] + solveMemoization(arr, 1, index+1, dp), 0 + solveMemoization(arr, 0, index+1, dp));
        }
        if(buy==1){
            profit = Math.max(arr[index] + solveMemoization(arr, 0, index+1, dp), 0 + solveMemoization(arr, 1, index+1, dp));
        }
        dp[index][buy] = profit;
        return profit;
    }

    //Using DP Tabulation with T.C=O(n*2)(for 2D array) & S.C=(n*2)(for external 2D array)
    static int solveTabulation(int[] arr){
        int profit = 0;
        int[][] dp = new int[arr.length+1][2];
        for(int[] row:dp) Arrays.fill(row,-1);
        dp[arr.length][0] = dp[arr.length][1] = 0;
        for(int index=arr.length-1; index>=0; index--){
            for(int buy=0; buy<2; buy++){
                if(buy==0){
                    profit = Math.max(-arr[index] + dp[index+1][1], 0 + dp[index+1][0]);
                }
                if(buy==1){
                    profit = Math.max(arr[index] + dp[index+1][0], 0 + dp[index+1][1]);
                }
                dp[index][buy] = profit;
            }
        }
        return dp[0][0];
    }

    //Optimal solution-1 using DP Space Optimization with T.C=O(n*2)(nested loop) & S.C=O(1)
    static int solveSpaceOptimization(int[] arr){
        int profit = 0;
        int[] current = new int[2];
        int[] ahead = new int[2];
        ahead[0] = ahead[1] = 0;
        for(int index=arr.length-1; index>=0; index--){
            for(int buy=0; buy<2; buy++){
                if(buy==0){
                    profit = Math.max(-arr[index] + ahead[1], 0 + ahead[0]);
                }
                if(buy==1){
                    profit = Math.max(arr[index] + ahead[0], 0 + ahead[1]);
                }
                current[buy] = profit;
            }
            ahead = current.clone();    //or Systems.arraycopy(current, 0, ahead, 0, 2)
        }
        return current[0];
    }

    //Optimal solution-2 using single pass greedy approach with T.C=O(n) & S.C=O(1)
    static int singlePass(int[] arr){
        int profit = 0;
        for(int i=1; i<arr.length; i++){
            if(arr[i]>arr[i-1])
                profit += arr[i]-arr[i-1];
        }
        return profit;
    }
}
