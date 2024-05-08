public class BestTimeBuySellStock {
    
    public static void main(String[] args) {
        int[] prices  = {7,1,5,3,6,4};
        int ans = maxProfit(prices);
        System.out.println("Answer:" + ans);
    }

    static int maxProfit(int[] prices){

        /* Different solutions with T.C=O(n) & S.C=O(1) */

        /*
        int profit=0, min_day=Integer.MAX_VALUE;
        for(int i=0;i<prices.length;i++){
            if(prices[i]<min_day){
                min_day=prices[i];
            } else if(prices[i]-min_day > profit){
                profit = prices[i]-min_day;
            }
        }
        return profit;
        */
    
        /*
        // DP Approach
        int profit=0, min_day=prices[0];
        for(int i=1;i<prices.length;i++){
            min_day=Math.min(min_day, prices[i]);
            profit=Math.max(profit, prices[i]-min_day);
        }
        return profit;
        */

        // two Pointer
        int profit=0, left=0, right=1;
        while(right<prices.length){
            if(prices[left]<prices[right]){
                profit = Math.max(prices[right]-prices[left], profit);
                right++;
            } else {
                left=right;
                right++;
            }
        }
        return profit;
    }
}