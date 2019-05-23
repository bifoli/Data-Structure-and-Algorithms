
public class LinkedListStack<E> implements Stack<E> {
	
	private LinkedListUsingDummyHead<E> list;
	
	public LinkedListStack() {
		list = new LinkedListUsingDummyHead<>();
	}
	
	@Override
	public int getSize() {
		return list.getSize();
	}
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	@Override
	//对链表头进行插入操作时间复杂度是O(1)
	public void push(E e) {
		list.addFirst(e);
	}
	
	@Override
	public E pop() {
		return list.removeFirst();
	}
	
	@Override
	public E peek() {
		return list.getFirst();
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Stack: top ");
		res.append(list);
		return res.toString();
	}
}
