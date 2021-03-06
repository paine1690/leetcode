package leetcode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTracking {
	
	//46. Permutations
	private static void swap(int[] nums, int a, int b){
		int temp=nums[a];
		nums[a]=nums[b];
		nums[b]=temp;
	}
	private static void perm(int[] nums, int start, List<List<Integer>> re) {
		if(start==nums.length){
			List<Integer> temp=new ArrayList<Integer>();
			for(int i=0; i<nums.length; i++){
				temp.add(nums[i]);
			}
			re.add(temp);
		}else{
			for(int i=start; i<nums.length; i++){
				swap(nums, start, i);
				perm(nums, start+1, re);
				swap(nums, start, i);
			}
		}
	}
	public static List<List<Integer>> permute(int[] nums) {    
		List<List<Integer>> re=new ArrayList<List<Integer>>();
		perm(nums, 0, re);
		return re;
    }
	
	//47. Permutations II
	private static boolean isSwap(int[] nums, int i){
		for(int k=i+1; k<nums.length; k++){
			if(nums[i]==nums[k]){
				return false;
			}
		}
		return true;
	}
	private static void permUnique(int[] nums, int start, List<List<Integer>> re){
		List<Integer> temp=new ArrayList<Integer>();
		if(start==nums.length){
			for(int i=0; i<nums.length; i++){
				temp.add(nums[i]);
			}
			re.add(temp);
		}else{
			for(int i=start; i<nums.length; i++){
				if(isSwap(nums, i)){
					swap(nums, start, i);
					permUnique(nums, start+1, re);
					swap(nums, start, i);
				}
			}
		}
	}
	public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        permUnique(nums, 0, re);
		return re;
    }
	
	
	//131. Palindrome Partitioning
	private static boolean isPalindrome(String s, int i, int j){
		while(i<j){
			if(s.charAt(i)!=s.charAt(j)){
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	
	static List<List<String>> re;
	
	private static void parti(String s, int start, int end, List<String> list){
		if(start>=end){
			re.add(new ArrayList<String>(list));
			return;
		}
		
		for(int i=start; i<end; i++){
			if(isPalindrome(s, start, i)){
				list.add(s.substring(start, i+1));
				parti(s, i+1, end, list);
				list.remove(list.size()-1);
			}
		}
	}
	
    public static List<List<String>> partition(String s) {
        re=new ArrayList<List<String>>();
    	List<String> list=new ArrayList<String>();
    	parti(s, 0, s.length(), list);  
    	return re;
    }
	
    //17. Letter Combinations of a Phone Number
    static String[] number=new String[]{
    		"",
    		"",
    		"abc",
    		"def",
    		"ghi",
    		"jkl",
    		"mno",
    		"pqrs",
    		"tuv",
    		"wxyz",
    };
    static int[] total=new int[]{0, 0, 3, 3, 3, 3, 3, 4, 3, 4};
    
    public List<String> letterCombinations(String digits) {
        List<String> re=new ArrayList<String>();
        if(digits.length()<1){
        	return re;
        }
        int[] answer=new int[digits.length()];
        
        while(true){
        	StringBuilder s=new StringBuilder();
        	for(int i=0; i<digits.length(); i++){
        		s.append(number[digits.charAt(i)-'0'].charAt(answer[i]));
        	}
        	re.add(s.toString());
        	int k=digits.length()-1;
        	while(k>=0){
        		if(answer[k]<total[digits.charAt(k)-'0']-1){
        			answer[k]++;
        			break;
        		}else{
        			answer[k]=0;
        			k--;
        		}
        	}
        	if(k<0){
        		break;
        	}
        }
        return re;
    }
    
    //17. Letter Combinations of a Phone Number
    public static void dfsGet(List<String> re, String s, char[] chars, int start){
    	if(start>=s.length()){
    		re.add(new String(chars));
    		return;
    	}
    	System.out.println(start+""+s.length());
    	for(int i=0; i<total[s.charAt(start)-'0']; i++){
    		chars[start]=number[s.charAt(start)-'0'].charAt(i);
    		dfsGet(re, s, chars, start+1);
    	}    	
    	
    }
    
    public static List<String> letterCombinations2(String digits) {
    	List<String> re=new ArrayList<String>();
    	if(digits.length()<1){
    		return re;
    	}
    	char[] chars=new char[digits.length()];
    	dfsGet(re, digits, chars, 0);    	
    	
    	return re;    	
    }
    
    //93. Restore IP Addresses
    private static void dfsGet(List<String> re, String s, String[] strs, int index, int start){
    	if(index>=strs.length){
    		if(start>=s.length()){
    			StringBuilder temp=new StringBuilder(strs[0]);
        		for(int i=1; i<strs.length; i++){
        			temp.append(".").append(strs[i]);
        		}
        		re.add(temp.toString());
        		
    		}
    		return;
    	}
    	
    	for(int i=1; i<=3&&start+i<=s.length(); i++){
    		String temp=s.substring(start, start+i);
    		if(temp.length()==1||(Integer.valueOf(temp)<=255&&temp.charAt(0)!='0')){
    			strs[index]=temp;
        		dfsGet(re, s, strs, index+1, start+i);
    			
    		}    		
    	}    	
    	
    }
    
    public static List<String> restoreIpAddresses(String s) {
        List<String> re=new ArrayList<String>();
        String[] strs=new String[4];
        dfsGet(re, s, strs, 0, 0);
        return re;
    }
    
    //79. Word Search
    static char[][] matrix;
    static int m, n;
    private static boolean dfsSearch(String s, int index, int i, int j){
    	if(index>=s.length()){
    		return true;
    	}
    	
    	char temp=matrix[i][j];
    	matrix[i][j]='0';
    	
    	if(i-1>=0&&matrix[i-1][j]==s.charAt(index)&&dfsSearch(s, index+1, i-1, j)){
    		return true;
    	}
    	if(j-1>=0&&matrix[i][j-1]==s.charAt(index)&&dfsSearch(s, index+1, i, j-1)){
    		return true;
    	}
    	if(i+1<m&&matrix[i+1][j]==s.charAt(index)&&dfsSearch(s, index+1, i+1, j)){
    		return true;
    	}
    	if(j+1<n&&matrix[i][j+1]==s.charAt(index)&&dfsSearch(s, index+1, i, j+1)){
    		return true;
    	}    
    	matrix[i][j]=temp;
    	return false;
    }
    
    public static boolean exist(char[][] board, String word) {
        if(board.length==0||board[0].length==0||word.length()==0){
        	return false;
        }
        m=board.length;
        n=board[0].length;
    	for(int i=0; i<m; i++){
    		for(int j=0; j<n; j++){
    			if(word.charAt(0)==board[i][j]){
    				matrix=board.clone();
    				if(dfsSearch(word, 1, i, j)){
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    
    //401. Binary Watch
    private static void printTime(List<String> re, boolean[] b){
    	int min=0, hour=0, temp=1;
    	for(int i=3; i>=0; i--){
    		hour+=b[i]? temp: 0;
    		temp*=2;
    	}
    	if(hour>11){
    		return;
    	}
    	
		temp=1;
		for(int i=9; i>=4; i--){
			min+=b[i]? temp: 0;
    		temp*=2;
		}	
    	if(min>=60){
    		return;
    	}
    	DecimalFormat df = new DecimalFormat("00"); 
    	String s=hour+":"+df.format(min);
    	re.add(s);
    	
    }
    
    private static void permute(List<String> re, boolean[] b, int index, int count, int num){
    	if(index>=b.length){
    		if(count==num){
    			printTime(re, b);
    		}
    		return;
    	}
    	b[index]=false;
    	permute(re, b, index+1, count, num);
    	b[index]=true;
    	permute(re, b, index+1, count+1, num);
    }
    
    public static List<String> readBinaryWatch(int num) {
        List<String> re=new ArrayList<String>();
    	boolean[] b=new boolean[10];
    	permute(re,b, 0, 0, num);    	
    	return re;
    }
    
    //357. Count Numbers with Unique Digits
    static int count;
    private static void dfsCount(int[] nums, boolean[] isUsed, int index, int n){
    	if(index>=n){
    		count++;
    		//System.out.println(Arrays.toString(nums));
    		return;
    	}
    	
    	for(int i=0; i<10; i++){
    		if(!isUsed[i]){
    			nums[index]=i;
    			boolean flag=true;
    			if(i==0){
    				flag=false;
    				for(int j=1; j<10; j++){
    					flag|=isUsed[j];
    				}
    			}    			
    			if(flag){
    				isUsed[i]=true;
    			}
        		dfsCount(nums, isUsed, index+1, n);
        		isUsed[i]=false;
    		}
    	}
    }
    
    public static int countNumbersWithUniqueDigits(int n) {
    	count=0;
    	int[] nums=new int[n];
    	boolean[] isUsed=new boolean[10];
    	dfsCount(nums, isUsed, 0, n);
    	
    	return count;
    }
    
    //77. Combinations
    private static void dfsCombine(List<List<Integer>> re, List<Integer> list, int index, int k, int n){
    	if(list.size()>=k){
    		re.add(new ArrayList<Integer>(list));
    		return;
    	}
    	
    	for(int i=index; i<=n; i++){
    		list.add(i);    		
    		if(list.size()+n-index>=k){
        		dfsCombine(re, list, i+1, k, n);
    		}    		
    		list.remove(list.size()-1);
    	}    	
    }
    
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        if(n<=0 || n<k)  
            return re;
        List<Integer> list=new ArrayList<Integer>();
    	dfsCombine(re, list, 1, k, n);    	
    	return re;
    }
    
    //39. Combination Sum
    private static void dfsSum(List<List<Integer>> re, List<Integer> list, int start, int target, int[] nums){
    	if(target==0){
    		re.add(new ArrayList<Integer>(list));
    		return;
    	}else if(target<0){
    		return;
    	}else{
    		for(int i=start; i<nums.length; i++){
    			list.add(nums[i]);
    			dfsSum(re, list, i, target-nums[i], nums);
    			list.remove(list.size()-1);
    		}
    	}  	
    }
    
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        List<Integer> list=new ArrayList<Integer>();
        Arrays.sort(candidates);
        dfsSum(re, list, 0, target, candidates);
        return re;
    }
    
    //40. Combination Sum II
    private static void dfsSum2(List<List<Integer>> re, List<Integer> list, int start, int target, int[] nums){
    	if(target==0){
    		re.add(new ArrayList<Integer>(list));
    		return;
    	}else if(target<0){
    		return;
    	}else{
    		for(int i=start; i<nums.length; i++){    
        		if(start!=i&&nums[i]==nums[i-1]){
        			continue;
        		}
    			list.add(nums[i]);
    			dfsSum2(re, list, i+1, target-nums[i], nums);
    			list.remove(list.size()-1);
    		}
    	}  	
    }
    
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        List<Integer> list=new ArrayList<Integer>();
        Arrays.sort(candidates);
        dfsSum2(re, list, 0, target, candidates);
        return re;
    }
    
    //216. Combination Sum III
    private static void dfsSum3(List<List<Integer>> re, List<Integer> list, int start, int k, int target){
    	if(list.size()>=k){
    		if(target==0){
    			re.add(new ArrayList<Integer>(list));
    		}
    		return;
    	}
    	if(target<=0){
    		return;
    	}else{
    		for(int i=start; i<10; i++){
				list.add(i);
				dfsSum3(re, list, i+1, k, target-i);
				list.remove(list.size()-1);    			
    		}
    	}    	
    }
    
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        List<Integer> list=new ArrayList<Integer>();
        dfsSum3(re, list, 1, k, n);        
        return re;
    }
    
    //78. Subsets
    private static void dfsSub(List<List<Integer>> re, List<Integer> list, int[] nums, int start){
   		re.add(new ArrayList<Integer>(list));
    	for(int i=start; i<nums.length; i++){
    		list.add(nums[i]);
    		dfsSub(re, list, nums, i+1);
    		list.remove(list.size()-1);
    	}    	
    }
    
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        List<Integer> list=new ArrayList<Integer>();
        Arrays.sort(nums);
        dfsSub(re, list, nums, 0);
        return re;
    }
    
    //90. Subsets II
    private static void dfsSub2(List<List<Integer>> re, List<Integer> list, int[] nums, int start){
   		re.add(new ArrayList<Integer>(list));
    	for(int i=start; i<nums.length; i++){
    		if(start!=i&&nums[i]==nums[i-1]){
    			continue;
    		}
    		list.add(nums[i]);
    		dfsSub2(re, list, nums, i+1);
    		list.remove(list.size()-1);
    	}    	
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        List<Integer> list=new ArrayList<Integer>();
        Arrays.sort(nums);
        dfsSub2(re, list, nums, 0);
        return re;
    }
    
    //89. Gray Code
    public static List<Integer> grayCode(int n) {
        List<Integer> re=new ArrayList<Integer>();
        re.add(0);
        for(int i=0; i<n; i++){
        	int len=re.size()-1;
        	int temp=re.size();
        	while(len>=0){
        		re.add(re.get(len--)+temp);
        	}
        }         
        return re;
    }
    
    //37. Sudoku Solver
	private static boolean isValid(char[][] board, int m, int n){
		char c=board[m][n];		
		for(int i=0; i<9; i++){
			if(i!=m&&board[i][n]==c){
				return false;
			}
		}
		
		for(int j=0; j<9; j++){
			if(j!=n&&board[m][j]==c){
				return false;
			}
		}
		
		for(int i=m/3*3; i<m/3*3+3; i++){
			for(int j=n/3*3; j<n/3*3+3; j++){
				if((i!=m&&j!=n)&&board[i][j]==c){
					return false;
				}
			}
		}
		return true;
	}	
	
	private static boolean dfs(List<Integer> list, int index, char[][] board){
		if(index>=list.size()){
			return true;
		}
		
		int k=list.get(index);
		int i=k/9, j=k%9;
		
		for(char c='1'; c<='9'; c++){
			board[i][j]=c;
			if(isValid(board, i, j)&&dfs(list, index+1, board)){
				return true;
			}
			board[i][j]='.';
		}
		return false;
	}
	
    public static boolean solveSudoku(char[][] board) {
    	List<Integer> list=new ArrayList<Integer>();
    	for(int i=0; i<9; i++){
    		for(int j=0; j<9; j++){
    			if(board[i][j]=='.'){
    				list.add(i*9+j);
    			}
    		}
    	}
    	return dfs(list, 0, board);
    }
    
    //51. N-Queens   
    private static boolean isQueue(int[] nums, int j){
    	for(int i=0; i<j; i++){
    		if(nums[i]==nums[j]||nums[j]-nums[i]==j-i||nums[i]-nums[j]==j-i){
				return false;
			}
    	}    	
    	return true;
    }
    
    private static void dfsQueue(List<List<String>> re, int[] nums, int start, char[][] chars){
    	if(start>=nums.length){
			List<String> list=new ArrayList<String>();
			for(int i=0; i<nums.length; i++){
				chars[i][nums[i]]='Q';
				list.add(new String(chars[i]));
				chars[i][nums[i]]='.';
			}
			re.add(list);
			//System.out.println(Arrays.toString(nums));    		
    		return;
    	}
    	
    	for(int i=0; i<nums.length; i++){
    		nums[start]=i;
    		if(isQueue(nums, start)){
    			dfsQueue(re, nums, start+1, chars);
    		}
    	}
    }
    
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> re=new ArrayList<List<String>>();
        char[][] chars=new char[n][n];
        for(int i=0; i<chars.length; i++){
        	Arrays.fill(chars[i], '.');
        	//System.out.println(Arrays.toString(chars[i]));
        }
        int[] nums=new int[n];
        dfsQueue(re, nums, 0, chars);
        return re;
    }
    
    //22. Generate Parentheses
    private static void dfsCreate(List<String> re, int n, int left, int right, StringBuilder s){
    	if(left>=n&&right>=n){
    		re.add(new String(s));
    		return;
    	}
    	
    	if(left<n){
    		s.append("(");
    		dfsCreate(re, n, left+1, right, s);
    		s.deleteCharAt(s.length()-1);
    	}
    	if(right<left){
	    	s.append(")");
	    	dfsCreate(re, n, left, right+1, s);
	    	s.deleteCharAt(s.length()-1);
    	}
    	
    }
    
    public static List<String> generateParenthesis(int n) {
        List<String> re=new ArrayList<String>();
        if(n==0){
        	return re;
        }
        StringBuilder s=new StringBuilder();
        s.append("(");
        dfsCreate(re, n, 1, 0, s);
        return re;
    }
    
    //494. Target Sum
    private static int re1;
    private static void dfsFind(int[] nums, int index, int sum, int S){
    	if(index>=nums.length){
    		if(sum==S){
    			re1++;
    		}    		
    		return;
    	}
    	int num=nums[index];
    	sum+=num;
    	dfsFind(nums, index+1, sum, S);
    	sum-=num;
    	sum-=num;
    	dfsFind(nums, index+1, sum, S);
    	sum+=num;
    }    
    
    public static int findTargetSumWays(int[] nums, int S) {
    	re1=0;
        dfsFind(nums, 0, 0, S);
        return re1;
    }
    
    //526. Beautiful Arrangement
    private static int re526;
    
    private static void dfsCount(boolean[] nums, int index){
    	if(index>=nums.length){
    		re526++;
    		return;
    	}    	
    	for(int i=1; i<nums.length; i++){
    		if(!nums[i]){
    			if(i%index==0||index%i==0){
    				nums[i]=true;
    				dfsCount(nums, index+1);
    				nums[i]=false;
    			}
    		}
    	}
    }
    
    public static int countArrangement(int N) {
    	re526=0;
    	boolean nums[]=new boolean[N+1];
    	dfsCount(nums, 1);
    	return re526;
    }    
    
	public static void main(String[] args) {
		System.out.println(countArrangement(2));
		
		
		//System.out.println(solveNQueens(9));
//		char[][] c={
//				"..9748...".toCharArray(),//		"519748632",
//				"7........".toCharArray(),//		"783652419",
//				".2.1.9...".toCharArray(),//		"426139875",
//				"..7...24.".toCharArray(),//		"357986241",
//				".64.1.59.".toCharArray(),//		"264317598",
//				".98...3..".toCharArray(),//		"198524367",
//				"...8.3.2.".toCharArray(),//		"975863124",
//				"........6".toCharArray(),//		"832491756",
//				"...2759..".toCharArray()//			"641275983"
//		};
//		solveSudoku(c);
//		System.out.println(countNumbersWithUniqueDigits(0));
//		String s="010010";
//		System.out.println(restoreIpAddresses(s));
//		int[] nums={1,1,2};
//		System.out.println(permuteUnique(nums));

	}

}
