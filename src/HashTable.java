import java.util.*;

public class HashTable {
	/*
	 * 类名HashTable是按照leetcode上面的tag起的，实际用的是HashMap
	 */
	
    public static int singleNumber(int[] nums) {
    	Set<Integer> set=new HashSet<Integer>();
    	for(int i=0; i<nums.length; i++){
    		if(set.contains(nums[i])){
    			set.remove(nums[i]);
    		}else{
    			set.add(nums[i]);
    		}
    	}
    	
    	Iterator<Integer> it=set.iterator();
    	return it.next();
    }
	
    public static boolean isIsomorphic(String s, String t) {
    	if(s.length()<2){
    		return true;
    	}
    	
    	Map<Character, Integer> map1=new HashMap<Character, Integer>();
    	Map<Integer, Character> map2=new HashMap<Integer, Character>();
    	for(int i=0; i<s.length(); i++){
    		if(!map1.containsKey(s.charAt(i))){
    			if(map2.containsValue(t.charAt(i))){
    				return false;
    			}else{
    				map1.put(s.charAt(i), i);
    				map2.put(i, t.charAt(i));
    			}
    		}else{
    			if(!map2.get(map1.get(s.charAt(i))).equals(t.charAt(i))){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public static boolean isIsomorphic2(String s, String t) {
    	if(s.length()<2){
    		return true;
    	}
    	Map<Character, Character> map=new HashMap<Character, Character>();
    	Set<Character> set=new HashSet<Character>();
    	for(int i=0; i<s.length();i++){
    		if(!map.containsKey(s.charAt(i))){
    			if(set.contains(t.charAt(i))){
    				return false;
    			}else{
    				map.put(s.charAt(i), t.charAt(i));
    				set.add(t.charAt(i));
    			}
    		}else{
    			if(t.charAt(i)!=map.get(s.charAt(i))){
    				return false;
    			}
    		}
    	}
    	return true;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={1,5,6,4,1,6,4};
		String s="ab";
		String t="aa";
		System.out.println(isIsomorphic2(s, t));
		
	}

}
