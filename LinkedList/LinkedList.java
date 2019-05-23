import LinkedListUsingDummyHead.Node;

public class LinkedList<E> {
	
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
	
	private Node head;
	int size;
	
	//初始化链表
	public LinkedList() {
		head = null;
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
	
	//在链表头添加新的元素
	public void addFirst(E e) {
		Node node = new Node(e);	//首先创建新的待插入的节点
		node.next = head;			//然后把该节点指向链表头节点head
		head = node;				//最后把head指向（换成）node
		
		//head = new Node(e, head);	//三步并作一步	
		size++;
	}
	
	//在链表的“索引”(从0开始)位置添加新的元素e
	//关键在于找“索引”之前的那一个节点，这样插入新的节点之后，它就是“索引”所在的位置
	public void add(int index, E e) {
		if(index < 0 || index > size) {
			throw new IllegalArgumentException("Add failed. Illegal index.");
		}
		
		//如果“索引”等于0，因为找不到前面的节点，所以调用addFirst
		if(index == 0) {
			addFirst(e);
		}else {
			//先找待插入“索引”的前一个节点
			Node prev = head;
			for(int i = 0; i < index - 1; i++) {
				prev = prev.next;
			}
			Node node = new Node(e);	//创建新的待插入的节点
			node.next = prev.next;		//把待插入的节点指向要找的“索引”的节点，也就是prev的下一个节点
			prev.next = node;			//最后把prev的节点指向新的插入的节点，给链表拼接上
			
			//prev.next = new Node(e, prev.next);	//三句并作一句
			size++;
		}
	}
	
	//在链表末尾添加新的元素e
	public void addLast(E e) {
		add(size, e);
	}
	
	@Override
	public String toString() {
		
		StringBuilder res = new StringBuilder();
		
		Node cur = head;
		while(cur != null) {
			res.append(cur + "->");
			cur = cur.next;
		}
		res.append("NULL");
		return res.toString();
	}
}
