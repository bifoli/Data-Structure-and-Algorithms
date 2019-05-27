/**
 * 
 * 二叉堆的性质：
 * 堆中某个节点的值总是不大于其父节点的值
 * 最大堆（相应地可以定义最小堆）
 * 
 * 
 *                          62(0)
 *                       /         \
 *                      41(1)      30(2)
 *                     /   \       /    \
 *                  28(3)  16(4)  22(5)  13(6)
 * 
 * index: 0  1  2  3  4  5  6
 *        62 41 30 28 16 22 13
 * 
 * parent(i) = (i - 1) / 2
 * left child(i) = 2 * i + 1
 * right child(i) = 2 * i + 2
 * 
 * 
 * */
public class MaxHeap<E extends Comparable<E>> {
	
	private Array<E> data;
	
	public MaxHeap(int capacity) {
		data = new Array<>(capacity);
	}
	
	public MaxHeap() {
		data = new Array<>();
	}
	
	//Heapify：就是找到最后一个非叶子节点开始，从后向前进行siftDown操作，直到根节点
	//这里只要拿到最后一个节点，求出其父亲节点，就是最后一个非叶子节点
	//heapify的算法复杂度为O(n)
	//程序的意义在于用户传来一个数组，就自动生成一个动态数组
	public MaxHeap(E[] arr) {
		
		data = new Array<>(arr);
		for(int i = parent(arr.length - 1); i >= 0; i--) {
			siftDown(i);
		}
	}
	
	//返回堆中的元素个数
	public int size() {
		return data.getSize();
	}
	
	//返回堆中是否为空
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	//设置三个私有的函数
	//返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
	public int parent(int index) {
		//如果索引为0，意味着没有父亲节点
		if(index == 0) {
			throw new IllegalArgumentException("index-0 doesn't have parent.");
		}
		return (index - 1) / 2;
	}
	
	//返回一个索引所表示的元素的左孩子节点的索引
	public int leftChild(int index) {
		return index * 2 + 1;
	}
	
	//返回一个索引所表示的元素的右孩子节点的索引
	public int rightChild(int index) {
		return index * 2 + 2;
	}
	
	//向堆中添加元素
	//这时候涉及到，如果添加元素大于其父亲节点的元素，那就需要进行上浮操作
	public void add(E e) {
		data.addLast(e);			//第一步添加元素
		siftUp(data.getSize() - 1);	//第二步维护堆的性质，需要上浮的元素索引就是最后一个元素所在的位置
	}
	
