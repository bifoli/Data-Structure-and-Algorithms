//基于节点数size的优化
public class UnionFind3 implements UF {
	
	private int[] parent;
	private int[] sz;		//sz[i]表示以i为根的集合中元素个数
	
	public UnionFind3(int size) {
		parent = new int[size];
		sz = new int[size];
		
		for(int i = 0; i < size; i++) {
			parent[i] = i;	//初始化每个节点都指向它自己，都是一棵独立的树
			sz[i] = 1;
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
	
	/**
	 * 此处可以优化的是：
	 *         8      9
	 *         |
	 *         3
	 *         |
	 *         4
	 * 如果此时执行union(4, 9)操作的话
	 * 第一种情况：
	 *         9
	 *         |
	 *         8
	 *         |
	 *         3
	 *         |
	 *         4
	 * 
	 * 第二种情况：
	 *         8
	 *         |  \
	 *         3   9
	 *         |
	 *         4
	 * 
	 * 很显然，第二种情况更优一些，这是把节点数少的指向节点数多的
	 * */
	@Override
	public void unionElements(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		
		if(pRoot == qRoot) {
			return;
		}
		
		//根据两个元素所在树的元素个数不同判断合并方向
		//将元素个数少的集合合并到元素个数多的集合中
		if(sz[pRoot] < sz[qRoot]) {
			parent[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		}else {
			parent[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
	}
}
