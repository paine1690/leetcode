package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class String_leetcode {
	
	//344. Reverse String   反转字符串
    public static String reverseString(String s) {
        StringBuilder re=new StringBuilder();
        for(int i=s.length()-1; i>=0; i--){
        	re.append(s.charAt(i));
        }
    	return re.toString();
    }
    
    //3. Longest Substring Without Repeating Characters 最长不重复子串
  	public static int lengthOfLongestSubstring2(String s) {
  		int[] pos=new int[256];
  		for(int i=0; i<pos.length; i++){
  			pos[i]=Integer.MAX_VALUE;
  		}
  		int re=0;
  		int count=0;
  		int start=0;
  		for(int i=0; i<s.length(); i++){
  			char chari=s.charAt(i);
  			int position=chari-' ';
  			if(i>pos[position]){//出现过
  				for(int j=start; j<pos[position]; j++){
  					pos[s.charAt(j)-' ']=Integer.MAX_VALUE;
  					count--;
  				}
  				start=pos[position]+1;
  				pos[position]=i;
  			}else{//没出现过
  				pos[position]=i;
  				count++;
  				if(count>re)
  					re=count;
  			}
  		}
  		return re;
  	}
  	
  	//125. Valid Palindrome 		判断回文字符串
  	public static boolean isPalindrome2(String s) {
  		if(s==null){
  			return true;
  		}
  		int i=0, j=s.length()-1;
  		char[] chars=s.toCharArray();
  		
  		while(i<j){
  			if(!Character.isLetterOrDigit(chars[i])){
  				i++;
  			} else if(!Character.isLetterOrDigit(chars[j])){
  				j--;
  			} else if(Character.toLowerCase(chars[i])!=Character.toLowerCase(chars[j])){
  				System.out.println(i);
  				System.out.println(j);
  				return false;
  			}else{
  				i++;
  				j--;
  			}
  		}
  		return true;
  	}
  	
  	//5. Longest Palindromic Substring	最长回文字串  Manacher算法
    private static String preProcess(String str){
    	int len=str.length();
		if(len==0){
			return "^$";
		}
		StringBuilder s=new StringBuilder("^");
		for(int i=0; i<str.length(); i++){
			s.append("#").append(str.charAt(i));
		}
		s.append("#$");
		return s.toString();
    }
    
    public static String longestPalindrome(String s) {
    	String T=preProcess(s);
    	int len=T.length();
    	int[] P=new int[len];
    	int C=0, R=0;
    	
    	for(int i=1; i<len-1; i++){
    		int j=C-(i-C);
    		int diff=R-i;
    		
    		if(diff>=0&&diff>P[j]){
    			P[i]=P[j];
    		}else{
    			P[i]=diff>=0? diff:0;
    			while(T.charAt(i+P[i]+1)==T.charAt(i-P[i]-1)){
    				P[i]++;
    			}
    			C=i;
    			R=i+P[i];
    		}
    	}
    	
    	int max=0;
		C=0;
		for(int i=1; i<len-1; i++){
			if(P[i]>max){
				max=P[i];
				C=i;
			}
		}
		int start=(C-max)/2;
		return s.substring(start, start+max);
    }

    //6. ZigZag Conversion
    public static String convert(String s, int numRows) {
    	int n=s.length();
    	if(numRows==1||numRows>=n){
    		return s;
    	}
    	else{
            int dk=numRows*2-2;
            char[] ss=s.toCharArray();
            char[] chars=new char[n];
            int k=0;
            for(int i=0; i<numRows; i++){
            	for(int j=i; j<n&&k<n; j+=dk){
            		chars[k++]=ss[j];
            		int temp=j+dk-2*i;
            		if(i>0&&i<numRows-1&&temp<n){	
            			chars[k++]=ss[temp];
            		}
            	}
            }
            String re=String.valueOf(chars);
        	return re;
    	}
    }
    
    //13. Roman to Integer
    public static int romanToInt(String s) {
    	int re=0;
    	int o=0;
        int[] step={1000, 500, 100, 50, 10, 5, 1};
        char[] add ={'M','D','C','L','X','V','I'};
        HashMap<Character, Integer> map=new HashMap<Character, Integer>();
        for(int i=0;i<step.length;i++){
        	map.put(add[i], step[i]);
        }
        char[] chars=s.toCharArray();
        for(int i=chars.length-1; i>=0; i--){
        	int n=map.get(chars[i]);
        	System.out.println(n);
        	if(n>o){
        		re+=n;
        	}else{
        		re-=n;
        	}
        	o=n;
        }
        return re;
    }
    
    //38. Count and Say
    private static String countandsay(String str){
    	StringBuilder s=new StringBuilder();
        int count=1;
        char value=' ';
        for(int i=0; i<str.length(); i++){
        	char temp=str.charAt(i);
        	if(temp==value){
        		count++;
        	}else{
        		s.append(count).append(value);
        		value=temp;
        		count=1;
        	}
        }
        return s.append(count).append(value).substring(2, s.length());
    }
    
    public static String countAndSay(int n) {
        String re="1";
        for(int i=1; i<n; i++){
        	re=countandsay(re);
        }
        return re;
    }
    
    //58. Length of Last Word
    public static int lengthOfLastWord(String s) {
        int re=0;
        int i;
        for(i=s.length()-1; i>=0; i--){
        	if(s.charAt(i)!=' ')
        		break;
        }
        for(; i>=0; i--){
        	if(s.charAt(i)!=' '){
        		re++;
        	}else{
        		return re;
        	}
        }
        return re;
    }
    
    //14. Longest Common Prefix
    public static String longestCommonPrefix(String[] strs) {
    	StringBuilder s=new StringBuilder();
    	if(strs.length==0){
    		return s.toString();
    	}
    	int len=Integer.MAX_VALUE;
        for(int i=0; i<strs.length; i++){
        	if(strs[i].length()<len)
        		len=strs[i].length();
        }
        for(int i=0; i<len; i++){
        	char temp=strs[0].charAt(i);
        	for(int j=0; j<strs.length; j++){
        		if(strs[j].charAt(i)!=temp){
        			return s.toString();
        		}
        	}
        	s.append(temp);
        }
        return s.toString();
    }
    
    //345. Reverse Vowels of a String
    private static boolean isVowel(char c){
    	return c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||c=='A'||c=='E'||c=='I'||c=='O'||c=='U';
    }
    public static String reverseVowels(String s) {
        StringBuilder re=new StringBuilder(s);
        int i=0, j=s.length()-1;
        while(i<j){
        	if(!isVowel(s.charAt(i))){
        		i++;
        	}else if(!isVowel(s.charAt(j))){
        		j--;
        	}else{
        		char temp=re.charAt(i);
        		re.replace(i, i+1, String.valueOf(re.charAt(j)));
        		re.replace(j, j+1, String.valueOf(temp));
        		i++;
        		j--;
        	}
        }
        return re.toString();
    }
    
    //71. Simplify Path
    public static String simplifyPath(String path) {
    	StringBuilder s=new StringBuilder();
    	Stack<String> stack =new Stack<String>();
        for(int i=0; i<path.length(); i++){
        	char c=path.charAt(i);
        	if(c=='/'){
        		if(s.length()>0&&!s.toString().equals(".")){
        			if(s.toString().equals("..")){
        				if(!stack.isEmpty())
        					stack.pop();
        					s.delete(0, s.length());
        			}else {
        				stack.push(s.toString());
        				s.delete(0, s.length());
        			}
        		}else 
        			s.delete(0, s.length());
        	}else{
        		s.append(c);
        	}
        }
        
        if(s.length()>0&&!s.toString().equals(".")){
			if(s.toString().equals("..")){
				if(!stack.isEmpty())
					stack.pop();
			}else {
				stack.push(s.toString());
				s.delete(0, s.length());
			}
		}else {
			s.delete(0, s.length());
		}
        
        if(stack.isEmpty()){
        	return "/";
        }
        String re="";
        while(!stack.isEmpty()){
        	re="/"+stack.pop()+re;
        }
    	return re;
    }
  	
    //151. Reverse Words in a String
    public static String reverseWords(String s) {
        StringBuilder re=new StringBuilder();
        if(s.length()==0){
        	return re.toString();
        }
        StringBuilder temp=new StringBuilder();
        int i=0;
        for(; i<s.length(); i++){
        	if(s.charAt(i)!=' '){
        		break;
        	}
        }
        for(; i<s.length(); i++){
        	if(s.charAt(i)==' '){
        		if(temp.length()>0){
        			re.insert(0, " "+temp);
            		temp.delete(0, temp.length());
        		}
        	}else{
        		temp.append(s.charAt(i));
        	}
        }
        re.insert(0, temp);
        if(re.length()>0&&re.charAt(0)==' '){
        	re.delete(0, 1);
        }
        return re.toString();
    }
    
    //22. Generate Parentheses
    private static void generate(List<String> re, int left, int right, StringBuilder s){
    	if(left==0&&right==0){
    		re.add(s.toString());
    		return;
    	}
    	if(left>0){
    		generate(re, left-1, right, new StringBuilder(s.toString()).append("("));
    	}
    	if(right>0&&left<right){
    		generate(re, left, right-1, new StringBuilder(s.toString()).append(")"));
    	}
    }
    public static List<String> generateParenthesis(int n) {
        List<String> re=new ArrayList<String>();
        generate(re, n, n, new StringBuilder(""));
        System.out.println(re.size());
    	return re;
    }
    
    
    
    //28. Implement strStr()   kmp
    private static int[] getNext(String p){
    	int[] next=new int[p.length()+1];
    	int j=0;
    	for(int i=1; i<p.length(); i++){
    		while(j>0&&p.charAt(i)!=p.charAt(j)){
    			j=next[j];
    		}
    		if(p.charAt(i)==p.charAt(j)){
    			j++;    			
    		}
    		next[i+1]=j;
    	}
    	
    	return next;
    }
    public static int strStr(String haystack, String needle) {
    	if(haystack.length()<1){
    		return 0;
    	}
    	if(needle.length()<1){
    		return -1;
    	}
    	int[] next=getNext(needle);
    	int j=0;
    	for(int i=0; i<haystack.length(); i++){
    		while(j>0&&needle.charAt(j)!=haystack.charAt(i)){
    			j=next[j];
    		}
    		if(needle.charAt(j)==haystack.charAt(i)){
    			j++;
    		}
    		if(j==needle.length()){
    			return i-j+1;
    		}
    	}    	
        return -1;
    }
    
    //43. Multiply Strings
    public static String multiply(String num1, String num2) {
        if(num1.length()==0||num2.length()==0){
        	return null;
        }
    	if(num1.equals("0")||num2.equals("0")){
    		return "0";
    	}
    	int[] re=new int[num1.length()+num2.length()+1];
    	String s1=new StringBuilder(num1).reverse().toString();
    	String s2=new StringBuilder(num2).reverse().toString();
    	
    	for(int i=0; i<s1.length(); i++){
    		int j,add=0;;
    		for(j=0; j<s2.length(); j++){
    			int mul=(s1.charAt(i)-'0')*(s2.charAt(j)-'0')+re[i+j]+add;
    			add=mul/10;
    			re[i+j]=mul%10;
    		}
    		re[i+j]=add;
    	}
    	
    	int i=re.length-1;
    	while(re[i]==0){
    		i--;
    	}
    	
    	StringBuilder s=new StringBuilder();
    	while(i>=0){
    		s.append(re[i]);
    		i--;
    	}
    	
    	return s.toString();
    }
    
    //165. Compare Version Numbers
    public static int compareVersion(String version1, String version2) {
        String[] s1=version1.split("\\.");
        String[] s2=version2.split("\\.");
        int n1=0, n2=0;
        int i=0, j=0;
        while(i<s1.length&&j<s2.length){
        	int sum=10;
        	int a=Integer.valueOf(s1[i++]);
        	int b=Integer.valueOf(s2[j++]);
        	while(sum<Math.max(a, b)){
        		sum*=10;
        	}
        	n1=n1*sum+a;
        	n2=n2*sum+b;
        }
        while(i<s1.length){
        	int sum=10;
        	int a=Integer.valueOf(s1[i++]);
        	while(sum<a){
        		sum*=10;
        	}
        	n1=n1*sum+a;
        	n2=n2*sum+0;
        }
        while(j<s2.length){
        	int sum=10;
        	int b=Integer.valueOf(Integer.valueOf(s2[j++]));
        	while(sum<b){
        		sum*=10;
        	}
        	n1=n1*sum+0;
        	n2=n2*sum+b;
        }        
        if(n1>n2){
        	return 1;
        }else if(n1<n2){
        	return -1;
        }else{
        	return 0;
        }
    }
    
    //10. Regular Expression Matching
    private static boolean match(char[] charS, char[] charP, int s, int p){
    	if(s>=charS.length&&p>=charP.length){
    		return true;
    	}
    	if(p>=charP.length){
    		return false;
    	}
    	
    	if(p<charP.length-1&&charP[p+1]=='*'){
    		if(s<charS.length&&(charS[s]==charP[p]||charP[p]=='.')){
    			return match(charS, charP, s, p+2) //0
    				||(s<charS.length&&match(charS, charP, s+1, p+2))//1
    				||(s<charS.length&&match(charS, charP, s+1, p));//n
    		}else{
    			return match(charS, charP, s, p+2);
    		}
    	}
    	if(s<charS.length&&(charS[s]==charP[p]||charP[p]=='.')){
    		return match(charS, charP, s+1, p+1);
    	}else{
    		return false;
    	}
    }
    
    public static boolean isMatch(String s, String p) {
        char[] s1=s.toCharArray();
        char[] s2=p.toCharArray();
    	return match(s1, s2, 0, 0);
    }
    
    //459. Repeated Substring Pattern
    private boolean isOK(String s, int l){
    	for(int i=0; i<l; i++){
    		for(int j=i; j<s.length(); j+=l){
    			if(s.charAt(j)!=s.charAt(i)){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public boolean repeatedSubstringPattern(String str) {
        int len=str.length();
        for(int l=1; l<len/2; l++){
        	if(len%l==0&&isOK(str, l)){
        		return true;
        	}
        }
    	return false;
    }
    
    //394. Decode String
    public static String decodeString(String s) {
        StringBuilder re=new StringBuilder();
        int i=0;
        while(i<s.length()){
        	char c=s.charAt(i);
        	if(Character.isDigit(c)){ 
        		int j=i+1, cnt=1;
        		while(Character.isDigit(s.charAt(j))){
        			j++;
        		}
        		int num=Integer.valueOf(s.substring(i, j));
        		i=++j;
        		while(j<s.length()){
        			char charJ=s.charAt(j);        			
        			if(charJ==']'){
        				cnt--;
        				if(cnt==0){
        					break;
        				}
        			}
        			if(charJ=='['){
        				cnt++;
        			}
        			j++;
        		}
        		String str=decodeString(s.substring(i, j));
        		for(int k=0; k<num; k++){
        			re.append(str);
        		}
        		i=j+1;
        	}else{
        		i++;
        		re.append(c);
        	}
        }
        return re.toString();
    }
    
    //412. Fizz Buzz
    public List<String> fizzBuzz(int n) {
        List<String> re=new ArrayList<String>();
        for(int i=1; i<=n; i++){
        	if(i%15==0){
        		re.add("FizzBuzz");
        	}else if(i%3==0){
        		re.add("Fizz");
        	}else if(i%5==0){
        		re.add("Buzz");
        	}else{
        		re.add(String.valueOf(i));
        	}
        }
        return re;
    }    

    //434. Number of Segments in a String
    public static int countSegments(String s) {
    	int re=0;
    	int i=0;
    	while(i<s.length()&&s.charAt(i)==' '){
    		i++;
    	}
    	
    	while(i<s.length()&&s.charAt(i)!=' '){
    		re++;
    		while(i<s.length()&&s.charAt(i)!=' '){
    			i++;
    		}
    		while(i<s.length()&&s.charAt(i)==' '){
    			i++;
    		}
    	}
    	return re;
    }
    

    //306. Additive Number
    private static String add(String s1, String s2){
		StringBuilder num1=new StringBuilder(s1).reverse();
		StringBuilder num2=new StringBuilder(s2).reverse();
    	StringBuilder re=new StringBuilder();
    	int flag=0;
    	int i=0, j=0;
    	while(i<num1.length()&&j<num2.length()){
    		int sum=num1.charAt(i)-'0'+num2.charAt(j)-'0'+flag;
    		flag=sum/10;
    		re.append(sum%10);
    		i++;
    		j++;
    	}
    	while(i<num1.length()){
    		int sum=num1.charAt(i)-'0'+flag;
    		flag=sum/10;
    		re.append(sum%10);
    		i++;
    	}
    	while(j<num2.length()){
    		int sum=num2.charAt(j)-'0'+flag;
    		flag=sum/10;
    		re.append(sum%10);
    		j++;
    	}
    	if(flag!=0){
    		re.append(flag);
    	}    	
    	return re.reverse().toString();
    }
    
    private static boolean isAdd(String s1, String s2, String num){
    	System.out.println(s1+" "+s2);
    	String sum=add(s1, s2);
    	if(sum.length()>num.length()){
    		return false;
    	}    	
    	String s=num.substring(0, sum.length());
    	if(sum.equals(s)){
    		if(sum.length()==num.length()){
    			return true;
    		}else{
    			return isAdd(s2, s, num.substring(s.length(), num.length()));
    		}
    	}else{
    		return false;
    	}
    }
    
    public static boolean isAdditiveNumber(String num) {
        int len=num.length();
    	for(int i=0; i<len/3; i++){
    		String s1=num.substring(0, i+1);
    		int endJ=i+(num.length()-i+1)/2;
    		for(int j=i+1; j<endJ; j++){    			
    			String s2=num.substring(i+1, j+1);
    			String s=num.substring(j+1, len);     			
    			if(isAdd(s1, s2, s)){
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    //468. Validate IP Address
    private static boolean isIPv4(String s){
        int i=0,pre=0, cnt=0;        
    	while(i<s.length()){
    		if(Character.isDigit(s.charAt(i))){
    			i++;
    		}else if(s.charAt(i)=='.'){
    			if(i==pre){
    				return false;
    			}
    			String num=s.substring(pre, i);
    			if(num.length()>3||(num.length()>1&&num.charAt(0)=='0')){
    				System.out.println(num);
    				return false;
    			}
    			if(Integer.valueOf(num)<0||Integer.valueOf(num)>255){
    				return false;
    			}
    			cnt++;    			
    			i++;
    			pre=i;
    		}else{
    			return false;
    		}
    	}
    	if(cnt==3){
    		if(s.length()==pre){
				return false;
			}
    		String num=s.substring(pre, s.length());
    		if(num.length()>3||(num.length()>0&&num.charAt(0)=='0')){
				return false;
			}
			if(Integer.valueOf(num)<0||Integer.valueOf(num)>255){
				return false;
			}
    		return true;
    	}else{
    		return false;
    	}    	
    }
    
    private static boolean isIPv6(String s){
        int i=0, pre=0, cnt=0;
        while(i<s.length()){
        	char c=s.charAt(i);
        	if(Character.isDigit(c)){
        		i++;
        	}else if(Character.isLetter(c)){
        		char temp=Character.toLowerCase(c);
        		if(temp>='a'&&temp<='f'){
        			i++;
        		}else{
        			return false;
        		}
        	}else if(c==':'){
        		if(i==pre||i-pre>4){
        			return false;
        		}
        		cnt++;
        		i++;
        		pre=i;
        	}else{
        		return false;
        	}
        }
        if(cnt==7){
        	if(s.length()==pre||s.length()-pre>4){
    			return false;
    		}
        	return true;
        }else{
        	return false;
        }
    }
    
    public static String validIPAddress(String IP) {
    	if(isIPv4(IP)){
    		return "IPv4";
    	}else if(isIPv6(IP)){
    		return "IPv6";
    	}else{
    		return "Neither";
    	}
    }
    
    //482. License Key Formatting
    public static String licenseKeyFormatting(String S, int K) {
        StringBuilder re=new StringBuilder();
        for(int i=S.length()-1, cnt=0; i>=0; i--){
        	char c=S.charAt(i);
        	if(c=='-'){
        		continue;
        	}
        	if(Character.isLowerCase(c)){
        		c-=32;
        	}
        	re.append(c);
        	if(++cnt==K){
        		cnt=0;
        		re.append('-');
        	}
        }
        re.reverse();
        if(re.length()>0&&re.charAt(0)=='-'){
        	re.deleteCharAt(0);
        }
        return re.toString();
    }
    
    //481. Magical String
    public static int magicalString(int n) {
    	if(n==0){
    		return 0;
    	}
    	if(n<=3){
    		return 1;
    	}
    	StringBuilder s=new StringBuilder("122");
        int num=2, i=2;       
        while(s.length()<n){
        	int cnt=s.charAt(i++)-'0';
        	num=3-num;
        	while(cnt>0){
        		s.append(num);
        		cnt--;
        	}
        }
        int re=0;
        for(i=0; i<n; i++){
        	if(s.charAt(i)=='1'){
        		re++;
        	}
        }
        return re;
    }
    
    //520. Detect Capital
    public static boolean detectCapitalUse(String word) {
        char c=word.charAt(0);
        boolean small=c>='a'&&c<='z';
        boolean big=false;
        for(int i=1; i<word.length(); i++){
        	c=word.charAt(i);
        	if(c>='A'&&c<='Z'){
        		big=true;
        		if(small){
        			return false;
        		}
        	}else{
        		small=true;
        		if(big){
        			return false;
        		}
        	}
        }
    	return true;
    }
    
    //541. Reverse String II
    public static String reverseStr(String s, int k) {
    	int len=s.length();
        StringBuilder re=new StringBuilder();
        for(int i=0; i<s.length(); i+=2*k){
        	int j=Math.min(i+k, len);
        	for(int index=j-1; index>=i; index--){
        		re.append(s.charAt(index));
        	}
        	for(int index=0; index<k&&j+index<s.length(); index++){
        		re.append(s.charAt(j+index));
        	}
        }   
        return re.toString();
    }
    
    //539. Minimum Time Difference
    public static int findMinDifference(List<String> timePoints) {
        int[] nums=new int[24*60];
    	int cnt=0;
        for(String s: timePoints){
        	int num=Integer.valueOf(s.substring(0, 2))*60+Integer.valueOf(s.substring(3, 5));
        	if(nums[num]==0){
        		cnt++;
        		nums[num]=1;
        	}else{
        		return 0;
        	}
        	
        }
    	List<Integer> list=new ArrayList<Integer>(cnt*2);
    	for(int i=0; i<nums.length; i++){
    		if(nums[i]>0){
    			list.add(i);
    		}
    	}
    	for(int i=0; i<nums.length/2; i++){
    		if(nums[i]>0){
    			list.add(i+1440);
    		}
    	}
    	System.out.println(list);
    	int re=Integer.MAX_VALUE;
    	for(int i=1; i<list.size(); i++){
    		re=Math.min(re, list.get(i)-list.get(i-1));
    	}
    	return re;
    }
    
    //551. Student Attendance Record I
    public boolean checkRecord(String s) {
        int cntA=0;
        for(int i=0; i<s.length(); i++){
        	if(s.charAt(i)=='A'){
        		if(++cntA>1){
        			return false;
        		}
        	}else if(s.charAt(i)=='L'&&i>1&&s.charAt(i-1)=='L'&&s.charAt(i-2)=='L'){
        		return false;
        	}
        }
        return true;
    }
    
    //557. Reverse Words in a String III
    private static void revers(char[] chars, int i, int j){
    	while(i<j){
    		char temp=chars[i];
    		chars[i]=chars[j];
    		chars[j]=temp;
    		i++;
    		j--;
    	}
    }
    
    public static String reverseWords3(String s) {
        char[] chars=s.toCharArray();
        int i=0;
        for(int j=0; j<chars.length; j++){
        	if(chars[j]==' '){
        		revers(chars, i, j-1);
        		i=j+1;
        	}
        } 
        revers(chars, i, chars.length-1);
        return new String(chars);
    }
    
    //556. Next Greater Element III
    private static void swap(char[] chars, int i, int j){
    	char temp=chars[i];
    	chars[i]=chars[j];
    	chars[j]=temp;
    }
    
    public static int nextGreaterElement(int n) {
        char[] chars=String.valueOf(n).toCharArray();        
        int i=chars.length-2, j=chars.length-1;        
        while(i>=0){
        	if(chars[i]>=chars[i+1]){
        		i--;
        	}else{
        		break;
        	}
        }
        if(i==-1){
        	return -1;
        }
        while(j>i){
        	if(chars[j]<=chars[i]){
        		j--;
        	}else{
        		break;
        	}
        }
        swap(chars, i, j);
        revers(chars, i+1, chars.length-1);  
        long re=0;
        for(i=0; i<chars.length; i++){
        	re=re*10+chars[i]-'0';
        }        
        return re>Integer.MAX_VALUE? -1: (int)re;
    }
    
    //537. Complex Number Multiplication
    public static String complexNumberMultiply(String a, String b) {
        int i=0;        
        while(i<a.length()){
        	if(a.charAt(i)=='+'){
        		break;
        	}
        	i++;
        }
        int a1=Integer.valueOf((a.substring(0, i)));
        int a2=Integer.valueOf(a.substring(i+1, a.length()-1));
    	i=0;
    	while(i<b.length()){
    		if(b.charAt(i)=='+'){
    			break;
    		}
    		i++;
    	}
    	int b1=Integer.valueOf(b.substring(0, i));
    	int b2=Integer.valueOf(b.substring(i+1, b.length()-1));    	
    	return (a1*b1-a2*b2)+"+"+(a1*b2+a2*b1)+"i";    	
    }    
    
    //583. Delete Operation for Two Strings
    public static int minDistance(String word1, String word2) {
      int m = word1.length(), n = word2.length();
      int[][] dp = new int[m + 1][n + 1];
      
      for (int i = 1; i <=m; i++) {
        for (int j = 1; j <= n; j++) {
          if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            dp[i][j] = dp[i - 1][j - 1] + 1;
          } else {
            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
          }
        }
      }
      return m + n - dp[m][n] * 2;
    }
    
    //592. Fraction Addition and Subtraction
    private static int gcd(int a, int b) {
      if (a < b) {
        return gcd(b, a);
      }
      while (b != 0) {
        int temp = a % b;
        a = b;
        b = temp;
      }
      return a;
    }
    
    private static void caculate(int[] num1, int[] num2) {
      int a2 = num1[1] * num2[1];
      int a1 = num1[0] * num2[1] + num1[1] * num2[0]; 
      int gcd = gcd(Math.abs(a1), a2);
      if (gcd != 0) {
        num1[0] = a1 / gcd;
        num1[1] = a2 / gcd;
      }      
      num2[0] = 0;
      num2[1] = 0;
    }
    
    public static String fractionAddition(String expression) {
      int[] result = new int[]{0, 1};
      int[] num = new int[]{0, 0};
      int flag = 1, index = 0;
      for (int i = 0; i < expression.length(); i++) {
        char c = expression.charAt(i);
        if (c == '+') {
          num[0] *= flag;
          caculate(result, num);
          index = 0;
          flag = 1;
        } else if (c == '-') {
          num[0] *= flag;
          caculate(result, num);
          index = 0;
          flag = -1;
        } else if (c == '/') {
          index = 1;
        } else {
          int cur = c - '0';
          num[index] = num[index] * 10 + cur;
        }        
      }
      num[0] *= flag;
      caculate(result, num);
      return result[0] + "/" + result[1];
    }
    
    public static void main(String[] args) {
        System.out.println(fractionAddition("-1/2+1/2"));
    }
}
