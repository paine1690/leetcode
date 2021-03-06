package leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class Design {
  // 208. Implement Trie (Prefix Tree)
  class TrieNode {
    boolean isWord;
    HashMap<Character, TrieNode> nexts;

    public TrieNode() {
      nexts = new HashMap<Character, TrieNode>();
    }
  }

  class Trie {
    private TrieNode root;

    public Trie() {
      root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
      TrieNode pNode = root;
      int i = 0, len = word.length();
      char[] s = word.toCharArray();
      // traverse existing
      while (i < len) {
        TrieNode next = pNode.nexts.get(s[i]);
        if (next != null) {
          pNode = next;
          i++;
        } else {
          break;
        }
      }
      // append new nodes
      while (i < len) {
        TrieNode newNode = new TrieNode();
        pNode.nexts.put(s[i], newNode);
        pNode = newNode;
        i++;
      }
      // set word end
      pNode.isWord = true;

    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
      TrieNode pNode = root;
      int i = 0, len = word.length();
      char[] s = word.toCharArray();
      while (i < len) {
        TrieNode next = pNode.nexts.get(s[i]);
        if (next == null) {
          return false;
        }
        pNode = next;
        i++;
      }
      return pNode.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
      TrieNode pNode = root;
      int i = 0, len = prefix.length();
      char[] s = prefix.toCharArray();
      while (i < len) {
        TrieNode next = pNode.nexts.get(s[i]);
        if (next == null) {
          return false;
        }
        pNode = next;
        i++;
      }
      return true;
    }

  }


  // 211. Add and Search Word - Data structure design
  class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
      root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
      TrieNode pNode = root;
      int i = 0, len = word.length();

      while (i < len) {
        TrieNode next = pNode.nexts.get(word.charAt(i));
        if (next == null) {
          break;
        }
        pNode = next;
        i++;
      }

      while (i < len) {
        TrieNode newNode = new TrieNode();
        pNode.nexts.put(word.charAt(i), newNode);
        pNode = newNode;
        i++;
      }

      pNode.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
      return searchFrom(root, word, 0);
    }

    private boolean searchFrom(TrieNode node, String word, int index) {
      if (index == word.length()) {
        System.out.println("fsd");
        return node.isWord;
      }

      TrieNode pNode = node;
      if (word.charAt(index) != '.') {
        TrieNode next = pNode.nexts.get(word.charAt(index));
        if (next == null) {
          return false;
        } else {
          return searchFrom(next, word, index + 1);
        }
      } else {
        boolean re = false;
        java.util.Collection<TrieNode> coll = pNode.nexts.values();
        for (TrieNode tNode : coll) {
          re |= searchFrom(tNode, word, index + 1);
        }
        return re;
      }
    }
  }

  // 307. Range Sum Query - Mutable 树状数组
  class NumArray2 {
    private int[] nums;
    private int[] tree;
    private int len;

    public NumArray2(int[] nums) {
      this.nums = nums;
      len = nums.length;
      tree = new int[len + 1];
      for (int i = 0; i < nums.length; i++) {
        add(i + 1, nums[i]);
      }
    }

    public void update(int i, int val) {
      int diff = val - nums[i];
      nums[i] = val;
      add(i + 1, diff);
    }

    public int sumRange(int i, int j) {
      return sumRange(j + 1) - sumRange(i);
    }

    private int sumRange(int index) {
      int sum = 0;
      while (index > 0) {
        sum += tree[index];
        index -= lowBit(index);
      }
      return sum;
    }

    private void add(int index, int val) {
      while (index <= len) {
        tree[index] += val;
        index += lowBit(index);
      }
    }

    private int lowBit(int index) {
      return index & (-index);
    }
  }

  // 307. Range Sum Query - Mutable 线段树
  class NumArray {
    private class TreeNode {
      public int start;
      public int end;
      public int sum;
      public TreeNode left, right;

      public TreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.sum = 0;
      }
    }

    private TreeNode root;
    private int[] nums;

    public NumArray(int[] nums) {
      this.nums = nums;
      this.root = buildTree(0, nums.length - 1);
    }

    public void update(int i, int val) {
      update(root, i, val);
    }

    public int sumRange(int i, int j) {
      return sumRange(root, i, j);
    }


    private int sumRange(TreeNode root, int start, int end) {
      if (start > end) {
        return 0;
      }
      if (root.start == start && root.end == end) {
        return root.sum;
      }
      int mid = root.start + (root.end - root.start) / 2;
      if (end <= mid) {
        return sumRange(root.left, start, end);
      } else if (start > mid) {
        return sumRange(root.right, start, end);
      } else {
        return sumRange(root.left, start, mid) + sumRange(root.right, mid + 1, end);
      }
    }

    private void update(TreeNode root, int pos, int val) {
      if (root == null) {
        return;
      }
      if (root.start == pos && root.end == pos) {
        root.sum = val;
        nums[pos] = val;
        return;
      }
      int mid = root.start + (root.end - root.start) / 2;
      if (pos <= mid) {
        update(root.left, pos, val);
      } else {
        update(root.right, pos, val);
      }
      root.sum = root.left.sum + root.right.sum;
    }

    private TreeNode buildTree(int start, int end) {
      if (start > end) {
        return null;
      }
      TreeNode root = new TreeNode(start, end);
      if (start == end) {
        root.sum = nums[start];
      } else {
        int mid = start + (end - start) / 2;
        root.left = buildTree(start, mid);
        root.right = buildTree(mid + 1, end);
        root.sum = root.left.sum + root.right.sum;
      }
      return root;
    }
  }



  // 146. LRU Cache
  class Node {
    int key, val;
    Node pre, next;

    public Node(int key, int v) {
      this.key = key;
      this.val = v;
    }
  }

  class LRUCache {
    Map<Integer, Node> map;
    int cap;
    Node head, tail;

    public LRUCache(int capacity) {
      map = new HashMap<Integer, Node>();
      this.cap = capacity;
      head = null;
      tail = null;
    }

    public int get(int key) {
      Node node = map.get(key);
      if (node == null) {
        return -1;
      } else {
        afterAccess(node);
        return node.val;
      }
    }

    public void put(int key, int value) {
      Node node = map.get(key);
      if (node == null) { // put
        node = new Node(key, value);
        if (tail == null) {
          head = node;
          tail = node;
        } else {
          tail.next = node;
          node.pre = tail;
          tail = node;
        }
        map.put(key, node);
        afterInsert();
      } else {
        node.val = value;
        afterAccess(node);
      }
    }

    private void afterInsert() {
      if (map.size() > cap) {
        Node node = head;
        map.remove(head.key);
        Node after = node.next;
        after.pre = null;
        node.next = null;
        head = after;
      }
    }

    private void afterAccess(Node node) {
      if (tail != node) {
        Node pre = node.pre;
        Node after = node.next;

        after.pre = pre;
        if (pre != null) {
          pre.next = after;
        } else {
          head = after;
        }
        node.pre = tail;
        node.next = null;
        tail.next = node;
        tail = node;
      }
    }
  }

  //146. LRU Cache linkedHashMap
  class LRUCache2 extends LinkedHashMap<Integer, Integer> {
  
    private int cap;

    public LRUCache2(int cap) {
      super(cap, 0.75f, true);
      this.cap = cap;
    }

    public int get(int key) {
      Integer vaule = super.get(key);
      if (vaule == null)
        return -1;
      else
        return vaule;
    }

    public void set(int key, int value) {
      super.put(key, value);
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
      return size() > cap;
    }
  }



  public void run() {
    LRUCache l = new LRUCache(1);
    l.put(2, 1);
    System.out.println(l.get(2));
    l.put(3, 2);
    System.out.println(l.get(2));
    System.out.println(l.get(3));
    // System.out.println("fsd");
    // NumArray n=new NumArray(new int[]{1,3,5});
    // System.out.println(n.sumRange(0, 2));
    // n.update(1, 2);
    // System.out.println(n.sumRange(0, 2));
    // WordDictionary dic=new WordDictionary();
    // dic.addWord("a");
    // dic.addWord("ab");
    // System.out.println(dic.root.nexts.get('a').nexts.get('b').isWord);
  }

  // 284. Peeking Iterator
  static class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iterator = null;
    Integer next = null;

    public PeekingIterator(Iterator<Integer> iterator) {
      this.iterator = iterator;
      if (iterator.hasNext()) {
        next = iterator.next();
      }

    }

    public Integer peek() {
      return next;
    }

    @Override
    public Integer next() {
      Integer re = next;
      if (iterator.hasNext()) {
        next = iterator.next();
      } else {
        next = null;
      }
      return re;
    }

    @Override
    public boolean hasNext() {
      return next != null;
    }
  }

  // 460. LFU Cache
  static class LFUCache {
    static class Node {
      Node pre, next;
      LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
      int cnt;

      public Node(Node pre, Node next, int cnt, int key) {
        this.pre = pre;
        this.next = next;
        this.cnt = cnt;
        set.add(key);
      }
    }

    Node head = null;
    Map<Integer, Integer> valueMap;
    Map<Integer, Node> nodeMap;
    int capacity;


    public LFUCache(int capacity) {
      this.capacity = capacity;
      valueMap = new HashMap<Integer, Integer>(capacity, 1f);
      nodeMap = new HashMap<Integer, Node>(capacity, 1f);
    }

    public int get(int key) {
      if (valueMap.containsKey(key)) {
        increase(key, valueMap.get(key));
      }
      return valueMap.getOrDefault(key, -1);
    }

    private void increase(int key, int value) {
      Node node = nodeMap.get(key);
      node.set.remove(key);

      if (node.next == null) {
        node.next = new Node(node, null, 1 + node.cnt, key);
      } else if (node.next.cnt == node.cnt + 1) {
        node.next.set.add(key);
      } else {
        Node nNode = new Node(node, node.next, node.cnt + 1, key);
        node.next.pre = nNode;
        node.next = nNode;
      }

      nodeMap.put(key, node.next);
      valueMap.put(key, value);
      if (node.set.isEmpty()) {
        remove(node);
      }
    }

    private void remove(Node node) {
      if (node == head) {
        head = node.next;
      } else {
        node.pre.next = node.next;
      }
      if (node.next != null) {
        node.next.pre = node.pre;
      }
    }

    public void put(int key, int value) {
      if (this.capacity == 0) {
        return;
      }
      if (valueMap.containsKey(key)) {
        increase(key, value);
      } else {
        if (valueMap.size() == this.capacity) {
          remove();
        }
        valueMap.put(key, value);
        addNode(key);
      }

    }

    private void remove() {
      if (head == null) {
        return;
      }
      int oldest = head.set.iterator().next();
      head.set.remove(oldest);
      if (head.set.isEmpty()) {
        remove(head);
      }
      valueMap.remove(oldest);
      nodeMap.remove(oldest);
    }

    private void addNode(int key) {
      if (head == null) {
        head = new Node(null, null, 1, key);
      } else if (head.cnt == 1) {
        head.set.add(key);
      } else {
        Node nNode = new Node(null, head, 1, key);
        head.pre = nNode;
        head = nNode;
      }
      nodeMap.put(key, head);
    }
  }

  // 535. Encode and Decode TinyURL
  static class Codec {
    Map<String, String> lToS = new HashMap<String, String>();
    Map<String, String> sToL = new HashMap<String, String>();
    char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private String to62(int num) {
      StringBuilder s = new StringBuilder();
      while (num > 0) {
        s.append(chars[num % 62]);
        num /= 62;
      }
      while (s.length() < 6) {
        s.append(0);
      }
      return s.reverse().toString();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {

      if (lToS.containsKey(longUrl)) {
        return lToS.get(longUrl);
      } else {
        String shortUrl = to62(lToS.size());
        lToS.put(longUrl, shortUrl);
        sToL.put(shortUrl, longUrl);
        return shortUrl;
      }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
      return sToL.get(shortUrl);
    }
  }



  public static void main(String[] args) {
    Codec c = new Codec();
    System.out.println(c.encode("https://leetcode.com/problems/design-tinyurl"));
    System.out.println(c.encode("https://leetcode.com/problems/design-tinydurl"));

    // LFUCache l=new LFUCache(2);
    // l.put(1, 1);
    // l.put(2, 2);
    // System.out.println(l.get(1));
    // l.put(3, 3);
    // System.out.println(l.get(2));
    // System.out.println(l.get(3));
    //
    // l.put(4, 4);
    // System.out.println(l.get(1));
    // System.out.println(l.get(3));
    // System.out.println(l.get(4));
  }

}


