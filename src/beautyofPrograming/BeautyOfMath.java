package beautyofPrograming;

import java.util.Arrays;
import java.util.Comparator;

public class BeautyOfMath {

	/*
	 * 求二进制中1的个数 
	 */
	//leetcode 191. Number of 1 Bits
	
	/*
	 * 2.2就n的阶乘中二进制最后一位1的位置
	 */
	public static int jiechentg(int n){
		int re=0;
		while(n>0){
			n>>=1;
			re+=n;
		}		
		return re;
	}
	
	/*
	 * 2.3频率最高的元素 
	 */
	//leetcode 169. Majority Element
	//leetcode 229. Majority Element II
	
	/*
	 * 2.41到n中1出现的次数
	 */
	public static int count1(int n){
		int re=0;
		int factor=1;
		int temp;		
		int high=0;
		int low=0;
		
		while(n/factor>0){
			temp=(n/factor)%10;
			low=n-(n/factor)*factor;
			high=n/(factor*10);
			
			if(temp==0){
				re+=high*factor;
			}else if(temp==1){
				re+=(high*factor+low+1);
			}else{
				re+=(high+1)*factor;
			}
			factor*=10;
		}
		return re;
	}
	
	/*
	 * 2.5寻找最大的k个数
	 */

	/*
	 * 2.7 寻找最大公约数
	 */
	//辗转相除
	public static int gcd1(int x, int y){
		int temp=0;
		while(y!=0){
			temp=x%y;
			x=y;
			y=temp;
		}
		return x;
	}
	
	//相减，避免取模运算
	public static int gcd2(int x, int y){
		if(x<y){
			return gcd2(y, x);
		}
		if(y==0){
			return x;
		}
		return gcd2(x-y, y);
	}
	
	//前面两者结合，O(log(max(x, y)))
	private static boolean isEven(int x){//判断是否为偶数
		return (x&1)==0;
	}
	public static int gcd3(int x, int y){
		if(x<y){
			return gcd3(y, x);
		}
		if(y==0){
			return x;
		}
		
		if(isEven(x)){
			if(isEven(y)){
				return gcd3(x>>1, y>>1)<<1;
			}else{
				return gcd3(x>>1, y);
			}
		}else{
			if(isEven(y)){
				return gcd3(x, y>>1);
			}else{
				return gcd3(x-y, y);
			}
		}
	}
	
	/*
	 * 2.9 斐波那契数列
	 */
	public static int fib(int n){
		if(n==0){
			return 0;
		}
		if(n==1){
			return 1;
		}
		return fib(n-1)+fib(n-2);
	}
	
	//时间空间都是O(n)
	public static int fib2(int n){
		if(n==0){
			return 0;
		}
		if(n==1){
			return 1;
		}
		int[] fib=new int[n+1];
		fib[0]=0; fib[1]=1;
		for(int i=2; i<=n; i++){
			fib[i]=fib[i-1]+fib[i-2];
		}
		return fib[n];
	}
	
	//通过矩阵的幂次，降到O(logn)矩阵的幂次与数的幂次求法相同
	
	/*
	 * 2.10 寻找数组中最大数和最小数
	 */
	public static int[] findMaxMin(int[] nums){
		int max=Integer.MIN_VALUE;
		int min=Integer.MAX_VALUE;
		int low,high;
		for(int i=0; i<nums.length; i+=2){
			if(i+1<nums.length){
				low=Math.min(nums[i], nums[i+1]);
				high=Math.max(nums[i], nums[i+1]);
			}else{
				low=nums[i];
				high=nums[i];
			}
			max=Math.max(max, high);
			min=Math.min(min, low);
		}		
		return new int[]{min, max};
	}
	
	//分治
	public static int[] findMinMax(int[] nums, int begin, int end){
		if(end-begin<=1){
			return new int[]{Math.min(nums[begin], nums[end]), Math.max(nums[begin], nums[end])};
		}
		int mid=(begin+end)>>>1;
		int[] head=findMinMax(nums, begin, mid);
		int[] tail=findMinMax(nums, mid+1, end);
		
		int min=Math.min(head[0], tail[0]);
		int max=Math.max(head[1], tail[1]);
		
		return new int[]{min, max};
	}
	
	/*
	 * 2.12快速寻找满足条件的两个数
	 */
	//leetcode 1
	
	/*
	 * 2.13 子数组最大乘积
	 */
	
	//额外空间存储
	public static int maxPro(int[] nums){
		int len=nums.length;
		int[] f=new int[len];
		int[] b=new int[len];
		int temp=1;
		for(int i=0; i<len; i++){
			temp*=nums[i];
			f[i]=temp;
		}
		temp=1;
		for(int i=len-1; i>=0; i--){
			temp*=nums[i];
			b[i]=temp;
		}
		int re=b[1];
		for(int i=1; i<len-1; i++){
			re=Math.max(re, f[i-1]*b[i+1]);
		}
		re=Math.max(re, f[len-2]);
		return re;		
	}
	
