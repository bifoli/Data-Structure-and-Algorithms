
public class LinkedListMap<K, V> implements Map<K, V> {

	//设置成私有的内部类，因为用户不需要了解链表的底层实现
	private class Node {
		public K key;
		public V value;
		public Node next;
		
		public Node(K key, V value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
		
		public Node(K key) {
			this(key, null, null);
		}
		
		public Node() {
			this(null, null, null);
		}
		
		@Override
		public String toString() {
			return key.toString() + " : " + value.toString();
		}
	}
	
	private Node dummyHead;
	private int size;
	
	public LinkedListMap() {
		dummyHead = new Node();
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
	
	//传入一个key，得到它所在的node
	//Map中的key和Set一样，都是独一无二的
	private Node getNode(K key) {
		Node cur = dummyHead.next;
		while(cur != null) {
			if(cur.key.equals(key)) {
				return cur;
			}
			cur = cur.next;
		}
		return null;
	}
	
	@Override
	public boolean contains(K key) {
		return getNode(key) != null;
	}
	
	@Override
	public V get(K key) {
		Node node = getNode(key);
		return node == null ? null : node.value;
	}
	
	@Override
	//添加操作，如果不存在key值，就添加；如果存在，就把新的value覆盖
	public void add(K key, V value) {
		
		Node node = getNode(key);
		if(node == null) {
			dummyHead.next = new Node(key, value, dummyHead.next);
			size++;
		}else {
			node.value = value;
		}
	}
	
	@Override
	public void set(K key, V value) {
		
		Node node = getNode(key);
		if(node == null) {
			throw new IllegalArgumentException(key + " doesn't exist!");
		}
		node.value = value;
	}
	
	@Override
	public V remove(K key) {
		Node prev = dummyHead;
		while(prev.next != null) {
			if(prev.next.key.equals(key)) {
				break;
			}
			prev = prev.next;
		}
		
		if(prev.next != null) {
			Node delNode = prev.next;
			prev.next = delNode.next;
			delNode.next = null;
			size--;
			return delNode.value;
		}
		return null;
	}
}
