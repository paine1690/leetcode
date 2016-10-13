package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 蓄水池取样问题
 * @author Paine
 *
 */
public class ReservoirSampling {

	
	//382. Linked List Random Node
	class Solution{
		ListNode head;
		
	    public Solution(ListNode head) {
	        this.head=head;        
	    }
	    
	    /** Returns a random node's value. */
	    public int getRandom() {
	    	ListNode head=this.head;
	    	Random r=new Random();
	    	int re=head.val;
	    	int i=1;
	        while(head.next!=null){
	        	head=head.next;        	
	        	int m=r.nextInt(i+1);
	        	if(m==0){
	        		re=head.val;
	        	}
	        	i++;
	        }
	        return re;
	    }
	}

	//398. Random Pick Index
	class Solution2 {
		int[] nums;
		Map<Integer, Integer> map=new HashMap<Integer, Integer>();
		Random r=new Random();
		
	    public Solution2(int[] nums) {
	        this.nums=nums;
	        for(int i=0; i<nums.length; i++){
	        	if(map.containsKey(nums[i])){
	        		map.put(nums[i], map.get(nums[i])+1);
	        	}else{
	        		map.put(nums[i], 1);
	        	}
	        }
	    }
	    
	    public int pick(int target) {
	        int len=map.get(target);
	        int m=r.nextInt(len);
	        int i=0;
	        for(i=0; i<nums.length; i++){
	        	if(nums[i]==target){
	        		if(m==0){
	        			break;
	        		}
	        		else{
	        			m--;
	        		}
	        	}
	        }	
	        return i;
	    }
	}

	//398. Random Pick Index
	static class Solution3 {
		int[] nums;
		Map<Integer, List<Integer>> map=new HashMap<Integer, List<Integer>>();
		Random r=new Random();
		
	    public Solution3(int[] nums) {
	    	for(int i=0; i<nums.length; i++){
	    		if(map.containsKey(nums[i])){
	    			map.get(nums[i]).add(i);
	    		}else{
	    			List<Integer> list=new ArrayList<Integer>();
	    			list.add(i);
	    			map.put(nums[i], list);
	    		}
	    	}
	    }
	    
	    public int pick(int target) {
	        List<Integer> list=map.get(target);
	        int len=list.size();
	        int m=r.nextInt(len);
	        return list.get(m);
	    }
	}
    
    
	public static void main(String[] args) {		
		Solution3 s=new Solution3(new int[]{1});
		s.pick(1);
		
	}

}
