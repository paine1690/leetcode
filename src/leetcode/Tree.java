package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * 		树
 * 
 * 
 * @author Paine
 *
 */
public class Tree {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	//144. Binary Tree Preorder Traversal	先序遍历
    private static void preOrder(TreeNode root, List<Integer> re){
    	if(root!=null){
        	re.add(root.val);
        	preOrder(root.left, re);
        	preOrder(root.right, re);
    	}
    	
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> re=new ArrayList<Integer>();
        preOrder(root, re);
        return re;
    }
    
    //94. Binary Tree Inorder Traversal		中序遍历
    private void inOrder(TreeNode root, List<Integer> re){
    	if(root!=null){
        	inOrder(root.left, re);
        	re.add(root.val);
        	inOrder(root.right, re);
    	}
    	
    }
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> re=new ArrayList<Integer>();
    	inOrder(root, re);
        return re;
    }
    
    //145. Binary Tree Postorder Traversal		后序遍历
    private static void postOrder(TreeNode root, List<Integer> re){
    	if(root!=null){
    		postOrder(root.left, re);
    		postOrder(root.right, re);
    		re.add(root.val);
    	}
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> re=new ArrayList<Integer>();
        postOrder(root, re);
        return re;
    }
	
  //102. Binary Tree Level Order Traversal		层序遍历
    private static void level(List<List<Integer>> re, Queue<TreeNode> p, Queue<TreeNode> q){
    	List<Integer> level=new ArrayList<Integer>();
    	while(!p.isEmpty()){
    		TreeNode temp=p.poll();
    		level.add(temp.val);
    		if(temp.left!=null)		q.offer(temp.left);
    		if(temp.right!=null)	q.offer(temp.right);
    	}
    	re.add(level);
    	if(!q.isEmpty()){
    		level(re, q, p);
    	}
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> re=new ArrayList<List<Integer>>();
        if(root==null){
    		return re;
    	}
    	Queue<TreeNode> p=new LinkedList<TreeNode>();
    	Queue<TreeNode> q=new LinkedList<TreeNode>();
    	p.offer(root);
    	level(re, p, q);
    	return re;
    }
	
	//104. Maximum Depth of Binary Tree		树的深度
    public static int maxDepth(TreeNode root) {
    	if(root==null){
			return 0;
		}
		return Math.max(maxDepth(root.left)+1, maxDepth(root.right)+1);
    }
    
    //226. Invert Binary Tree		反转二叉树
    public TreeNode invertTree(TreeNode root) {
    	if(root==null){
    		return root;
    	}
        TreeNode temp=invertTree(root.left);
        root.left=invertTree(root.right);
        root.right=temp;
        return root;
    }
    
    //110. Balanced Binary Tree		平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if(root==null){
        	return true;
        }
        if(root.left==null&&root.right==null){
        	return true;
        }
    	if(Math.abs(maxDepth(root.left)-maxDepth(root.right))>1){
    		return false;
    	}
    	return isBalanced(root.left)&&isBalanced(root.right);
    }
    
    //110. Balanced Binary Tree		平衡二叉树
    private static int getDeep(TreeNode root){
    	if(root==null){
    		return 0;
    	}
    	root.val=Math.max(getDeep(root.left)+1, getDeep(root.right)+1);
    	return root.val;
    }
    
    private static boolean dfs(TreeNode root){
    	if(root==null){
        	return true;
        }
        if(root.left==null&&root.right==null){
        	return true;
        }
        int l=0, r=0;
        if(root.left!=null){
        	l=root.left.val;
        }
        if(root.right!=null){
        	r=root.right.val;
        }
    	if(Math.abs(l-r)>1){
    		return false;
    	}
    	return dfs(root.left)&&dfs(root.right);
    	
    }
    public boolean isBalanced2(TreeNode root) {
       getDeep(root);
       return dfs(root);
    }
    
    /**
     * 上面的都是一些二叉树的基础操作
     */
    
    //111. Minimum Depth of Binary Tree
    //100. Same Tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
    	if(p==null&&q==null){
    		return true;
    	}
    	if((p!=null&&q!=null)&&(p.val==q.val)){
            return (isSameTree(p.left, q.left))&&(isSameTree(p.right, q.right));
    	}
        else{
        	return false;
        }
    }
    
    //112. Path Sum
    public boolean hasPathSum(TreeNode root, int sum) {
    	if(root==null){
    		return false;
    	}
        if(root.left==null&&root.right==null){
        	return root.val==sum;
        }else {
        	return (hasPathSum(root.left, sum-root.val))||(hasPathSum(root.right, sum-root.val));
        }    	
    }
    
    //257. Binary Tree Paths
    private static List<String> add(int val, List<String> str){
    	List<String> temp=new ArrayList<String>();
    	for(String s:str){
    		temp.add(String.valueOf(val)+"->"+s);
    	}
    	return temp;
    }
    private static List<String> treePath(TreeNode root){
    	List<String> temp=new ArrayList<String>();
    	if(root.left==null&&root.right==null){
    		temp.add(String.valueOf(root.val));
    		return temp;
    	}
    	if(root.left!=null){
    		temp.addAll(add(root.val, treePath(root.left)));
    	}
    	if(root.right!=null){
    		temp.addAll(add(root.val, treePath(root.right)));
    	}
    	return temp;
    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> re=new ArrayList<String>();
        if(root!=null){
        	re=treePath(root);
        }
        return re;
    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
