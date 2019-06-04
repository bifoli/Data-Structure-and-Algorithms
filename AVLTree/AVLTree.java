import java.util.ArrayList;

/**
 * 如图所示，这样的一个二叉树不是一个平衡二叉树，因为平衡二叉树对于任意一个节点，
 * 左子树和右子树的高度差不能超过1
 * 
 * 括号里面标注的是每一个节点的高度
 *                    (5)12
 *                    /      \
 *                (4)8        18(2)
 *                 /    \    /
 *             (3)5    11(1) 17(1)
 *              /   \
 *          (2)4     7(1)
 *            /
 *        (1)2
 * 同时需要计算平衡因子（以下简称因子）：
 * 12的因子是左子树高度4 - 右子树高度2 = 2
 * 8的因子是左子树高度3 - 右子树高度1 = 2
 * 18的因子是左子树高度1 - 右子树高度0 = 1
 * 以此类推
 */

//AVLTree基于Binary Search Tree上实现Balanced Binary Tree
public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;	//存储每一个节点的高度值，默认为1

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    
    //判断该二叉树是否是一棵二分搜索树
    public boolean isBST() {
    	
    	//用inOrder将树中元素存储在ArrayList中，然后判断是否ascending
    	ArrayList<K> keys = new ArrayList<>();
    	inOrder(root, keys);
    	
    	for(int i = 1; i < keys.size(); i++) {
    		if(keys.get(i - 1).compareTo(keys.get(i)) > 0) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private void inOrder(Node node, ArrayList<K> keys) {
    	if(node == null) {
    		return;
    	}
    	
    	inOrder(node.left, keys);
    	keys.add(node.key);
    	inOrder(node.right, keys);
    }
    
    //判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced() {
    	return isBalanced(root);
    }
    
    //判断以node为根的二叉树是否是一棵平衡二叉树，递归算法
    private boolean isBalanced(Node node) {
    	if(node == null) {
    		return true;
    	}
    	
    	int balanceFactor = getBalanceFactor(node);
    	if(Math.abs(balanceFactor) > 1) {
    		return false;
    	}
    	return isBalanced(node.left) && isBalanced(node.right);
    }
    
    //传入一个节点，返回该节点的高度值。设置这个函数主要为了判断node为空的情况
    private int getHeight(Node node) {
    	if(node == null) {
    		return 0;
    	}
    	return node.height;
    }
    
    //获得每个节点node的平衡因子
    private int getBalanceFactor(Node node) {
    	if(node == null) {
    		return 0;
    	}
    	return getHeight(node.left) - getHeight(node.right);
    }

    // 向二分搜索树中添加新的元素(key, value)
    /**
     * 1. 右旋转LL(T1 < z < T2 < x < T3 < y < T4)
     *             y   
     *          /     \
     *         x       T4   x.right = y            x        y.left = T3              x
     *       /   \         ------------>        /     \    ------------->          /   \                
     *      z     T3                           z       y                          z     y
     *    /   \                              /   \      \                       /  \   /  \
     *   T1    T2                           T1   T2      T4                    T1   T2 T3  T4
     * 
     * 2. 左旋转RR(T4 < y < T3 < x < T1 < z < T2)
     * 
     *             y
     *          /     \
     *         T4      x         x.left = y          x         y.right = T3            x
     *               /   \      ------------>      /   \      -------------->       /     \
     *              T3    z                       y     z                          y       z
     *                   / \                     /     /  \                       / \     / \
     *                  T1  T2                  T4    T1   T2                    T4 T3   T1  T2
     * 
     * 3. LR
     *            y                                  y
     *          /   \                              /   \
     *         x     T4     对x进行左旋转           z     T4   再进行后续右旋转LL
     *       /   \         -------------->       / \        ----------------->
     *      T1    z                            x    T3
     *           /  \                         /  \
     *          T2   T3                      T1  T2
     * 
     * 4. RL
     *             y                                 y
     *          /    \       对x进行右旋转        /          \           再进行后续左旋转RR
     *         T1     x     -------------->      T1          z     ------------------->
     *              /  \                               /   \
     *             z    T4                            T2    x
     *            / \                                      / \
     *           T2  T3                                   T3  T4
     * 
     * */
    
    //右旋转的操作
    private Node rightRotate(Node y) {
    	
    	//暂存操作
    	Node x = y.left;
    	Node T3 = x.right;
    	
    	//向右旋转过程
    	x.right = y;
    	y.left = T3;
    	
    	//更新节点height, 因为T1,T2,T3,T4都是叶子节点，其高度值没有发生变化，只有x和y有变化
    	y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
    	x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
    	
    	return x;
    }
    
    //左旋转操作
    private Node leftRotate(Node y) {
    	//暂存操作
    	Node x = y.right;
    	Node T2 = x.left;
    	
    	//向左旋转操作
    	x.left = y;
    	y.right = T2;
    	
    	//更新height
    	y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
    	x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
    	
    	return x;
    }
    
    public void add(K key, V value){
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;		//相等的话更新value
        
        //更新height值
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1) {
        	System.out.println("unbalanced : " + balanceFactor);
        }
        
        //平衡维护
        //LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
        	return rightRotate(node);
        }
        
        //RR
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
        	return leftRotate(node);
        }
        
        //LR
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
        	
        	node.left = leftRotate(node.left);
        	return rightRotate(node);
        }
        
        //RL
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
        	
        	node.right = rightRotate(node.right);
        	return leftRotate(node);
        }
        
        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }
}
