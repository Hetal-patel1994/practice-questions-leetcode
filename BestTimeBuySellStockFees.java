import java.util.Arrays;

public class BestTimeBuySellStockFees {
    
    public static void main(String[] args) {
        int[] prices  = {1,3,2,8,4,9};
        int fee = 2;
        int ans = maxProfit(prices, fee);
        System.out.println("Answer: " + ans);
    }

    static int maxProfit(int[] prices, int fee){

        //Using Recursion with T.C=O(n^n)(exponential) & S.C=O(n) for recursive stack
        //return solveRecursion(prices, 0, 0, fee);

        //Using Memoization with T.C=O(n*2)(for 2D array) & S.C=O(n*2)+O(n)(for 2D array and recursive stack)
        //int[][] dp = new int[prices.length+1][2];
        //for(int[] row:dp) Arrays.fill(row,-1);
        //return solveMemoization(prices, 0, 0, dp, fee);

        //Using Tabulation with T.C=O(n*2)(for 2D array) & S.C=(n*2)(for external 2D array)
        //return solveTabulation(prices, fee);

        //Using Space Optimization with T.C=O(n*2)(nested loop) & S.C=O(1)
        //return solveSpaceOptimization(prices, fee);

        //Using More Space Optimization with T.C=O(n)(one loop) & S.C=O(1)
        return solveMoreSpaceOptimization(prices, fee);
    }

    static int solveRecursion(int[] arr, int buy, int index, int fee){
        int profit = 0;
        if(arr.length == index) return 0;
        if(buy==0){
            profit = Math.max(-arr[index] + solveRecursion(arr, 1, index+1, fee), 0 + solveRecursion(arr, 0, index+1, fee));
        } else {
            profit = Math.max((arr[index] - fee) + solveRecursion(arr, 0, index+1, fee), 0 + solveRecursion(arr, 1, index+1, fee));
        }
        return profit;
    }

    static int solveMemoization(int[] arr, int buy, int index, int[][] dp, int fee){
        int profit = 0;
        if(arr.length == index) return 0;
        if(dp[index][buy] != -1) 
            return dp[index][buy];
        if(buy==0){
            profit = Math.max(-arr[index] + solveMemoization(arr, 1, index+1, dp, fee), 0 + solveMemoization(arr, 0, index+1, dp, fee));
        } else {
            profit = Math.max((arr[index] - fee) + solveMemoization(arr, 0, index+1, dp, fee), 0 + solveMemoization(arr, 1, index+1, dp, fee));
        }
        dp[index][buy] = profit;
        return profit;
    }

    static int solveTabulation(int[] arr, int fee){
        int profit=0;
        int[][] dp = new int[arr.length+1][2];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        for(int index=arr.length-1; index >=0; index--){
            for(int buy=0; buy<2; buy++){
                if(buy==0){
                    profit = Math.max(-arr[index] + dp[index+1][1], 0 + dp[index+1][0]);
                } else {
                    profit = Math.max((arr[index] - fee) + dp[index+1][0], 0 + dp[index+1][1]);
                }
                dp[index][buy] = profit;
            }
        }
        return dp[0][1];
    }

    static int solveSpaceOptimization(int[] arr, int fee){
        int profit=0;
        int[] current = new int[2];
        int[] ahead = new int[2];
        for(int index=arr.length-1; index >=0; index--){
            for(int buy=0; buy<2; buy++){
                if(buy==0){
                    profit = Math.max(-arr[index] + ahead[1], 0 + ahead[0]);
                } else {
                    profit = Math.max((arr[index] - fee) + ahead[0], 0 + ahead[1]);
                }
                current[buy] = profit;
            }
            ahead = current.clone();
        }
        return current[0];
    }

    static int solveMoreSpaceOptimization(int[] arr, int fee){
        int current = 0, currentNext = 0, ahead = 0, aheadNext = 0;
        for(int index=arr.length-1; index >=0; index--){
            current = Math.max(-arr[index] + aheadNext, 0 + ahead);
            currentNext = Math.max((arr[index] - fee) + ahead, 0 + aheadNext);
            ahead = current;
            aheadNext = currentNext;
        }
        return current;
    }
}
