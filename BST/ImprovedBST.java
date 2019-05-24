import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//泛型E需要具有可比较性
public class ImprovedBST<E extends Comparable<E>> {
	
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
	
	public ImprovedBST() {
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
		
		root = add(root, e);
	}
	
	//向以node为根的二分搜索树中插入元素E，递归算法
	//返回插入新节点后二分搜索树的根
	private Node add(Node node, E e) {
		
		//Base case
		if(node == null) {
			size++;
			return new Node(e);
		}
		
		//Recursive rules
		if(e.compareTo(node.e) < 0) {
			node.left = add(node.left, e);
		}else if(e.compareTo(node.e) > 0) {
			node.right = add(node.right, e);
		}
		
		return node;
	}
	
	//查看二分搜索树中是否包含元素e
	public boolean contains(E e) {
		return contains(root, e);
	}
	
	//查看以node为根的二分搜索树中是否包含元素e，递归算法
	private boolean contains(Node node, E e) {
		
		//base case
		if(node == null) {
			return false;
		}
		
		//recursive rules
		if(e.compareTo(node.e) == 0) {
			return true;
		}else if(e.compareTo(node.e) < 0) {
			return contains(node.left, e);
		}else {
			return contains(node.right, e);
		}
	}
	
	//二分搜索树的前序遍历
	public void preOrder() {
		preOrder(root);
	}
	
	//前序遍历以node为根的二分搜索树，递归算法
	private void preOrder(Node node) {
		
		if(node == null) {
			return;
		}
		
		System.out.print(node.e);
		preOrder(node.left);
		preOrder(node.right);
	}
	
	//二分搜索树的非递归(non-recursive)前序遍历
	public void preOrderNR() {
		Stack<Node> stack = new Stack<>();
		stack.push(root);				//第一步，把root压进栈
		while(!stack.isEmpty()) {
			Node cur = stack.pop();
			System.out.print(cur.e);	//把根节点打印出来
			
			if(cur.right != null) {
				stack.push(cur.right);	//再判断左右子树是否为空，这里注意因为是栈结构，所以要先把右子树压栈再把左子树压栈，这样左子树就会先出栈
			}
			
			if(cur.left != null) {
				stack.push(cur.left);
			}
		}
		
	}
	
	//二分搜索树的中序遍历
	public void inOrder() {
		inOrder(root);
	}
	
	//中序遍历以node为根的二分搜索树，递归算法
	private void inOrder(Node node) {
		if(node == null) {
			return;
		}
		
		inOrder(node.left);
		System.out.print(node.e);
		inOrder(node.right);
	}
	
	//二分搜索树的后序遍历
	public void postOrder() {
		postOrder(root);
	}
	
