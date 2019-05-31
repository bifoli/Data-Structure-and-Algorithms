/**
初始化的时候：
0 1 2 3 4 5 6 7 8 9
此时每一个节点都是根节点，它都是指向自己的

parent  0  1  2  3  4  5  6  7  8  9
--------------------------------------
        0  1  2  3  4  5  6  7  8  9

在经过数次union操作之后(union(4, 3), union(3, 8), union(6, 5), union(9, 4), union(2, 1), union(5, 0), union(7, 2), union(6, 2))
此处的union(9, 4)是让9的节点指向4节点的根节点而不是指向4节点，充分利用树结构而不是链表结构
此处的union(6, 2)是让6的根节点0指向2的根节点1
         1               8
      /  |  \            |  \
     0   2   7           3   9
     |                   |
     5                   4
     |
     6

0  1  2  3  4  5  6  7  8  9
---------------------------------------
1  1  1  8  3  0  5  1  8  8

*/
public class UnionFind2 implements UF {
	
	private int[] parent;
	
	public UnionFind2(int size) {
		parent = new int[size];
		
		for(int i = 0; i < size; i++) {
			parent[i] = i;	//初始化每个节点都指向它自己，都是一棵独立的树
		}
	}
	
	@Override
	public int getSize() {
		return parent.length;
	}
	
	//查找过程，比如此时需要查找节点6，那最终返回的是根节点1的集合编号1
	//时间复杂度为O(h)，h为树的高度
	private int find(int p) {
		if(p < 0 && p > parent.length) {
			throw new IllegalArgumentException("p is out of bound.");
		}
		
		while(p != parent[p]) {
			//比如查找节点6，此时节点6的parent是5，那就一直往上查找，知道根节点1的parent是1，查找停止
			p = parent[p];
		}
		return p;
	}
	
	//查看元素p和元素q是否所属一个集合
	//O(h)时间复杂度，h为树的高度
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
	
	//合并元素p和元素q所属的集合
	//O(h)时间复杂度，h为树的高度
	@Override
	public void unionElements(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		
		if(pRoot == qRoot) {
			return;
		}
		
		parent[pRoot] = qRoot;
	}
}
