//泛型E需要具有可比较性
public class BST<E extends Comparable<E>> {
	
	//私有内部类，构造函数
	private class Node {
		public E e;
		public Node left, right;
		
		public Node(E e) {
			this.e = e;
			left = null;
			right = null;
		}
	}
	
	private Node root;
	private int size;
	
	public BST() {
		root = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	//向二分搜索树中添加新的元素e
	//这里的二分搜索树不包含重复元素
	public void add(E e) {
		//如果根节点为空，那就在根节点插入元素
		if(root == null) {
			root = new Node(e);
			size++;
		}else {
			//调用下面私有的add方法
			add(root, e);
		}
	}
	
	/**
	 * Base case分为四种：
	 * 1.           null       根节点为空
	 *              /  \
	 *            null  null
	 * 
	 * 2.             666             待插入元素也是666，那就什么都不做，直接返回
	 *               /   \
	 *             null   null
	 * 
	 * 3.              50              待插入元素是25比50小，左子树为空，这样就插入左子树
	 *                /   \
	 *               null  null 
	 * 
	 * 4.               50            待插入元素是60比50大，右子树为空，这样就插入右子树
	 *                 /  \
	 *              null   null
	 * 
	 * 第一种是根节点为空，后面三种都是根节点已经有元素，然后待插入元素与根节点的元素进行比较
	 * */
	
	//向以node为根的二分搜索树中插入元素E，递归算法
	private void add(Node node, E e) {
		
		//Base case
		if(e.equals(node.e)) {				
			return;
		}else if(e.compareTo(node.e) < 0 && node.left == null) {
			node.left = new Node(e);
			size++;
			return;
		}else if(e.compareTo(node.e) > 0 && node.right == null) {
			node.right = new Node(e);
			size++;
			return;
		}
		
		//Recursive rules
		if(e.compareTo(node.e) < 0) {
			add(node.left, e);	//如果node.left不为空，那就继续往左边找一直找到可插入的最左边的叶子节点
		}else {
			add(node.right, e);	//同样找到可插入的最右边叶子节点
		}
	}
}