	//后序遍历以node为根的二分搜索树，递归算法
	private void postOrder(Node node) {
		if(node == null) {
			return;
		}
		
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.e);
	}
	
	//二分搜索树的层序遍历(BFS)
	public void levelOrder() {
		
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		//先让头节点入队，然后再头节点出队的同时，把左右子树的节点分别入队
		//谁出队，就把它的左右节点入队，直到队列为空
		while(!q.isEmpty()) {
			Node cur = q.remove();
			System.out.println(cur.e);
			
			if(cur.left != null) {
				q.add(cur.left);
			}
			
			if(cur.right != null) {
				q.add(cur.right);
			}
		}
	}
	
	//寻找二分搜索树的最小元素
	public E minimum() {
		if(size == 0) {
			throw new IllegalArgumentException("BST is empty!");
		}
		
		return minimum(root).e;
	}
	
	//返回以node为根的二分搜索树的最小值所在的节点
	private Node minimum(Node node) {
		//最小节点一定在左子树的左子树的左子树......
		//Base case
		if(node.left == null) {
			return node;
		}
		
		//Recursive rules
		return minimum(node.left);
	}
	
	//寻找二分搜索树的最大元素
	public E maximum() {
		if(size == 0) {
			throw new IllegalArgumentException("BST is empty!");
		}
		
		return maximum(root).e;
	}
	
	//返回以node为根的二分搜索树的最大值所在的节点
	private Node maximum(Node node) {
		
		//base case
		if(node.right == null) {
			return node;
		}
		
		//recursive rules
		return maximum(node.right);
	}
	
	/**
	 * 有两种情况：
	 * 
	 * 第一种（最小值在叶子节点处）
	 *                     20                                 20
	 *                   /     \                              /   \
	 *                  15      58   ---->                   15    58
	 *                /    \		                  \	
	 *               12     16		                  16
	 * 这种情况只需要直接删除12所在的节点就可以了      
	 *         
	 * 第二种（最小值不在叶子节点除）
	 * 	                41		          41
	 * 	              /     \                  /     \
	 *                   22      58   ---->      33       58
	 *                    \		               \		
	 *                     33		         37
	 *                       \
	 *                       37			
	 * 
	 * 这种情况需要把22所在的节点删除，然后把右子树33拼接到22所在的节点
	 * 其实第一种和第二种情况类似，只不过第一种情况是把12的右子树null拼接到12所在的节点
	 * 
	 * */
	//从二分搜索树中删除最小值所在的节点，返回最小值
	public E removeMin() {
		E ret = minimum();	//调用minimum函数
		root = removeMin(root);
		return ret;
	}
	
	//删除掉以node为根的二分搜索树中的最小节点
	//返回删除节点后新的二分搜索树的根
	private Node removeMin(Node node) {
		
		//base case: 如果当前节点就是要找的最小值，那么要判断当前节点的右子树是否有元素，把右子树拼接上去。如果右子树也是空，那也就把空拼接上去
		if(node.left == null) {
			Node rightNode = node.right;	//把当前节点的右子树储存起来
			node.right = null;				//手动清空当前节点的右子树
			size--;							//remove和add操作都要记得对size进行变化
			return rightNode;				//把右子树拼接上去
		}
		
		//recursive rules
		node.left = removeMin(node.left);
		return node;
	}
	
	//从二分搜索树中删除最大值所在节点
	public E removeMax() {
		E ret = maximum();
		root = removeMax(root);
		return ret;
	}
	
	//删除掉以node为根的二分搜索树中的最大节点
	//返回删除节点后新的二分搜索树的根
	private Node removeMax(Node node) {
		
		if(node.right == null) {
			Node leftNode = node.left;
			node.left = null;
			size--;
			return leftNode;
		}
		
		node.right = removeMax(node.right);
		return node;
	}
	
	/**
	 * 二分搜索树删除任意节点
	 * 
	 * 第一种（删除只有左孩子的节点，58所在的节点）
	 *                       41                       41
	 *                    /      \                 /       \
	 *                   22       58   ---->      22        50
	 *                  /  \      /              /  \       /  \
	 *                 15   33   50             15   33    42   53
	 *                           / \
	 *                          42  53
	 * 直接把58所在的节点的左子树拼接上去就行了
	 * 
	 * 第二种（删除只有右孩子的节点，58所在的节点）
	 *                       41                       41
	 *                    /      \                 /       \
	 *                   22       58   ---->      22        60
	 *                  /  \        \            /  \       /  \
	 *                 15   33       60        15   33    59   63
	 *                               / \
	 *                              59  63
	 *                    
	 * 直接把58所在的节点的右子树拼接上去就行了
	 * 
	 * 第三种（删除左右都有孩子的节点，58所在的节点d）
	 *                        41                       
	 *                    /       \               
	 *                   22        58   (d)   
	 *                  /  \      /  \          
	 *                 15   33   50   60        
	 *                          /  \  / \
	 *                         42  53 59 63
	 * 		       (s)
	 * 
	 * 此时需要找到58的后继，因为58肯定要比左子树所有元素要大，所以就要在右子树里面找
	 * 但是58又一定比右子树里所有元素要小，所以此时需要找到58右子树的最小值，也就是59
	 * s = min(d -> right)，s是d的后继
	 * 找到后继s之后，先把后继s储存在delMin中，然后删掉这个节点s
	 * 然后把原来d节点的右子树拼接到s节点上，再把d节点的左子树拼接到s节点上
	 * 这样就等于删除了d节点
	 * s -> right = delMin(d -> right)
	 * s -> left = d -> left
	 * 
	 * */
	//从二分搜索树中删除元素为e的节点
	public void remove(E e) {
		root = remove(root, e);
	}
	
	//删除以node为根的二分搜索树中的值为e的节点，递归算法
	//返回删除节点后新的二分搜索树的根
	private Node remove(Node node, E e) {
		
		//base case
		if(node == null) {
			return null;
		}
		
		//recursive rules
		if(e.compareTo(node.e) < 0) {
			node.left = remove(node.left, e);
			return node;
		}else if(e.compareTo(node.e) > 0) {
			node.right = remove(node.right, e);
			return node;
		}else {						//e.equals(node.e)的情况
			if(node.left == null) {	//第一种情况，左孩子为空
				Node rightNode = node.right;
				node.right = null;
				size--;
				return rightNode;
			}
			
			if(node.right == null) {//第二种情况，右孩子为空
				Node leftNode = node.left;
				node.left = null;
				size--;
				return leftNode;
			}
			
			//第三种情况，左右孩子都不为空
			Node successor = minimum(node.right);	//找到待删除节点右子树的最小节点
			successor.right = removeMin(node.right);//把删除了后继节点的子树赋予给后继节点，等于是给后继节点拼接上右子树
			successor.left = node.left;				//把原来待删除节点的左子树赋给后继节点
			
			node.left = node.right = null;			//手动把待删除节点node清空
			return successor;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		generateBSTString(root, 0, res);
		return res.toString();
	}
	
	//生成以node为根节点，深度为depth的描述二叉树的字符串
	private void generateBSTString(Node node, int depth, StringBuilder res) {
		if(node == null) {
			res.append(generateDepthString(depth) + "null\n");
			return;
		}
		
		res.append(generateDepthString(depth) + node.e + "\n");
		generateBSTString(node.left, depth + 1, res);
		generateBSTString(node.right, depth + 1, res);
	}
	
	private String generateDepthString(int depth) {
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < depth; i++) {
			res.append("--");
		}
		return res.toString();
	}
}
