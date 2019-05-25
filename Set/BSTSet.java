//基于二分搜索树的集合类
public class BSTSet<E extends Comparable<E>> implements Set<E> {
	
	//因为使用的BST是不包含重复元素的，所以可以用作实现Set
	private BST<E> bst;
	
	public BSTSet() {
		bst = new BST<>();
	}
	
	@Override
	public int getSize() {
		return bst.size();
	}
	
	@Override
	public boolean isEmpty() {
		return bst.isEmpty();
	}
	
	@Override
	public void add(E e) {
		bst.add(e);
	}
	
	@Override
	public boolean contains(E e) {
		return bst.contains(e);
	}
	
	@Override
	public void remove(E e) {
		bst.remove(e);
	}
}