	private void siftUp(int k) {
		//此时需要保证k大于0，也就是说在树的结构中，必须保证它limit在第二层，这样才能与父亲节点（根节点）比较
		//如果k=0的话，根节点没有父亲节点，不能与之互换
		while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
			data.swap(k, parent(k));
			k = parent(k);
		}
	}
	
	/**
	 * 
	 * 从堆中去除元素
	 * 第一步：从根节点移除元素62
	 * 第二步；把最后一层最右边的叶子节点，也就是元素16赋给根节点
	 * 第三步：把16所在的节点删除
	 * (前三步可以并作，先交换节点0与节点10，然后删除节点10；也就是交换62与16然后删除62)
	 * 第四步：将新的根节点元素16与左右孩子52和30作比较，取两者之间大的数与之swap，这样52成了根节点元素，16到了52的位置
	 * 第五步：继续将16与左右孩子28和41作比较，取两者之间大的数41与之swap
	 * 第六步：继续将16与此时只有左孩子的15作比较，发现比15大，程序结束
	 * 
	 *                       62(0)
	 *                   /            \
	 *                 52(1)          30(2)
	 *              /      \         /       \
	 *           28(3)     41(4)    22(5)     13(6)
	 *          /	\      /   \
	 *        19(7) 17(8) 15(9) 16(10)
	 * 
	 * 
	 * */
	//寻找堆中的最大元素
	public E findMax() {
		if(data.getSize() == 0) {
			throw new IllegalArgumentException("Cannot findMax when heap is empty.");
		}
		return data.get(0);
	}
	
	//取出堆中最大元素
	public E extractMax() {
		
		E ret = findMax();
		data.swap(0, data.getSize() - 1);
		data.removeLast();
		siftDown(0);
		return ret;
	}
	
	/**
	 * 第一种情况
	 *                       62(0)
	 *                   /            \
	 *                 52(1)          30(2)
	 *              /      \         /       \
	 *           28(3)     41(4)    22(5)     13(6)
	 *          /	\      /   \
	 *        19(7) 17(8) 15(9) 16(10)
	 *        
	 *        经过变化之后：
	 * 
	 *                       52(0)
	 *                   /            \
	 *                 41(1)          30(2)
	 *              /      \         /       \
	 *           28(3)     16(4)    22(5)     13(6)
	 *          /	\      /   
	 *        19(7) 17(8) 15(9)
	 *        
	 *        此时k = 4, leftChild(k) = 9，满足循环条件，继续执行发现16>15, break;
	 * 
	 * 第二种情况
	 *                       62(0)
	 *                   /            \
	 *                 52(1)          30(2)
	 *              /      \         /       \
	 *           28(3)     41(4)    22(5)     13(6)
	 *          /	\      /   
	 *        19(7) 17(8) 15(9)
	 *        
	 *        经过变化之后：
	 * 
	 *                       52(0)
	 *                   /            \
	 *                 41(1)          30(2)
	 *              /      \         /       \
	 *           28(3)     15(4)    22(5)     13(6)
	 *          /	\        
	 *        19(7) 17(8)
	 *        
	 *        此时k = 4, leftChild(k) = 9, 不满足循环条件while(leftChild(k) < data.getSize()), 程序结束
	 *  
	 *        
	 *        
	 *        
	 * 第三种情况
	 *                       62(0)
	 *                   /            \
	 *                 52(1)          30(2)
	 *              /      \         /       \
	 *           41(3)     28(4)    22(5)     13(6)
	 *          /	\      /   
	 *        19(7) 17(8) 15(9)
	 *        
	 *        经过变化之后：
	 * 
	 *                       52(0)
	 *                   /            \
	 *                 41(1)          30(2)
	 *              /      \         /       \
	 *           15(3)     28(4)    22(5)     13(6)
	 *          /	\      
	 *        19(7) 17(8) 
	 *        
	 *        此时，k = 3，leftChild(k) = 7，满足循环继续执行
	 * 
	 *                       52(0)
	 *                   /            \
	 *                 41(1)          30(2)
	 *              /      \         /       \
	 *           19(3)     28(4)    22(5)     13(6)
	 *          /	\      
	 *        15(7) 17(8) 
	 *        
	 *        此时，k = 7, leftChild(k) = 22, 程序结束
	 * */
	private void siftDown(int k) {
		
		while(leftChild(k) < data.getSize()) {	//k的左孩子索引越界，循环停止
			
			//首先比较左右孩子的最大值
			int j = leftChild(k);	//因为在循环内可以保证有左孩子
			//它的右孩子就是j + 1，如果有右孩子(j+1<data.getSize())，并且右孩子大于左孩子，就把索引值赋给j
			if(j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
				j = rightChild(k);
			}//data[j]是leftChild和rightChild中的最大值
			
			//我们再比较索引k的值和左右孩子最大值data[j]的大小
			//如果k的值大于data[j]，程序停止；反之，继续siftDown
			if(data.get(k).compareTo(data.get(j)) >= 0) {
				break;
			}
			data.swap(k, j);
			k = j;
		}
	}
	
	//Replace：取出最大元素后，放入一个新元素
	//Solution1: 先extractMax，再add，两次O(logn)的操作
	//Solution2：直接将堆顶元素替换以后sift Down，一次O(logn)的操作
	public E replace(E e) {
		
		E ret = findMax();
		data.set(0, e);
		siftDown(0);
		return ret;
	}
}