	//
	private static int proExc(int[] nums, int n){
		int i;
		int re=1;
		for(i=0; i<nums.length; i++){
			if(nums[i]==n){
				break;
			}
			re*=nums[i];
		}
		for(int j=i+1; j<nums.length; j++){
			re*=nums[j];
		}		
		return re;
	}
	public static int maxPro2(int[] nums){
		int zeroCount=0;
		int pCount=0;
		int nCount=0;
		int pMin=Integer.MAX_VALUE;
		int nMin=Integer.MIN_VALUE;
		int nMax=0;
		for(int i=0; i<nums.length; i++){
			if(nums[i]==0){
				zeroCount++;
			}else if(nums[i]>0){
				pCount++;
				pMin=Math.min(nums[i], pMin);
			}else{
				nCount++;
				nMin=Math.max(nums[i], nMin);
				nMax=Math.min(nums[i], nMax);
			}
		}		
		if(zeroCount>1){
			return 0;
		}else{
			boolean p=isEven(nCount);
			if(zeroCount==1){
				if(p){
					return proExc(nums, 0);
				}else{
					return 0;
				}
			}else{
				if(p){
					if(pCount>0){
						return proExc(nums, pMin);
					}else{
						return proExc(nums, nMax);
					}
				}else{
					return proExc(nums, nMin);
				}
			}
		}
	}
	
	/*
	 * 2.14 数组子数组之和最大值
	 */
	//leetcode 53. Maximum Subarray
	
	/*
	 * 2.15 数组子数组之和最大值(二维)
	 */
	public static int[][] matrix;
	public static void pre(){
		int m=matrix.length;
		int n=matrix[0].length;
		for(int j=1; j<n; j++){
			matrix[0][j]+=matrix[0][j-1];
		}
		
		for(int i=1; i<m; i++){
			matrix[i][0]+=matrix[i-1][0];
			for(int j=1; j<n; j++){
				matrix[i][j]+=matrix[i-1][j]+matrix[i][j-1]-matrix[i-1][j-1];
			}
		}
	}
	
	public static int getSum(int ai, int aj, int bi, int bj){
		if(ai==0&&aj==0){
			return matrix[bi][bj];
		}else if(ai==0){
			return matrix[bi][bj]-matrix[bi][aj-1];
		}else if(aj==0){
			return matrix[bi][bj]-matrix[ai-1][bj];
		}else{
			return matrix[bi][bj]-matrix[bi][aj-1]-matrix[ai-1][bj]+matrix[ai-1][aj-1];
		}
	}
	
	public static int maxSum(int[][] A){
		if(A.length==0||A[0].length==0){
			return 0;
		}
		matrix=A;
		pre();
		int m=A.length;
		int n=A[0].length;
		int re=0;
		
		for(int ai=0; ai<m; ai++){
			for(int bi=ai; bi<m; bi++){
				int temp=getSum(ai, 0, bi, 0);
				for(int j=1; j<n; j++){
					temp=Math.max(0,  temp);
					temp+=getSum(ai, j, bi, j);
					re=Math.max(re,  temp);
				}				
			}
		}
		return re;
	}
	
	/*
	 * 2.16 数组中最长升序子序列
	 */
	//leetcode 300. Longest Increasing Subsequence
    private static int binarySearch(int[] nums, int i, int j, int target){
    	while(i<=j){
    		int mid=(i+j)>>>1;
        	if(nums[mid]==target){
        		return mid;
        	}else if(nums[mid]<target){
        		i=mid+1;
        	}else{
        		j=mid-1;
        	}
    	}
    	return ++j;
    }
    
    public static int lengthOfLIS2(int[] nums) {
    	if(nums.length<2){
    		return nums.length;
    	}
    	int len=0;
    	int[] d=new int[nums.length+1];
    	d[0]=Integer.MIN_VALUE;
    	for(int i=0; i<nums.length; i++){
    		if(nums[i]>d[len]){
    			len++;
    			d[len]=nums[i];
    		}else{
    			int j=binarySearch(d, 1, len, nums[i]);
    			d[j]=nums[i];
    		}
    		System.out.println(Arrays.toString(d));
    	}
    	return len;
    }
	/*
	 * 2.17 数组循环移位
	 */
	private static void reverse(int[] nums, int start, int end){
		while(start<end){
			int temp=nums[start];
			nums[start]=nums[end];
			nums[end]=temp;
			start++;
			end--;
		}
	}
	public static void rightShift(int[] nums, int k){
		int len=nums.length;
		k%=len;
		reverse(nums, 0, len-k-1);
		reverse(nums, len-k, len-1);
		reverse(nums, 0, len-1);
		System.out.println(Arrays.toString(nums));
		
	}
	
