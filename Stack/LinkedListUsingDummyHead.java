import LinkedListUsingDummyHead.Node;

/**
 * 
 *   null -> 0 -> 1 -> 2 -> 3 -> 4 -> null 
 *     |
 *  dummyHead
 *  
 *  
 *  
  *    删除索引为2位置的元素
 *  
 *              prev
 *               |
 *  null -> 0 -> 1 -> 2 -> 3 -> 4 -> NULL
 *    |               | 
 * dummyHead        delNode
 */
public class LinkedListUsingDummyHead<E> {
	
	//设置成私有的内部类，因为用户不需要了解链表的底层实现
	private class Node {
		public E e;
		public Node next;
		
		//当用户同时传来元素e和next的node时
		public Node(E e, Node next) {
			this.e = e;
			this.next = next;
		}
		
		//当用户只传来一个元素e的时候
		public Node(E e) {
			this(e, null);
		}
		
		//当用户什么都不传的时候
		public Node() {
			this(null, null);
		}
		
		@Override
		public String toString() {
			return e.toString();
		}
	}
	
	private Node dummyHead;
	int size;
	
	//初始化链表
	public LinkedListUsingDummyHead() {
		dummyHead = new Node(null, null);
		size = 0;
	}
	
	//获取链表中的元素个数
	public int getSize() {
		return size;
	}
	
	//返回链表是否为空
	public boolean isEmpty() {
		return size == 0;
	}
	
	//在链表的“索引”(从0开始)位置添加新的元素e
	//关键在于找“索引”之前的那一个节点，这样插入新的节点之后，它就是“索引”所在的位置
	public void add(int index, E e) {
		if(index < 0 || index > size) {
			throw new IllegalArgumentException("Add failed. Illegal index.");
		}
		
		Node prev = dummyHead;
		for(int i = 0; i < index; i++) {
			prev = prev.next;
		}
		
		prev.next = new Node(e, prev.next);
		size++;
	}
	
	//在链表头添加新的元素
	public void addFirst(E e) {
		add(0, e);
	}
	
	//在链表末尾添加新的元素e
	public void addLast(E e) {
		add(size, e);
	}
	
	//获得链表的第index(从0开始)个位置的元素
	public E get(int index) {
		
		if(index < 0 || index > size) {
			throw new IllegalArgumentException("Get failed. Illegal index.");
		}
		
		//找当前节点，与上面add操作中的找前一个节点prev操作有区别
		Node cur = dummyHead.next;
		for(int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.e;
	}
	
	//获得链表的第一个元素
	public E getFirst() {
		return get(0);
	}
	
	//获得链表的最后一个元素
	public E getLast() {
		return get(size - 1);
	}
	
	//修改链表的第index(从0开始)个位置的元素为e
	public void set(int index, E e) {
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("Set failed. Illegal index");
		}
		
		Node cur = dummyHead.next;
		for(int i = 0; i < index; i++) {
			cur = cur.next;
		}
		cur.e = e;
	}
	
	//查找链表中是否有元素e
	public boolean contains(E e) {
		
		Node cur = dummyHead.next;
		while(cur != null) {
			if(cur.e.equals(e)) {
				return true;
			}else {
				cur = cur.next;
			}
		}
		return false;
	}
	
	//从链表中删除index(从0开始)位置的元素，返回删除的元素
	public E remove(int index) {
		
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("Remove failed. Illegal index");
		}
		
		Node prev = dummyHead;
		for(int i = 0; i < index; i++) {
			prev = prev.next;
		}
		
		Node retNode = prev.next;
		prev.next = retNode.next;
		retNode.next = null;
		size--;
		
		return retNode.e;
	}
	
	//从链表中删除第一个元素，返回删除的元素
	public E removeFirst() {
		return remove(0);
	}
	
	//从链表中删除最后一个元素，返回删除的元素
	public E removeLast() {
		return remove(size - 1);
	}
	
	@Override
	public String toString() {
		
		StringBuilder res = new StringBuilder();
		
		Node cur = dummyHead.next;
		while(cur != null) {
			res.append(cur + "->");
			cur = cur.next;
		}
		res.append("NULL");
		return res.toString();
	}
}
