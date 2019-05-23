/**
 * 改进链表：分别设置头节点head和尾节点tail，保证插入和删除操作时间复杂度都是O(1)
 * 
 * 队首
 * 
 *     0 -> 1 -> 2 -> 3 -> 4 -> NULL
 * 	   |                   |
 *    head                tail
 * 
 * 从head端删除元素，从tail端插入元素，不使用dummyNode
 * 
 * 
 * */
public class LinkedListQueue<E> implements Queue<E> {
	
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
	
	private Node head, tail;
	private int size;
	
	public LinkedListQueue() {
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public void enqueue(E e) {
		//先判断是否为空，也就是tail是否为空，如果tail为空那链表一定为空
		//如果是空链表，就在tail上添加，之后head也指向tail
		if(tail == null) {
			tail = new Node(e);
			head = tail;
		}else {
			//如果不为空的话，就直接在tail.next添加元素
			tail.next = new Node(e);
			tail = tail.next;
		}
		size++;
	}
	
	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
		}
		
		Node retNode = head;	//先把head存进返回的retNode里
		head = head.next;		//然后把新的head指向head.next
		retNode.next = null;	//再手动清空
		//这里会出现一种情况就是如果链表里只有一个元素，dequeue之后head指向null，但是tail还是指向retNode
		//此时需要手动把tail也指向null
		if(head == null) {
			tail = null;
		}
		size--;
		return retNode.e;
	}
	
	@Override
	public E getFront() {
		if(isEmpty()) {
			throw new IllegalArgumentException("Queue is empty");
		}
		return head.e;
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Queue: front ");
		
		Node cur = head;
		while(cur != null) {
			res.append(cur + "->");
			cur = cur.next;
		}
		res.append("NULL tail");
		return res.toString();
	}
}