	/*
	 * 2.19 区间重合判断
	 */
	static class numsComparator implements Comparator<int[]>{
		@Override
		public int compare(int[] arg0, int[] arg1) {
			return (arg0[0]<arg1[0]? -1: (arg0[0]==arg1[0]? 0: 1));
		}
		
	}

	private static int lineMerge(int[][] nums){
		int len=0;
		int low=nums[0][0];
		int high=nums[0][1];
		for(int i=1; i<nums.length; i++){
			if(nums[i][0]<=high){
				high=Math.max(high, nums[i][1]);
			}else{
				nums[len][0]=low;
				nums[len][1]=high;
				len++;
				low=nums[i][0];
				high=nums[i][1];
			}
		}
		nums[len][0]=low;
		nums[len][1]=high;
		return ++len;
		
	}
	
	private static int binarySearch(int[][] nums, int len, int target){
		int i=0;
		int j=len-1;
		while(i<=j){
			int mid=(i+j)>>>1;
			if(nums[mid][0]<=target&&nums[mid][1]>=target){
				return mid;
			}else if(nums[mid][1]<target){
				i=mid+1;
			}else{
				j=mid-1;
			}
		}
		return -1;		
	}	
	
	public static boolean linesCover(int[][] nums, int[] target){
		//step1: 按区间低位进行排序
		Arrays.sort(nums, new numsComparator() );
		System.out.println(Arrays.deepToString(nums));
		//step2: 合并区间,新的区间个数为len
		int len=lineMerge(nums);
		for(int i=0; i<len; i++){
			System.out.println(Arrays.toString(nums[i]));
		}
		//step3: 二分查找
		int i=binarySearch(nums, len, target[0]);
		int j=binarySearch(nums, len, target[1]);
		//在同一区间且不为-1
		return (i!=-1&&i==j);
	}
	 
	/*
	 * 3.1 字符串移位包含  
	 */
	private static int[] getNext(String P){
		int m=P.length();
		int[] next=new int[m+1];
		next[0]=next[1]=0;
		int j=0;
		for(int i=1; i<m; i++){
			while(j>0&&P.charAt(i)!=P.charAt(j)){
				j=next[j];
			}
			if(P.charAt(i)==P.charAt(j)){
				j++;
			}
			next[i+1]=j;
		}		
		return next;
	}
	private static boolean kmp(String T, String P){
		int[] next=getNext(P);
		int j=0;
		for(int i=0; i<T.length(); i++){
			while(j>0&&T.charAt(i)!=P.charAt(j)){
				j=next[j];
			}
			if(T.charAt(i)==P.charAt(j)){
				j++;
			}
			if(j==P.length()){
				return true;
			}
		}
		return false;
	}
	public static boolean isIn(String s1, String s2){
		s1+=s1.substring(0, s2.length()-1);
		System.out.println(s1);
		return kmp(s1, s2);
	}
	
	/*
	 * 3.2 电话号码与对应英语单词
	 */
	static char[][] c={
			{' '},               //0      
			{' '},               //1
			{'a', 'b', 'c'},     //2
			{'d', 'e', 'f'},     //3
			{'g', 'h', 'i'},     //4
			{'j', 'k', 'l'},     //5		
			{'m', 'n', 'o'},     //6
			{'p', 'q', 'r', 's'},//7
			{'t', 'u', 'v'},     //8
			{'w', 'x', 'y', 'z'},//9	
	//index:  0    1    2    3
	};
	static int[] total={0, 0, 3, 3, 3, 3, 3, 4, 3, 4};
	//非递归实现
	public static void printNum(int[] nums){
		
		
		int len=nums.length;
		int[] index=new int[len];
		while(true){
			StringBuilder s=new StringBuilder();
			for(int i=0; i<len; i++){
				s.append(c[nums[i]][index[i]]);
			}
			System.out.println(s);	
			int k=len-1;//
			while(k>=0){
				if(index[k]<total[nums[k]]-1){
					index[k]++;
					break;
				}else{
					index[k]=0;
					k--;
				}
			}
			if(k<0){
				break;
			}
		}
	}
	//递归实现
	private static void printRec(int[] nums, int[] index, int k, int len){
		if(k>0){
			StringBuilder s=new StringBuilder();
			for(int i=0; i<len; i++){
				s.append(c[nums[i]][index[i]]);
			}
			System.out.println(s);	
			if(index[k]<total[k]){
				index[k]++;
				printRec(nums, index, k, len);
			}else{
				
			}
			
			
		}
	
		
	}

	public static void printNums(int[] nums){
		int[] index=new int[nums.length];
		printRec(nums, index, nums.length-1, nums.length);
	}
	public static void main(String[] args) {
		int[] nums={5,6,8,3,7,9};
		System.out.println(Arrays.toString(nums));		
		System.out.println(isIn("abcd", "cdab"));
		int[] nums2={2, 3, 4};
		printNums(nums2);
	}

	
	
}
