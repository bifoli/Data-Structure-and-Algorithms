
public class ArrayQueue<E> implements Queue<E> {
	
	private Array<E> array;
	
	public ArrayQueue(int capacity) {
		array = new Array<>(capacity);
	}
	
	public ArrayQueue() {
		array = new Array<>();
	}
	
	@Override
	public int getSize() {
		return array.getSize();
	}
	
	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}
	
	public int getCapacity() {
		return array.getCapacity();
	}
	
	@Override
	//从数组尾部插入，头部弹出
	public void enqueue(E e) {
		array.addLast(e);
	}
	
	@Override
	public E dequeue() {
		//这里要判断数组是否为空，但是我们使用的array底层已经实现了这一操作
		return array.removeFirst();
	}
	
	@Override
	public E getFront() {
		return array.getFirst();
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Queue: ");
		res.append("front [");
		for(int i = 0; i < array.getSize(); i++) {
			res.append(array.get(i));
			if(i != array.getSize() - 1) {
				res.append(", ");
			}
		}
		res.append("] tail");
		return res.toString();
	}
}
