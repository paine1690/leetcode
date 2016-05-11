package leetcode;

public class DynamicProgramming {
	
	//70. Climbing Stairs		斐波那契数列
    public static int climbStairs(int n) {
    	int a=0, b=1;
    	int re=0;
    	for(int i=1; i<=n; i++){
    		re=a+b;
    		a=b;
    		b=re;
    	}
        return re;
    }
	
    //121. Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
    	if(prices.length<2){
    		return 0;
    	}
        int re=0;
        int low=prices[0];
        for(int i=1; i<prices.length; i++){
        	if(prices[i]<low){
        		low=prices[i];
        	}else{
        		re=Math.max(re, prices[i]-low);
        	}
        }
        return re;
    }
	
    //198. House Robber   dp[i]=max(dp[i-1], dp[i-2]+num[i])
    public int rob(int[] nums) {
        if(nums.length<2){
        	return nums.length==0? 0:nums[0];
        }
    	nums[1]=Math.max(nums[0], nums[1]);
    	for(int i=2; i<nums.length; i++){
    		nums[i]=Math.max(nums[i-1], nums[i-2]+nums[i]);
    	}
    	return nums[nums.length-1];
    }
    
    //303. Range Sum Query - Immutable
    class NumArray {
    	private int[] re;
        public NumArray(int[] nums) {
        	if(nums.length>0){
        		re=new int[nums.length];
                re[0]=nums[0];
                for(int i=1; i<nums.length; i++){
                	re[i]=nums[i]+re[i-1];
                }
        	}
        }
        public int sumRange(int i, int j) {
           return i==0? re[j]:(re[j]-re[i-1]);
        }
    }
    
    //64. Minimum Path Sum
    public int minPathSum(int[][] grid) {
    	if(grid.length==0||grid[0].length==0){
        	return 0;
        }
        int m=grid.length;
        int n=grid[0].length;
        for(int i=1; i<m; i++){
        	grid[i][0]+=grid[i-1][0];
        }
        for(int i=1; i<n; i++){
        	grid[0][i]+=grid[0][i-1];
        }
        for(int i=1; i<m&&i<n; i++){
        	grid[i][i]+=Math.min(grid[i-1][i], grid[i][i-1]);
        	for(int j=i+1; j<m; j++){
        		grid[j][i]+=Math.min(grid[j-1][i], grid[j][i-1]);
        	}
        	for(int j=i+1; j<n; j++){
        		grid[i][j]+=Math.min(grid[i-1][j], grid[i][j-1]);
        	}
        }
        return grid[m-1][n-1];
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

