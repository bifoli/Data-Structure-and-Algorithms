//第一个版本的Union-Find，Quick Find
//Time complexity: unionElements(p, q): O(n); isConnected(p, q): O(1)

/**
刚开始存储的元素和集合编号是：
元素:     0  1  2  3  4  5  6  7  8  9
集合编号:  0  1  2  3  4  5  6  7  8  9

union实现的操作就是把union之后的两个元素的集合编号改成一样的数组，比如unionElements(1, 3)，
就把元素1和元素3的集合编号都改成1：
元素:     0  1  2  3  4  5  6  7  8  9
集合编号:  0  1  2  1  4  5  6  7  8  9
 
isConnected操作就是查看p对应的集合编号和q对应的集合编号是否一样，比如isConnected(1, 3)
他俩对应的集合编号都是1，所以此时return true
*/
public class UnionFind1 implements UF {
	
	private int[] id;
	
	public UnionFind1(int size) {
		
		id = new int[size];
		
		for(int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}
	
	@Override
	public int getSize() {
		return id.length;
	}
	
	//查找元素p所对应的集合编号
	private int find(int p) {
		
		if(p < 0 && p > id.length) {
			throw new IllegalArgumentException("p is out of bound.");
		}
		return id[p];
	}
	
	//查看元素p和元素q是否所属一个集合
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
	
	//合并元素p和元素q所属的集合
	/**
	 * 合并操作中，如果说集合是这样的
	 * 元素:   0  1  2  3  4  5  6  7  8  9
	 * 集合编号: 0  0  0  0  0  0  1  1  1  1
	 * 在进行unionElements操作时，比如unionElements(5, 6)
	 * 此时需要遍历一遍集合，把所有集合编号等于0的元素，都改成1，与元素6连接上
	 * 此时集合长这样：
	 * 元素:   0  1  2  3  4  5  6  7  8  9
	 * 集合编号: 1  1  1  1  1  1  1  1  1  1

	 * 等于两大门派只要有联姻，其中一个门派就要把对方全部吞并，全部改名灭绝师太
	 * */
	@Override
	public void unionElements(int p, int q) {
		
		int pID = find(p);
		int qID = find(q);
		
		if(pID == qID) {
			return;
		}
		
		for(int i = 0; i < id.length; i++) {
			if(id[i] == pID) {
				id[i] = qID;
			}
		}
	}
}
