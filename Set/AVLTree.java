import java.util.ArrayList;

import AVLTree.Node;


//AVLTree基于Binary Search Tree上实现Balanced Binary Tree
public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;	//存储每一个节点的高度值，默认为1

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
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
    
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {

        if(node == null) {
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0) {
        	node.left = add(node.left, key, value);
        }else if(key.compareTo(node.key) > 0) {
        	node.right = add(node.right, key, value);
        }else {	// key.compareTo(node.key) == 0
        	node.value = value;		//相等的话更新value
        }
        
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
    private Node getNode(Node node, K key) {

        if(node == null)
            return null;

        if(key.equals(node.key)) {
            return node;
        }else if(key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        }else {	// if(key.compareTo(node.key) > 0)
        	return getNode(node.right, key);
        }   
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key) {

        Node node = getNode(root, key);
        if(node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {

        if( node == null ) {
        	return null;
        }  
        
        Node retNode;
        if( key.compareTo(node.key) < 0 ) {
            node.left = remove(node.left , key);
            retNode = node;
        }
        else if(key.compareTo(node.key) > 0 ) {
            node.right = remove(node.right, key);
            retNode = node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }

            // 待删除节点右子树为空的情况
            else if(node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }

            else {// 待删除节点左右子树均不为空的情况
	
	            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
	            // 用这个节点顶替待删除节点的位置
	            Node successor = minimum(node.right);
	            successor.right = remove(node.right, successor.key);
	            successor.left = node.left;
	
	            node.left = node.right = null;
	
	            retNode = successor;
            }
        }
        
        if(retNode == null) {
        	return null;
        }
        
        //更新height值
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        
        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        
        //平衡维护
        //LL
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
        	return rightRotate(retNode);
        }
        
        //RR
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
        	return leftRotate(retNode);
        }
        
        //LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
        	
        	retNode.left = leftRotate(retNode.left);
        	return rightRotate(retNode);
        }
        
        //RL
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
        	
        	retNode.right = rightRotate(retNode.right);
        	return leftRotate(retNode);
        }
        
        return retNode;
    }
}
