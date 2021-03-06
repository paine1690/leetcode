package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Math_leetcode {

	//263. Ugly Number
    public boolean isUgly(int num) {
    	if(num<1){
        	return false;
        }
        while(num%2==0){
    		num/=2;
    	}
    	while(num%3==0){
    		num/=3;
    	}
    	while(num%5==0){
    		num/=5;
    	}
    	if(num==1){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    //204. Count Primes		素数筛法
    public int countPrimes(int n) {
        if(n<2){
            return 0;
        }
        int re=0;
        int half=n>>1;
		int sqrt=(int)Math.sqrt(n);
		boolean[] prime=new boolean[half];
		for(int i=0; i<half; i++){
			prime[i]=true;
		}
		for(int i=0; i<=sqrt; i++){
			if(prime[i]){
				for(int k=i+i+3, j=k*i+k+i; j<half; j+=k){
					//k=2*i+3,即i对应的素数，起点是k的平方，即2*j=k*k,然后依次递增k
					prime[j]=false;
				}
			}
		}
		
		for(int i=0; i<n; i++){
			if(prime[i]){
				re++;
			}
		}
		return re;
    }
    
    //326. Power of Three
    public static boolean isPowerOfThree(int n) {
    	if(n==0){
    		return false;
    	}
        double temp=Math.log(n)/Math.log(3);
        return Math.abs(temp-Math.rint(temp))<0.0000000001; 
    }
    
    //258. Add Digits
    public int addDigits(int num) {
        return (num-1)%9+1;
    }
    
    //66. Plus One
    public int[] plusOne(int[] digits) {
    	int flag=0;
    	if(++digits[digits.length-1]>9){
    		digits[digits.length-1]%=10;
    		flag=1;
    	}
        for(int i=digits.length-2; i>=0; i--){
        	if(flag==1){
        		if(++digits[i]>9){
        			digits[i]%=10;
        			flag=1;
        		}else{
        			flag=0;
        		}
        	}else{
        		break;
        	}
        }
    	if(flag==1){
    		int[] re=new int[digits.length+1];
    		re[0]=1;
    		for(int i=0; i<digits.length; i++){
    			re[i+1]=digits[i];
    		}
    		return re;
    	}
    	return digits;
    }
    
    //168. Excel Sheet Column Title
    public static String convertToTitle(int n) {
        String s="";
        while(n>0){
        	int a=(n-1)%26;
        	s=(char)('A'+a)+s;
        	n=(n-1)/26;
        }        
        return s;
    }
    
    //171. Excel Sheet Column Number
    public static int titleToNumber(String s) {
        int re=0;
        for(int i=0; i<s.length(); i++){
        	re=re*26+s.charAt(i)-'A'+1;
        }
        return re;
    }
    
    //223. Rectangle Area  计算两个矩形的重叠面积
    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int re=(C-A)*(D-B)+(G-E)*(H-F);
              
        int a1=Math.max(A, E);
        int a2=Math.min(C, G);
        int a=0,b=0;
        if(a2>a1){
        	a=a2-a1;
        }
        int b1=Math.max(B, F);
        int b2=Math.min(D, H);
        if(b2>b1){
        	b=b2-b1;
        }
        return re-a*b;
    }
    
    //202. Happy Number
    public static boolean isHappy(int n) {
        Set<Integer> set=new HashSet<Integer>();
        while(n!=1){
        	if(set.contains(n)){
        		return false;
        	}else{
        		set.add(n);
        		int temp=0;
        		while(n>0){
        			int i=n%10;
        			temp+=i*i;
        			n/=10;
        		}
        		n=temp;
        	}
        }
    	return true;
    }
    
    //67. Add Binary
    public static String addBinary(String a, String b) {
        String s="";
        int i=a.length()-1;
        int j=b.length()-1;
        int flag=0;
        while(i>=0&&j>=0){
        	int temp=a.charAt(i--)-'0'+b.charAt(j--)-'0'+flag;
        	if(temp>1){
        		temp-=2;
        		flag=1;
        	}else{
        		flag=0;
        	}
        	s=temp+s;
        }
        while(i>=0){
        	int temp=a.charAt(i--)-'0'+flag;
        	if(temp>1){
        		temp-=2;
        		flag=1;
        	}else{
        		flag=0;
        	}
        	s=temp+s;
        }
        while(j>=0){
        	int temp=b.charAt(j--)-'0'+flag;
        	if(temp>1){
        		temp-=2;
        		flag=1;
        	}else{
        		flag=0;
        	}
        	s=temp+s;
        }
        if(flag==1){
        	s=1+s;
        }
        return s;
    }
    
    //172. Factorial Trailing Zeroes
    public static int trailingZeroes(int n) {
        int re=0;
        while(n>0){
        	re+=n/5;
        	n/=5;
        }
        return re;
    }
    
    //343. Integer Break
    public static int integerBreak(int n) {
        if(n<4){
        	return n-1;
        }
        int re=1;
        while(n>4){
        	re*=3;
        	n-=3;
        }
        return re*n;
    }
    
    //50. Pow(x, n) 
    public static double myPow(double x, int n) {
        if(n<0){
        	if(n==Integer.MIN_VALUE){
        		return 1.0/myPow(x,Integer.MAX_VALUE)*x;
        	}else{
        		return 1.0/myPow(x, -n);
        	}
        }
        if(n==0){
        	return 1.0;
        }
        double re=1.0;
        for(; n>0; x*=x, n>>=1){
        	if((n&1)==1){
        		re*=x;
        	}
        }
        return re;
    }    
    
    //43. Multiply Strings
    public static String multiply(String num1, String num2) {
    	if(num1==null||num2==null||num1.length()==0||num2.length()==0){
    		return "0";
    	}
    	if(num1.equals("0")||num2.equals("0")){
    		return "0";
    	}
    	int[] re=new int[num1.length()+num2.length()];    	
    	
    	String s1=new StringBuilder(num1).reverse().toString();
    	String s2=new StringBuilder(num2).reverse().toString();
    	int flag=0;
    	
    	for(int i=0; i<s1.length(); i++){
    		int j=0;
    		for(; j<s2.length(); j++){
    			int val=(s1.charAt(i)-'0')*(s2.charAt(j)-'0')+flag+re[i+j];
    			re[i+j]=val%10;
    			flag=val/10;
    		}
    		re[i+j]=flag;
    		flag=0;
    	}
    	int i=re.length-1;
    	while(re[i]==0){
    		i--;
    	}
    	StringBuilder s=new StringBuilder();
    	while(i>=0){
    		s.append(re[i--]);
    	}
    	return s.toString();
    }
    
    //8. String to Integer (atoi)
    public static int myAtoi(String str) {
        if(str.length()==0){
            return 0;
        }
        long re=0;
        char[] chars=str.toCharArray();
        int i=0, flag=1;
        while(i<chars.length&&chars[i]==' '){
            i++;
        }
        if(chars[i]=='+'){
            i++;
        }else if(chars[i]=='-'){
            flag=-1;
            i++;
        }
        
        while(i<chars.length){
            int num=chars[i]-'0';
            if(num<0||num>9){
                break;
            }            
            re=re*10+num;    
            if(re>Integer.MAX_VALUE){
            	break;
            }
            i++;
        }        
        if(flag==1){
        	if(re>Integer.MAX_VALUE){
        		return Integer.MAX_VALUE;
        	}
        }else{
        	if(re>(long)Integer.MAX_VALUE+1){
        		return Integer.MIN_VALUE;
        	}        		
        }
        return (int)re*flag;
    }
    
    //423. Reconstruct Original Digits from English
    public static String originalDigits(String s) {
        StringBuilder re=new StringBuilder();
        
        int[] nums=new int[10];
        for(int i=0; i<s.length(); i++){
        	char c=s.charAt(i);        	
        	if(c=='z'){//   0
        		nums[0]++;
        	}else if(c=='w'){// 2
        		nums[2]++;
        	}else if(c=='u'){// 4
        		nums[4]++;
        	}else if(c=='x'){// 6
        		nums[6]++; 
        	}else if(c=='g'){// 8
        		nums[8]++;
        	}else if(c=='s'){// 6/7
        		nums[7]++;
        	}else if(c=='f'){// 4/5
        		nums[5]++; 
        	}else if(c=='h'){// 3/8
        		nums[3]++;
        	}else if(c=='i'){// 5/6/8/9
        		nums[9]++;
        	}else if(c=='o'){// 0/1/2/4
        		nums[1]++;
        	}        	
        }
        
        nums[7]-=nums[6];
        nums[5]-=nums[4];
        nums[3]-=nums[8];
        nums[9]-=(nums[5]+nums[6]+nums[8]);
        nums[1]-=(nums[0]+nums[2]+nums[4]);
       
        for(int i=0; i<10; i++){
        	for(int j=0; j<nums[i]; j++){
        		re.append(i);
        	}
        }        
        return re.toString();
    }
    
    //415. Add Strings
    public static String addStrings(String num1, String num2) {
    	int len1=num1.length(), len2=num2.length();
    	
        StringBuilder s1=new StringBuilder(num1).reverse();
        StringBuilder s2=new StringBuilder(num2).reverse();
        int[] nums=new int[Math.max(len1, len2)+1];
    	int flag=0;
    	int i=0;
    	for(i=0; i<len1&&i<len2; i++){
    		int n1=s1.charAt(i)-'0';
    		int n2=s2.charAt(i)-'0';
    		int sum=n1+n2+flag;
    		nums[i]=sum%10;
    		flag=sum/10;
    	}       
    	
    	while(i<len1){
    		int sum=s1.charAt(i)-'0'+flag;
    		nums[i++]=sum%10;
    		flag=sum/10;
    	}
    	
    	
    	while(i<len2){
    		int sum=s2.charAt(i)-'0'+flag;
    		nums[i++]=sum%10;
    		flag=sum/10;
    	}
        nums[i]=flag;
        
        StringBuilder re=new StringBuilder();
        if(nums[nums.length-1]!=0){
        	re.append(nums[nums.length-1]);
        }
        
        for(i=nums.length-2; i>=0; i--){
        	re.append(nums[i]);
        }
    	return re.toString();
    }
    
    //400. Nth Digit
    public static int findNthDigit(int n) {
        long base=9, pow=1, nTh=0;
        
        while(n>base*pow){
        	n-=base*pow;
        	nTh+=base;
        	base*=10;
        	pow++;
        }
        System.out.println(n+" "+nTh+" "+base);
        n--;
        long num=nTh+n/pow+1;
        int digit=(int) (n%pow);
        System.out.println(num+" "+digit);
        
        return String.valueOf(num).charAt(digit)-'0';
    }
    
    //29. Divide Two Integers
    public static int divide(int dividend, int divisor) {
        if(divisor==0){
        	return Integer.MAX_VALUE;
        }
        long a=Math.abs((long)dividend);
        long b=Math.abs((long)divisor);
        
        int sign=1;
        if(dividend<0){
        	sign=-sign;
        }
        if(divisor<0){
        	sign=-sign;
        }
        long re=0;
        while(a>=b){
        	long c=b;
        	int cnt=1;
        	while(a>=c){
        		re+=cnt;
        		a-=c;
        		cnt<<=1;
        		c<<=1;
        	}
        }
        
        if(re>Integer.MAX_VALUE&&sign==1){
        	return Integer.MAX_VALUE;
        }
        
        return sign==1? (int)re:-(int)re;
    }
    
    //371. Sum of Two Integers
    public int getSum(int a, int b) {
        while(b!=0){
        	int temp=a&b;
        	a^=b;
        	b=(temp<<1);
        }
    	return a;
    }
    
    //372. Super Pow
    public static int superPow(int a, int[] b) {
        int re=1;
        for(int i=0; i<b.length; i++){
        	re=superP(re, 10)*superP(a, b[i])%1337;
        }
    	return re;
    }
    
    private static int superP(int a, int b){
    	if(b==0){
    		return 1;
    	}
    	if(b==1){
    		return a%1337;
    	}
    	a%=1337;
    	return (superP(a*a, b/2)*superP(a, b%2))%1337;    	
    }
    
    //396. Rotate Function
    public static int maxRotateFunction(int[] A) {
    	int sum=0, re=0, temp=0, n=A.length;
    	
    	for(int i=0; i<A.length; i++){
    		sum+=A[i];
    		temp+=i*A[i];
    	}
    	re=temp;
    	for(int i=A.length-1; i>=0; i--){
    		temp=temp+sum-n*A[i];
    		re=Math.max(re, temp);
    	}
    	return re;
    }
    
    //405. Convert a Number to Hexadecimal
    public static String toHex(int num) {
    	if(num==0){
    		return "0";
    	}
        StringBuilder re=new StringBuilder();
        while(num!=0){
        	int temp=num&15;
        	if(temp<10){
        		re.append(temp);
        	}else{
        		re.append((char)(temp-10+'a'));
        	}
        	num>>>=4;
        }
        return re.reverse().toString();
    }
    
    //402. Remove K Digits
    public static String removeKdigits(String num, int k) {
    	if(k==num.length()){
    		return "0";
    	}
    	int len=num.length()-k;    	
    	Stack<Character> stack=new Stack<Character>();
    	for(int i=0; i<num.length(); i++){
    		char c=num.charAt(i);
    		while(!stack.isEmpty()&&c<stack.peek()&&stack.size()+num.length()-i>len){
    			stack.pop();
    		}
    		if(stack.isEmpty()||stack.size()<len){
    			stack.push(c);
    		}
    	}
    	StringBuilder s=new StringBuilder();
    	while(!stack.isEmpty()){
    		s.append(stack.pop());
    	}
    	s.reverse();
    	while(s.length()>0&&s.charAt(0)=='0'){
    		s.deleteCharAt(0);
    	}
    	return s.length()==0? "0": s.toString();
    }
    
    //365. Water and Jug Problem
    private int gcd(int a, int b){
    	if(a<b){
    		return gcd(b, a);
    	}
    	
    	while(b!=0){
    		int temp=a%b;
    		a=b;
    		b=temp;
    	}
    	return a;
    }
    
    public boolean canMeasureWater(int x, int y, int z) {
    	if(z>x+y){
    		return false;
    	}
        int gcd=gcd(x, y);        
    	return gcd!=0? z%gcd==0: z==0;
    }
    
    //453. Minimum Moves to Equal Array Elements
    public int minMoves(int[] nums) {
        int re=0;
        Arrays.sort(nums);
        for(int i=nums.length-1; i>0; i--){
        	re+=nums[i]-nums[0];
        }
        return re;
    }
    
    //453. Minimum Moves to Equal Array Elements
    public int minMoves1_1(int[] nums) {
        int min=Integer.MAX_VALUE, sum=0;
        for(int num: nums){
        	sum+=num;
        	min=Math.min(min, num);
        }
        return sum-min*nums.length;
    }
    
    //462. Minimum Moves to Equal Array Elements II
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int i=0, j=nums.length-1, re=0;
        while(i<j){
        	re+=nums[j]-nums[i];
        	i++;
        	j--;
        }
    	return re;
    }
    
    //469. Convex Polygon
    private int product(List<Integer> a, List<Integer> b, List<Integer> c){
    	int re=(b.get(0)-a.get(0))*(c.get(1)-a.get(1))
    			-(c.get(0)-a.get(0))*(b.get(1)-a.get(1));    	
    	return re==0? 0: re>0? 1: -1;
    }
    
    public boolean isConvex(List<List<Integer>> points) {        
    	int size=points.size();
    	int pre=0;
    	for(int i=0; i<points.size(); i++){
    		int product=product(points.get(i), points.get((i+1)%size), points.get((i+2)%size));
    		if(product==0){
    			continue;
    		}
    		if(pre!=0&&product!=pre){
    			return false;
    		}
    		pre=product;
    	}
    	return true;
    }
    
    //463. Island Perimeter
    public static int islandPerimeter(int[][] grid) {        
        if(grid.length==0||grid[0].length==0){
        	return 0;
        }
        int re=0, m=grid.length, n=grid[0].length;
        
        for(int i=0; i<m; i++){
        	for(int j=0; j<n; j++){
        		if(grid[i][j]==1){
        			re+=4;
        			if(i>0&&grid[i-1][j]==1){
            			re--;
            		}
            		if(j>0&&grid[i][j-1]==1){
            			re--;
            		}
            		if(i<m-1&&grid[i+1][j]==1){
            			re--;
            		}
            		if(j<n-1&&grid[i][j+1]==1){
            			re--;
            		}
        		}        		
        	}
        }
        
        return re;
    }

    //413. Arithmetic Slices
    public int numberOfArithmeticSlices(int[] A) {
        if(A.length<3){
        	return 0;
        }
    	int re=0, cnt=0;
    	for(int i=2; i<A.length; i++){
    		if(A[i]-A[i-1]==A[i-1]-A[i-2]){
    			re+=++cnt;
    		}else{
    			cnt=0;
    		}
    	}
    	return re;
    }
    
    //60. Permutation Sequence
    public static String getPermutation(int n, int k) {
    	StringBuilder re=new StringBuilder();
    	List<Integer> list=new ArrayList<Integer>(n);
    	for(int i=1; i<=n; i++){
    		list.add(i);
    	}
    	int factor=n, sum=1;
    	while(factor!=0){
    		sum*=factor--;
    	}
    	factor=n;
    	sum/=factor;
    	
    	while(k>1){    
    		int index=(k-1)/sum;  
    		re.append(list.remove(index));
    		k-=index*sum;
    		sum/=--factor;
    	}
    	while(!list.isEmpty()){
    		re.append(list.remove(0));
    	}
    	return re.toString();
    }
    
    //233. Number of Digit One
    public static int countDigitOne(int n) {
    	long re=0, last=n, next=0,factor=1;
    	long cur=0;
    	while(n>0){
    		last=n/10;
    		next+=cur*factor/10;
    		cur=n-last*10;
    		n=(int) last;
    		if(cur>1){
    			re+=factor*(last+1);
    		}else if(cur==1){
    			re+=factor*last+next+1;
    		}else{
    			re+=factor*last;
    		}
    		System.out.println(re);
    		factor*=10;
    	}
    	return (int) re;
    }
    
    //386. Lexicographical Numbers
    private static void add(int n, int num, List<Integer> re){
    	if(num>n){
    		return;
    	}
    	re.add(num);
    	add(n, num*10, re);
    	if(num%10!=9){
    		add(n, num+1, re);
    	}
    }
    
    public static List<Integer> lexicalOrder(int n) {
        List<Integer> re=new ArrayList<Integer>(n);        
        
		add(n, 1, re);
		
        return re;
    }
    
    //492. Construct the Rectangle
    public int[] constructRectangle(int area) {
		int sqrt=(int) Math.sqrt(area);
		if(area%sqrt==0){
			return new int[]{area/sqrt, sqrt};
		}
		while(sqrt>1){
			sqrt--;
			if(area%sqrt==0){
				return new int[]{area/sqrt, sqrt};
			}
		}
		return new int[]{area, 1};
	}
   
    //504. Base 7
    public static String convertToBase7(int num) {
        StringBuilder s=new StringBuilder();
        if(num==0){
        	return "0";
        }
        boolean flag=false;
    	if(num<0){
    		num=-num;    	
    		flag=true;
    	}
    	
    	while(num!=0){
    		s.append(num%7);
    		num/=7;
    	}
    	if(flag){
    		s.append('-');
    	}
    	return s.reverse().toString();
    }

	public static void main(String[] args) {
		System.out.println(convertToBase7(100));
		
	}
}

